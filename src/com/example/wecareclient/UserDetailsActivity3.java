package com.example.wecareclient;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import clientInterface.UserDetailsInterface;
import util.CrashHandler;

/**
 * 
 * @author ying
 * 
 */
public class UserDetailsActivity3 extends Activity implements OnClickListener, UserDetailsInterface{
	private EditText height, weight, goalOfWeight;
	private SharedPreferences sharedPreferences;
	private SharedPreferences.Editor editor;
	
	private ImageView pre, next;
	private Context mContext;
	
	private String heightStr, weightStr, goalofweightStr;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_user_details3);
		
		sharedPreferences = this.getSharedPreferences("base64", Context.MODE_PRIVATE);
		editor = sharedPreferences.edit();
		
		faultTolerance();
		
		initView();
		
		pre.setOnClickListener(this);
		next.setOnClickListener(this);
	}
	
	public void initView(){
		   height = (EditText) findViewById(R.id.text_tall);
		   weight = (EditText) findViewById(R.id.text_weight);
		   goalOfWeight = (EditText) findViewById(R.id.text_wight_goal);
		   
		   pre = (ImageView)findViewById(R.id.previous);
		   next = (ImageView)findViewById(R.id.next);
		 
		} 

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.user_details_activity3, menu);
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
		startActivity(new Intent(UserDetailsActivity3.this, UserDetailsActivity2.class));
		this.finish();
	}

	@Override
	public void doNext() {
		// TODO Auto-generated method stub
		heightStr = height.getText().toString();
		weightStr = weight.getText().toString();
		goalofweightStr = goalOfWeight.getText().toString();
		
		if(heightStr.equals("")){
			heightStr = "170";
		}
		if(weightStr.equals("")){
			weightStr = "50";
		}
		if(goalofweightStr.equals("")){
			goalofweightStr = "45";
		}
		
		if(checkValidInput(heightStr, weightStr, goalofweightStr) == true && checkWeight(goalofweightStr,weightStr) == true){
			storeData();
			
			startActivity(new Intent(UserDetailsActivity3.this, UserDetailsActivity4.class));
			this.finish();
		}else if(checkWeight(goalofweightStr,weightStr) == false){
			Toast.makeText(UserDetailsActivity3.this,"The goal should be less than current weight",Toast.LENGTH_SHORT).show();

		}else{
			Toast.makeText(UserDetailsActivity3.this,"The input should be in number format!",Toast.LENGTH_SHORT).show();

		}
	}
	
	public boolean checkWeight(String lose, String cur){
		
		double loseW = Double.parseDouble(lose);
		double curW = Double.parseDouble(cur);
		
		if(loseW > curW){
			return false;
		}
		return true;
	}
	
	public boolean checkValidInput(String heightStr, String weightStr, String goalofweightStr){
		try {
			Double.parseDouble(heightStr);
			Double.parseDouble(weightStr);
			Double.parseDouble(goalofweightStr);
		} catch (NumberFormatException e) {
			// TODO: handle exception
			return false;
		}
		
		return true;
	}
	
	public void storeData(){
		editor.putString("height", heightStr);
		editor.putString("weight", weightStr);
		editor.putString("goalofweight", goalofweightStr);
		editor.commit();
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch ( v.getId()) {
		case R.id.previous:
			doPrevious();
			break;
		
		case R.id.next:
			doNext();
			break;
			
		default:
			break;
		}
	}

	@Override
	public void faultTolerance() {
		// TODO Auto-generated method stub
		CrashHandler crashHandler = CrashHandler.getInstance();
        crashHandler.init(getApplicationContext());
        mContext = this;
	}
}
