package com.example.wecareclient;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import clientInterface.UserDetailsInterface;
import util.CrashHandler;

/**
 * 
 * @author ying
 * 
 */
public class UserAccountActivity extends Activity implements OnClickListener, UserDetailsInterface{
	private SharedPreferences sharedPreferences;
	private SharedPreferences.Editor editor;
	private LinearLayout My_list_nutrition;
	private LinearLayout My_list_progress;
	private LinearLayout My_list_recomm;

	private ImageView My_head;
	
	final int TAKE_PICTURE = 1;
	
	private String un;
	
	private TextView username;
	private Context mContext;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_user_account);
		
		sharedPreferences = this.getSharedPreferences("base64", Context.MODE_PRIVATE);
		editor = sharedPreferences.edit();
		
		faultTolerance();
		initView();
	}
	
	public void initView(){
		
		username = (TextView)findViewById(R.id.My_logintoast);
		My_list_nutrition = (LinearLayout)findViewById(R.id.My_list_nutrition);
		My_list_nutrition.setOnClickListener(this);
		My_list_progress = (LinearLayout)findViewById(R.id.My_list_progress);
		My_list_progress.setOnClickListener(this);
		
		My_list_recomm = (LinearLayout)findViewById(R.id.My_list_recomm);
		My_list_recomm.setOnClickListener(this);
		
		My_head = (ImageView)findViewById(R.id.My_head);
		My_head.setOnClickListener(this);
		
		
		init();
	}
	
	public void init(){
		un = sharedPreferences.getString("username", "");
		
		if(!un.equals(""))
			username.setText(un);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.user_account, menu);
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
		switch (v.getId()) {
		case R.id.My_list_nutrition:
			goToNutritionActivity();
			break;
		case R.id.My_list_recomm:
			goToRecommendationActivity();
			break;
		case R.id.My_list_progress:
			goToProgressActivity();
			break;
		case R.id.My_head:
			startActivityForResult(new Intent("android.media.action.IMAGE_CAPTURE"), TAKE_PICTURE);
			break;
		default:
			break;
		}
	}
	
private void goToRecommendationActivity() {
		// TODO Auto-generated method stub
		startActivity(new Intent(UserAccountActivity.this, RecommendActivity.class));
		this.finish();
	}

	private void goToProgressActivity() {
		// TODO Auto-generated method stub
		startActivity(new Intent(UserAccountActivity.this, MyProgressActivity.class));
		this.finish();
	}

	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == TAKE_PICTURE) {
            if (resultCode == RESULT_OK) {
                Bitmap bm = (Bitmap) data.getExtras().get("data");
                My_head.setImageBitmap(bm); 
                
                File myCaptureFile = new File("sdcard/my.jpg");
                try {
	                BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(myCaptureFile));
	
	                bm.compress(Bitmap.CompressFormat.JPEG, 80, bos);
		           
		            bos.flush();
		           
		            bos.close();
			    } catch (FileNotFoundException e) {
				     // TODO Auto-generated catch block
				     e.printStackTrace();
			     
			    } catch (IOException e) {
			     // TODO Auto-generated catch block
			     e.printStackTrace();
			    }
	            }
	        }
		}
	
	
	
	public boolean macth(Bitmap b1, Bitmap b2){
	    if(b1.getWidth()==b2.getWidth()
			        &&b1.getHeight()==b2.getHeight()){
		        int xCount = b1.getWidth();
		        int yCount = b1.getHeight();
		        for(int x=0; x<xCount; x++){
		            for(int y=0; y<yCount; y++){
			                if(b1.getPixel(x, y)!=b2.getPixel(x, y)){
				                    return false;
			                }
		            }            
		        }    
		        return true;
	    }else{
		        return false;
	    }
	}



	private void goToNutritionActivity() {
		// TODO Auto-generated method stub
		startActivity(new Intent(UserAccountActivity.this, NutritionActivity.class));
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
