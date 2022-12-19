package com.annotations;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class AnnotationRunner {

	public static void main(String[] args)
			throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Cat myCat = new Cat("White Asian Fur");
		checkIfImportant(myCat);
		runImmediateMethods(myCat, "meow....!");
		printImportantFeature(myCat);
	}

	static void printImportantFeature(Animal animal) throws IllegalArgumentException, IllegalAccessException {
		for (Field field : animal.getClass().getDeclaredFields()) {
			if (field.isAnnotationPresent(ImportantFeature.class)) {
				System.out.println("fetching field value");
				field.setAccessible(true);
				Object object = field.get(animal);
				// New Impl
				if (object instanceof String stringValue) {
					System.out.println("New Iml::Feature: " + stringValue.toUpperCase());
				}

				// Old Impl
				if (object instanceof String) {
					String stringValueOf = String.valueOf(object);
//					object = null; // test condition
					String toString = object != null ? object.toString() : "null"; // this will throw null pointer
																					// exception if object is null hence
																					// not to use without null check
					System.out.println("Old Impl::Feature String ValueOf: " + stringValueOf.toUpperCase());
					System.out.println("Old Impl::Feature: toString " + toString.toUpperCase());
				}

			}
		}
	}

	static void runImmediateMethods(Animal animal, String sound)
			throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Method[] declaredMethods = animal.getClass().getDeclaredMethods();
		for (Method method : declaredMethods) {
			if (checkIfRunImmediateAnnotationIsPresent(method)) {
//				Parameter[] parameters = method.getParameters();
//				System.out.println("Method Parameters: " + parameters);

				RunImmediately annotation = method.getAnnotation(RunImmediately.class);
				for (int i = 1; i <= annotation.times(); i++) {
					method.invoke(animal, sound);
				}

			}
		}

	}

	private static boolean checkIfRunImmediateAnnotationIsPresent(Method method) {
		boolean runAnnotationPresent = false;
		String status = "Annotation not present";
		if (method.isAnnotationPresent(RunImmediately.class)) {
			status = "Annotation Present on method " + method.getName();
			runAnnotationPresent = true;
		}
		System.out.println(status);
		return runAnnotationPresent;
	}

	static void checkIfImportant(Animal animal) {
		String importance = "Not Important";
		if (animal.getClass().isAnnotationPresent(VeryImportant.class)) {
			importance = "It is important";
		}
		System.out.println(importance);

	}

}
