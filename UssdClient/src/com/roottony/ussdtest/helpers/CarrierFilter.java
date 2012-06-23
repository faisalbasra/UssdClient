package com.roottony.ussdtest.helpers;

import static com.roottony.ussdtest.helpers.Carrier.NUMBER_WITH_CODE_LENGTH;
import static com.roottony.ussdtest.helpers.Carrier.OPERATOR_CODE_LENGTH;
import static com.roottony.ussdtest.helpers.Carrier.RAW_NUMBER_LENGTH;

import java.util.ArrayList;
import java.util.List;

import com.roottony.ussdtest.ContactItem;

import android.util.Log;


public class CarrierFilter {
	
	public List<ContactItem> filterCarrierContacts(List<ContactItem> contacts, Carrier carrier) {
		
		final List<ContactItem> carrierContacts = new ArrayList<ContactItem>();
		
		for (ContactItem item: contacts) {
			List<String> carrierPhonesOfContact = new ArrayList<String>();
			
			for (String phone: item.getPhones()) {
				if (isCarrierNumber(phone, carrier)) {
					carrierPhonesOfContact.add(phone);
				}
			}
			
			if (!carrierPhonesOfContact.isEmpty()) {
				carrierContacts.add(new ContactItem(item.getID(), 
													item.getName(), 
													carrierPhonesOfContact));
			}
		}
		
		return carrierContacts;
	}
	
	private boolean isCarrierNumber(String number, Carrier carrier) {
		Log.v("Number", "" + number);
		
		if (number.length() == RAW_NUMBER_LENGTH) {
			return isRawNumberCarrierNumber(number, carrier);
		}
		
		String operatorCode = number
			.substring(number.length() - NUMBER_WITH_CODE_LENGTH , number.length())
			.substring(0, OPERATOR_CODE_LENGTH);
		
		for (String carrierPhoneCode : carrier.getPhoneCodes()) {
			if (operatorCode.equals(carrierPhoneCode)) {
				return true;
			}
		}
		return false;
	}

	private boolean isRawNumberCarrierNumber(String number, Carrier carrier) {
		// TODO: how to determine whether 7-digit number belongs to the given carrier ?
		return false;
	}
}
