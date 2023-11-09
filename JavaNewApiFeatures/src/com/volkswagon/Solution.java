package com.volkswagon;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Solution {
	public static void main(String[] args) {
		Integer[] arr = { 1, 2, 37, 3, 42, 12 };
		System.out.println("Unsorted array:" + arr);
		List<Integer> sorted = Stream.of(arr).sorted().collect(Collectors.toList());
		System.out.println("Sorted Array: " + sorted);
	}

}
