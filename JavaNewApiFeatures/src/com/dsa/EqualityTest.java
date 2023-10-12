package com.dsa;

public class EqualityTest {

	public static void main(String[] args) {
		checkNum();
	}

	private static void checkNum() {
		int a = 2;
		int b = a + 1;
		if ((a = 3) == b) {
			System.out.println("a:" + a);

		} else {
			System.out.println("b:" + b);
		}
	}

}
