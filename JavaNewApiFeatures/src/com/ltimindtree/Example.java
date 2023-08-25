package com.ltimindtree;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Example {

	/*
	 * input: ("India-INR","USA-USD","Austria-EURO")
	 * 
	 * output: ("India","INR"),
	 * 
	 * ("USA","USD"),
	 * 
	 * ("Austria","EURO"
	 */
	public static void main(String[] args) {

		String regEx = "-";
		List<String> countryCurr = Arrays.asList("India-INR", "USA-USD", "Austia-EURO", "country");

		List<List<String>> collect = countryCurr.stream().map(s -> s.split(regEx)).map(Arrays::asList).collect(Collectors.toList());

		System.out.println("collection:" + collect);

	}

}
