package com.mphasis;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Solution2 {

	/*
	 * Write a program to count the occurance of each word in java using using
	 * HashMap.
	 * 
	 * I/O - hello world hello O/O - hello - 2 world - 1
	 */
	public static void main(String[] args) {
		
		String s = "hello world hello";
		
		String[] sArry = s.split(" ");
		
		Map<String, Integer> charCount = new HashMap<>();
//		for(int i=0; i<sArry.length; i++) {
//			String key = sArry[i];			
//			if(charCount.containsKey(key)) {
//				Integer count = charCount.get(key);
//				charCount.put(key, ++count);
//			} else {
//				charCount.put(key, 1);
//			}
//		}
		
		for(int i=0; i<sArry.length; i++) {
			String key = sArry[i];
			charCount.computeIfAbsent(key, k -> 0);
			charCount.compute(key, (k, v) -> v + 1);
		}
		
		System.out.println("Char count:" + charCount.toString());
				

	}
	
	// TODO java 8, Threads, thread scheduler

}
