package Adapter;

import java.io.IOException;
import java.io.InputStream;

import com.example.wecareclient.R;
import com.example.wecareclient.dto.Item;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ItemView {

	View view;
	private static int width;
	private static int height;

	public ItemView(Context context, ViewGroup parent, Item item) throws IOException {

		LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		view = layoutInflater.inflate(R.layout.item_strip, parent, false);

		ImageView imageView = (ImageView) view.findViewById(R.id.topic_icon);

		WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		DisplayMetrics displayMetrics = new DisplayMetrics();

		windowManager.getDefaultDisplay().getMetrics(displayMetrics);

		width = displayMetrics.widthPixels;
		height = (int) (width * 0.8);

		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(width, height);
		imageView.setLayoutParams(params);

		new DownloadImageTask(imageView).execute("http://martamontenegro.com/wp-content/uploads/2013/04/FruitsVeggies.jpg");

		TextView itemName = (TextView) view.findViewById(R.id.itemName);
		TextView itemPrice = (TextView) view.findViewById(R.id.itemPrice);

		itemName.setText(item.getName());
		itemPrice.setText(String.valueOf(item.getPrice()));

	}

	private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
		ImageView bmImage;

		public DownloadImageTask(ImageView bmImage) {
			this.bmImage = bmImage;
		}

		protected Bitmap doInBackground(String... urls) {
			String urldisplay = urls[0];
			Bitmap mIcon11 = null;
			try {
				InputStream in = new java.net.URL(urldisplay).openStream();
				mIcon11 = BitmapFactory.decodeStream(in);
			} catch (Exception e) {
				Log.e("Error", e.getMessage());
				e.printStackTrace();
			}
			return mIcon11;
		}

		protected void onPostExecute(Bitmap result) {

			if (result != null) {

				bmImage.setImageBitmap(Bitmap.createScaledBitmap(result, width, height, false));
				//bmImage.setImageBitmap(result);

			}

		}
	}

}
