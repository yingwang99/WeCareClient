package Adapter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.example.wecareclient.R;
import com.example.wecareclient.dto.Item;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class TopicListView {

	View view;

	public TopicListView(Context context, ViewGroup parent, int queryType) throws IOException {

		LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		view = layoutInflater.inflate(R.layout.topic_list, parent, false);
		
		List<Item> productList = new ArrayList<Item>();
		productList.add(new Item());
		productList.add(new Item());
		productList.add(new Item());
		productList.add(new Item());
		productList.add(new Item());
		productList.add(new Item());
		
		RecyclerView productView = (RecyclerView) view.findViewById(R.id.productList);
		ProductListAdapter productAdapter = new ProductListAdapter(productList);
		CustomLinearLayoutManager myLayoutManager = new CustomLinearLayoutManager(context);
		myLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
		productView.setAdapter(productAdapter);
		myLayoutManager.setScrollEnabled(false);
		productView.setLayoutManager(myLayoutManager);

	}

}
