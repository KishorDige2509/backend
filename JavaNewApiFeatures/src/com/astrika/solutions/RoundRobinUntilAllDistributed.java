package com.astrika.solutions;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.OptionalInt;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.atomic.LongAccumulator;
import java.util.stream.IntStream;

public class RoundRobinUntilAllDistributed {

	public static void main(String[] args) {
		
		int totalLeads = 1;
		
		// Create a list of elements to be distributed more optimized way would be to
		// use queue and use remove on it
		Queue<Integer> partnerLeads = new LinkedList<>();
//		IntStream.rangeClosed(1, 217).boxed().collect(Collectors.toList());
		IntStream.rangeClosed(1, totalLeads).forEach(partnerLeads::add);

//		elements.add(1);

		int elementsSize = partnerLeads.size();

		System.out.println("Element size: " + elementsSize);

		// Create a list of people to distribute the elements to, each with a maximum
		// capacity
		// TODO set the capacity based on elements size in percentage
		List<Container> branches = new ArrayList<>();
		branches.add(new Container("Mumbai", getCapacityByPercentage(0.3f, elementsSize), false));
		branches.add(new Container("Goa", getCapacityByPercentage(0.2f, elementsSize), false));
		branches.add(new Container("Nagpur", getCapacityByPercentage(0.2f, elementsSize), false));
		branches.add(new Container("Nashik", getCapacityByPercentage(0.3f, elementsSize), true));

		System.out.println(branches.get(0).name + " Capacity:" + branches.get(0).capacity);
		System.out.println(branches.get(1).name + " Capacity:" + branches.get(1).capacity);
		System.out.println(branches.get(2).name + " Capacity:" + branches.get(2).capacity);
		System.out.println(branches.get(3).name + " Capacity:" + branches.get(3).capacity);

		System.out.println("==========================================");
		System.out.println();

		// fetch last distribution index of branch
		int lastBranchDistributionIndex = IntStream.range(0, branches.size())
				.filter(i -> branches.get(i).lastLeadAssigned).findFirst().orElse(-1);

		// Distribute the leads to the branches in a round-robin fashion
		List<List<Integer>> leadDistributionsForAvanseBranches = distributeElements(partnerLeads, branches,
				++lastBranchDistributionIndex);

		// Print the Branch distributions
		System.out.println("*******************************");
		System.out.println("Branch Distributions...");
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
		System.out.println("*******************************");

		// Distribute the leads to Sales Managers of Branches in round robin fashion
		for (int h = 0; h < leadDistributionsForAvanseBranches.size(); h++) {
			Queue<Integer> branchLeads = new LinkedList<>(leadDistributionsForAvanseBranches.get(h));

			Integer branchLeadSize = branchLeads.size();

			// fetch Sales Managers from DB for a Branch
			List<Container> salesManagers = new ArrayList<>();
			salesManagers.add(new Container("Sales Manager No. 1 of Branch " + branches.get(h).name,
					getCapacityByPercentage(0.41f, branchLeadSize), false));
			salesManagers.add(new Container("Sales Manager No. 2 of Branch " + branches.get(h).name,
					getCapacityByPercentage(0.16f, branchLeadSize), true));
			salesManagers.add(new Container("Sales Manager No. 3 of Branch " + branches.get(h).name,
					getCapacityByPercentage(0.23f, branchLeadSize), false));
			salesManagers.add(new Container("Sales Manager No. 4 of Branch " + branches.get(h).name,
					getCapacityByPercentage(0.353f, branchLeadSize), false));
			
			// Print Capacity
			System.out.println(salesManagers.get(0).name + " Capacity:" + salesManagers.get(0).capacity);
			System.out.println(salesManagers.get(1).name + " Capacity:" + salesManagers.get(1).capacity);
			System.out.println(salesManagers.get(2).name + " Capacity:" + salesManagers.get(2).capacity);
			System.out.println(salesManagers.get(3).name + " Capacity:" + salesManagers.get(3).capacity);
			
			// fetch last distribution index of branch
			int lastSmDistributionIndex = IntStream.range(0, salesManagers.size())
					.filter(i -> salesManagers.get(i).lastLeadAssigned).findFirst().orElse(-1);

			// Distribute lead in round robin fashion
			List<List<Integer>> leadDistributionsForBranchSMs = distributeElements(branchLeads, salesManagers,
					++lastSmDistributionIndex);

			// Print the distributions
			for (int i = 0; i < leadDistributionsForBranchSMs.size(); i++) {
				if (i == 0) {
					System.out.println("==========================================");
					System.out.println("Element Distribution: ");
					System.out.println();
				}

				System.out.println(salesManagers.get(i).name + " size: " + leadDistributionsForBranchSMs.get(i).size());
				System.out.println(salesManagers.get(i).name + " elements: " + leadDistributionsForBranchSMs.get(i));
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
		public boolean lastLeadAssigned;

		public Container(String name, int capacity, boolean lastLeadAssigned) {
			this.name = name;
			this.capacity = capacity;
			this.lastLeadAssigned = lastLeadAssigned;
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
		int currentContainerIndex;
		// check last distribution index and set max value if greater than array size
		// and zero in case of negative
		if (lastDistributionIndex >= containers.size()) {
			currentContainerIndex = containers.size() - 1;
		} else if (lastDistributionIndex < 0) {
			currentContainerIndex = 0;
		} else {
			currentContainerIndex = lastDistributionIndex;
		}

		System.out.println("==========================================");
		System.out.println("Start index : " + currentContainerIndex);
		System.out.println("==========================================");
		System.out.println();

		int containerSize = containers.size();
		Queue<Integer> skippedContainers = new LinkedList<>();
		while (!leads.isEmpty() && skippedContainers.size() < containerSize) {
			if (skippedContainers.contains(currentContainerIndex)) {
				currentContainerIndex = (currentContainerIndex + 1) % containerSize;
			}
			Container container = containers.get(currentContainerIndex);
			if (container.capacity > distributions.get(currentContainerIndex).size()) {
				distributions.get(currentContainerIndex).add(leads.poll());
			} else if (!skippedContainers.contains(currentContainerIndex)) {
				skippedContainers.add(currentContainerIndex);
			}
			currentContainerIndex = (currentContainerIndex + 1) % containerSize;
		}

		System.out.println("Skipped Containers: " + skippedContainers);

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
		System.out.println("Index before: " + currentContainerIndex);
		System.out.println();

//		personIndex++;
		lastDistributionIndex = currentContainerIndex;
		System.out.println();

		for (int i = 0; i < leads.size(); i++) {
			if (i == 0) {
				System.out.println("==========================================");
				System.out.println("Distributing pending elements... after capacity");
				System.out.println();
			}
			currentContainerIndex = (currentContainerIndex + i) % containerSize;
			distributions.get(currentContainerIndex).add(leads.remove());
			System.out.println("Index after: " + currentContainerIndex);
			if (i == distributions.size() - 1) {
				lastDistributionIndex = currentContainerIndex;
				System.out.println("==========================================");
				System.out.println();
			}
		}

		System.out.println("Last distribution Index: " + lastDistributionIndex);
		return distributions;
	}

	public static int getCapacityByPercentage(float proportion, int elementsSize) {
		return Math.round(proportion * elementsSize);
	}
}
