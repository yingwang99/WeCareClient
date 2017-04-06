package com.example.wecareclient;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import clientInterface.UserDetailsInterface;
import util.CrashHandler;

/**
 * @author 
 * 
 */
public class UserDetailsActivity1 extends Activity implements OnClickListener, UserDetailsInterface{

	private ImageView pre, next;
	private Button loseWeight_Button, keepDiet_Button,gainWeight_Button;

	private SharedPreferences sharedPreferences;
	private SharedPreferences.Editor editor;
	
	private String usergoal = "";
	private Context mContext;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);

		setContentView(R.layout.activity_user_details1);
		
		faultTolerance();
	
		initView();
		
		pre.setOnClickListener(this);
		next.setOnClickListener(this);
		loseWeight_Button.setOnClickListener(this);
		keepDiet_Button.setOnClickListener(this);
		gainWeight_Button.setOnClickListener(this);
		
		sharedPreferences = getSharedPreferences("base64", Context.MODE_PRIVATE);
		editor = sharedPreferences.edit();
		
	}
	
	public void initView(){
		loseWeight_Button = (Button) findViewById(R.id.loseWeight_Button);
		keepDiet_Button = (Button) findViewById(R.id.keepDiet_Button);
		gainWeight_Button = (Button)findViewById(R.id.gainWeight_Button);
		
		pre = (ImageView)findViewById(R.id.previous);
		next = (ImageView)findViewById(R.id.next);
	
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()){
		case R.id.loseWeight_Button:
			doLoseWeight();
			break;
		case R.id.keepDiet_Button:
			doKeepDiet();
			break;
		case R.id.gainWeight_Button:
			doGainWeight();
			break;		
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

	private void doGainWeight() {
		usergoal = "3";
		
		gainWeight_Button.setBackgroundColor(Color.parseColor("#0099FF"));
		loseWeight_Button.setBackgroundResource(R.drawable.frag_button);
		keepDiet_Button.setBackgroundResource(R.drawable.frag_button);
	}

	private void doKeepDiet() {
		usergoal = "2";
		
		keepDiet_Button.setBackgroundColor(Color.parseColor("#0099FF"));
		loseWeight_Button.setBackgroundResource(R.drawable.frag_button);
		gainWeight_Button.setBackgroundResource(R.drawable.frag_button);
	}
	
	private void doLoseWeight() {
		usergoal = "1";
		
		loseWeight_Button.setBackgroundColor(Color.parseColor("#0099FF"));
		gainWeight_Button.setBackgroundResource(R.drawable.frag_button);
		keepDiet_Button.setBackgroundResource(R.drawable.frag_button);
	}
	
	/**
	 * forward to previous page
	 */
	@Override
	public void doPrevious() {
		// TODO Auto-generated method stub
		startActivity(new Intent(UserDetailsActivity1.this, MainActivity.class));
		this.finish();
	}

	/**
	 * forward to next page
	 */
	@Override
	public void doNext() {
		// TODO Auto-generated method stub
		if(usergoal.equals("")){
			Toast.makeText(UserDetailsActivity1.this,"Please select your goal!",Toast.LENGTH_SHORT).show();
		}else{
			editor.putString("usergoal", usergoal);
			editor.commit();
			
			startActivity(new Intent(UserDetailsActivity1.this, UserDetailsActivity2.class));
			this.finish();
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



