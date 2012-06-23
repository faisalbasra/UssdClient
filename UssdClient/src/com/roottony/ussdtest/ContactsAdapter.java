package com.roottony.ussdtest;

import java.util.List;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ContactsAdapter extends BaseAdapter {
	
	private List<ContactItem> contactList;
	
	private Activity context;
	
	private static class ViewHolder {
		TextView contactName;
		TextView contactPhone;
	}
	
	public ContactsAdapter(Activity context, List<ContactItem> contacts) {
		this.context = context;
		this.contactList = contacts;
	}

	@Override
	public int getCount() {
		return contactList.size();
	}

	@Override
	public Object getItem(int position) {
		return contactList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View rowView = convertView;
		if (rowView == null) {
			LayoutInflater inflater = context.getLayoutInflater();
			rowView = inflater.inflate(R.layout.list_entry, null);
			ViewHolder viewHolder = new ViewHolder();
			viewHolder.contactName = (TextView) rowView.findViewById(R.id.name_entry);
			viewHolder.contactPhone = (TextView) rowView.findViewById(R.id.number_entry);
			rowView.setTag(viewHolder);
		}

		ViewHolder holder = (ViewHolder) rowView.getTag();
		ContactItem contact = contactList.get(position);
		holder.contactName.setText(contact.getName());
		
		
		holder.contactPhone.setText(getPhones(contact));

		return rowView;
	}

	private CharSequence getPhones(ContactItem contact) {
		StringBuilder builder = new StringBuilder();
		for (String phone: contact.getPhones()) {
			builder.append(formatPhone(phone)).append(", ");
		}
		
		builder.delete(builder.lastIndexOf(", "), builder.length());
		return builder.toString();
	}

	private StringBuilder formatPhone(String phone) {
		StringBuilder builder = new StringBuilder(phone).reverse();
		if (builder.length() > 7) {
			builder.insert(7, " ");
		}
		return builder.reverse();
	}
}
