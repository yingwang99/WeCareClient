package Adapter;

import java.util.List;

import com.example.wecareclient.R;
import com.example.wecareclient.dto.Item;

import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ProductListAdapter extends Adapter<ProductViewHolder> {

	// may need a listener here
	public List<Item> productList;

	public ProductListAdapter(List<Item> products) {
		this.productList = products;
	}

	@Override
	public void onBindViewHolder(final ProductViewHolder holder, int position) {
		
		holder.imageView.setImageResource(R.drawable.recepie);
		// TODO Auto-generated method stub

	}

	@Override
	public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_item_strip, parent, false);
		ProductViewHolder holder = new ProductViewHolder(view);
		return holder;
		
	}

	@Override
	public int getItemCount() {
		// TODO Auto-generated method stub
		return productList.size();
	}

}

