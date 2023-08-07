package com.dsa;

import java.util.Scanner;

public class Prime {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter number to check if prime:");
		int number = sc.nextInt();
		boolean prime = isPrime(number);
		System.out.println("Prime:" + prime);

	}

	private static boolean isPrime(int number) {
		int div = 2;

		if (number <= 1) {
			return false;
		}
		
		if(number <=3) {
			return true;
		}

		boolean prime = true;
		while (div < number) {
			int rem = number % div++;
			if (rem == 0) {
				return false;
			}
		}
		return prime;
	}

}
