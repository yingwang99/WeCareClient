package com.example.wecareclient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Adapter.ItemListAdapter;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ViewSwitcher.ViewFactory;



public class MyRewardActivity extends BaseActivity {

		@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);

			//BaseActivity.rewardSystem.itemDBManager.init();

			setContentView(R.layout.activity_rsmain);
			if (savedInstanceState == null) {
				getFragmentManager().beginTransaction().add(R.id.container, new PlaceholderFragment()).commit();
			}

		}

		/**
		 * A placeholder fragment containing a simple view.
		 */
		public static class PlaceholderFragment extends Fragment {

			public PlaceholderFragment() {
			}

			@Override
			public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
				View rootView = inflater.inflate(R.layout.fragment_rsmain, container, false);

				
				//get all items
				//getActivity(),BaseActivity.rewardSystem.itemDBManager.getAll()
				
				List<Integer> queryTypes = new ArrayList<Integer>();
				queryTypes.add(1);
				queryTypes.add(2);
				queryTypes.add(3);
				ItemListAdapter itemListAdapter = new ItemListAdapter(getActivity(), queryTypes);
				
				ListView itemListView = (ListView) rootView.findViewById(R.id.itemList);
				itemListView.setAdapter(itemListAdapter);

				final int[] imageIds = new int[] { R.drawable.gym, R.drawable.herb, R.drawable.nike };
				final ImageSwitcher imageSwitcher = new ImageSwitcher(getActivity());
				List<Map<String, Object>> imageSet = new ArrayList<Map<String, Object>>();
				for (int i = 0; i < imageIds.length; i++) {
					Map<String, Object> item = new HashMap<String, Object>();
					item.put("images", imageIds[i]);
					imageSet.add(item);
				}

				imageSwitcher.setFactory(new ViewFactory() {

					@Override
					public View makeView() {

						ImageView imageView = new ImageView(getActivity());
						imageView.setScaleType(ImageView.ScaleType.FIT_XY);

						return imageView;
					}

				});

				
				imageSwitcher.postDelayed(new Runnable() {
					int i = 0;

					public void run() {

						switch (i++ % 3) {
						case 0:
							imageSwitcher.setImageResource(imageIds[0]);
							imageSwitcher.postDelayed(this, 5000);
							break;
						case 1:
							imageSwitcher.setImageResource(imageIds[1]);
							imageSwitcher.postDelayed(this, 5000);
							break;
						case 2:
							imageSwitcher.setImageResource(imageIds[2]);
							imageSwitcher.postDelayed(this, 5000);
							break;
						}

					}
				}, 0);

				// test area

				itemListView.addHeaderView(imageSwitcher);

				return rootView;
			}

		}
	}
