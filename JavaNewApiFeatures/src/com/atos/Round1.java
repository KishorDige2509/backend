package com.atos;

import java.util.HashSet;
import java.util.Set;

public class Round1 {

	public static void main(String[] args) {
		Integer[] arr = { 1, 2, 2, 3, 3, 4, 4, 5, 6, 8, 7 };
		Set<Integer> dupFreeInts = new HashSet<>();
		for (Integer i : arr) {
			dupFreeInts.add(i);
		}
		System.out.println("Set:" + dupFreeInts.toString());
	}

}
