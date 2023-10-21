package com.infosys;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Solution {
	
	public static void main(String[] args) {
		List<String> names = Arrays.asList("Raj", "Amit", "Lucky");
		List<String> sorted = names.stream().sorted((n1, n2) -> n2.compareTo(n1)).collect(Collectors.toList());
//		Collections.reverse(sorted);
		
		System.out.println("List:" + sorted);
		
		
		
	}

}
