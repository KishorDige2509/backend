package com.leclique;

import java.util.HashSet;
import java.util.Set;

//@Data
public class Phone {

	private Set<Contact> content;

	public Phone(Set<Contact> content) {
		super();
		this.content = content;
	}

	public Set<Contact> getContent() {
		return content;
	}

	public void setContent(Set<Contact> content) {
		this.content = content;
	}
	
	public void addContacts(Set<Contact> contacts) {
		if(this.content == null) {
			content = new HashSet<>();
		}
		content.addAll(contacts);
	}

}
