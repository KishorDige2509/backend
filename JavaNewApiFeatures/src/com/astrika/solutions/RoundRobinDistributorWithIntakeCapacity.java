package com.astrika.solutions;

import java.util.ArrayList;
import java.util.List;

public class RoundRobinDistributorWithIntakeCapacity {

	public static void main(String[] args) {
		// Create a list of elements to be distributed
		List<Integer> elements = new ArrayList<>();
		for (int i = 1; i <= 10; i++) {
			elements.add(i);
		}

		// Create a list of people to distribute the elements to, each with a maximum
		// capacity
		List<Person> people = new ArrayList<>();
		people.add(new Person("Alice", 3));
		people.add(new Person("Bob", 2));
		people.add(new Person("Charlie", 4));
		people.add(new Person("Dave", 5));

		// Distribute the elements to the people in a round-robin fashion
		List<List<Integer>> distributions = roundRobinDistribute(elements, people);

		// Print the distributions
		for (int i = 0; i < distributions.size(); i++) {
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

	public static <T> List<List<T>> roundRobinDistribute(List<T> elements, List<Person> people) {
		// Create a list of lists to hold the distributions for each person
		List<List<T>> distributions = new ArrayList<>();
		for (int i = 0; i < people.size(); i++) {
			distributions.add(new ArrayList<>());
		}

		// Distribute the elements in a round-robin fashion, taking into account the
		// capacity of each person
		int personIndex = 0;
		for (int i = 0; i < elements.size(); i++) {
			Person person = people.get(personIndex);
			if (person.capacity > distributions.get(personIndex).size()) {
				distributions.get(personIndex).add(elements.get(i));
			} else {
				personIndex = (personIndex + 1) % people.size();
				i--; // Decrement the index to try again with the next person
			}
		}

		return distributions;
	}
}
