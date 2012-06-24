package com.roottony.ussdtest;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.roottony.ussdtest.adapters.ContactsAdapter;
import com.roottony.ussdtest.adapters.SectionedCottactAdapter;
import com.roottony.ussdtest.helpers.Carrier;
import com.roottony.ussdtest.helpers.ContactLoader;

public class ContactListFragment extends Fragment{
	
	String ENCODED_HESH = Uri.encode("#");
	
	String GET_BALANCE = "*100#";
	String GET_TRAFFIC = "*100*1#";
	
	List<ContactItem> contacts = new ArrayList<ContactItem>();
	
	private ContactsAdapter contactsAdapter;
	
	private ListView contactList;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		contactsAdapter = new SectionedCottactAdapter(getActivity(), 
				new ContactLoader().getContacts(getActivity(), Carrier.VELCOM));
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View fragmentView = inflater.inflate(R.layout.list_fragment_layout, container, false);
		createListView(fragmentView);
		return fragmentView;
	}

	private void createListView(View fragmentView) {
		contactList = (ListView) fragmentView.findViewById(R.id.list_contacts);
		contactList.setAdapter(contactsAdapter);
		contactList.setOnItemClickListener(new OnContactClickListener());
	}

	private class OnContactClickListener implements OnItemClickListener {

		@Override
		public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
			ContactItem selectedItem 
						= (ContactItem)parent.getAdapter().getItem(position);
			
			String number = selectedItem.getPhones().get(0);
			
			Uri request = null;
			
			if (number.equals(GET_BALANCE) || number.equals(GET_TRAFFIC)) {
				request = Uri.parse("tel:" + number.replace("#", ENCODED_HESH));
			} else {
				String rawNumber = number
					.substring(number.length() - Carrier.RAW_NUMBER_LENGTH, number.length());
				request = Uri.parse("tel:" + "*131*" + rawNumber + ENCODED_HESH);
			}
			
			Intent callIntent = new Intent(Intent.ACTION_CALL);
	        callIntent.setData(request);
	        startActivity(callIntent);
		}
	}
}
