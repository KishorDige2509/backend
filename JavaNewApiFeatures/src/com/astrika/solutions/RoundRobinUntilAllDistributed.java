package com.astrika.solutions;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.stream.IntStream;

public class RoundRobinUntilAllDistributed {

	public static void main(String[] args) {
		// Create a list of elements to be distributed more optimized way would be to
		// use queue and use remove on it

		Queue<Integer> branchLeads = new LinkedList<>();

//		IntStream.rangeClosed(1, 217).boxed().collect(Collectors.toList());
		IntStream.rangeClosed(1, 100).forEach(branchLeads::add);

//		elements.add(1);

		int elementsSize = branchLeads.size();

		System.out.println("Element size: " + elementsSize);

		// Create a list of people to distribute the elements to, each with a maximum
		// capacity
		// TODO set the capacity based on elements size in percentage
		List<Container> branches = new ArrayList<>();
		branches.add(new Container("Mumbai", getCapacityByPercentage(0.3f, elementsSize)));
		branches.add(new Container("Goa", getCapacityByPercentage(0.2f, elementsSize)));
		branches.add(new Container("Nagpur", getCapacityByPercentage(0.2f, elementsSize)));
		branches.add(new Container("Nashik", getCapacityByPercentage(0.3f, elementsSize)));

		System.out.println(branches.get(0).name + " Capacity:" + branches.get(0).capacity);
		System.out.println(branches.get(1).name + " Capacity:" + branches.get(1).capacity);
		System.out.println(branches.get(2).name + " Capacity:" + branches.get(2).capacity);
		System.out.println(branches.get(3).name + " Capacity:" + branches.get(3).capacity);

		System.out.println("==========================================");
		System.out.println();

		// fetch last Index of distribution for this set of people
		int lastDistributionIndex = 2;

		// Distribute the elements to the people in a round-robin fashion, distributing
		// all pending elements over the capacity once the capacity of each person is
		// reached
		List<List<Integer>> leadDistributionsForAvanseBranches = distributeElements(branchLeads, branches,
				lastDistributionIndex);

		// Print the distributions
		for (int i = 0; i < leadDistributionsForAvanseBranches.size(); i++) {
			if (i == 0) {
				System.out.println("==========================================");
				System.out.println("Element Distribution: ");
				System.out.println();
			}

			System.out.println(branches.get(i).name + " size: " + leadDistributionsForAvanseBranches.get(i).size());
			System.out.println(branches.get(i).name + " elements: " + leadDistributionsForAvanseBranches.get(i));
			System.out.println();
			
			if (i == leadDistributionsForAvanseBranches.size() - 1) {
				System.out.println("==========================================");
				System.out.println();
			}
		}

		// Lead Distribution with in Sales Managers		
		for(int h=0; h<leadDistributionsForAvanseBranches.size(); h++) {
			Queue<Integer> mumbaiLeads = new LinkedList<>(leadDistributionsForAvanseBranches.get(h));

			Integer leadSize = mumbaiLeads.size();

			// fetch Sales Managers from DB for a Branch
			List<Container> salesManagers = new ArrayList<>();
			salesManagers.add(new Container("Sales Manager of Branch no. " + h, getCapacityByPercentage(0.4f, leadSize)));
			salesManagers.add(new Container("Sales Manager of Branch no. " + h, getCapacityByPercentage(0.1f, leadSize)));
			salesManagers.add(new Container("Sales Manager of Branch no. " + h, getCapacityByPercentage(0.2f, leadSize)));
			salesManagers.add(new Container("Sales Manager of Branch no. " + h, getCapacityByPercentage(0.3f, leadSize)));

			// Distribute lead in round robin fashion
			List<List<Integer>> leadDistributionsForMumbaiSM = distributeElements(mumbaiLeads, salesManagers,
					lastDistributionIndex);

			// Print the distributions
			for (int i = 0; i < leadDistributionsForMumbaiSM.size(); i++) {
				if (i == 0) {
					System.out.println("==========================================");
					System.out.println("Element Distribution: ");
					System.out.println();
				}

				System.out.println(salesManagers.get(i).name + " size: " + leadDistributionsForMumbaiSM.get(i).size());
				System.out.println(salesManagers.get(i).name + " elements: " + leadDistributionsForMumbaiSM.get(i));
				System.out.println();
				
				if (i == leadDistributionsForAvanseBranches.size() - 1) {
					System.out.println("==========================================");
					System.out.println();
				}
			}
		}
		
		

	}

	public static class Container {
		public String name;
		public int capacity;

		public Container(String name, int capacity) {
			this.name = name;
			this.capacity = capacity;
		}
	}

	public static <T> List<List<T>> distributeElements(Queue<T> leads, List<Container> containers,
			int lastDistributionIndex) {

		// Create a list of lists to hold the distributions for each person
		List<List<T>> distributions = new ArrayList<>();
		for (int i = 0; i < containers.size(); i++) {
			distributions.add(new ArrayList<>());
		}

		System.out.println("==========================================");
		System.out.println("Containers: " + containers.size());
		System.out.println("last distribution index: " + lastDistributionIndex);
		System.out.println("==========================================");
		System.out.println();

		// Distribute the elements in a round-robin fashion, until the capacity of each
		// Container is reached
		int containerIndex;
		// check last distribution index and set max value if greater than array size
		// and zero in case of negative
		if (lastDistributionIndex >= containers.size()) {
			containerIndex = containers.size() - 1;
		} else if (lastDistributionIndex < 0) {
			containerIndex = 0;
		} else {
			containerIndex = lastDistributionIndex;
		}

		System.out.println("==========================================");
		System.out.println("Start index : " + containerIndex);
		System.out.println("==========================================");
		System.out.println();

		int peopleSize = containers.size();
		Set<Integer> skippedContainers = new HashSet<>();
		while (!leads.isEmpty() && skippedContainers.size() < peopleSize) {
			if (skippedContainers.contains(containerIndex)) {
				containerIndex = (containerIndex + 1) % peopleSize;
			}
			Container branch = containers.get(containerIndex);
			if (branch.capacity > distributions.get(containerIndex).size()) {
				distributions.get(containerIndex).add(leads.poll());
			} else {				
				skippedContainers.add(containerIndex);
			}
			containerIndex = (containerIndex + 1) % peopleSize;
		}
		// commented due to not distributing all in required manner
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
		System.out.println("Index before: " + containerIndex);
		System.out.println();

//		personIndex++;

		System.out.println();

		for (int i = 0; i < leads.size(); i++) {
			if (i == 0) {
				System.out.println("==========================================");
				System.out.println("Distributing pending elements... after capacity");
				System.out.println();
			}
			containerIndex = (containerIndex + i) % peopleSize;
			distributions.get(containerIndex).add(leads.remove());
			System.out.println("Index after: " + containerIndex);
			if (i == distributions.size() - 1) {
				System.out.println("==========================================");
				System.out.println();
			}
		}

		return distributions;
	}

	public static int getCapacityByPercentage(float proportion, int elementsSize) {
		return Math.round(proportion * elementsSize);
	}
}
