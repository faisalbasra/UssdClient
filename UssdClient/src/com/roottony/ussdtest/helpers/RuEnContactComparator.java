package com.roottony.ussdtest.helpers;

import java.util.Comparator;

import com.roottony.ussdtest.ContactItem;

public class RuEnContactComparator implements Comparator<ContactItem> {
	@Override
	public int compare(ContactItem lhs, ContactItem rhs) {
		String leftName = lhs.getName();
		String rightName = rhs.getName();
		
		return leftName.compareTo(rightName);
	}
}