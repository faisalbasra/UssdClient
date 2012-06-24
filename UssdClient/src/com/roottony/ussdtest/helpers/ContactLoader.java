package com.roottony.ussdtest.helpers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.roottony.ussdtest.ContactItem;

import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;

public class ContactLoader {

	public List<ContactItem> getContacts(Context context, Carrier carrier) {
		List<ContactItem> contacts = readContacts(context);
		sortAlphabetically(context, contacts);
		return new CarrierFilter().filterCarrierContacts(contacts, carrier);
	}

	private List<ContactItem> readContacts(Context context) {
		List<ContactItem> allContacts = new ArrayList<ContactItem>();

		Cursor cursor = context.getContentResolver().query(ContactsContract.Contacts.CONTENT_URI, null, null, null,
				null);

		while (cursor.moveToNext()) {
			String contactName = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
			String contactID = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
			String hasPhone = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER));

			if (Integer.parseInt(hasPhone) == 1) {
				List<String> phoneNumbers = new ArrayList<String>();

				// You know it has a number so now query it like this
				Cursor phones = context.getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
						null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = " + contactID, null, null);
				while (phones.moveToNext()) {
					String phoneNumber = phones.getString(phones
							.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
					phoneNumbers.add(phoneNumber);
				}
				phones.close();

				allContacts.add(new ContactItem(contactID, contactName, phoneNumbers));
			}
		}

		cursor.close();
		return allContacts;
	}

	private void sortAlphabetically(Context context, List<ContactItem> contacts) {
		Collections.sort(contacts, new RuEnContactComparator());
	}



}
