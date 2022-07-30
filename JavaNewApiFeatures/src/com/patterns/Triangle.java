package com.patterns;

public class Triangle {
	public static void main(String[] args) {
		solidRightAngledTriangle();
		System.out.println();
		hollowRightAngledTriangle();
		System.out.println();
		invertedRightAngledTriangle();
		System.out.println();
		invertedHollowRightAngledTriangle();
	}

	private static void invertedHollowRightAngledTriangle() {
		for(int i=1; i<=5; i++) {
			for(int j=5; j>=1; j--) {
				if(j==5 || i==1 || i==j) {
					System.out.print("*");
				} else {
					System.out.print(" ");
				}
			}
			System.out.println();
		}
	}

	private static void hollowRightAngledTriangle() {
		for (int i = 1; i <= 5; i++) {
			for (int j = 1; j <= 5; j++) {
				if (j == 1 || i == j || i == 5) {
					System.out.print("*");
				} else {
					System.out.print(" ");
				}
			}
			System.out.println();
		}

	}

	public static void solidRightAngledTriangle() {
		for (int i = 1; i <= 5; i++) {
			for (int j = 1; j <= 5; j++) {
				if (j <= i) {
					System.out.print("*");
				}
			}
			System.out.println();
		}
	}

	public static void invertedRightAngledTriangle() {
		for (int i = 1; i <= 5; i++) {
			for (int j = 1; j <= 5; j++) {
				if(i<=j) {
					System.out.print("*");
				}
			}
			System.out.println();
		}
	}

}
