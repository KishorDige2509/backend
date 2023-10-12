package com.cognizant;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.comparator.Employee;

public class Solution {

	public static void main(String[] args) {
		System.out.println("");

		int[] arr = { 1, 23, 45, 3, 5 };

		List<Employee> employees = new ArrayList<>();

		List<Employee> ascEmployees = employees.stream().sorted(Comparator.comparing(Employee::getId))
				.collect(Collectors.toList());
		List<Employee> descEmployees = employees.stream().sorted(Comparator.comparing(Employee::getId).reversed())
				.collect(Collectors.toList());

	}

}
