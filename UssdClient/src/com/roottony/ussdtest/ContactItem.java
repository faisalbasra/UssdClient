package com.roottony.ussdtest;

import java.util.List;

import lombok.Getter;
import lombok.ToString;
import android.graphics.drawable.Drawable;

@ToString
public class ContactItem {
	@Getter private final String name;
	@Getter private final String ID;
	@Getter private final Drawable picture;
	@Getter private final List<String> phones;
	
	public ContactItem(String ID, String name, Drawable picture, List<String> phones) {
		this.ID = ID;
		this.name = name;
		this.phones = phones;
		
		this.picture = picture;
	}
}
