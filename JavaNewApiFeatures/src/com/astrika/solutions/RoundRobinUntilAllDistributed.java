package com.astrika.solutions;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RoundRobinUntilAllDistributed {

	public static void main(String[] args) {
		// Create a list of elements to be distributed more optimized way would be to
		// use queue and use remove on it

		List<Integer> elements = IntStream.rangeClosed(1, 217).boxed().collect(Collectors.toList());

		int elementsSize = elements.size();

		System.out.println("Element size: " + elementsSize);

		// Create a list of people to distribute the elements to, each with a maximum
		// capacity
		// TODO set the capacity based on elements size in percentage
		List<Person> people = new ArrayList<>();
		people.add(new Person("Alice", getCapacityByPercentage(0.3f, elementsSize)));
		people.add(new Person("Bob", getCapacityByPercentage(0.2f, elementsSize)));
		people.add(new Person("Charlie", getCapacityByPercentage(0.2f, elementsSize)));
		people.add(new Person("Dave", getCapacityByPercentage(0.3f, elementsSize)));

		System.out.println("People: " + people.get(0).capacity);
		System.out.println("People: " + people.get(1).capacity);
		System.out.println("People: " + people.get(2).capacity);
		System.out.println("People: " + people.get(3).capacity);

		// fetch last Index of distribution for this set of people
		int lastDistributionIndex = 2;

		// Distribute the elements to the people in a round-robin fashion, distributing
		// all pending elements over the capacity once the capacity of each person is
		// reached
		List<List<Integer>> distributions = distributeElements(elements, people, lastDistributionIndex);

		// Print the distributions
		for (int i = 0; i < distributions.size(); i++) {
			System.out.println(people.get(i).name + ": " + distributions.get(i).size());
			System.out.println(people.get(i).name + ": " + distributions.get(i));
		}
	}

	public static class Person {
		public String name;
		public int capacity;

		public Person(String name, int capacity) {
			this.name = name;
			this.capacity = capacity;
		}
	}

	public static <T> List<List<T>> distributeElements(List<T> elements, List<Person> people,
			int lastDistributionIndex) {

		// Create a list of lists to hold the distributions for each person
		List<List<T>> distributions = new ArrayList<>();
		for (int i = 0; i < people.size(); i++) {
			distributions.add(new LinkedList<>());
		}

		System.out.println("People size: " + people.size());
		System.out.println("last distribution index: " + lastDistributionIndex);

		// Distribute the elements in a round-robin fashion, until the capacity of each
		// person is reached
		int personIndex;
		// check last distribution index and set max value if greater than array size
		// and zero in case of negative
		if (lastDistributionIndex >= people.size()) {
			personIndex = people.size() - 1;
		} else if (lastDistributionIndex < 0) {
			personIndex = 0;
		} else {
			personIndex = lastDistributionIndex;
		}
		System.out.println("Start index of person: " + personIndex);
		int peopleSize = people.size();
		Set<Integer> skippedPersons = new HashSet<>();
		while (!elements.isEmpty() && skippedPersons.size() < peopleSize) {
			if (skippedPersons.contains(personIndex)) {
				personIndex = (personIndex + 1) % peopleSize;
			}
			Person person = people.get(personIndex);
			if (person.capacity > distributions.get(personIndex).size()) {
				distributions.get(personIndex).add(elements.remove(0));
			} else {
				// TODO instead of adding skipped person to new list remove the person from
				// people array this will reduce people size and there by bounding indexes and
				// improve performance
				skippedPersons.add(personIndex);
			}
			personIndex = (personIndex + 1) % peopleSize;
		}
		// commented due to not distributing all as after capacity we use below logic
		// Distribute the remaining elements in a round-robin fashion over the capacity
//		while (!elements.isEmpty() && threshold<overallCapacity) {
//			Person person = people.get(personIndex);
//			System.out.println("Adding leads for : " + person.name + " with container Index:" + personIndex);
//			if (person.capacity > distributions.get(personIndex).size()) {
//				distributions.get(personIndex).add(elements.remove(0));
//			} else {
//				threshold++;
//				System.out.println("Capacity Reached for Person: " + person.name);
//			}
//			personIndex = (personIndex + 1) % people.size();	
//		}

		// Distribute the remaining elements in a round-robin fashion over the capacity
//		while (!elements.isEmpty()) {
//			for (int i = 0; i < people.size(); i++) {
//				if (!elements.isEmpty()) {
//					distributions.get(i).add(elements.remove(0));
//				}
//			}
//		}

		// Distribute the remaining elements in a round-robin fashion over the capacity
		System.out.println("Index before: " + personIndex);
		personIndex++;
		for (int i = 0; i < elements.size(); i++) {
			personIndex = (personIndex + i) % peopleSize;
			distributions.get(personIndex).add(elements.get(i));
			System.out.println("Index after: " + personIndex);
		}

		return distributions;
	}

	public static int getCapacityByPercentage(float proportion, int elementsSize) {
		return Math.round(proportion * elementsSize);
	}
}
