package com.cognizant;

public class Example {

	public static void main(String[] args) {
		int[] arr = { 1, 2, 0, 5, 6, 10, 9 };

		int secondMaxNum = getSecondMaxNum(arr);

		System.out.println("Second Max number" + secondMaxNum);

	}

	private static int getSecondMaxNum(int[] arr) {
		int secondMaxNum = 0;
		int len = arr.length;
		int max = 0;
		for (int i = 0; i < len; i++) {
			int num = arr[i];
			if (num > max) {
				secondMaxNum = max;
				max = num;
			} else if(num>secondMaxNum && num<max) {
				secondMaxNum = num;
			}
				
		}
		return secondMaxNum;
	}

}
