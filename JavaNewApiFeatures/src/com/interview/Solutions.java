package com.interview;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Solutions {

	// Transform to Map<Integer, String>

	public static void main(String[] args) {
		
		System.out.println("Program Arguments: "+ args[0] +" arg2: "+ args[1]);

		List<Integer> list = Arrays.asList(1, 2, 3, 4);

		Map<Integer, String> collectMap = list.stream()
				.collect(Collectors.toMap(Function.identity(), e -> e.toString()));

		Map<Integer, String> transformMap = new HashMap<>();

		list.stream().forEach(i -> {
			transformMap.put(i, i + "");
		});

		for (Entry<Integer, String> entry : transformMap.entrySet()) {
			System.out.println(entry.getKey() + ":" + '"' + entry.getValue() + '"');
		}

		System.out.println("=======================================");

		for (Entry<Integer, String> entry : collectMap.entrySet()) {
			System.out.println(entry.getKey() + ":" + '"' + entry.getValue() + '"');
		}

		System.out.println("=======================================");
		// Today is Friday and date is 12th August

		String str = "Today is Friday and date is 12th August.";

		Map<Character, Integer> countMap = new HashMap<>();

		for (int i = 0; i < str.length(); i++) {
			if (countMap.containsKey(str.charAt(i))) {
				countMap.put(str.charAt(i), countMap.get(str.charAt(i)) + 1);
			} else {
				countMap.put(str.charAt(i), 1);
			}
		}

		System.out.println("Char content: " + countMap.toString());
		System.out.println("=======================================");

		Map<Character, Integer> countMapImp = new HashMap<>();
		for (int i = 0; i < str.length(); i++) {
			countMapImp.computeIfPresent(str.charAt(i), (k, v) -> v + 1);
			countMapImp.putIfAbsent(str.charAt(i), 1);
		}
		System.out.println("Char content Improved: " + countMap.toString());
		System.out.println("=======================================");

		System.out.println("Char count using stream: " + Stream.of(str.split(""))
				.collect(Collectors.groupingBy(Function.identity(), Collectors.counting())).toString());
		System.out.println("=======================================");

		System.out.println("Char Max occurr count using stream: "
				+ Stream.of(str.split("")).collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
						.entrySet().parallelStream().max(Entry.comparingByValue()).toString());
		System.out.println("=======================================");

		System.out.println("Char Min occurr count using stream: "
				+ Stream.of(str.split("")).collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
						.entrySet().parallelStream().min(Entry.comparingByValue()).toString());
		System.out.println("=======================================");

		// TODO
		System.out.println("Char Sorted Asc using stream: " + Stream.of(str.split(""))
				.collect(Collectors.groupingBy(Function.identity(), Collectors.counting())).entrySet().stream()
				.collect(Collectors.toMap(Entry::getKey, Entry::getValue, (e1, e2) -> e2, HashMap::new)).toString());
		System.out.println("=======================================");

		System.out.println("Using to Map: " + Stream.of(str.split(""))
				.collect(Collectors.toMap(Function.identity(), String::getBytes, (e1, e2) -> e1, HashMap::new)));

	}
}
