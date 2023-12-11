package com.capgemini;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

public class Solution2 {

	/*
	 * [2:38 PM] Pandey, Ruchi Given a String, find the first non-repeated character
	 * in it using Stream functions?
	 * 
	 * "my name is Kishor"
	 */

	String s = "my name is Kishor";

	public static void main(String[] args) {
		String input = "programming";

		Optional<Character> firstNonRepeating = findFirstNonRepeatingCharacter(input);

		if (firstNonRepeating.isPresent()) {
			System.out.println("First Non-Repeating Character: " + firstNonRepeating.get());
		} else {
			System.out.println("No non-repeating characters found.");
		}
	}

	public static Optional<Character> findFirstNonRepeatingCharacter(String input) {
		Map<Character, Long> charCountMap = input.chars().mapToObj(c -> (char) c).collect(LinkedHashMap::new,
				(map, c) -> map.merge(c, 1L, Long::sum), LinkedHashMap::putAll);

		return charCountMap.entrySet().stream().filter(entry -> entry.getValue() == 1).map(Map.Entry::getKey)
				.findFirst();
	}

}
