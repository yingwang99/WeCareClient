package com.example.wecareclient;

import java.util.Calendar;
import java.util.Date;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import clientInterface.UserDetailsInterface;
import util.CrashHandler;
import util.DateUtil;

/**
 * 
 * @author ying
 * 
 */
public class UserDetailsActivity2 extends Activity implements OnClickListener, UserDetailsInterface{

	private SharedPreferences sharedPreferences;
	private SharedPreferences.Editor editor;
	
	private ImageView pre, next;
	
	private EditText dob, country;
	private TextView m,f;

	String genderStr, dobStr, countryStr;
	private Context mContext;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_user_details2);
		
		faultTolerance();
		
		initView();
		
		pre.setOnClickListener(this);
		next.setOnClickListener(this);
		m.setOnClickListener(this);
		f.setOnClickListener(this);
		
		sharedPreferences = getSharedPreferences("base64", Context.MODE_PRIVATE);
		editor = sharedPreferences.edit();
		
		setDateListener();  

	    
		
		
	}

	private void setDateListener() {
		dob.setOnTouchListener(new OnTouchListener() {  
	
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				if (event.getAction() == MotionEvent.ACTION_DOWN) {  
	                showDatePickDlg();  
	                return true;  
	            } 
				return false;
			}  
	    });  
	    /*dob.setOnFocusChangeListener(new OnFocusChangeListener() {  
	
	        @Override  
	        public void onFocusChange(View v, boolean hasFocus) {  
	            if (hasFocus) {  
	                showDatePickDlg();  
	            }
	        }  
	    });*/
	}
	
	public void initView(){
		m = (TextView) findViewById(R.id.male);
		f = (TextView) findViewById(R.id.female);
		
		dob = (EditText) findViewById(R.id.dayofbirth);
		
		country = (EditText) findViewById(R.id.text_country);
		
		f.setBackgroundColor(Color.parseColor("#0099FF"));
		genderStr = "f";
		
		pre = (ImageView)findViewById(R.id.previous);
		next = (ImageView)findViewById(R.id.next);
	
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.user_details_activity2, menu);
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
		switch (v.getId()){
		case R.id.previous:
			doPrevious();
			break;
		case R.id.next:
			doNext();
			break;
		case R.id.male:
			doMaleSelection();
			break;
		case R.id.female:
			doFemaleSelection();
			break;
			
		default:
			break;
		}
	}

	private void doFemaleSelection() {
		f.setBackgroundColor(Color.parseColor("#0099FF"));
		m.setBackgroundResource(R.drawable.frag_button);
		
		genderStr = "f";
	}

	private void doMaleSelection() {
		m.setBackgroundColor(Color.parseColor("#0099FF"));
		f.setBackgroundResource(R.drawable.frag_button);
		
		genderStr = "m";
	}

	@Override
	public void doPrevious() {
		// TODO Auto-generated method stub
		startActivity(new Intent(UserDetailsActivity2.this, UserDetailsActivity1.class));
		this.finish();
	}

	@Override
	public void doNext() {
		// TODO Auto-generated method stub
		dobStr = dob.getText().toString();
		countryStr = country.getText().toString();
		
		if(dobStr.equals("")){
			dobStr = "1970-1-1";
		}
		if(countryStr.equals("")){
			countryStr = "Ireland";
		}
		
		if(checkBirthday(dobStr)){
			storeData();
			
			startActivity(new Intent(UserDetailsActivity2.this, UserDetailsActivity3.class));
			this.finish();
		}else{
			Toast.makeText(UserDetailsActivity2.this,"Invalid Birthday",Toast.LENGTH_SHORT).show();
		}
		
		
	}
	
	public void storeData(){
		editor.putString("gender", genderStr);
		editor.putString("dob", dobStr);
		editor.putString("country", countryStr);
		editor.commit();
	}
	
	protected void showDatePickDlg() {  
	    Calendar calendar = Calendar.getInstance();  
	    DatePickerDialog datePickerDialog = new DatePickerDialog(this, new OnDateSetListener() {  
	
			@Override
			public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
				// TODO Auto-generated method stub
				 dob.setText(year + "-" + monthOfYear+1 + "-" + dayOfMonth);  
			}  
	    }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));  
	   
	    datePickerDialog.show();  	
	}

	@Override
	public void faultTolerance() {
		// TODO Auto-generated method stub
		CrashHandler crashHandler = CrashHandler.getInstance();
        crashHandler.init(getApplicationContext());
        mContext = this;
	}  
	
	public boolean checkBirthday(String dob){
		Date dobStr = (Date) DateUtil.getConvertStringToDate(dob);
		Date cur = new Date();
		
		if(cur.getTime() - dobStr.getTime() > 0){
			return true;
		}
		
		
		return false;
	}
}
