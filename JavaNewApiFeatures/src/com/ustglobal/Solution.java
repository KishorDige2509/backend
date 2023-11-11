package com.ustglobal;

public class Solution {

	public static void main(String[] args) {
		String s = "cccnnnaaacccnnnaaawwwqqq";
		int len = s.length();
		if (len > 0) {
			char currChar = s.charAt(0);
			int count = 1;

			// start loop from next character i=1
			for (int i = 1; i < len; i++) {
				if (s.charAt(i) == currChar) {
					count++;
				} else {
					System.out.println("The count of character " + currChar + " is " + count);
					currChar = s.charAt(i);
					count = 1;
				}
			}
			System.out.println("The count of character " + currChar + " is " + count);
		} else {
			System.out.println("The string is empty.");
		}

	}

}
