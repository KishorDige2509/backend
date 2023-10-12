package com.wissen;

import java.util.Arrays;

public class Example2 {

	public static void main(String[] args) {
		int[] arr = { 1, 2, 3, 4, 5 ,6};
		rearrangeArray(arr);
		System.out.println("arr" + Arrays.toString(arr));
		
	}
	
	public static void rearrangeArray(int[] arr) {
        for (int i = 0; i < arr.length - 1; i = i + 2) {
            // Swap adjacent elements
            int temp = arr[i];
            arr[i] = arr[i + 1];
            arr[i + 1] = temp;
        }
    }

}
