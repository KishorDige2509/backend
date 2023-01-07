package interview;

import java.util.Scanner;

public class Fibonacci {
	static long[] fibCache;

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int nthFib;

		do {
			System.out.println("Enter the 'n'th fibonaaci number to find or '0' to exit: \n\t");
			nthFib = in.nextInt();
			fibCache = new long[nthFib + 1];
			System.out.println("Fibonaaci is: " + fibonaaci(nthFib) + "\n");
		} while (nthFib > 0);
	}

	private static long fibonaaci(int n) {
		if (n <= 1) {
			return n;
		}

		if (fibCache[n] != 0) {
			return fibCache[n];
		}

		long nthFibonaaciNumber = fibonaaci(n - 1) + fibonaaci(n - 2);
		fibCache[n] = nthFibonaaciNumber;

		return nthFibonaaciNumber;
	}

}
