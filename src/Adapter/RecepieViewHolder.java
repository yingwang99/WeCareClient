package Adapter;


import com.example.wecareclient.R;

import android.support.v7.widget.RecyclerView;

import android.view.View;
import android.widget.ImageView;


public class RecepieViewHolder extends RecyclerView.ViewHolder{

	public ImageView imageView;
	
	public RecepieViewHolder(View recepieView) {
		super(recepieView);
		
		imageView = (ImageView) recepieView.findViewById(R.id.topic_icon);
		
	}


}
