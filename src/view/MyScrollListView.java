package view;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ScrollView;

public class MyScrollListView extends ScrollView{


	private ListView left;
	private ListView right;
	private LinearLayout innerLayout;

	public MyScrollListView(Context context,
			ListView left,
			ListView right) {
		super(context);
		this.left = left;
		this.right = right;
		initView(context);
	}

	private void initView(Context context){
		innerLayout = new LinearLayout(context);
		innerLayout.setLayoutParams(new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.FILL_PARENT,
				LinearLayout.LayoutParams.WRAP_CONTENT));
		innerLayout.setOrientation(LinearLayout.HORIZONTAL);
		setListViewHeightBasedOnChildren(left);
		setListViewHeightBasedOnChildren(right);
		innerLayout.addView(left);
		innerLayout.addView(right);
		addView(innerLayout);
	}

	public static void setListViewHeightBasedOnChildren(ListView listView)
	{
		ListAdapter listAdapter = listView.getAdapter();
		if (listAdapter == null)
		{
			return;
		}

		int totalHeight = 0;
		for (int i = 0, len = listAdapter.getCount(); i < len; i++)
		{
			View listItem = listAdapter.getView(i, null, listView);
			listItem.measure(0, 0);
			totalHeight += listItem.getMeasuredHeight();
		}
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.MATCH_PARENT,
				LinearLayout.LayoutParams.WRAP_CONTENT);
		params.weight = 1;
		params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1)) + listView.getPaddingTop() + listView.getPaddingBottom();
		listView.setLayoutParams(params);
	}
}
