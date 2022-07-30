package com.patterns;

public class Alphabets {
	public static void main(String[] args) {
		printN();
	}
	
	private static void printN() {
		for(int i=1; i<=5; i++) {
			for(int j=1; j<=5; j++) {
				if(j==1 || i==j || j==5) {
					System.out.print("*");
				} else {
					System.out.print(" ");
				}
			}
			System.out.println();
		}
		
	}
}
