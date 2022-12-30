package com.astrika.solutions;

import java.util.ArrayList;
import java.util.List;

public class RoundRobinDistributor {

	public static void main(String[] args) {
		// Create a list of elements to be distributed
		List<Integer> elements = new ArrayList<>();
		for (int i = 1; i <= 10; i++) {
			elements.add(i);
		}

		// Create a list of people to distribute the elements to
		List<String> people = new ArrayList<>();
		people.add("Alice");
		people.add("Bob");
		people.add("Charlie");
		people.add("Dave");
		people.add("Krishna");

		// fetch last Index of distribution for this set of people
		int lastDistributionIndex = 4;

		// Distribute the elements to the people in a round-robin fashion
		List<List<Integer>> distributions = roundRobinDistribute(elements, people, lastDistributionIndex);

		// Print the distributions
		for (int i = 0; i < distributions.size(); i++) {
			System.out.println(people.get(i) + ": " + distributions.get(i));
		}
	}

	public static <T> List<List<T>> roundRobinDistribute(List<T> elements, List<String> people,
			int lastDistributionIndex) {

		// Create a list of lists to hold the distributions for each person
		List<List<T>> distributions = new ArrayList<>();
		for (int i = 0; i < people.size(); i++) {
			distributions.add(new ArrayList<>());
		}

		// Distribute the elements in a round-robin fashion
		for (int i = 0; i < elements.size(); i++) {
			int personIndex = (lastDistributionIndex + i) % people.size();
			distributions.get(personIndex).add(elements.get(i));
		}

		return distributions;
	}

}
