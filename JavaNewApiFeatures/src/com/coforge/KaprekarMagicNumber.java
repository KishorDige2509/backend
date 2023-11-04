package com.coforge;

import java.util.Arrays;

public class KaprekarMagicNumber {
    public static void main(String[] args) {
        int number = 1234; // Change this to the number you want to use

        if (isValidInput(number)) {
            int steps = findKaprekarSteps(number);
            System.out.println("Kaprekar's Constant (6174) found in " + steps + " steps.");
        } else {
            System.out.println("Invalid input. Please provide a 4-digit number with at least two distinct digits.");
        }
    }

    // Check if the input is a valid 4-digit number with at least two distinct digits
    public static boolean isValidInput(int num) {
        if (num < 1000 || num > 9999)
            return false;

        char[] digits = Integer.toString(num).toCharArray();
        Arrays.sort(digits);

        return digits[0] != digits[3];
    }

    // Perform the Kaprekar routine to find 6174
    public static int findKaprekarSteps(int num) {
        int steps = 0;

        while (num != 6174) {
            int descending = sortDescending(num);
            int ascending = sortAscending(num);
            num = descending - ascending;
            steps++;
            System.out.println(descending + " - " + ascending + " = " + num);
        }

        return steps;
    }

    // Helper method to sort digits in descending order
    public static int sortDescending(int num) {
        char[] digits = Integer.toString(num).toCharArray();
        Arrays.sort(digits);
        String descending = new StringBuilder(new String(digits)).reverse().toString();
        return Integer.parseInt(descending);
    }

    // Helper method to sort digits in ascending order
    public static int sortAscending(int num) {
        char[] digits = Integer.toString(num).toCharArray();
        Arrays.sort(digits);
        return Integer.parseInt(new String(digits));
    }
}

// write a functional interface and provide a lambda implementation