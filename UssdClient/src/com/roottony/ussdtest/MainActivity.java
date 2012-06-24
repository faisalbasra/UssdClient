package com.roottony.ussdtest;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;

import com.astuetz.viewpager.extensions.ScrollingTabsView;
import com.astuetz.viewpager.extensions.TabsAdapter;
import com.roottony.ussdtest.adapters.UssdPagerAdapter;
import com.roottony.ussdtest.ui.ScrollingTabsAdapter;

public class MainActivity extends FragmentActivity{
	
	private static final int TABS_NUMBER = 3;
	
	ViewPager mPager;
	
	UssdPagerAdapter mAdapter;
	
	private ScrollingTabsView mScrollingTabs;
	private TabsAdapter mScrollingTabsAdapter;
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		mAdapter = new UssdPagerAdapter(getSupportFragmentManager(), TABS_NUMBER);
		
		mPager = initViewPager(mAdapter);
		
		mScrollingTabsAdapter = new ScrollingTabsAdapter(this);
		
		mScrollingTabs = (ScrollingTabsView) findViewById(R.id.scrolling_tabs);
		mScrollingTabs.setAdapter(mScrollingTabsAdapter);
		mScrollingTabs.setViewPager(mPager);
	}
	
	private ViewPager initViewPager(UssdPagerAdapter mAdapter) {
		
		ViewPager pager = (ViewPager) findViewById(R.id.pager);
		pager.setAdapter(mAdapter);
		pager.setCurrentItem(0);
		pager.setPageMargin(20);
		
		return pager;
	}
}