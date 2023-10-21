package com.wissen;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CamelToSnakeImproved {

	public static void main(String[] args) {
		System.out.println(camelToSnakeCase("whatIsYourName"));
	}

	public static String camelToSnakeCase(String text) {
		if (text.isEmpty()) {
			return "";
		}
		if (text.length() == 1) {
			return text.toUpperCase();
		}

		Pattern upperCasePattern = Pattern.compile("[A-Z]");

		// Upper Case pattern matcher
		Matcher matcher = upperCasePattern.matcher(text);

		// Add underscore before Upper case characters
		String result = matcher.replaceAll("_$0"); // Use $0 to reference the matched pattern

		return result.toUpperCase();
	}
}
