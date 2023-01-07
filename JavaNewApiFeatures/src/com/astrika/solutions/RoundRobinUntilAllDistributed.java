package com.astrika.solutions;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
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

		// fetch Branches from DB for a Partner
		List<Container> branches = new ArrayList<>();
		branches.add(new Container("Mumbai", getCapacityByPercentage(0.3f, elementsSize), true));
		branches.add(new Container("Goa", getCapacityByPercentage(0.2f, elementsSize), false));
		branches.add(new Container("Nagpur", getCapacityByPercentage(0.2f, elementsSize), false));
		branches.add(new Container("Nashik", getCapacityByPercentage(0.3f, elementsSize), false));

		// Print Capacity
		System.out.println(branches.get(0).name + " Capacity:" + branches.get(0).capacity);
		System.out.println(branches.get(1).name + " Capacity:" + branches.get(1).capacity);
		System.out.println(branches.get(2).name + " Capacity:" + branches.get(2).capacity);
		System.out.println(branches.get(3).name + " Capacity:" + branches.get(3).capacity);
		System.out.println("==========================================");
		System.out.println();

		// fetch last distribution index
		int lastBranchDistributionIndex = IntStream.range(0, branches.size())
				.filter(i -> branches.get(i).lastLeadAssigned).findFirst().orElse(-1);

		System.out.println("Last Distribution Index:" + lastBranchDistributionIndex);

		// set next distribution index
		int nextDistributionIndex = ++lastBranchDistributionIndex;

		// Distribute the leads in a round-robin fashion
		List<List<Integer>> leadDistributionsForAvanseBranches = distributeElements(partnerLeads, branches,
				nextDistributionIndex);

		// Print the distributions
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
		distributeLeadsToSalesManagersOfBranches(branches, leadDistributionsForAvanseBranches);

	}

	private static void distributeLeadsToSalesManagersOfBranches(List<Container> branches,
			List<List<Integer>> leadDistributionsForAvanseBranches) {
		for (int branchNo = 0; branchNo < leadDistributionsForAvanseBranches.size(); branchNo++) {
			Queue<Integer> branchLeads = new LinkedList<>(leadDistributionsForAvanseBranches.get(branchNo));

			Integer branchLeadSize = branchLeads.size();

			// fetch Sales Managers from DB for a Branch
			List<Container> salesManagers = new ArrayList<>();
			salesManagers.add(new Container("Sales Manager No. 1 of Branch " + branches.get(branchNo).name,
					getCapacityByPercentage(0.41f, branchLeadSize), false));
			salesManagers.add(new Container("Sales Manager No. 2 of Branch " + branches.get(branchNo).name,
					getCapacityByPercentage(0.16f, branchLeadSize), true));
			salesManagers.add(new Container("Sales Manager No. 3 of Branch " + branches.get(branchNo).name,
					getCapacityByPercentage(0.23f, branchLeadSize), false));
			salesManagers.add(new Container("Sales Manager No. 4 of Branch " + branches.get(branchNo).name,
					getCapacityByPercentage(0.343f, branchLeadSize), false));

			// Print Capacity
			System.out.println(salesManagers.get(0).name + " Capacity:" + salesManagers.get(0).capacity);
			System.out.println(salesManagers.get(1).name + " Capacity:" + salesManagers.get(1).capacity);
			System.out.println(salesManagers.get(2).name + " Capacity:" + salesManagers.get(2).capacity);
			System.out.println(salesManagers.get(3).name + " Capacity:" + salesManagers.get(3).capacity);
			System.out.println();

			// fetch last distribution index
			int lastSmDistributionIndex = IntStream.range(0, salesManagers.size())
					.filter(i -> salesManagers.get(i).lastLeadAssigned).findFirst().orElse(-1);
			System.out.println("Last Distribution Index:" + lastSmDistributionIndex);

			// set next distribution index
			int nextDistributionIndex = ++lastSmDistributionIndex;

			// Distribute leads in round robin fashion
			List<List<Integer>> leadDistributionsForBranchSMs = distributeElements(branchLeads, salesManagers,
					nextDistributionIndex);

			// Print the distributions
			for (int salesManagerNo = 0; salesManagerNo < leadDistributionsForBranchSMs.size(); salesManagerNo++) {
				if (salesManagerNo == 0) {
					System.out.println("==========================================");
					System.out.println("Element Distribution: ");
					System.out.println();
				}

				System.out.println(salesManagers.get(salesManagerNo).name + " size: "
						+ leadDistributionsForBranchSMs.get(salesManagerNo).size());
				System.out.println(salesManagers.get(salesManagerNo).name + " elements: "
						+ leadDistributionsForBranchSMs.get(salesManagerNo));
				System.out.println();

				// Note: from here we can send this as a Object to lead-service to set
				// AvanseBranch and AvanseSM on lead
				/*
				 * Contents of Class will be AvanaseBranchId, AvanseSmUserId and List of Leads
				 */

				if (salesManagerNo == leadDistributionsForAvanseBranches.size() - 1) {
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
			int startDistributionIndex) {

		// Create a list of lists to hold the distributions for each Container
		List<List<T>> distributions = new ArrayList<>();
		for (int i = 0; i < containers.size(); i++) {
			distributions.add(new ArrayList<>());
		}

		System.out.println("==========================================");
		System.out.println("Containers: " + containers.size());
		System.out.println("Start Distribution index: " + startDistributionIndex);
		System.out.println("==========================================");
		System.out.println();

		// Get current container index
		int currentContainerIndex = getCurrentContainerIndex(containers, startDistributionIndex);

		System.out.println("==========================================");
		System.out.println("Current Container index : " + currentContainerIndex);
		System.out.println("==========================================");
		System.out.println();

		// Initialize last distribution index
		int lastDistributionIndex = currentContainerIndex;

		int containerSize = containers.size();
		boolean roundRobinHappened = false;
		Queue<Integer> skippedContainers = new LinkedList<>();
		while (!leads.isEmpty() && skippedContainers.size() < containerSize) {
			if (skippedContainers.contains(currentContainerIndex)) {
				currentContainerIndex = (currentContainerIndex + 1) % containerSize;
			}
			Container container = containers.get(currentContainerIndex);
			if (container.capacity > distributions.get(currentContainerIndex).size()) {
				distributions.get(currentContainerIndex).add(leads.poll());
				lastDistributionIndex = currentContainerIndex;
				roundRobinHappened = true;
			} else if (!skippedContainers.contains(currentContainerIndex)) {
				skippedContainers.add(currentContainerIndex);
			}
			currentContainerIndex = (currentContainerIndex + 1) % containerSize;
		}

		System.out.println("Skipped Containers: " + skippedContainers);
		System.out.println();

		System.out.println("Index before: " + currentContainerIndex);
		System.out.println();
		// Distribute the remaining elements in a round-robin fashion over the capacity
		for (int i = 0; i < leads.size(); i++) {
			if (i == 0) {
				System.out.println("==========================================");
				System.out.println("Distributing pending elements... after capacity");
				System.out.println();

				// increment lastDistributionIndex if round-robin was previously done
				if (roundRobinHappened) {
					lastDistributionIndex++;
				}
			}
			currentContainerIndex = (lastDistributionIndex + i) % containerSize;
			distributions.get(currentContainerIndex).add(leads.remove());
			if (i == distributions.size() - 1) {
				lastDistributionIndex = currentContainerIndex;
				System.out.println("==========================================");
				System.out.println();
			}
		}
		System.out.println("Index after: " + currentContainerIndex);
		System.out.println("Last distribution Index: " + lastDistributionIndex);

		// Based on lastDistribution index we can set the lastLeadAssigned flag on
		// container
		containers.get(lastDistributionIndex).lastLeadAssigned = true;

		// Print container name
		System.out.println(containers.get(lastDistributionIndex).name + " : flag value: "
				+ containers.get(lastDistributionIndex).lastLeadAssigned);

		return distributions;
	}

	private static int getCurrentContainerIndex(List<Container> containers, int lastDistributionIndex) {
		int currentContainerIndex;
		if (lastDistributionIndex >= containers.size()) {
			// Commented: as this condition is not required, instead start from zero
			// required to enable round-robin
//			currentContainerIndex = containers.size() - 1;
			currentContainerIndex = 0;
		} else if (lastDistributionIndex < 0) {
			currentContainerIndex = 0;
		} else {
			currentContainerIndex = lastDistributionIndex;
		}
		return currentContainerIndex;
	}

	public static int getCapacityByPercentage(float proportion, int elementsSize) {
		return Math.round(proportion * elementsSize);
	}
}

// First Tried Code
//commented due to not distributing all in required manner
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
