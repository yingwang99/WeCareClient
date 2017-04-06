package com.example.wecareclient;

import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.crypto.EncryptedPrivateKeyInfo;

import com.example.wecareclient.dto.User;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import clientInterface.UserDetailsInterface;
import db.FeedReaderContract.FeedEntry;
import db.FeedReaderDBUserManager;
import db.FeedReaderDbHelper;
import util.CrashHandler;
import util.DateUtil;
import util.HttpUtils;
import util.RSAUtils;
import util.Url;

/**
 * 
 * @author ying
 * 
 */
public class UserDetailsActivity5 extends Activity implements OnClickListener, UserDetailsInterface{
	private SharedPreferences sharedPreferences;
	private SharedPreferences.Editor editor;
	
	private EditText username, pwd, pwd_confirm,email;
	private TextView command;
	
	private ImageView pre, next;
	private boolean checkValid = false;
	
	private Context mContext;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_user_details5);
		
		sharedPreferences = this.getSharedPreferences("base64", Context.MODE_PRIVATE);
		editor = sharedPreferences.edit();
		
		faultTolerance();
		initView();
		
		pre.setOnClickListener(this);
		next.setOnClickListener(this);
	}
	
	public void initView(){
		username = (EditText) findViewById(R.id.username_input);
		pwd = (EditText) findViewById(R.id.passward_input);
		pwd_confirm = (EditText) findViewById(R.id.passward_confirm_input);
		email = (EditText) findViewById(R.id.email_address);
		
		command = (TextView) findViewById(R.id.command);
		
		pre = (ImageView)findViewById(R.id.previous);
		next = (ImageView)findViewById(R.id.next);

	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.user_details_activity5, menu);
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
		startActivity(new Intent(UserDetailsActivity5.this, UserDetailsActivity4.class));
		this.finish();
	}
	

	@Override
	public void doNext() {
		// TODO Auto-generated method stub
		checkValid = checkEmailAdd(email.getText().toString());
		
		if(checkValid == false){
			Toast.makeText(UserDetailsActivity5.this,"Invalid email address!",Toast.LENGTH_SHORT).show();
		}else{	
		if(!username.getText().toString().equals("") && !pwd.getText().toString().equals("") 
				&& !pwd_confirm.getText().toString().equals("") && !email.getText().toString().equals("") 
				&& checkPwd(pwd.getText().toString()) == true && pwd.getText().toString().equals(pwd_confirm.getText().toString())){
			
			if(storeInDb() > 0){
				rsa(username.getText().toString());
				rsa(pwd.getText().toString());
				rsa(email.getText().toString());
				editor.putString("username", username.getText().toString());
				editor.putString("pwd", pwd.getText().toString());
				editor.putString("email", email.getText().toString());
				editor.commit();
				
				sendToServer();
				startActivity(new Intent(UserDetailsActivity5.this, FragmentActivity.class));
				this.finish();
			}else{
				Toast.makeText(UserDetailsActivity5.this,"Username already exists!",Toast.LENGTH_SHORT).show();
			}
		}else if(checkPwd(pwd.getText().toString()) == false){
			Toast.makeText(UserDetailsActivity5.this,"Password should be at least six letters!",Toast.LENGTH_SHORT).show();
		}else if(!pwd.getText().toString().equals(pwd_confirm.getText().toString())){
			Toast.makeText(UserDetailsActivity5.this,"Passwords are not match..",Toast.LENGTH_SHORT).show();
		}else{
			Toast.makeText(UserDetailsActivity5.this,"Please fill all fields..",Toast.LENGTH_SHORT).show();
		}
		
		}
	}
	
	public boolean checkPwd(String pwd){
		if(pwd.length() < 6){
			return false;
		}
		
		return true;
	}
	
	public void sendToServer(){
		User user = getDataFromSharedpreference();
		SignUp sign = new SignUp();
		sign.execute(rsa(user.getUsername()), rsa(user.getPassword()), user.getEmail(), user.getUserGoal(),user.getGender(),DateUtil.getConvertDateToString(user.getDayOfBirth()),
				user.getCountry(), String.valueOf(user.getHeight()), String.valueOf(user.getWeight()), String.valueOf(user.getGoalOfWeight()), 
				String.valueOf(user.getWeeklyGoal()));
	}
	
	public String rsa(String msg){
		HashMap<String, Object> map;
		String encryptMsg = "";
		try {
			map = RSAUtils.getKeys();
			RSAPublicKey publicKey = (RSAPublicKey) map.get("public");  
	        RSAPrivateKey privateKey = (RSAPrivateKey) map.get("private");  
	          
	        String modulus = publicKey.getModulus().toString();  
	        String public_exponent = publicKey.getPublicExponent().toString();  
	        String private_exponent = privateKey.getPrivateExponent().toString();  

	        RSAPublicKey pubKey = RSAUtils.getPublicKey(modulus, public_exponent);  
	        RSAPrivateKey priKey = RSAUtils.getPrivateKey(modulus, private_exponent);  

	        encryptMsg = RSAUtils.encryptByPublicKey(msg, pubKey);  
	        System.err.println(encryptMsg);  

	        msg = RSAUtils.decryptByPrivateKey(encryptMsg, priKey);  
	        System.err.println(msg); 
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		return encryptMsg;
         
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

		default:
			break;
		}
	}
	
	public boolean checkEmailAdd(String email){
		 String check = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";    
		 Pattern regex = Pattern.compile(check);    
		 Matcher matcher = regex.matcher(email);    
		 boolean isMatched = matcher.matches();   
		 
		 return isMatched;
	}
	
	public long storeInDb(){
		
		FeedReaderDBUserManager UserManager = new FeedReaderDBUserManager();
		
		FeedReaderDbHelper mDbHelper = new FeedReaderDbHelper(getApplicationContext());

		// Gets the data repository in write mode
		SQLiteDatabase db = mDbHelper.getWritableDatabase();

		// Insert the new row, returning the primary key value of the new row
		long newRowId = db.insert(FeedEntry.TABLE_NAME, null, UserManager.getUserValues(getDataFromSharedpreference()));
		 
		return newRowId;
	}
	
	public User getDataFromSharedpreference(){
		User user = new User();
		
		user.setUserGoal(sharedPreferences.getString("usergoal", ""));
		user.setDayOfBirth(DateUtil.getConvertStringToDate(sharedPreferences.getString("dob", "")));
		user.setGender(sharedPreferences.getString("gender", ""));
		user.setCountry(sharedPreferences.getString("country", ""));
		user.setWeight(Double.parseDouble(sharedPreferences.getString("weight", "")));
		user.setHeight(Double.parseDouble(sharedPreferences.getString("height", "")));
		user.setGoalOfWeight(Double.parseDouble(sharedPreferences.getString("goalofweight", "")));
		user.setWeeklyGoal(Double.parseDouble(sharedPreferences.getString("weeklyGoal", "")));
		user.setUsername(username.getText().toString());
		user.setPassword(pwd.getText().toString());
		user.setEmail(email.getText().toString());

		return user;
	}
	
	public class SignUp extends AsyncTask<String, Void, String> {

		ProgressDialog p = new ProgressDialog(UserDetailsActivity5.this,
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
			String string = "username=" + arg0[0] + "&password=" + arg0[1] + "&email=" + arg0[2] + "&userGoal=" + arg0[3] + "&gender="
				+ arg0[4] + "&dob=" + arg0[5] + "&country=" + arg0[6] + "&height=" + arg0[7] + "&weight=" + arg0[8] + "&goalOfWeight=" + arg0[9] 
			+ "&weeklyGoal=" + arg0[10];
	
			String result = HttpUtils.doGet(Url.serverUrl() + "CustomerAddServlet?" + string);
			return result;
		}
		
		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			p.dismiss();
			
			if(result.equals("1")){
				Toast.makeText(UserDetailsActivity5.this,"Sign Up Failed!",Toast.LENGTH_SHORT).show();
			}else{
				editor.putString("userId", result);
				editor.commit();
				Toast.makeText(UserDetailsActivity5.this,"Registered successful!",Toast.LENGTH_SHORT).show();
				startActivity(new Intent(UserDetailsActivity5.this, FragmentActivity.class));
			}
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
