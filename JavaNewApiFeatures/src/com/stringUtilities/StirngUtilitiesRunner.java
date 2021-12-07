package com.stringUtilities;

public class StirngUtilitiesRunner {
	public static void main(String[] args) {
		System.out.println(" LR ".isBlank());
		String testString = " LR ";
		System.out.println(testString.stripLeading().replace(" ", "@"));
		System.out.println(testString.stripTrailing().replace(" ", "@"));
		
		"Line1\nLine2\nLine3\nLine4".lines().forEach(System.out::println);
		System.out.println("UPPER".transform(s->s.substring(2)));
		System.out.println("My name is %s. My age is %d. dob is %s".formatted("Kishor", 29, "02/09/1992"));
	}

}
