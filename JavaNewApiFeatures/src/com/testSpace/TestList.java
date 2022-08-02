package com.testSpace;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestList {
	
	private static class CustomObj{
		public CustomObj(int i, String string) {
			this.id = i;
			this.name = string;
		}
		int id;
		String name;		
	}
	
	public static void main(String[] args) {
		List<CustomObj> customList = Arrays.asList(new CustomObj(1, "one"), new CustomObj(1, "two"), null);
		
		List<CustomObj> customRecList = new ArrayList<>();
		for(CustomObj obj: customList) {
			customRecList.add(obj);
		}
		
		for(CustomObj obj: customRecList) {
			if(obj == null) {
				System.out.println("found null");
			} else {
				System.out.println("found not null");
			}
		}
	}

}
