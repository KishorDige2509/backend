package com.dsa;

import java.util.Arrays;
import java.util.Random;

public class QuickSort {

	public static void main(String[] args) {

		int len = 10;
		Random rand = new Random();
		int[] numbers = new int[len];

		for (int i = 0; i < len; i++) {
			numbers[i] = rand.nextInt(100);
		}

		System.out.println("Before:");
		printArray1(numbers);
		
		System.out.println("Cal length:" + numbers.length);

		quickSort(numbers);

		System.out.println("After:");
		printArray1(numbers);
	}

	private static void quickSort(int[] numbers) {
		Random rn = new Random();
		quickSort(numbers, 0, numbers.length - 1, rn);
	}

	private static void quickSort(int[] arr, int lowIndex, int highIndex, Random random) {

		// exit condition
		if (lowIndex >= highIndex) {
			return;
		}
		
		int pivotIndex = random.nextInt(highIndex - lowIndex) + lowIndex;

		// get pivot
		int pivot = arr[pivotIndex];
		
		swap(arr, pivotIndex, highIndex);

		// do partition
		int leftPointer = partition(arr, lowIndex, highIndex, pivot);
		
		// recursively sort left sub array
		quickSort(arr, lowIndex, leftPointer - 1, random);
		// recursively sort right sub array
		quickSort(arr, leftPointer + 1, highIndex, random);

	}

	private static int partition(int[] arr, int lowIndex, int highIndex, int pivot) {
		int leftPointer = lowIndex;
		int rightPointer = highIndex;

		while (leftPointer < rightPointer) {
			// find
			while (arr[leftPointer] <= pivot && leftPointer < rightPointer) {
				leftPointer++;
			}
			while (arr[rightPointer] >= pivot && leftPointer < rightPointer) {
				rightPointer--;
			}
			swap(arr, leftPointer, rightPointer);
		}
		swap(arr, leftPointer, highIndex);
		return leftPointer;
	}

	private static void swap(int[] arr, int leftPointer, int rightPointer) {
		int temp = arr[leftPointer];
		arr[leftPointer] = arr[rightPointer];
		arr[rightPointer] = temp;
	}

	private static void printArray1(int[] numbers) {
		System.out.println("==============================");
		Arrays.stream(numbers).forEach(System.out::println);
		System.out.println();
		System.out.println("==============================");
	}

	private static void printArray2(int[] numbers) {
		System.out.println("==============================");
		Arrays.stream(numbers).forEach(i -> System.out.printf("%d ", i));
		System.out.println();
		System.out.println("==============================");
	}

	private static void printArray3(int[] numbers) {
		System.out.println("==============================");
//		Arrays.stream(numbers).mapToObj(String::valueOf).flatMap(str -> Arrays.stream(str.split(""))).collect(Collectors.joining(" "));
		Arrays.stream(numbers).mapToObj(String::valueOf).flatMap(str -> Arrays.stream(str.split("")))
				.forEach(d -> System.out.print(d + " "));
		System.out.println();
		System.out.println("==============================");
	}
}
