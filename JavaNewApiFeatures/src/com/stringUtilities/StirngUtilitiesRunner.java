package com.stringUtilities;

import java.util.ArrayList;
import java.util.List;

public class StirngUtilitiesRunner {
	public static void main(String[] args) {
		System.out.println(" LR ".isBlank());
		String testString = " LR ";
		System.out.println(testString.stripLeading().replace(" ", "@"));
		System.out.println(testString.stripTrailing().replace(" ", "@"));
		
		"Line1\nLine2\nLine3\nLine4".lines().forEach(System.out::println);
		System.out.println("UPPER".transform(s->s.substring(2)));
		System.out.println("My name is %s. My age is %d. dob is %s".formatted("Kishor", 29, "02/09/1992"));
		
		Object obj = null;
		
		System.out.println("Object to string: " + String.valueOf(obj));
		
		Object objLong = 1L;
		
		System.out.println("Object to string: " + String.valueOf(objLong));
		
		if("null".equals(String.valueOf(objLong))) {
			System.out.println("null is object");
		}
		
		List<String> strList = new ArrayList<>();
		
		strList.add("some1");
		strList.add("some2");
		
		String names = strList.toString();
		
		System.out.println("names: " + names);
		
	}

}
