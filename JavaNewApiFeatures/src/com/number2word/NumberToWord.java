package com.number2word;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import com.cadRole.CadRole;

public class NumberToWord {

	public static void main(String[] args) {

		System.out.println("Package: " + CadRole.class.getPackage().getName());

		Map<Integer, String> sigleDigitMap = new HashMap<>();

		sigleDigitMap.put(1, "one");
		sigleDigitMap.put(2, "two");
		sigleDigitMap.put(3, "three");
		sigleDigitMap.put(4, "four");
		sigleDigitMap.put(5, "five");
		sigleDigitMap.put(6, "six");
		sigleDigitMap.put(7, "seven");
		sigleDigitMap.put(8, "eight");
		sigleDigitMap.put(9, "nine");

		Map<Integer, String> doubleDigitMap = new HashMap<>();

		doubleDigitMap.put(5, "fifty");
		doubleDigitMap.put(6, "sixty");

		Map<Integer, String> aboveOrEqualToHundred = new HashMap<>();

		aboveOrEqualToHundred.put(100, "hundred");
		aboveOrEqualToHundred.put(1000, "thousand");
		aboveOrEqualToHundred.put(100000, "lakh");
		aboveOrEqualToHundred.put(10000000, "crore");

		// Deque should be used instead of stack as stack is synchronized and not efficient
		Stack<String> word = new Stack<>();

		String numString = "";
		Integer num = 5659;
		int loop = 5;
		Integer res = 0;
		while (loop > 1) {
			res = num % 10;
			if (loop == 5) {
				numString = sigleDigitMap.get(res);
			}

			if (loop == 4) {
				numString = "and " + doubleDigitMap.get(res);
			}

			if (loop == 3) {
				numString = sigleDigitMap.get(res) + " " + aboveOrEqualToHundred.get(100);
			}

			if (loop == 2) {
				numString = doubleDigitMap.get(res) + " " + aboveOrEqualToHundred.get(1000);
			}

			res = (num - res) / 10;
			num = res;
			loop--;
			word.addElement(numString);
		}
		System.out.println("Word stack: " + word);

		// reverse string
		StringBuilder finalWord = new StringBuilder();
		for (int i = 0; i < 4; i++) {
			finalWord.append(word.pop() + " ");
		}
		System.out.println("finalWord : " + finalWord);

	}

}
