package Adapter;

import java.util.List;

import com.example.wecareclient.R;
import com.example.wecareclient.dto.Food;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class BreakfastListAdapter extends BaseAdapter{

	private List<Food> list;
	private Context mContext;
	
	public BreakfastListAdapter(List<Food> list, Context mContext) {
		this.list = list;
		this.mContext = mContext;
	}
	
	
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@SuppressLint("ViewHolder")
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		final Food food = list.get(position);
		
		convertView = LayoutInflater.from(mContext).inflate(R.layout.food_item_display, null);
			
		TextView categories = (TextView)convertView.findViewById(R.id.categories);
		categories.setText(food.getKind());
		
		TextView colories = (TextView)convertView.findViewById(R.id.colories);
		colories.setText(String.valueOf(food.getCalorie()));
	
		return convertView;
	}
	
	public void addNewFood(Food food){
		list.add(food);
		this.notifyDataSetChanged();
	}
	
	public double getTotalCol(){
		double count = 0;
		
		for(int i = 0; i < list.size(); i++){
			count = count + list.get(i).getCalorie();
		}
		
		return count;
	}
}
