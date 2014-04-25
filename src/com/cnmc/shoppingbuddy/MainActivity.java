package com.cnmc.shoppingbuddy;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.shoppingbuddy.R;

public class MainActivity extends FragmentActivity {
	private DrawerLayout mDrawerLayout;
	private ListView mDrawerList;
	private ActionBarDrawerToggle mDrawerToggle;

	private CharSequence mDrawerTitle;
	private CharSequence mTitle;
	private CustomDrawerAdapter adapter;
	private List<DrawerItem> dataList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.first_activity);

		dataList = new ArrayList<DrawerItem>();
		mTitle = mDrawerTitle = getTitle();
		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		mDrawerList = (ListView) findViewById(R.id.left_drawer);

		// mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow,GravityCompat.START);

		dataList.add(new DrawerItem("Home"));
		dataList.add(new DrawerItem("MyList"));
		dataList.add(new DrawerItem("Preferred Locations"));
		dataList.add(new DrawerItem("Maps"));
		dataList.add(new DrawerItem("Settings"));
		dataList.add(new DrawerItem("Feedback"));
		adapter = new CustomDrawerAdapter(this, R.layout.custom_drawer_item,
				dataList);

		mDrawerList.setAdapter(adapter);
		mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setHomeButtonEnabled(true);

		mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
				R.drawable.ic_drawer, R.string.drawer_open,
				R.string.drawer_close) {
			public void onDrawerClosed(View view) {
				getActionBar().setTitle(mTitle);
				invalidateOptionsMenu(); // creates call to
											// onPrepareOptionsMenu()
			}

			public void onDrawerOpened(View drawerView) {
				getActionBar().setTitle(mDrawerTitle);
				invalidateOptionsMenu(); // creates call to
											// onPrepareOptionsMenu()
			}
		};

		mDrawerLayout.setDrawerListener(mDrawerToggle);

		if (savedInstanceState == null) {
			selectItem(0);
		}
		
		android.support.v4.app.FragmentManager myFragmentManager = getSupportFragmentManager();
        FragmentTransaction tx = myFragmentManager.beginTransaction();
        tx.replace(R.id.content_frame,Fragment.instantiate(MainActivity.this, "com.cnmc.shoppingbuddy.UserHomeFragment"));
        tx.commit();

		/*FragmentManager fragmentManager = getSupportFragmentManager();
		FragmentTransaction tx = fragmentManager.beginTransaction();
		tx.replace(R.id.content_frame, Fragment.instantiate(MainActivity.this,
				"com.cnmc.shoppingbuddy.UserHomeFragment"),
				"USER_HOME_PAGE_FRAGMENT");
		// tx.replace(R.id.content_frame,
		// Fragment.instantiate(MainActivity.this,
		// "com.cnmc.shoppingbuddy.AddedListDisplayFragment"),"ADDED_LIST_FRAGMENT");
		tx.commit();*/

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.user_action, menu);
		return true;
	}

	// Code for Navigation Drawer
	public void sendMessage(View view) {
		//Intent intent = new Intent(this, AddItemToListActivity.class);
		//startActivity(intent);
	}

	public void selectItem(int possition) {

		Fragment fragment = null;
		Bundle args = new Bundle();
		switch (possition) {
		case 0:
			fragment = new UserHomeFragment();
			args.putString(UserHomeFragment.TAG, dataList.get(possition)
					.getItemName());
			break;
		case 1:
			fragment = new AddedListDisplayFragment();
			args.putString(AddedListDisplayFragment.TAG, dataList.get(possition)
					.getItemName());

			break;
		case 2:
			fragment = new FragmentTwo();
			args.putString(FragmentTwo.ITEM_NAME, dataList.get(possition)
					.getItemName());

			break;
		case 3:
			fragment = new FragmentTwo();
			args.putString(FragmentTwo.ITEM_NAME, dataList.get(possition)
					.getItemName());

			break;
		case 4:
			fragment = new FragmentTwo();
			args.putString(FragmentTwo.ITEM_NAME, dataList.get(possition)
					.getItemName());

			break;
		case 5:
			fragment = new FragmentTwo();
			args.putString(FragmentTwo.ITEM_NAME, dataList.get(possition)
					.getItemName());

			break;
		case 6:
			fragment = new FragmentTwo();
			args.putString(FragmentTwo.ITEM_NAME, dataList.get(possition)
					.getItemName());

			break;

		default:
			break;
		}

		fragment.setArguments(args);
		FragmentManager frgManager = getSupportFragmentManager();
		frgManager.beginTransaction().replace(R.id.content_frame, fragment)
				.commit();

		mDrawerList.setItemChecked(possition, true);
		setTitle(dataList.get(possition).getItemName());
		mDrawerLayout.closeDrawer(mDrawerList);

	}

	private class DrawerItemClickListener implements
			ListView.OnItemClickListener {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			selectItem(position);

		}
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		
	        // Pass the event to ActionBarDrawerToggle, if it returns
	        // true, then it has handled the app icon touch event
	        if (mDrawerToggle.onOptionsItemSelected(item)) {
	          return true;
	        }
	        // Handle your other action bar items...

	        return super.onOptionsItemSelected(item);
	    
	}
}
