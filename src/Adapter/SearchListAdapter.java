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

public class SearchListAdapter extends BaseAdapter{
	private List<Food> list;
	private Context mContext;
	
	public SearchListAdapter(List<Food> list, Context mContext) {
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
		convertView = LayoutInflater.from(mContext).inflate(R.layout.food_item, null);
			
		TextView name = (TextView)convertView.findViewById(R.id.name);
		name.setText(food.getKind() + " " + food.getG() + " " + food.getCalorie());
		
	
		return convertView;
	}

}
