package com.predicateNot;

import java.util.List;
import java.util.function.Predicate;

public class PredicateNotRunner {
	public static void main(String[] args) {
		List<Integer> numbersIntegers = List.of(1,2,3,4,5,6,7,8);
//		numbersIntegers.stream().filter(num -> num%2==0).forEach(System.out::println);
		
		Predicate<Integer> evenNumberPredicate = number -> number%2==0;		
//		numbersIntegers.stream().filter(evenNumberPredicate).forEach(System.out::println);
//		numbersIntegers.stream().filter(evenNumberPredicate.negate()).forEach(System.out::println);
		numbersIntegers.stream().filter(Predicate.not(evenNumberPredicate)).forEach(System.out::println);
		
//		numbersIntegers.stream().filter(PredicateNotRunner::isEven).forEach(System.out::println);
//		numbersIntegers.stream().filter(Predicate.not(PredicateNotRunner::isEven)).forEach(System.out::println);
	}
	
	public static boolean isEven(Integer number) {
		return number%2==0;
	}
}
