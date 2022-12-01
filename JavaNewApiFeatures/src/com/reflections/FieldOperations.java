package com.reflections;

import java.lang.reflect.Field;
import java.util.stream.Stream;

public class FieldOperations {

	static class Inner {
		String name;
		String mobile;
	}

	static Field[] fields;
	static {
		fields = Inner.class.getDeclaredFields();
	}

	public static void main(String[] args) {

		System.out.println("printing");
		Stream.of(fields).forEach(f -> {
			f.setAccessible(true);
			System.out.println("Name:" + f.getName());
		});

	}
}
