package com.mockTests.Classess;

import com.mockTests.Repository.Predicate;

public class Check<T> implements Predicate<T> {

	@Override
	public <T> boolean test(T t) {
		if(t.getClass().getName().equalsIgnoreCase("java.lang.String")) {
//			if(java.lang.String.t == )
		}
		return false;
	}

}
