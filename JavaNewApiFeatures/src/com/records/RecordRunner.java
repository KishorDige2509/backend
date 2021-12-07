package com.records;

public class RecordRunner {
	
	record Person(String userName, String password) {
		// no-arguments constructor cannot be defined for records
		
		// compact constructor
		public Person{}
	}
	
	public static void main(String[] args) {
		Person person = new Person("root","root");
		System.out.println(person);
		
		// added something
		
	}

}
