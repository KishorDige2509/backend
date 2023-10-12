package com.dsa;

public class Sum1toN {
	
	public static void main(String[] args) {
		int i = 0;
		int n = 100;
		int sum = 0;
		while(i<=n) {
			sum = sum + i;
			i++;
		}
		
		System.out.println("Sum:" + sum);
	}

}
