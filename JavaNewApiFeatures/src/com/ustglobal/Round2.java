package com.ustglobal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.wissen.Student;

public class Round2 {

	public static void main(String[] args) {

		// map with Stirng as key and number as value
		Map<String, Integer> map = new HashMap<>();
		map.put("abc", 20);
		map.put("pqr", 40);
		map.put("sds", 35);
		map.put("scdsf", 67);
		
//		String key = get3rdMaxKey(map);

//		if (key != null) {
//			System.out.println("Key=" + key + " value=" + map.get(key));
//		}
		
		List<Student> students = new ArrayList<>();
		
//		students.stream().filter(s -> s.getMarks()>40)
//		.sorted((s1, s2) -> {
//			int comp = s1.getFirstName().compareTo(s2.getFirstName());
//			if(comp==0) {
//				return s1.getFirstName().compareTo(s2.getLastName());				
//			} else {
//				return s1.getLastName().compareTo(s2.getLastName());
//			}
//			
//		}).collect(Collectors.toList());

	}
	
	private boolean checkAnagram(String s1, String s2) {
		// edge cases 
		
		List<String> st1 = Arrays.asList(s1.split(""));
		List<String> st2 = Arrays.asList(s2.split(""));
		
		return st1.containsAll(st2) && st2.containsAll(st1);
		
	}

	private static String get3rdMaxKey(Map<String, Integer> map, int n) {
		int maxValToFindAt = 3;
		int counter = 0;

		Integer max1 = 0;
		String key = null;
		for (Map.Entry<String, Integer> entry : map.entrySet()) {
			
		}
		return key;
	}
	

}
