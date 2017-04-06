package Adapter;

import java.util.List;

import com.example.wecareclient.R;
import com.example.wecareclient.dto.Item;

import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class RecepieListAdapter extends Adapter<RecepieViewHolder> {

	// may need a listener here
	public List<Item> recepieList;

	public RecepieListAdapter(List<Item> recepies) {
		this.recepieList = recepies;
	}

	@Override
	public void onBindViewHolder(final RecepieViewHolder holder, int position) {
		
		holder.imageView.setImageResource(R.drawable.recepie);
		// TODO Auto-generated method stub

	}

	@Override
	public RecepieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recepie_strip, parent, false);
		RecepieViewHolder holder = new RecepieViewHolder(view);
		return holder;
		
	}
	
	

	@Override
	public int getItemCount() {
		// TODO Auto-generated method stub
		return recepieList.size();
	}

}
