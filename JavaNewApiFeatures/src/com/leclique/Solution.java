package com.leclique;

import java.beans.DesignMode;
import java.util.HashSet;
import java.util.Set;

public class Solution {

	/*
	 * transfer data from old phone to new one new one having few contents already
	 * 
	 */
	public static void main(String[] args) {

		Set<Contact> contacts1 = new HashSet<>();
		Set<Contact> contacts2 = new HashSet<>();

		contacts1.add(new Contact("Kishor0", "123"));
		contacts1.add(new Contact("Kishor1", "124"));
		contacts1.add(new Contact("Kishor2", "125"));
		contacts1.add(new Contact("Kishor3", "126"));
		contacts1.add(new Contact("Kishor4", "127"));
		contacts1.add(new Contact("Kishor5", "128"));
		Phone phone1 = new Phone(contacts1);

		contacts2.add(new Contact("Kishor0", "123"));
		contacts2.add(new Contact("Kishor1", "125"));
		contacts2.add(new Contact("Kishor2", "125"));
		contacts2.add(new Contact("Kishor6", "126"));
		contacts2.add(new Contact("Kishor7", "127"));
		Phone phone2 = new Phone(contacts2);
		
		copyContacts(phone1, phone2);
		
		System.out.println("Contacts of phone2:" + phone2.getContent().toString());

	}

	/**
	 * 
	 * @param source
	 * @param destination
	 */
	private static void copyContacts(Phone source, Phone destination) {
		// handle edge cases
		if(source == null || destination == null) {
			System.out.println("Source or destination cannot be null");
		}
		
		destination.addContacts(source.getContent());
	}

}
