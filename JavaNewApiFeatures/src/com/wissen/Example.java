package com.wissen;

public class Example {
	
	public static void main(String[] args) {
		long[] arr = {2,2,-1,4,6,-3};
		
		int len = arr.length;
		int bestRoute = getBestRoute(arr, len);
		System.out.println("Best Route:" + bestRoute);
	}
	
	public static int getBestRoute(long[] arr, int n) {
		for(int i=0; i<n; i++) {
			long sumBefore = getSumBefore(arr, i-1, n);
			long sumAfter = getSumAfter(arr, i+1, n);
			if(sumBefore == sumAfter) {
				return i+1;
			}
		}
		return -1;
	}

	private static long getSumAfter(long[] arr, int from, int n) {
		long sum = 0;
		for(int i=from; i<n; i++) {
			sum = sum + arr[i];
		}
		return sum;
	}

	private static long getSumBefore(long[] arr, int till, int n) {
		long sum = 0;
		for(int i=0; i<=till; i++) {
			sum = sum + arr[i];
		}
		return sum;
	}

}
