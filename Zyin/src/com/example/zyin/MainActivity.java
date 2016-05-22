package com.example.zyin;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.NavigationView.OnNavigationItemSelectedListener;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends ActionBarActivity {

	private DrawerLayout mDrawerLayout;
	private ActionBarDrawerToggle mDrawerToggle;
	private NavigationView menuleft;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// 设置ToolBar
		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		// Title
		toolbar.setTitle("");
		// App Logo
		 toolbar.setLogo(R.drawable.logo);
		// Sub Title
//		toolbar.setSubtitle("Sub title");

		if (toolbar != null) {
			setSupportActionBar(toolbar);
		}

		// Navigation Icon 要設定在 setSupoortActionBar 才有作用
		// 否則會出現 back button
		toolbar.setNavigationIcon(R.drawable.perm_group_system_tools);

		// 打開 up button
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer);
		// 實作 drawer toggle 並放入 toolbar
		mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.drawer_open,
				R.string.drawer_close);
		mDrawerToggle.syncState();
		mDrawerLayout.setDrawerListener(mDrawerToggle);
		menuleft = (NavigationView) findViewById(R.id.left_drawer);
		menuleft.setNavigationItemSelectedListener(new OnNavigationItemSelectedListener() {
			
			@Override
			public boolean onNavigationItemSelected(MenuItem item) {
				switch (item.getItemId()) {
				case R.id.dayin:
					Fragment comtentFragment = new leftFragment();
					FragmentManager fm = getFragmentManager();
					fm.beginTransaction()
							.replace(R.id.draw_content, comtentFragment).commit();
					mDrawerLayout.closeDrawer(menuleft);
					item.setChecked(true);
					return true;
					

				default:
					break;
				}
				return false; 
			}
		});

		
		
		Intent intent = getIntent();
		int choise  = intent.getIntExtra("Choise", 0);
		switch (choise) {
		case 0:
			break;
		case 1:
			Fragment DaYinFra = new leftFragment();
			Bundle bundle = new Bundle();
			bundle.putBoolean("addwenjian", true);
			DaYinFra.setArguments(bundle);
			FragmentManager fm = getFragmentManager();
			fm.beginTransaction()
					.replace(R.id.draw_content, DaYinFra).commit();
			mDrawerLayout.closeDrawer(menuleft);
			break;
		default:
			break;
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
