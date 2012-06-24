package com.roottony.ussdtest.helpers;

import java.util.Comparator;

public class RuEnStringComparator implements Comparator<String> {
	@Override
	public int compare(String lhs, String rhs) {
		return lhs.compareTo(rhs);
	}
}