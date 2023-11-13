package com.wissen;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SplitArrayAndFlatternArray {
	public static void main(String[] args) throws Exception {

		int[] original = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
		int splitSize = 3;

		/*
		 * expected Output [0, 1, 2] [3, 4, 5] [6, 7, 8] [9]
		 */

		List<int[]> list = spList(original, splitSize);
		list.forEach(splitArray -> System.out.println(Arrays.toString(splitArray)));

		Object[] array = { 1, 2, new Object[] { 3, 4, new Object[] { 5 }, 6, 7 }, 8, 9, 10 };

		Integer[] flattenedArray = flatten(array);

		System.out.println(Arrays.toString(flattenedArray));
	}

	public static List<int[]> splitArray(int[] array, int chunkSize) {
		List<int[]> chunks = new ArrayList<>();
		int index = 0;

		while (index < array.length) {
			int chunkLength = Math.min(chunkSize, array.length - index);
			int[] chunk = new int[chunkLength];

			for (int i = 0; i < chunkLength; i++) {
				chunk[i] = array[index + i];
			}

			chunks.add(chunk);
			index += chunkSize;
		}

		return chunks;
	}
	
	public static List<int[]> spList(int[] array, int chunkSize){
		int len = array.length;
		List<int[]> subList = new ArrayList<>();
		for(int i=0; i<len; i++) {
			int calChunkSize = Math.min(chunkSize, len-i);
			int[] sub = new int[calChunkSize];
			for(int j=0; j<calChunkSize; j++) {
				if(i<len) {					
					sub[j] = array[i];
					i++;
				}
			}
			subList.add(sub);
			i--;
		}
		return subList;
	}
	

	public static Integer[] flatten(Object[] inputArray) {
		List<Integer> flatList = new ArrayList<>();
		flattenArray(inputArray, flatList);
		return flatList.toArray(new Integer[0]);
	}

	private static void flattenArray(Object[] inputArray, List<Integer> flatList) {
		for (Object obj : inputArray) {
			if (obj instanceof Integer) {
				flatList.add((Integer) obj);
			} else if (obj instanceof Object[]) {
				flattenArray((Object[]) obj, flatList);
			}
		}
	}

}
