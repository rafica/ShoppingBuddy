package com.buddy;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class MainActivity extends Activity {
	private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;

    private CharSequence mDrawerTitle;
    private CharSequence mTitle;
    CustomDrawerAdapter adapter;

    List<DrawerItem> dataList;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		dataList = new ArrayList<DrawerItem>();
        mTitle = mDrawerTitle = getTitle();
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);

        //mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow,GravityCompat.START);
	
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
              SelectItem(0);
        }

        
	}
	public void sendMessage(View view){
		Intent intent=new Intent(this,AddItemToListActivity.class);
		startActivity(intent);	
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	
	public void SelectItem(int possition) {
		 
        Fragment fragment = null;
        Bundle args = new Bundle();
        switch (possition) {
        case 0:
              fragment = new FragmentOne();
              args.putString(FragmentOne.ITEM_NAME, dataList.get(possition)
                          .getItemName());
              break;
        case 1:
              fragment = new FragmentTwo();
              args.putString(FragmentTwo.ITEM_NAME, dataList.get(possition)
                          .getItemName());
              
              break;
        case 2:
              fragment = new FragmentThree();
              args.putString(FragmentThree.ITEM_NAME, dataList.get(possition)
                          .getItemName());
              
              break;
        case 3:
              fragment = new FragmentOne();
              args.putString(FragmentOne.ITEM_NAME, dataList.get(possition)
                          .getItemName());
             
              break;
        case 4:
              fragment = new FragmentTwo();
              args.putString(FragmentTwo.ITEM_NAME, dataList.get(possition)
                          .getItemName());
             
              break;
        case 5:
              fragment = new FragmentThree();
              args.putString(FragmentThree.ITEM_NAME, dataList.get(possition)
                          .getItemName());
              
              break;
        case 6:
              fragment = new FragmentOne();
              args.putString(FragmentOne.ITEM_NAME, dataList.get(possition)
                          .getItemName());
              
              break;
       
        default:
              break;
        }

        fragment.setArguments(args);
        FragmentManager frgManager = getFragmentManager();
        frgManager.beginTransaction().replace(R.id.content_frame, fragment)
                    .commit();

        mDrawerList.setItemChecked(possition, true);
        setTitle(dataList.get(possition).getItemName());
        mDrawerLayout.closeDrawer(mDrawerList);

  }
	
	@Override
    public void setTitle(CharSequence title) {
          mTitle = title;
          getActionBar().setTitle(mTitle);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
          super.onPostCreate(savedInstanceState);
          // Sync the toggle state after onRestoreInstanceState has occurred.
          mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
          super.onConfigurationChanged(newConfig);
          // Pass any configuration change to the drawer toggles
          mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
          // The action bar home/up action should open or close the drawer.
          // ActionBarDrawerToggle will take care of this.
          if (mDrawerToggle.onOptionsItemSelected(item)) {
                return true;
          }

          return false;
    }

      private class DrawerItemClickListener implements
                ListView.OnItemClickListener {
          @Override
          public void onItemClick(AdapterView<?> parent, View view, int position,
                      long id) {
                SelectItem(position);

          }
    }
}