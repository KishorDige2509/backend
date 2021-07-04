package com.mockTests.Repository;

@FunctionalInterface
public interface Predicate<T> {
	public <T> boolean test(T t);
	
	
}
