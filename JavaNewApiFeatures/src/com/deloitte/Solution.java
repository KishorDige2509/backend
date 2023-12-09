package com.deloitte;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Solution {
	
	public static void main(String[] args) {
		
		List<String> strs = new ArrayList<>();
		
		strs.add("Raj0");
		strs.add("Raj1");
		strs.add("Raj2");
		strs.add("Raj3");
		strs.add("Raj4");
		
		Optional<String> findFirstOpt = strs.stream().filter(s -> s.equals("Raj0")).findFirst();
		
		if(findFirstOpt.isPresent()) {
			System.out.println("Find:" + findFirstOpt.get());
		}
		
	}
	

}
