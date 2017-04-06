package Adapter;

import com.example.wecareclient.R;

import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.widget.ImageView;

public class ProductViewHolder extends ViewHolder{

	public ImageView imageView;
	
	public ProductViewHolder(View productView) {
		super(productView);
		
		imageView = (ImageView) productView.findViewById(R.id.product_128x128_thumbnail);
		
	}

}
