package com.astrika.solutions;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class RoundRobinUntilAllDistributed {

	public static void main(String[] args) {
		// Create a list of elements to be distributed
		List<Integer> elements = new LinkedList<>();
		for (int i = 1; i <= 2000; i++) {
			elements.add(i);
		}

		// Create a list of people to distribute the elements to, each with a maximum
		// capacity
		List<Person> people = new ArrayList<>();
		people.add(new Person("Alice", 3));
		people.add(new Person("Bob", 2));
		people.add(new Person("Charlie", 4));
		people.add(new Person("Dave", 5));
		
		// fetch last Index of distribution for this set of people
		int lastDistributionIndex = -1;


		// Distribute the elements to the people in a round-robin fashion, distributing
		// all pending elements over the capacity once the capacity of each person is
		// reached
		List<List<Integer>> distributions = distributeElements(elements, people, lastDistributionIndex);

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

	public static <T> List<List<T>> distributeElements(List<T> elements, List<Person> people, int lastDistributionIndex) {
				
		// Create a list of lists to hold the distributions for each person
		List<List<T>> distributions = new ArrayList<>();
		for (int i = 0; i < people.size(); i++) {
			distributions.add(new ArrayList<T>());
		}
		
		System.out.println("People size: " + people.size());
		System.out.println("last distribution index: " + lastDistributionIndex);

		// Distribute the elements in a round-robin fashion, until the capacity of each
		// person is reached
		int personIndex;
		// check last distribution index and set max value if greater than array size and zero in case of negative
		if(lastDistributionIndex>=people.size()) {
			personIndex = people.size()-1;
		} else if(lastDistributionIndex<0) {
			personIndex = 0;
		} else {
			personIndex = lastDistributionIndex;
		}
		System.out.println("Start index of person: " + personIndex);
		int threshold = 0;
		int overallCapacity = people.size();
		while (!elements.isEmpty() && threshold<overallCapacity) {
			Person person = people.get(personIndex);
			System.out.println("Adding leads for : " + person.name + " with container Index:" + personIndex);
			if (person.capacity > distributions.get(personIndex).size()) {
				distributions.get(personIndex).add(elements.remove(0));
			} else {
				threshold++;
				System.out.println("Capacity Reached for Person: " + person.name);
			}
			personIndex = (personIndex + 1) % people.size();	
		}

		// Distribute the remaining elements in a round-robin fashion over the capacity
		while (!elements.isEmpty()) {
			for (int i = 0; i < people.size(); i++) {
				if (!elements.isEmpty()) {
					distributions.get(i).add(elements.remove(0));
				}
			}
		}

		return distributions;
	}
}
