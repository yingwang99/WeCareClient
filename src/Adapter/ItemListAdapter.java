package Adapter;

import java.io.IOException;
import java.util.List;

import com.example.wecareclient.R;


import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

public class ItemListAdapter extends ArrayAdapter<Integer> {

	private Context context;

	// may need a listener here
	public List<Integer> itemList;

	public ItemListAdapter(Context context, List<Integer> itemList) {
		super(context, R.layout.fragment_rsmain, itemList);

		this.context = context;
		this.itemList = itemList;

	}

	@Override
	public int getCount() {
		return itemList.size() + 1;
	}

	@Override
	public Integer getItem(int position) {

		return itemList.get(position);

	}

	public List<Integer> getItemlist() {
		return this.itemList;
	}

	@Override
	public int getPosition(Integer item) {

		return itemList.indexOf(item);

	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		TopicListView topicView = null;
		BannerView bannerView = null;

		if (position == 0) {
			try {
				bannerView = new BannerView(context, parent);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return bannerView.view;
		} else {

			try {
				//itemView = new ItemView(context, parent, itemList.get(position - 1));
				topicView = new TopicListView(context,parent, 1);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return topicView.view;
		}

	}

}
