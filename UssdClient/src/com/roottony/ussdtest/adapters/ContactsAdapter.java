package com.roottony.ussdtest.adapters;

import java.util.List;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.roottony.ussdtest.ContactItem;
import com.roottony.ussdtest.R;
import com.roottony.ussdtest.helpers.Carrier;

public class ContactsAdapter extends BaseAdapter {
	
	List<ContactItem> contactList;
	
	private Activity context;
	
	static class ViewHolder {
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
			rowView = createNewView();
		}
		
		fillViewWithData(rowView, position);
		
		return rowView;
	}

	ViewHolder fillViewWithData(View rowView, int position) {
		ViewHolder holder = (ViewHolder) rowView.getTag();
		
		ContactItem contact = contactList.get(position);
		holder.contactName.setText(contact.getName());
		holder.contactPhone.setText(getPhones(contact));
		
		return holder;
	}

	private View createNewView() {
		LayoutInflater inflater = context.getLayoutInflater();
		View rowView = getRowLayout(inflater);
		
		ViewHolder viewHolder = getInitializedViewHolder(rowView);
		
		rowView.setTag(viewHolder);
		
		return rowView;
	}

	ViewHolder getInitializedViewHolder(View rowView) {
		ViewHolder viewHolder = new ViewHolder();
		viewHolder.contactName = (TextView) rowView.findViewById(R.id.name_entry);
		viewHolder.contactPhone = (TextView) rowView.findViewById(R.id.number_entry);
		return viewHolder;
	}

	private View getRowLayout(LayoutInflater inflater) {
		return inflater.inflate(R.layout.list_entry, null);
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
		if (builder.length() > Carrier.RAW_NUMBER_LENGTH) {
			builder.insert(Carrier.RAW_NUMBER_LENGTH, " ");
		}
		return builder.reverse();
	}
}
