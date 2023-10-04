package com.neosoft;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class NeoSoftSolution {

	public static void main(String[] args) {
		List<Integer> numbers = Arrays.asList(66, 77, 88, 66, 77, 88, 44, 33);
		System.out.println("Second Highest Number:" + getSecondHighestNum(numbers));
	}

	private static Integer getSecondHighestNum(List<Integer> numbers) {
		if (numbers == null || numbers.isEmpty()) {
			return -1;
		}
		Integer secMax = 0;
		Integer max = 0;
		for (Integer num : numbers) {
			if (num > max) {
				secMax = max;
				max = num;
			} else if (num > secMax && num<max) {
				secMax = num;
			}
		}
		return secMax;
	}
}
