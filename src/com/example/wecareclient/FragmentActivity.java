package com.example.wecareclient;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.conn.ManagedClientConnection;

import android.app.ActivityGroup;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.hardware.Camera.Face;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;
import clientInterface.UserDetailsInterface;
import util.CrashHandler;

/**
 * 
 * @author ying
 * 
 */
public class FragmentActivity extends ActivityGroup implements UserDetailsInterface{

	private LinearLayout mMyBottemHomeBtn,mMyBottemUpgrationBtn, mMyBottemRewardingBtn,
			 mMyBottemMyBtn;
	private TextView  mMyBottemHomeTxt, mMyBottemUpgrationTxt, mMyBottemRewardingTxt,mMyBottemMyTxt;
	private List<View> list = new ArrayList<View>();
	private View view = null;
	private View view1 = null;
	private View view2 = null;
	private View view3 = null;
	
	private android.support.v4.view.ViewPager mViewPager;
	private PagerAdapter pagerAdapter = null;
	private Context mContext;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_frame);
		
		faultTolerance();
		initView();
	}


	@SuppressWarnings("deprecation")
	private void initView() {
		mViewPager = (ViewPager) findViewById(R.id.FramePager);
		mMyBottemHomeBtn = (LinearLayout) findViewById(R.id.MyBottemHomeBtn);
		mMyBottemUpgrationBtn = (LinearLayout) findViewById(R.id.MyBottemUpgrationBtn);
		mMyBottemRewardingBtn = (LinearLayout) findViewById(R.id.MyBottemRewardBtn);
		mMyBottemMyBtn = (LinearLayout) findViewById(R.id.MyBottemMyBtn);
		
		
		
		mMyBottemHomeTxt = (TextView) findViewById(R.id.MyBottemHomeTxt);
		mMyBottemUpgrationTxt = (TextView) findViewById(R.id.MyBottemUpgrationTxt);
		mMyBottemRewardingTxt = (TextView) findViewById(R.id.MyBottemrewardTxt);
		mMyBottemMyTxt = (TextView) findViewById(R.id.MyBottemMyTxt);
		createView();
		pagerAdapter = new PagerAdapter() {
			
			@Override
			public boolean isViewFromObject(View arg0, Object arg1) {
				return arg0 == arg1;
			}

			@Override
			public int getCount() {
				return list.size();
			}

			@Override
			public void destroyItem(ViewGroup container, int position,
					Object object) {
			
				container.removeView(list.get(position));
			}

			
			@Override
			public Object instantiateItem(ViewGroup container, int position) {
				View v = list.get(position);
				container.addView(v);
				return v;
			}
		};
		mViewPager.setAdapter(pagerAdapter);

		MyBtnOnclick mytouchlistener = new MyBtnOnclick();
		mMyBottemHomeBtn.setOnClickListener(mytouchlistener);
		mMyBottemUpgrationBtn.setOnClickListener(mytouchlistener);
		mMyBottemRewardingBtn.setOnClickListener(mytouchlistener);
		mMyBottemMyBtn.setOnClickListener(mytouchlistener);

		mViewPager.setOnPageChangeListener(new OnPageChangeListener() {
			@Override
			public void onPageSelected(int arg0) {
				initBottemBtn();
				
				int flag = (Integer) list.get((arg0)).getTag();
				if (flag == 0) {
					
					mMyBottemHomeTxt.setTextColor(Color.parseColor("#FF8C00"));

				} else if (flag == 1) {
					
					mMyBottemUpgrationTxt.setTextColor(Color.parseColor("#FF8C00"));
				} else if (flag == 2) {
					
					mMyBottemRewardingTxt.setTextColor(Color.parseColor("#FF8C00"));
				} else if (flag == 3) {
					
					mMyBottemMyTxt.setTextColor(Color.parseColor("#FF8C00"));
				}
			}

		
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
			}

		
			@Override
			public void onPageScrollStateChanged(int arg0) {
			}
		});

	}

	@SuppressWarnings("deprecation")
	private void createView() {

		Intent intent=new Intent(FragmentActivity.this, HomeActivity.class);																							
	    intent.putExtra("food", getIntent().getSerializableExtra("food"));
	    intent.putExtra("meal_type", getIntent().getStringExtra("meal_type"));
	    
	    
		view = this
				.getLocalActivityManager()
				.startActivity("Home",
						intent)
				.getDecorView();
		view.setTag(0);
		list.add(view);
		
		Intent intent1=new Intent(FragmentActivity.this, UpgrationActivity.class);

		view1 = FragmentActivity.this
				.getLocalActivityManager()
				.startActivity("Upgration",
						intent1)
				.getDecorView();
		view1.setTag(1);
		list.add(view1);
		
		Intent intent2=new Intent(FragmentActivity.this, MyRewardActivity.class);
		
		view2 = FragmentActivity.this
				.getLocalActivityManager()
				.startActivity("Reward",
						intent2)
				.getDecorView();
		view2.setTag(2);
		list.add(view2);
		
		Intent intent3=new Intent(FragmentActivity.this, UserAccountActivity.class);
		
		view3 = FragmentActivity.this
				.getLocalActivityManager()
				.startActivity("Me",
						intent3)
				.getDecorView();
		view3.setTag(3);
		list.add(view3);
		
		
	}

	
	private class MyBtnOnclick implements View.OnClickListener {

		@Override
		public void onClick(View arg0) {
			int mBtnid = arg0.getId();
			switch (mBtnid) {
			case R.id.MyBottemHomeBtn:
				mViewPager.setCurrentItem(0);
				initBottemBtn();
				
				mMyBottemHomeTxt.setTextColor(Color.parseColor("#FF8C00"));
				break;
				
			case R.id.MyBottemUpgrationBtn:
				mViewPager.setCurrentItem(1);
				initBottemBtn();
				
				mMyBottemUpgrationTxt.setTextColor(Color.parseColor("#FF8C00"));
				break;
				
			case R.id.MyBottemRewardBtn:
				mViewPager.setCurrentItem(2);
				initBottemBtn();
				
				mMyBottemRewardingTxt.setTextColor(Color.parseColor("#FF8C00"));
				break;
			
			case R.id.MyBottemMyBtn:
				mViewPager.setCurrentItem(3);
				initBottemBtn();
				
				mMyBottemMyTxt.setTextColor(Color.parseColor("#FF8C00"));
				break;
			
			}

		}

	}

	private void initBottemBtn() {
		
		mMyBottemHomeTxt.setTextColor(getResources().getColor(
				R.drawable.search_bottem_textcolor));
		mMyBottemUpgrationTxt.setTextColor(getResources().getColor(
				R.drawable.search_bottem_textcolor));
		mMyBottemRewardingTxt.setTextColor(getResources().getColor(
				R.drawable.search_bottem_textcolor));
		mMyBottemMyTxt.setTextColor(getResources().getColor(
				R.drawable.search_bottem_textcolor));
		
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
