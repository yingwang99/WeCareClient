package com.example.wecareclient;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import clientInterface.UserDetailsInterface;
import db.FeedReaderContract.FeedEntry;
import db.FeedReaderDbHelper;
import lecho.lib.hellocharts.gesture.ContainerScrollType;
import lecho.lib.hellocharts.gesture.ZoomType;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.model.ValueShape;
import lecho.lib.hellocharts.model.Viewport;
import lecho.lib.hellocharts.view.LineChartView;
import util.CrashHandler;

/**
 * 
 * @author ying
 * 
 */
public class MyProgressActivity extends Activity implements OnClickListener, UserDetailsInterface{
	private LineChartView lineChart;
	private ImageView previous;
    String[] date = {"0am","1am","2am","3am","4am","5am","6am","7am","8am","9am","10am","11am","12am","13pm","14pm","15pm","16pm","17pm","18pm","19pm","20pm","21pm","22pm","23pm"};
    float[] score= {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
    private List<PointValue> mPointValues = new ArrayList<PointValue>();
    private List<AxisValue> mAxisXValues = new ArrayList<AxisValue>();
    private Context mContext;
    
    private SharedPreferences sharedPreferences;
	private SharedPreferences.Editor editor;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_my_progress);
		faultTolerance();
		
		sharedPreferences = this.getSharedPreferences("base64", Context.MODE_PRIVATE);
		editor = sharedPreferences.edit();
		
		lineChart = (LineChartView)findViewById(R.id.line_chart);
		storeToFoodList();
		
        getAxisXLables();
        getAxisPoints();
        initLineChart();
        
        initView();

	}
	
	public Cursor getData() {
		  FeedReaderDbHelper mDbHelper = new FeedReaderDbHelper(getApplicationContext());
	      SQLiteDatabase db = mDbHelper.getReadableDatabase();
	      
	      Cursor res =  db.rawQuery( "select * from " + FeedEntry.TABLE_NAME_FOOD, null );
	      return res;
	   }
	
	public void storeToFoodList(){
		Cursor cursor = getData();
		
		if (cursor.moveToFirst()) {

	        while (cursor.isAfterLast() == false) {
	        	try{
		        	double calories = Double.parseDouble(cursor.getString(cursor.getColumnIndex(FeedEntry.COLUMN_NAME_CALORIES)));		 	
		        	int hr = Integer.parseInt(cursor.getString(cursor.getColumnIndex(FeedEntry.COLUMN_NAME_Add_TIME)));
		        	
		        	score[hr] = (float) (score[hr] + calories);
		        	
	        	}catch(NumberFormatException e){
	        		e.printStackTrace();
	        	}
	        	
	        	cursor.moveToNext();
	        }
		}
		
	}
	
	public void initView(){
		previous = (ImageView)findViewById(R.id.previous);
		previous.setOnClickListener(this);
	}
	
	private void getAxisXLables(){
        for (int i = 0; i < date.length; i++) {    
            mAxisXValues.add(new AxisValue(i).setLabel(date[i]));    
        }       
    }
   
	 private void getAxisPoints(){
	        for (int i = 0; i < score.length; i++) {    
	        	mPointValues.add(new PointValue(i, score[i]));      
	        }    	
	    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.my_progress, menu);
		return true;
	}
	
	private void initLineChart(){
        Line line = new Line(mPointValues).setColor(Color.parseColor("#FFCD41"));  
        List<Line> lines = new ArrayList<Line>();    
        line.setShape(ValueShape.CIRCLE);
        line.setCubic(false);
        line.setFilled(false);
        line.setHasLabels(true);
        line.setHasLines(true);
        line.setHasPoints(true);
        lines.add(line);  
        LineChartData data = new LineChartData();  
        data.setLines(lines);  

        Axis axisX = new Axis(); 
        axisX.setHasTiltedLabels(true);  
        axisX.setTextColor(Color.GRAY); 
        axisX.setTextSize(10);
        axisX.setMaxLabelChars(8); 
        axisX.setValues(mAxisXValues); 
        data.setAxisXBottom(axisX);     
        axisX.setHasLines(true);

        Axis axisY = new Axis(); 
        axisY.setName("");
        axisY.setTextSize(10);
        data.setAxisYLeft(axisY);  

        lineChart.setInteractive(true); 
        lineChart.setZoomType(ZoomType.HORIZONTAL);  
        lineChart.setMaxZoom((float) 2);
        lineChart.setContainerScrollEnabled(true, ContainerScrollType.HORIZONTAL);  
        lineChart.setLineChartData(data);  
        lineChart.setVisibility(View.VISIBLE);
       
        Viewport v = new Viewport(lineChart.getMaximumViewport()); 
          v.left = 0; 
          v.right= 7; 
          lineChart.setCurrentViewport(v); 
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
			goToPrevious();
			break;

		default:
			break;
		}
	}

	private void goToPrevious() {
		// TODO Auto-generated method stub
		startActivity(new Intent(MyProgressActivity.this, FragmentActivity.class));
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
