package com.accelya;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Solution {

	/*
	 * "evil" = "vile" "a gentleman" = "elegant man"
	 */
	public static void main(String[] args) {
		String word1 = "a gentleman";
		String word2 = "elegantt man";
		System.out.println("Is Anagram:" + checkIfAnagram(word1, word2));

	}

	private static boolean checkIfAnagram(String word1, String word2) {
		boolean test = false;
		if(word1 == null || word2 == null) {
			return test;
		}
		
		if(word1.length() != word2.length()) {
			return test;
		}
		
		List<String> word1Li = Arrays.asList(word1.split(""));
		List<String> word2Li = Arrays.asList(word2.split(""));
		
		if(!Collections.disjoint(word1Li, word2Li)) {
			test = true;
		}
		
		return test;
	}

}
