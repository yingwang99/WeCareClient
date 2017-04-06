package com.example.wecareclient;

import org.apache.commons.collections4.Predicate;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.example.wecareclient.dto.Food;

import android.app.Activity;
import android.app.SearchManager.OnCancelListener;
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
import android.widget.TextView;
import clientInterface.UserDetailsInterface;

public class RecommendActivity extends Activity implements UserDetailsInterface,OnClickListener{

	private SharedPreferences sharedPreferences;
	private SharedPreferences.Editor editor;
	private TextView recom;
	
	private ImageView pre;
	
	
	@Override
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_recommend);
		recom = (TextView) findViewById(R.id.rec);
		pre = (ImageView)findViewById(R.id.previous);
		pre.setOnClickListener(this);
	
		
		sharedPreferences = this.getSharedPreferences("base64", Context.MODE_PRIVATE);
		editor = sharedPreferences.edit();
		
		String rec = sharedPreferences.getString("recommend", "");
		
		if(rec != null){
			System.out.println("dferg" + rec);
			try{
			Food food = (Food)JSON.parseObject(rec,Food.class);
			recom.setText(food.getKind() + " + " + food.getCategray() + " + " + food.getG() + "g" + " + " 
					+ food.getCalorie() + "col");
			}catch(JSONException ex){
				recom.setText("no match recommendation");			}
		}else{
			recom.setText("no match recommendation");
		}
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.recommend, menu);
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
		startActivity(new Intent(RecommendActivity.this, FragmentActivity.class));
		this.finish();
	}

	@Override
	public void doNext() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void faultTolerance() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.previous:
			doPrevious();
			break;

		default:
			break;
		}
	}
}
