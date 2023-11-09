package com.cognizant;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.comparator.Employee;

public class Solution {

	public static void main(String[] args) {
		System.out.println("");

		int[] arr = { 1, 23, 45, 3, 5 };

		
		List<Employee> employees = IntStream.range(1, 5)
                .mapToObj(i -> new Employee((long) i, "name_" + i, "dept_" + i))
                .collect(Collectors.toList());

		List<Employee> ascEmployees = employees.stream().sorted(Comparator.comparing(Employee::getId))
				.collect(Collectors.toList());
		List<Employee> descEmployees = employees.stream().sorted(Comparator.comparing(Employee::getId).reversed())
				.collect(Collectors.toList());
		
		System.out.println("Asc: " + ascEmployees);
		System.out.println("Desc: " + descEmployees);

	}

}
