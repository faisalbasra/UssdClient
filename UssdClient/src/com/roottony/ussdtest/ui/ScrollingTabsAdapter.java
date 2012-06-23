package com.roottony.ussdtest.ui;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import com.astuetz.viewpager.extensions.TabsAdapter;
import com.roottony.ussdtest.R;

public class ScrollingTabsAdapter implements TabsAdapter {
	
	private Activity mContext;
	
	private String[] tabTitles;
	
	public ScrollingTabsAdapter(Activity ctx) {
		this.mContext = ctx;
		
		tabTitles = mContext.getResources().getStringArray(R.array.tab_names);	
	}
	
	@Override
	public View getView(int position) {
		Button tab;
		
		LayoutInflater inflater = mContext.getLayoutInflater();
		tab = (Button) inflater.inflate(R.layout.tab_scrolling, null);
		
		if (position < tabTitles.length) tab.setText(tabTitles[position]);
		
		return tab;
	}
	
}
