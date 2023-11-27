package com.trinesis;

import java.util.ArrayList;
import java.util.List;

public class Solution {
	public static void main(String[] args) {

	}

	public int[] distributeCandies(int candies, int kids) {

		List<Integer> distributions = new ArrayList<>(kids);
		int dist;
		while (candies != 0) {
			dist = 1;
			for (int i = 1; i < kids; i++) {
				dist = dist + i;
				distributions.set(i-1, distributions.get(i - 1) + dist);
			}
		}

		return new int[0];
	}
	
	/*
	 * public int[] distributeCandies(int candies, int kids) {

    List<Integer> distributions = new ArrayList<>(kids);
    int dist = 1;

    for (int i = 0; i < kids; i++) {
        distributions.add(0);
    }

    while (candies > 0) {
        for (int i = 0; i < kids && candies > 0; i++) {
            distributions.set(i, distributions.get(i) + dist);
            candies -= dist;
            dist++;
        }
    }

    return distributions.stream().mapToInt(Integer::intValue).toArray();
}

	 */

}
