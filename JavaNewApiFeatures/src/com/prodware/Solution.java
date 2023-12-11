package com.prodware;

public class Solution {
	
	public static void main(String[] args) {
		
//		printSwappedString();
		printSeries(7);
		
	}
	
	private static void printSeries(int n) {
		//ava Program to Print Series 1, 2, 9, 28, 65, 126, 217 --power of 3 +1
		
		if(n == 0) {
			System.out.println("invalid input");
		}
		
		System.err.print("" + 1);
		
		double num = 1;
		while (n>0) {
			double numToPrint = Math.pow(num, 3) + 1;
			System.out.print(", " + numToPrint);
			num++;
			n--;
		}
	}

	private static void printSwappedString() {
		/**
		 * swap two strings
		 * s1, s2
		 */
		
		String s1 = "Hello";
		String s2 = "World";
		String s3 = null;
		
		System.out.println("before s1=" + s1 + " s2=" + s2);
		
		s3 = s1;
		s1 = s2;
		s2 = s3;
		
		System.out.println("after s1=" + s1 + " s2=" + s2);
	}
}
