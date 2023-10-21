package com.dsa;

import java.util.Scanner;

public class ReverseTriagle {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
//		printReverseStar(sc);
		printInvertedStar(sc);
	}

	private static void printInvertedStar(Scanner sc) {
		System.out.println("Enter no. of rows:");
		int n = sc.nextInt();
		int i = 1;
		while (i<=n) {
			int j = 1;
			while (j<=n) {
				if(j>=i) {
					System.out.print("*");
				} else {
					System.out.print(" ");
				}
				j++;
			}
			System.out.println();
			i++;
		}

	}

	private static void printReverseStar(Scanner sc) {
		System.out.println("Enter no. of rows:");
		int n = sc.nextInt();
		int i = 1;
		while (i <= n) {
			int j = 1;
			int space = n-i;
			while (j <= n) {
				while(space>0) {
					System.out.print(" ");
					space--;
					j++;
				}
				System.out.print("*");
				j++;
			}
			System.out.println();
			i++;
		}
	}

}
