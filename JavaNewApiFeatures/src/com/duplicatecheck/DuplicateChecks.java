package com.duplicatecheck;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DuplicateChecks {

	public static void main(String[] args) {
		List<Integer> ids = Arrays.asList(1,2,1,2,3,4,1);
		Set<Integer> addedFieldIds = new HashSet<>();
		for(Integer id: ids) {
			
			if(addedFieldIds.contains(id)) {
				continue;
			}
			
			System.out.println("Value: " + id);
			
			addedFieldIds.add(id);
		}
		
	}
}
