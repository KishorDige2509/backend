package com.mphasis;

import java.util.Arrays;
import java.util.stream.Stream;

public class Solution {

	/*
	 * ip = {1,2,3,4,5,6,7,8} r=3 out={4,5,6,7,8,1,2,3}
	 */
	public static void main(String[] args) {
		Integer[] arr = { 1, 2, 3, 4, 5, 6, 7, 8 };
		int r = 3;
		System.out.println("out=" + Arrays.toString(rotateArray(arr, r)));

		printEvenMultipleBy5(arr);
	}

	private static int[] rotateArray(Integer[] arr, int r) {
		int len = arr.length;
		return Arrays.stream(arr).map(i -> arr[(i+r)%len]).mapToInt(Integer::intValue).toArray();
	}

	/*
	 * ip = 1,2,3,4,5,6,7,8 out =10,20,30,40
	 * 
	 */

	private static void printEvenMultipleBy5(Integer[] arr) {
//		for (int i = 0; i < arr.length; i++) {
//			int num = arr[i];
//			if (num % 2 == 0) {
//				System.out.println("Num " + i + num * 5);
//			}
//		}

		Stream.of(arr).forEach(i -> {
			if (i % 2 == 0) {
				System.out.println("Num " + i * 5);
			}
		});
	}

}