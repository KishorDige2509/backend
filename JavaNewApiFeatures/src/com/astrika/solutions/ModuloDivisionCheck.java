package com.astrika.solutions;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

public class ModuloDivisionCheck {
	
	private static final Long MAX_ALLOWED_PERCENTAGE_LOAD = 100L;
	
	public static void main(String[] args) {
		List<Long> sMs = LongStream.rangeClosed(1, 10).boxed().collect(Collectors.toList());
		
		int nextIndex = (-1+1)%sMs.size();
		
		System.out.println(nextIndex);
		
		int four = 4;
		
		int four1 = 4;
		
		System.out.println("List:" + sMs);
		
		if(four<four1) {
			System.out.println("True");
		}
		
		
		Long lowestPercentageLoad = MAX_ALLOWED_PERCENTAGE_LOAD;
		
		for (Long domain : sMs) {
			
			Long percentageLoad = domain;

				// Find lowest percentage load
				if (percentageLoad < lowestPercentageLoad) {
					lowestPercentageLoad = percentageLoad;
				}
				
				final Long num = percentageLoad;
				System.out.println("Num" + num);
			
		}
		System.out.println("Lowest per load: " + lowestPercentageLoad);
	}

}
