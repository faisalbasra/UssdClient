package com.roottony.ussdtest.helpers;

import java.util.Arrays;
import java.util.List;

import lombok.Getter;

public enum Carrier {
	VELCOM(new String[]{"296", "293", "291", "299", "447"});
	
	public static final int RAW_NUMBER_LENGTH = 7;
	public static final int OPERATOR_CODE_LENGTH = 3;
	
	public static final int NUMBER_WITH_CODE_LENGTH 
									= RAW_NUMBER_LENGTH + OPERATOR_CODE_LENGTH - 1;

	@Getter private List<String> phoneCodes;
	
	private Carrier(String[] phoneCodes) {
		this.phoneCodes = Arrays.asList(phoneCodes);
	}
}
