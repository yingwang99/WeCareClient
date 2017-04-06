package com.example.wecareclient;

import java.util.ArrayList;
import java.util.List;

import com.example.wecareclient.dto.Food;

import Adapter.BreakfastListAdapter;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import clientInterface.UserDetailsInterface;
import db.FeedReaderContract.FeedEntry;
import db.FeedReaderDBUserManager;
import db.FeedReaderDbHelper;
import util.CrashHandler;
import util.HttpUtils;
import util.Url;

public class HomeActivity extends Activity implements OnClickListener{

	private SharedPreferences sharedPreferences;
	private SharedPreferences.Editor editor;
	private TextView goal_of_calories, food_intake, amount_left;
	private TextView breakfast_click, lunch_click, dinner_click;
	
	private ListView breakfastList, lunchList, DinnerList;
	private List<Food> bfList, lchList, dinList;

	private BreakfastListAdapter adapter, lunchAdapter, dinnerAdapter;
	private double foodIntake;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_home);
		
		sharedPreferences = this.getSharedPreferences("base64", Context.MODE_PRIVATE);
		editor = sharedPreferences.edit();
		
		init();
		
		updateInfo();
	}

	private void init() {
		bfList = new ArrayList<Food>();
		lchList = new ArrayList<Food>();
		dinList = new ArrayList<Food>();
		
		initView();
		setClickListener();
		loadBreakfastList();

	}

	private void updateInfo() {
		Food food = (Food) getIntent().getSerializableExtra("food");
		
		if(food != null){	
			 AddFoodToServer addFoodToServer = new AddFoodToServer();
			    addFoodToServer.execute(food.getKind(), food.getCategray(),String.valueOf(food.getG()),
			    		String.valueOf(food.getCalorie()), String.valueOf(0));
			    
			addFoodToAdapter(food);
			Double goal = Double.parseDouble(sharedPreferences.getString("dailyGoal", ""));
			//Double foodIntake = Double.parseDouble(food_intake.getText().toString()) + food.getCalorie();
			foodIntake = foodIntake + food.getCalorie();
			editor.putString("intake", String.valueOf(foodIntake));
			editor.commit();
			food_intake.setText(String.valueOf(foodIntake));
			amount_left.setText(String.valueOf(goal - foodIntake));
		}
	}
	
	
	public void initView(){
		goal_of_calories = (TextView) findViewById(R.id.goal_of_calories);
		goal_of_calories.setText(sharedPreferences.getString("dailyGoal", ""));
		
		food_intake = (TextView) findViewById(R.id.amount_of_food_intake);
		
		amount_left = (TextView) findViewById(R.id.amount_of_intake_left);
		
		breakfast_click = (TextView) findViewById(R.id.breakfast_click);
		lunch_click = (TextView) findViewById(R.id.lunch_click);
		dinner_click = (TextView) findViewById(R.id.dinner_click);
		
		breakfastList = (ListView) findViewById(R.id.breakfast_listView);
		lunchList = (ListView)findViewById(R.id.lunch_listView);
		DinnerList = (ListView)findViewById(R.id.dinner_listView);
		
	}
	
	public void setListViewHeightBasedOnChildren(ListView listView) {   
        ListAdapter listAdapter = listView.getAdapter();   
        if (listAdapter == null) {   
            return;   
        }   
   
        int totalHeight = 0;   
        for (int i = 0, len = listAdapter.getCount(); i < len; i++) {   
            View listItem = listAdapter.getView(i, null, listView);   
            listItem.measure(0, 0);    
            totalHeight += listItem.getMeasuredHeight();    
        }   
   
        ViewGroup.LayoutParams params = listView.getLayoutParams();   
        params.height = totalHeight+ (listView.getDividerHeight() * (listAdapter.getCount() - 1));   

        listView.setLayoutParams(params);   
    }    
	
	public void loadBreakfastList(){	
		storeToFoodList();	
		adapter = new BreakfastListAdapter(bfList, HomeActivity.this);
		breakfastList.setAdapter(adapter);
		setListViewHeightBasedOnChildren(breakfastList);
		
		lunchAdapter = new BreakfastListAdapter(lchList, HomeActivity.this);
		lunchList.setAdapter(lunchAdapter);
		setListViewHeightBasedOnChildren(lunchList);
		
		dinnerAdapter = new BreakfastListAdapter(dinList, HomeActivity.this);
		DinnerList.setAdapter(dinnerAdapter);
		setListViewHeightBasedOnChildren(DinnerList);
		
		setTrackText();

	}

	private void setTrackText() {
		Double goal = Double.parseDouble(sharedPreferences.getString("dailyGoal", ""));
		Double foodIntake;
		if(sharedPreferences.getString("intake", "") != null){
			foodIntake = Double.parseDouble(sharedPreferences.getString("intake", ""));
		}else{
			foodIntake = Double.parseDouble(food_intake.getText().toString());
		}
		
		food_intake.setText(String.valueOf(foodIntake));
		amount_left.setText(String.valueOf(goal-foodIntake));
	}
	
	public Cursor getData() {
		  FeedReaderDbHelper mDbHelper = new FeedReaderDbHelper(getApplicationContext());
	      SQLiteDatabase db = mDbHelper.getReadableDatabase();
	      
	      Cursor res =  db.rawQuery( "select * from " + FeedEntry.TABLE_NAME_FOOD, null );
	      return res;
	   }
	
	public void storeToFoodList(){
		Cursor cursor = getData();
		
		if (cursor.moveToFirst()) {

	        while (cursor.isAfterLast() == false) {
	        	try{
	        		int gram = Integer.parseInt(cursor.getString(cursor.getColumnIndex(FeedEntry.COLUMN_NAME_GRAMS)));
		        	double calories = Double.parseDouble(cursor.getString(cursor.getColumnIndex(FeedEntry.COLUMN_NAME_CALORIES)));		 	
	        	
		        	Food food = new Food(cursor.getString(cursor.getColumnIndex(FeedEntry.COLUMN_NAME_KIND)),
		        			cursor.getString(cursor
		                    .getColumnIndex(FeedEntry.COLUMN_NAME_CATEGORIES)),gram, calories);
		        	food.setMeal_type(cursor.getString(cursor
		                    .getColumnIndex(FeedEntry.COLUMN_NAME_MEALTYPE)));
		        	
		        	foodIntake = foodIntake + calories;
		        	addFoodToList(food);
		        	
	        	}catch(NumberFormatException e){
	        		e.printStackTrace();
	        	}
	        	
	        	cursor.moveToNext();
	        }
		}
		editor.putString("intake", String.valueOf(foodIntake));
		editor.commit();
		
	}
	
	public void addFoodToAdapter(Food newFood){
		String meal_type = getIntent().getStringExtra(("meal_type"));
		System.out.println("meal_type" + meal_type);
		if(meal_type.equals("Breakfast")){
    		adapter.addNewFood(newFood);
    	}else if(meal_type.equals("Lunch")){
    		lunchAdapter.addNewFood(newFood);
    	}else if(meal_type.equals("Dinner")){
    		dinnerAdapter.addNewFood(newFood);
    	}
	}
	
	public void addFoodToList(Food food){
		if(food.getMeal_type().equals("Breakfast")){
    		bfList.add(food);
    	}else if(food.getMeal_type().equals("Lunch")){
    		lchList.add(food);
    	}else if(food.getMeal_type().equals("Dinner")){
    		dinList.add(food);
    	}
	}

	private void setClickListener() {
		breakfast_click.setOnClickListener(this);
		lunch_click.setOnClickListener(this);
		dinner_click.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.home, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		
		case R.id.breakfast_click:
			goToAddMealActivity("Breakfast");
			break;
		case R.id.lunch_click:
			goToAddMealActivity("Lunch");
			break;
		case R.id.dinner_click:
			goToAddMealActivity("Dinner");
			break;
		default:
			break;
		}
	}

	public void goToAddMealActivity(String mealType){
		Intent intent= new Intent(HomeActivity.this, AddMealActivity.class);
		intent.putExtra("mealType", mealType);
		startActivity(intent);
		this.finish();
	}
	

	public class AddFoodToServer extends AsyncTask<String, Void, String> {

		ProgressDialog p = new ProgressDialog(HomeActivity.this,
				ProgressDialog.STYLE_SPINNER);

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			p.setMessage("Loading...");
			p.show();
		}

		@Override
		protected String doInBackground(String... arg0) {
			// TODO Auto-generated method stub
			
			String result = HttpUtils.doGet(Url.serverUrl() + "ListRecommendServlet?" + "kind=" + arg0[0] + "&catagray=" + 
			arg0[1] + "&g=" + arg0[2] + "&calorie=" + arg0[3] + "&userId=" + arg0[4]);
			return result;
		}
		
		@SuppressLint("ShowToast")
		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			p.dismiss();
	
			Toast.makeText(HomeActivity.this, result, Toast.LENGTH_SHORT);
			editor.putString("recommend", result);
			editor.commit();
		}
	}



}
