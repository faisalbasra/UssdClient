package com.roottony.ussdtest.adapters;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import android.app.Activity;
import android.view.View;
import android.widget.SectionIndexer;
import android.widget.TextView;

import com.roottony.ussdtest.ContactItem;
import com.roottony.ussdtest.R;
import com.roottony.ussdtest.helpers.RuEnStringComparator;

public class SectionedCottactAdapter extends ContactsAdapter implements SectionIndexer{
	
	private HashMap<String, Integer> alphabeticalMap = new HashMap<String, Integer>();
	private HashMap<Integer, String> reverseAlphabeticalMap = new HashMap<Integer, String>();
	private int alphabeticalIndexOffset = 0;
	
	private boolean hasSectionHeaders = true;
	
	private String[] sections;
	
	static class IDNameSectionedViewHolder extends ViewHolder{
		public TextView separator;
	}
	
	public SectionedCottactAdapter(Activity context, List<ContactItem> contacts) {
		super(context, contacts);
		
		this.alphabeticalIndexOffset = 0;
		this.hasSectionHeaders = true;
		
		calculateAlphabeticalIndexes();
	}
	
	private void calculateAlphabeticalIndexes() {
		int size = contactList.size();
		for (int i = size - 1; i >= alphabeticalIndexOffset; i--) {
			String element = contactList.get(i).getName();
			
			String letter = element.substring(0, 1);
			Integer startPosition = i + alphabeticalIndexOffset;
			alphabeticalMap.put(letter, startPosition);
		}
		
		if (hasSectionHeaders) {
			for (Map.Entry<String, Integer> entry : alphabeticalMap.entrySet()) {
				reverseAlphabeticalMap.put(entry.getValue(), entry.getKey());
			}
		}
		
		Set<String> keys = alphabeticalMap.keySet();
		
		Iterator<String> it = keys.iterator();
        ArrayList<String> keyList = new ArrayList<String>();

        while (it.hasNext()) {
                String key = it.next();
                keyList.add(key);
        }

        Collections.sort(keyList, new RuEnStringComparator());
        
		sections = new String[keyList.size()]; 
		keyList.toArray(sections);
	}

	@Override
	public int getPositionForSection(int section) {
		String letter = sections[section];
        return alphabeticalMap.get(letter) - alphabeticalIndexOffset;
	}

	@Override
	public int getSectionForPosition(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Object[] getSections() {
		return sections;
	}
	
	ViewHolder getInitializedViewHolder(View rowView) {
		IDNameSectionedViewHolder viewHolder = new IDNameSectionedViewHolder();
		viewHolder.contactName = (TextView) rowView.findViewById(R.id.name_entry);
		viewHolder.contactPhone = (TextView) rowView.findViewById(R.id.number_entry);
		if (hasSectionHeaders) {
			viewHolder.separator = (TextView) rowView.findViewById(R.id.header);
		}
		return viewHolder;
	}
	
	ViewHolder fillViewWithData(View rowView, int position) {
		if (hasSectionHeaders) {
			IDNameSectionedViewHolder holder 
					= (IDNameSectionedViewHolder) super.fillViewWithData(rowView, position);
			
			if (reverseAlphabeticalMap.containsKey(position + alphabeticalIndexOffset)) {
				holder.separator.setVisibility(View.VISIBLE);
				holder.separator.setText(reverseAlphabeticalMap.get(position + alphabeticalIndexOffset));
			} else {
				holder.separator.setVisibility(View.GONE);
			}
			return holder;
		} else {
			return super.fillViewWithData(rowView, position);
		}
	}
	
}
