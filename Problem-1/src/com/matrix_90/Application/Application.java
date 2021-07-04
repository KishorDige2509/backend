package com.matrix_90.Application;

import java.util.Scanner;

public class Application {

	public static void main(String[] args) {

		final String ROTATE = "ROTATE";
		String rotate = "";
		
		Scanner in = new Scanner(System.in);

		System.out.println("Enter Value of \"N\" for a (NxN) Matrix:");
		int userInput = in.nextInt();

		System.out.println("Value of N:" + userInput);
		int[][] matrix = new int[userInput][userInput];
		int[][] tempMatrix = new int[userInput][userInput];

		for (int row = 0; row < matrix.length; row++) {
			for (int col = 0; col < matrix[row].length; col++) {
				System.out.println("Enter value of element:");
				matrix[row][col] = in.nextInt();
			}
		}

		System.out.println("Matrix is :");
		for (int row = 0; row < matrix.length; row++) {
			for (int col = 0; col < matrix[row].length; col++) {
				tempMatrix[row][col] = matrix[row][col];
				System.out.print(matrix[row][col] + " ");
			}
			System.out.println();
		}

		do {
			System.out.println("Enter \"ROTATE\" to rotate the matrix by 90 degree or \"EXIT\" to exit:");
			Scanner rotateInput = new Scanner(System.in);

			rotate = rotateInput.nextLine();

			System.out.println("Your choice: " + rotate);

			// Rotating Logic
			if (rotate.equals(ROTATE)) {
				System.out.println("...rotating the matrix by 90 degree");
				System.out.println("Rotated matrix is:");
				for (int row = 0; row < matrix.length; row++) {

					int matrixlen = matrix.length;

					for (int col = 0; col < matrix[row].length; col++) {
						tempMatrix[row][col] = matrix[matrixlen - 1][row];
						System.out.print(tempMatrix[row][col] + " ");
						matrixlen--;
					}

					System.out.println();

				}

				for (int row = 0; row < matrix.length; row++) {
					for (int col = 0; col < matrix[row].length; col++) {
						matrix[row][col] = tempMatrix[row][col];

					}

				}

			}

		} while (rotate.equals(ROTATE));

		System.out.println("************* End ****************");

	}

}
