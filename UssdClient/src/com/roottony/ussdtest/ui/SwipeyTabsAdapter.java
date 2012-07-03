package com.roottony.ussdtest.ui;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;

import com.roottony.ussdtest.R;

public class SwipeyTabsAdapter implements TabsAdapter {
	
	private Activity mContext;
	
	private String[] tabTitles;
	
	public SwipeyTabsAdapter(Activity ctx) {
		this.mContext = ctx;
		
		tabTitles = mContext.getResources().getStringArray(R.array.tab_names);
	}
	
	@Override
	public View getView(int position) {
		SwipeyTabButton tab;
		
		LayoutInflater inflater = mContext.getLayoutInflater();
		tab = (SwipeyTabButton) inflater.inflate(R.layout.tab_swipey, null);
		
		if (position < tabTitles.length) tab.setText(tabTitles[position]);
		
		return tab;
	}
	
}
