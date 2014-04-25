package com.cnmc.shoppingbuddy;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.shoppingbuddy.R;

public class AddedListDisplayFragment extends Fragment {
	private ArrayList<AddedListNameStatus> listNameStatus;
	private EfficientAdapter listAdapter;
	public static String TAG = "AddedListDisplayFragment";
	public AddedListDisplayFragment(){
		listNameStatus = new ArrayList<AddedListDisplayFragment.AddedListNameStatus>();		
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View listDisplayLayout = inflater.inflate(R.layout.added_list_display, container, false);
		//Adding Items in the list
		listNameStatus.add(new AddedListNameStatus("Grocery Items", true));
		listNameStatus.add(new AddedListNameStatus("Grocery Items", true));
		listNameStatus.add(new AddedListNameStatus("Grocery Items", true));
		listAdapter = new EfficientAdapter(getActivity(), listNameStatus);	
		ListView list = (ListView)listDisplayLayout.findViewById(android.R.id.list);
		list.setAdapter(listAdapter);
		return listDisplayLayout;
	}
	
	public static final class AddedListNameStatus{
		private String listName;
		private boolean status;
		public AddedListNameStatus(String listName, boolean status) {
			this.listName = listName;
			this.status = status;
		}
		public String getListName() {
			return listName;
		}
		public void setListName(String listName) {
			this.listName = listName;
		}
		public boolean isStatus() {
			return status;
		}
		public void setStatus(boolean status) {
			this.status = status;
		}		
	}
}
