package com.example.wecareclient;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import clientInterface.UserDetailsInterface;
import util.CrashHandler;

public class UserDetailsActivity4 extends Activity implements OnClickListener, UserDetailsInterface{
	
	private Button b1,b2,b3,b4;
	
	private SharedPreferences sharedPreferences;
	private SharedPreferences.Editor editor;
	
	private ImageView pre, next;
	
	private String weeklyGoal;
	
	private double goaOfWeight = 0, w = 0;
	private Context mContext;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);		
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_user_details4);
		
		sharedPreferences = this.getSharedPreferences("base64", Context.MODE_PRIVATE);
		editor = sharedPreferences.edit();
		
		faultTolerance();
		initView();
		
		setRecommenedGoal();
		
		pre.setOnClickListener(this);
		next.setOnClickListener(this);
	}
	
	public void setRecommenedGoal(){

		try{
			goaOfWeight = Double.parseDouble(sharedPreferences.getString("goalofweight", ""));
			w = Double.parseDouble(sharedPreferences.getString("weight", ""));
			
			double goal = w - goaOfWeight;
		
			calcRecGoal(goal);
		}catch(NumberFormatException n){
			
		}
	}

	private void calcRecGoal(double goal) {
		if(goal < 5){
			b1.setText(b1.getText().toString() + "\n" + "(recommend)");
			b1.setBackgroundColor(Color.parseColor("#0099FF"));
			
			weeklyGoal = "0.2";
		}else if(goal <= 8){
			b2.setText(b2.getText().toString() + "\n" + "(recommend)");
			b2.setBackgroundColor(Color.parseColor("#0099FF"));
			
			weeklyGoal = "0.5";
		}else if(goal <= 10 ){
			b3.setText(b3.getText().toString() + "\n" + "(recommend)");
			b3.setBackgroundColor(Color.parseColor("#0099FF"));
			
			weeklyGoal = "0.8";
		}else{
			b4.setText(b4.getText().toString() + "\n" + "(recommend)");
			b4.setBackgroundColor(Color.parseColor("#0099FF"));
			
			weeklyGoal = "1";
		}
		
	}

	private void initView() {
		// TODO Auto-generated method stub
		b1 = (Button) findViewById(R.id.button1);
		b2 = (Button) findViewById(R.id.button2);
		b3 = (Button) findViewById(R.id.button3);
		b4 = (Button) findViewById(R.id.button4);
		
		pre = (ImageView)findViewById(R.id.previous);
		next = (ImageView)findViewById(R.id.next);
				
		b1.setOnClickListener(this);
		b2.setOnClickListener(this);
		b3.setOnClickListener(this);
		b4.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.user_details_activity4, menu);
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
	public void doPrevious() {
		// TODO Auto-generated method stub
		startActivity(new Intent(UserDetailsActivity4.this, UserDetailsActivity3.class));
		this.finish();
	}

	@Override
	public void doNext() {
		// TODO Auto-generated method stub
		editor.putString("weeklyGoal", weeklyGoal);
		setDailyGoal();
		editor.commit();
		
		startActivity(new Intent(UserDetailsActivity4.this, UserDetailsActivity5.class));
		this.finish();
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.previous:
			doPrevious();
			break;
		case R.id.next:
			doNext();
			break;
		case R.id.button1:
			doButton1();
			break;
		case R.id.button2:
			doButton2();
			break;
		case R.id.button3:
			doButton3();
			break;
		case R.id.button4:
			doButton4();
			break;
			
		default:
			break;
		}
	}

	private void doButton4() {
		// TODO Auto-generated method stub
		b4.setBackgroundColor(Color.parseColor("#0099FF"));
		b2.setBackgroundResource(R.drawable.frag_button);
		b3.setBackgroundResource(R.drawable.frag_button);
		b1.setBackgroundResource(R.drawable.frag_button);
		
		weeklyGoal = "1";

	}

	private void doButton3() {
		// TODO Auto-generated method stub
		b3.setBackgroundColor(Color.parseColor("#0099FF"));
		b2.setBackgroundResource(R.drawable.frag_button);
		b1.setBackgroundResource(R.drawable.frag_button);
		b4.setBackgroundResource(R.drawable.frag_button);
		
		weeklyGoal = "0.8";

	}

	private void doButton2() {
		// TODO Auto-generated method stub
		b2.setBackgroundResource(R.drawable.frag_button);
		b1.setBackgroundResource(R.drawable.frag_button);
		b3.setBackgroundResource(R.drawable.frag_button);
		b4.setBackgroundResource(R.drawable.frag_button);
		
		weeklyGoal = "0.5";
	}

	private void doButton1() {
		// TODO Auto-generated method stub
		b1.setBackgroundColor(Color.parseColor("#0099FF"));
		b2.setBackgroundResource(R.drawable.frag_button);
		b3.setBackgroundResource(R.drawable.frag_button);
		b4.setBackgroundResource(R.drawable.frag_button);

		weeklyGoal = "0.2";
	}
	
	private void setDailyGoal(){
		int goal = 0;
		
		if(weeklyGoal.equals("0.2")){
			goal = 10;
		}else if(weeklyGoal.equals("0.5")){
			goal = 11;
		}else if(weeklyGoal.equals("0.8")){
			goal = 12;
		}else if(weeklyGoal.equals("1")){
			goal = 13;
		}
		
		editor.putString("dailyGoal", w*2*goal+"");
	}

	@Override
	public void faultTolerance() {
		// TODO Auto-generated method stub	
		CrashHandler crashHandler = CrashHandler.getInstance();
        crashHandler.init(getApplicationContext());
        mContext = this;
	}

	
}
