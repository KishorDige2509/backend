package com.typeInferences;

import java.util.List;
import java.util.stream.Stream;

public class TypeInferencesRunner {
	public static void main(String[] args) {
		List<String> names1 = List.of("Rahul", "Rangs");
		List<String> names2 = List.of("Ravi", "Ranga");
		
		// var makes our life easier
		List<List<String>> namesList = List.of(names1, names2);
		var names = List.of(names1, names2);
		
		System.out.println(names);
		
		names.stream().forEach(System.out::println);
		
		System.out.println();
		
		for (var name : names) {
			System.out.println(name);
		}
		
		for(var i=0; i<10; i++)
			if(i==0)
			System.out.println("done...");
			else {
				System.out.println("breaking");
				break;
			}
		
		Stream<String> filter = List.of("Rhaul","kishor","boss","rja").stream().filter(s->s.length()>3);
		filter.forEach(System.out::println);
			
		var filter1 = List.of("Rhaul","kishor","boss","rja").stream().filter(s->s.length()>3);
		filter1.forEach(System.out::println);
	}
}
