package com.example.wecareclient;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends Activity implements OnClickListener{
	
	private ImageView pre;
	private EditText username, password;
	private TextView register_link;
	private Button loginbth;
	private final String user_input = "12345";
	private final String pwd_input = "12345";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_login);
		
		initView();
		
	}
	/**
	 * init view
	 */
	public void initView(){
		pre = (ImageView) findViewById(R.id.previous);
		username = (EditText) findViewById(R.id.username);
		password = (EditText) findViewById(R.id.password);
		register_link = (TextView) findViewById(R.id.register_link);
		loginbth = (Button) findViewById(R.id.loginbtn);
		
		pre.setOnClickListener(this);
		register_link.setOnClickListener(this);
		loginbth.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
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
		case R.id.previous:
			doPrevious();
			break;
		case R.id.loginbtn:
			checkInput();
			break;
		case R.id.register_link:
			doSignUp();
			break;

		default:
			break;
		}
	}

	/**
	 * forward to sign up page
	 */
	private void doSignUp() {
		// TODO Auto-generated method stub
		startActivity(new Intent(LoginActivity.this,UserDetailsActivity1.class));
		this.finish();
	}

	/**
	 * forward to login page
	 */
	private void doLogin() {
		// TODO Auto-generated method stub
		startActivity(new Intent(LoginActivity.this,FragmentActivity.class));
		this.finish();
	}
	/**
	 * check for valid input, if the input is not valid, show an error message
	 */
	private void checkInput() {
		// TODO Auto-generated method stub
		if(username.getText().toString().equals(user_input) && password
				.getText().toString().equals(pwd_input)){
			doLogin();
		}else{
			Toast.makeText(LoginActivity.this,"Invalid username or password",Toast.LENGTH_SHORT).show();

		}
	}
	/**
	 * go previous page
	 */
	private void doPrevious() {
		// TODO Auto-generated method stub
		startActivity(new Intent(LoginActivity.this, MainActivity.class));
		this.finish();
	}
}
