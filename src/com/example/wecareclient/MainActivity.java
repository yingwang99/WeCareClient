package com.example.wecareclient;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import clientInterface.UserDetailsInterface;
import util.CrashHandler;

/**
 *@Author: ying
 */
public class MainActivity extends Activity implements OnClickListener,UserDetailsInterface{

	//private Button login;
	private Button register;
	private Context mContext;
	@Override 
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.main);
		
		faultTolerance();
		//login = (Button)findViewById(R.id.loginbtn);
		register = (Button)findViewById(R.id.registerbtn);
		
		//login.setOnClickListener(this);
		register.setOnClickListener(this);
	}
	/**
	 * forward to the login page
	 */
	/*private void forwardToLoginPage(){
		startActivity(new Intent(MainActivity.this, LoginActivity.class));
		this.finish();
	}*/
	/**
	 * forward to the sign up page
	 */
	private void forwardToSignUpPage(){
		startActivity(new Intent(MainActivity.this, UserDetailsActivity1.class));
		this.finish();
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		//case R.id.loginbtn:
			//forwardToLoginPage();
			//break;
		case R.id.registerbtn:
			forwardToSignUpPage();
		default:
			break;
		}
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
