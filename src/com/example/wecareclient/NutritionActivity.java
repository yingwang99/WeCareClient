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
import android.widget.ImageView;
import clientInterface.UserDetailsInterface;
import util.CrashHandler;

public class NutritionActivity extends Activity implements OnClickListener, UserDetailsInterface{
	
	private ImageView previous;
	private Context mContext;
	
	private SharedPreferences sharedPreferences;
	private SharedPreferences.Editor editor;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_nutrition);
		
		sharedPreferences = this.getSharedPreferences("base64", Context.MODE_PRIVATE);
		editor = sharedPreferences.edit();
		
		faultTolerance();
		initView();
	}

	public void initView(){
		previous = (ImageView)findViewById(R.id.previous);
		previous.setOnClickListener(this);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.nutrition, menu);
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
			goToPreviousActivity();
			break;

		default:
			break;
		}
	}

	private void goToPreviousActivity() {
		// TODO Auto-generated method stub
		startActivity(new Intent(NutritionActivity.this, FragmentActivity.class));
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
