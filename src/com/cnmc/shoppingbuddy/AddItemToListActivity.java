package com.cnmc.shoppingbuddy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.Toast;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupCollapseListener;
import android.widget.ExpandableListView.OnGroupExpandListener;
import android.os.Build;

public class AddItemToListActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_item_to_list);

		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_item_to_list, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {
		
		ExpandableListAdapter shopListAdapter;
		ExpandableListView shopExpListView;
		List<String> listCategoryHeader;
		HashMap<String, List<String>> listCategoryChild;


		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(
					R.layout.fragment_add_item_to_list, container, false);
			
			
			
			// get the listview
			shopExpListView = (ExpandableListView) rootView
					.findViewById(R.id.shoppingExpListView);
			shopExpListView.setOnChildClickListener(new OnChildClickListener() {

				@Override
				public boolean onChildClick(ExpandableListView parent, View v,
						int groupPosition, int childPosition, long id) {
					Toast.makeText(
							getActivity(),
							listCategoryHeader.get(groupPosition)
									+ " : "
									+ listCategoryChild.get(
											listCategoryHeader.get(groupPosition)).get(
											childPosition), Toast.LENGTH_SHORT)
							.show();
					return false;
				}
			});
			
			shopExpListView.setOnGroupExpandListener(new OnGroupExpandListener() {
				 
			    @Override
			    public void onGroupExpand(int groupPosition) {
			        Toast.makeText(getActivity(),
			                listCategoryHeader.get(groupPosition) + " Expanded",
			                Toast.LENGTH_SHORT).show();
			    }
			});
			
			shopExpListView.setOnGroupCollapseListener(new OnGroupCollapseListener() {
				 
			    @Override
			    public void onGroupCollapse(int groupPosition) {
			        Toast.makeText(getActivity(),
			                listCategoryHeader.get(groupPosition) + " Collapsed",
			                Toast.LENGTH_SHORT).show();
			 
			    }
			});
			
			prepareListData();
			
			shopListAdapter = new ExpandableListAdapter(getActivity(), listCategoryHeader,
					listCategoryChild);
			shopExpListView.setAdapter(shopListAdapter);

			return rootView;
		}
		
		/*
		 * Preparing the list data
		 */
		private void prepareListData() {
			listCategoryHeader = new ArrayList<String>();
			listCategoryChild = new HashMap<String, List<String>>();

			// Adding child data
			listCategoryHeader.add("Groceries");
			listCategoryHeader.add("Clothing");
			listCategoryHeader.add("Electronics");
			listCategoryHeader.add("Medication");
			listCategoryHeader.add("House Hold Appliances");

			// Adding child data
			List<String> groceries = new ArrayList<String>();
			groceries.add("Groc Item 1");
			groceries.add("Groc Item 2");
			groceries.add("Groc Item 3");
			groceries.add("Groc Item 4");
			groceries.add("Groc Item 5");
			groceries.add("Groc Item 6");
			groceries.add("Groc Item 7");

			List<String> clothing = new ArrayList<String>();
			clothing.add("Clothing Item 1");
			clothing.add("Clothing Item 2");
			clothing.add("Clothing Item 3");
			clothing.add("Clothing Item 4");
			clothing.add("Clothing Item 5");
			clothing.add("Clothing Item 6");

			List<String> electronics = new ArrayList<String>();
			electronics.add("Electronics Item 1");
			electronics.add("Electronics Item 2");
			electronics.add("Electronics Item 3");
			electronics.add("Electronics Item 4");
			electronics.add("Electronics Item 5");
			
			List<String> medication = new ArrayList<String>();
			medication.add("Medication Item 1");			
			medication.add("Medication Item 2");			
			medication.add("Medication Item 3");			
			medication.add("Medication Item 4");			
			medication.add("Medication Item 5");			
			
			List<String> houseHoldAppliances = new ArrayList<String>();
			houseHoldAppliances.add("houseHoldAppliance Item 1");	
			houseHoldAppliances.add("houseHoldAppliance Item 2");	
			houseHoldAppliances.add("houseHoldAppliance Item 3");	
			houseHoldAppliances.add("houseHoldAppliance Item 4");	
			houseHoldAppliances.add("houseHoldAppliance Item 5");	
			
			listCategoryChild.put(listCategoryHeader.get(0), groceries); // Header, Child data
			listCategoryChild.put(listCategoryHeader.get(1), clothing);
			listCategoryChild.put(listCategoryHeader.get(2), electronics);
			listCategoryChild.put(listCategoryHeader.get(3), medication);
			listCategoryChild.put(listCategoryHeader.get(4), houseHoldAppliances);
		}
	}

}
