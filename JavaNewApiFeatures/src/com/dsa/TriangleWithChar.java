package com.dsa;

import java.util.Scanner;

public class TriangleWithChar {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
//		printCharIncreamentedOnNextLine(sc);
//		printCharIncreamentedOnSameLine(sc);
//		printCharInTriangleIncreamentedOnNextLine(sc);
		printCharInTriangleDecrementedOnNextLine(sc);
	}

	private static void printCharInTriangleDecrementedOnNextLine(Scanner sc) {
		System.out.println("Enter no. of rows:");
		int n = sc.nextInt();
		int i = 1;
		
		while (i <= n) {
			int j = 1;
			char ch = (char) ('A' + n - i);
			char chp = ch;
			while (j <= i) {				
				System.out.print(chp);
				j++;
				chp++;
			}
			System.out.println();
			i++;
		}

	}

	private static void printCharInTriangleIncreamentedOnNextLine(Scanner sc) {
		System.out.println("Enter no. of rows:");
		int n = sc.nextInt();
		int i = 1;
		char ch = 'A';
		while (i <= n) {
			int j = 1;
			while (j <= i) {
				System.out.print(ch);
				j++;
			}
			System.out.println();
			i++;
			ch++;
		}
	}

	private static void printCharIncreamentedOnSameLine(Scanner sc) {
		System.out.println("Enter no. of rows:");
		int n = sc.nextInt();
		int i = 1;
		while (i <= n) {
			int j = 1;
			char ch = 'A';
			while (j <= n) {
				System.out.print(ch + " ");
				ch++;
				j++;
			}
			System.out.println();
			i++;
		}

	}

	private static void printCharIncreamentedOnNextLine(Scanner sc) {
		System.out.println("Enter no. of rows:");
		int n = sc.nextInt();
		int i = 1;
		char ch = 'A';
		while (i <= n) {
			int j = 1;
			while (j <= n) {
				System.out.print(ch);
				j++;
			}
			ch++;
			System.out.println();
			i++;
		}
	}

}
