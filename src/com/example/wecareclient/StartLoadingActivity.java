package com.example.wecareclient;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import clientInterface.UserDetailsInterface;
import util.CrashHandler;

/**
 * 
 * @author ying
 * 
 */
public class StartLoadingActivity extends Activity implements UserDetailsInterface{
	private SharedPreferences sharedPreferences;
	private Context mContext;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.start_loading_activity);
		
		faultTolerance();
		
		jump();
		
		
		sharedPreferences = this.getSharedPreferences("base64", Context.MODE_PRIVATE);
	}

	/**
	 * sleep 3 secs
	 */
	private void jump() {
		new Handler().postDelayed(new Runnable() {
			
			 @Override
			 public void run() {
				 String check = sharedPreferences.getString("username", "");
				 if(check != null && !check.equals("")){
					 loadHomeUI();
				 }else{
					 loadMainUI();
				 }
			 }
			 }, 3000);		
	}


	/**
	 * forward to main page
	 */
	protected void loadMainUI() {
		startActivity(new Intent(StartLoadingActivity.this, MainActivity.class));
		this.finish();
	}
	
	/**
	 * forward to home page
	 */
	protected void loadHomeUI() {
		startActivity(new Intent(StartLoadingActivity.this, FragmentActivity.class));
		this.finish();
	}

	@Override
	public void doPrevious() {
		// TODO Auto-generated method stub
		
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
