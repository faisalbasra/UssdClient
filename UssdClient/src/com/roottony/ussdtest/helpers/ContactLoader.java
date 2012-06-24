package com.roottony.ussdtest.helpers;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.provider.ContactsContract;
import android.provider.ContactsContract.Contacts;

import com.roottony.ussdtest.ContactItem;

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

				InputStream inputStream = Contacts.openContactPhotoInputStream(
			            context.getContentResolver(),
			            ContentUris.withAppendedId(Contacts.CONTENT_URI, Long.parseLong(contactID)));
			    
				BitmapDrawable picture = null;
				if(inputStream != null) {
			    	Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
			    	picture = new BitmapDrawable(context.getResources(), bitmap);
			    }
				
				allContacts.add(
					new ContactItem(contactID, contactName, picture, phoneNumbers));
			}
		}

		cursor.close();
		return allContacts;
	}

	private void sortAlphabetically(Context context, List<ContactItem> contacts) {
		Collections.sort(contacts, new RuEnContactComparator());
	}



}
