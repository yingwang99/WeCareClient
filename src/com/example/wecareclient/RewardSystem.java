package com.example.wecareclient;

import android.app.Application;
import db.ItemDBManager;
import db.*;
public class RewardSystem extends Application {

	public ItemDBManager itemDBManager = new ItemDBManager(this); 
	
	@Override
	public void onCreate(){
		super.onCreate();
		itemDBManager.open();
	}
	
	@Override
	public void onTerminate(){
		super.onTerminate();
		itemDBManager.close();
	}
}
