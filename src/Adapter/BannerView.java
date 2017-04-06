package Adapter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.example.wecareclient.R;
import com.example.wecareclient.dto.Item;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class BannerView{

	View view;
	List<Item> recepieList = new ArrayList<Item>();
	RecepieListAdapter recepieAdapter;
	public BannerView(Context context, ViewGroup parent) throws IOException {

		LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		view = layoutInflater.inflate(R.layout.category_section, parent, false);
		
	    recepieList.add(new Item());
	    recepieList.add(new Item());
	    recepieList.add(new Item());
			
		RecyclerView recepieView = (RecyclerView) view.findViewById(R.id.recepieList);
	    recepieAdapter = new RecepieListAdapter(recepieList);
	    System.out.println(recepieAdapter.getItemCount()); 
	    CustomLinearLayoutManager myLayoutManager = new CustomLinearLayoutManager(context);
		myLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
		recepieView.setAdapter(recepieAdapter);
		myLayoutManager.setScrollEnabled(false);
		recepieView.setLayoutManager(myLayoutManager);


	}

	

}
