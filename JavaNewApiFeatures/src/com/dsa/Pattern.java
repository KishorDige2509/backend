package com.dsa;

public class Pattern {

	public static void main(String[] args) {
		printSquare(4);
		printTriangle(5);
	}

	private static void printTriangle(int i) {
		printLine();

		int row = 1;
		int count = 1;
		while (row <= i) {
			int col = 1;
			while (col <= row) {
				System.out.print(count);
				count++;
				col++;
			}
			printLine();
			row++;
		}
		printLine();
	}

	private static void printSquare(int n) {
		printLine();
		System.out.println("Square will be printed of:" + n + "X" + n);
		if (n <= 0) {
			System.out.println("");
		}
		int i = n;
		while (i >= 0) {
			int j = n;
			while (j >= 0) {
				System.out.print(j);
				j--;
			}
			printLine();
			i--;
		}
		printLine();
	}

	private static void printLine() {
		System.out.println();
	}

}
