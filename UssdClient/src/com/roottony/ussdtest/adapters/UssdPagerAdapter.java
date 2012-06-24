package com.roottony.ussdtest.adapters;

import com.roottony.ussdtest.ContactListFragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class UssdPagerAdapter extends FragmentPagerAdapter {
	
	private int mLength = 0;
	
	public UssdPagerAdapter(FragmentManager fm, int length) {
		super(fm);
		mLength = length;
	}
	
	@Override
	public int getCount() {
		return mLength;
	}
	
	@Override
    public Fragment getItem(int position) {
    	switch (position) {
		case 0:
			return new ContactListFragment();
		case 1:
			return new ContactListFragment();
		case 2:
			return new ContactListFragment();
		default:
			break;
		}
		return null;
	}
	
}
