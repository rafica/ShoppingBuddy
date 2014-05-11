package com.cnmc.shoppingbuddy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.Toast;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupCollapseListener;
import android.widget.ExpandableListView.OnGroupExpandListener;

public class AddItemToListActivity extends FragmentActivity{ 

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_item_to_list);
		
		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
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
					Log.i("AddItem","clicked a child list item");
					
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
					listCategoryChild,"addItem");
			
			Button showDetailButton = (Button) rootView.findViewById(R.id.showDetailButton);
			
			showDetailButton.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					System.out.println("Clicked Done Adding");
					HashMap<String,ArrayList<String>> itemsHashMap = shopListAdapter.glob_item_hmap;
					String priority =  getActivity().getIntent().getExtras().getString("priority").toString();
					String listName =  getActivity().getIntent().getExtras().getString("name").toString();
					
					System.out.println("Hash Map: "+itemsHashMap.toString());
					System.out.println("FOR DETAIL VIEW SCREEN"+itemsHashMap+"\n"+priority+"\n"+listName);
					
					Intent intent=new Intent(getActivity(),DetailViewActivity.class);
	          	  	Bundle extras = new Bundle();
	          	  	extras.putString("priority",priority);
	          	  	extras.putString("name",listName);
	          	  	extras.putSerializable("itemshashmap", itemsHashMap);

	         	  	System.out.println("Added extras for Detail View Activity successfully");
	         	  	intent.putExtras(extras);
	          	  	startActivity(intent);
					
				}
			});
			
			
			
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
			listCategoryHeader.add("Convenience Store");
			listCategoryHeader.add("Department Store");
			listCategoryHeader.add("Furniture Store");
			listCategoryHeader.add("Health");
			listCategoryHeader.add("Liquor Store");
			listCategoryHeader.add("Shoe Store");
			listCategoryHeader.add("Shopping Mall");
			listCategoryHeader.add("Veterinary Care");
			listCategoryHeader.add("Florist");

			
			// Adding child data
			List<String> groceries = new ArrayList<String>();
			groceries.add("Custom Item");
			groceries.add("Groc Item 1");
			groceries.add("Groc Item 2");
			groceries.add("Groc Item 3");
			groceries.add("Groc Item 4");
			groceries.add("Groc Item 5");
			groceries.add("Groc Item 6");
			groceries.add("Groc Item 7");

			List<String> clothing = new ArrayList<String>();
			clothing.add("Custom Item");
			clothing.add("Clothing Item 1");
			clothing.add("Clothing Item 2");
			clothing.add("Clothing Item 3");
			clothing.add("Clothing Item 4");
			clothing.add("Clothing Item 5");
			clothing.add("Clothing Item 6");

			List<String> electronics = new ArrayList<String>();
			electronics.add("Custom Item");
			electronics.add("Electronics Item 1");
			electronics.add("Electronics Item 2");
			electronics.add("Electronics Item 3");
			electronics.add("Electronics Item 4");
			electronics.add("Electronics Item 5");
			
			
			List<String> convenience = new ArrayList<String>();
			convenience.add("Custom Item");
			convenience.add("Convenience Store Item 1");
			convenience.add("Convenience Store Item 2");
			convenience.add("Convenience Store Item 3");
			convenience.add("Convenience Store Item 4");
			
			List<String> department = new ArrayList<String>();
			department.add("Custom Item");
			department.add("Department Store Item 1");
			department.add("Department Store Item 2");
			department.add("Department Store Item 3");
			department.add("Department Store Item 4");
			
			List<String> furniture = new ArrayList<String>();
			furniture.add("Custom Item");
			furniture.add("Furniture Store Item 1");
			furniture.add("Furniture Store Item 2");
			furniture.add("Furniture Store Item 3");
			furniture.add("Furniture Store Item 4");
			
			List<String> health = new ArrayList<String>();
			health.add("Custom Item");
			health.add("Health Store Item 1");
			health.add("Health Store Item 2");
			health.add("Health Store Item 3");
			health.add("Health Store Item 4");
			
			List<String> liquor = new ArrayList<String>();
			liquor.add("Custom Item");
			liquor.add("Liquor Store Item 1");
			liquor.add("Liquor Store Item 2");
			liquor.add("Liquor Store Item 3");
			liquor.add("Liquor Store Item 4");
			
			List<String> shoe = new ArrayList<String>();
			shoe.add("Shoe Store Item 1");
			shoe.add("Shoe Store Item 2");
			shoe.add("Shoe Store Item 3");
			shoe.add("Shoe Store Item 4");

			List<String> shoppingmall = new ArrayList<String>();
			shoppingmall.add("Custom Item");
			shoppingmall.add("Shopping Mall Item 1");
			shoppingmall.add("Shopping Mall Item 2");
			shoppingmall.add("Shopping Mall Item 3");
			shoppingmall.add("Shopping Mall Item 4");
			
			List<String> veterinary = new ArrayList<String>();
			veterinary.add("Custom Item");
			veterinary.add("Veterinary  Item 1");
			veterinary.add("Veterinary  Item 2");
			veterinary.add("Veterinary  Item 3");
			veterinary.add("Veterinary  Item 4");
			
			List<String> florist = new ArrayList<String>();
			florist.add("Custom Item");
			florist.add("Florist  Item 1");
			florist.add("Florist  Item 1");
			florist.add("Florist  Item 1");
			florist.add("Florist  Item 1");

			
			listCategoryChild.put(listCategoryHeader.get(0), groceries); // Header, Child data
			listCategoryChild.put(listCategoryHeader.get(1), clothing);
			listCategoryChild.put(listCategoryHeader.get(2), electronics);
			listCategoryChild.put(listCategoryHeader.get(3), convenience);
			listCategoryChild.put(listCategoryHeader.get(4), department);
			listCategoryChild.put(listCategoryHeader.get(5), furniture);
			listCategoryChild.put(listCategoryHeader.get(6), health);
			listCategoryChild.put(listCategoryHeader.get(7), liquor);
			listCategoryChild.put(listCategoryHeader.get(8), shoe);
			listCategoryChild.put(listCategoryHeader.get(9), shoppingmall);
			listCategoryChild.put(listCategoryHeader.get(10), veterinary);
			listCategoryChild.put(listCategoryHeader.get(11), florist);

			
		}

	}

	

}
