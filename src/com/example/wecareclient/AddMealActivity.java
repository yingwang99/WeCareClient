package com.example.wecareclient;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.example.wecareclient.dto.Food;
import com.example.wecareclient.dto.User;

import Adapter.SearchListAdapter;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ImageView;
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

/**
 * 
 * @author ying
 * 
 */
public class AddMealActivity extends Activity implements OnClickListener, UserDetailsInterface{
	private EditText searchcontent;
	private TextView search;
	private ImageView search_button1, pre;
	int VOICE_RECOGNITION_REQUEST_CODE = 0;
	private TextView main_type;
	
	private ListView listView;
	private Context mContext;
	
	private SharedPreferences sharedPreferences;
	private SharedPreferences.Editor editor;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_add_meal);	
		
		sharedPreferences = this.getSharedPreferences("base64", Context.MODE_PRIVATE);
		editor = sharedPreferences.edit();
		
		
		faultTolerance();
		initView();
		setListner();
	}
	
	public void initView(){
		listView=(ListView)findViewById(R.id.listView);
		
		main_type = (TextView)findViewById(R.id.main_title);
		main_type.setText("Add Meal");
		
		searchcontent=(EditText)findViewById(R.id.searchtext);
		search=(TextView)findViewById(R.id.Search_txt);
		
		search_button1 = (ImageView) findViewById(R.id.Search_button1);	
		
		pre = (ImageView) findViewById(R.id.previous);
	}
	
	public void setListner(){
		search.setOnClickListener(this);
		search_button1.setOnClickListener(this);
		pre.setOnClickListener(this);
	}
	
	private void startVoiceRecognitionActivity() {  
	    try {  
	        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);  
	        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,  
	                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);  
	        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Start speaking");  
	        startActivityForResult(intent, VOICE_RECOGNITION_REQUEST_CODE);  
	    } catch (Exception e) {  
	        e.printStackTrace();  
	        showDialog();  
	    }  
	}  
	
	private void showDialog() {  
	    AlertDialog.Builder builder = new Builder(AddMealActivity.this);  
	    builder.setMessage(R.string.dialog_content);  
	    builder.setTitle(R.string.dialog_title);  
	    builder.setNegativeButton(R.string.download,  
	            new android.content.DialogInterface.OnClickListener() {  

					@Override
					public void onClick(DialogInterface dialog, int arg1) {
						// TODO Auto-generated method stub
						dialog.dismiss();  
	                    Uri uri = Uri.parse(getApplication().getString(R.string.voice_url));  
	                    Intent it = new Intent(Intent.ACTION_VIEW, uri);  
	                    startActivity(it);  
					}  
	            });  
	    		builder.setPositiveButton(R.string.cancel,  
	            new android.content.DialogInterface.OnClickListener() {  
	                @Override  
	                public void onClick(DialogInterface dialog, int which) {  
	                    dialog.dismiss();  
	                }  
	            });  
	    builder.create().show();  
	}  
	  
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {  
	    if (requestCode == VOICE_RECOGNITION_REQUEST_CODE  
	            && resultCode == RESULT_OK) {  
	        ArrayList<String> results = data  
	                .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);  
	  
	        if (results.size() > 0) {  
	        	searchcontent.setText(results.get(0));  
	        } else {  
	        	Toast.makeText(AddMealActivity.this, "Test failed", Toast.LENGTH_SHORT)
				.show();
	        }  
	    }  
	}  

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_meal, menu);
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
		case R.id.Search_button1:
			startVoiceRecognitionActivity();
			break;
		case R.id.previous:
			doPrevious();
			break;
		case R.id.Search_txt:
			SearchMeal searchMeal = new SearchMeal();
			searchMeal.execute(searchcontent.getText().toString());
			break;

		default:
			break;
		}
	}
	
	public class SearchMeal extends AsyncTask<String, Void, String> {
		List<Food> foods = new ArrayList<Food>();

		ProgressDialog p = new ProgressDialog(AddMealActivity.this,
				ProgressDialog.STYLE_SPINNER);

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			p.setMessage("Loading...");
			p.show();
		}
		
		@Override
		protected String doInBackground(String... params) {
			// TODO Auto-generated method stub
			//String result = HttpUtils.doGet(Url.url() + "food/" + params[0]);
			String result = "";
			return result;
		}
		
		public void init(){
			Food food1 = new Food("Lamb","meat",50, 100);
			Food food2 = new Food("Pork", "meat", 30, 90);
			Food food3 = new Food("Chicken", "meat", 35, 100);
			Food food4 = new Food("Beef", "meat", 30, 80);
			Food food5 = new Food("Pork", "meat", 50, 130);
			
			foods.add(food1);
			foods.add(food2);
			foods.add(food3);
			foods.add(food4);
			foods.add(food5);
		}
		
		@SuppressWarnings("unchecked")
		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			p.dismiss();
			
			init();
			System.out.println(result);

			try{
				foods = (List<Food>) JSON.parseArray(result, Food.class);
			}catch(JSONException c){
				System.out.println("catch exception");
			}
			
			listView.setAdapter(new SearchListAdapter(foods, AddMealActivity.this));
			
			listView.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
					// TODO Auto-generated method stub
					
					    
						Food food = foods.get(position);
						
						food.setMeal_type(getIntent().getStringExtra("mealType"));
						
						Calendar calendar = Calendar.getInstance();
						int hr = calendar.get(Calendar.HOUR_OF_DAY);
												
						food.setAdd_time(String.valueOf(hr));

						long isStoreSuccess = storeInDb(food);
						
						if(isStoreSuccess >= 1){
							Toast.makeText(AddMealActivity.this, "Add Successful!", Toast.LENGTH_SHORT).show();
							
							Intent intent = new Intent(AddMealActivity.this,FragmentActivity.class);
						    intent.putExtra("food", food);
						    intent.putExtra("meal_type", getIntent().getStringExtra("mealType"));
				           
							startActivity(intent);

						}else{
							Toast.makeText(AddMealActivity.this, "Add Failed!", Toast.LENGTH_SHORT).show();
						}		
				}
			});
		}
		
		private long storeInDb(Food f){
			
			FeedReaderDBUserManager UserManager = new FeedReaderDBUserManager();
			
			FeedReaderDbHelper mDbHelper = new FeedReaderDbHelper(getApplicationContext());

			// Gets the data repository in write mode
			SQLiteDatabase db = mDbHelper.getWritableDatabase();

			// Insert the new row, returning the primary key value of the new row
			long newRowId = db.insert(FeedEntry.TABLE_NAME_FOOD, null, UserManager.getFoodValues(f));
			 
			return newRowId;
		}
		
	}


	@Override
	public void doPrevious() {
		// TODO Auto-generated method stub
		startActivity(new Intent(AddMealActivity.this,FragmentActivity.class));
		this.finish();
	}

	@Override
	public void doNext() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void faultTolerance() {
		// TODO Auto-generated method stub
		CrashHandler crashHandler = CrashHandler.getInstance();
        crashHandler.init(getApplicationContext());
        mContext = this;
	}
}
