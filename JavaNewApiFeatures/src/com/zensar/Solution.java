package com.zensar;

import java.util.HashMap;
import java.util.Map;

public class Solution {
	
	public static void main(String[] args) {
		String toCheck = "my Name Is Kishor Kishor";
		
		String[] split = toCheck.split(" ");
		
		Map<String, Integer> occurances = new HashMap<>();
		Integer maxLen = 0;
		for(int i=0; i<split.length; i++) {
			String s = split[i];
			int length = s.length();
			if(length>=maxLen) {
				maxLen = length;
				if(occurances.containsKey(s)) {
					Integer count = occurances.get(s);
					occurances.put(s, ++count);
				} else {
					occurances.put(s, 1);
				}
			}
			
		}
		
		System.out.println("Occurances:" +  occurances);
		System.out.println("Length:" +  maxLen);
		
	}

}
