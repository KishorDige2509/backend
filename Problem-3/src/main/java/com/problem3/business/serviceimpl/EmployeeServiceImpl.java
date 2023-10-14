package com.problem3.business.serviceimpl;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.problem3.integration.domain.Employee;

public class EmployeeServiceImpl {

	public static void main(String[] args) {
		List<Employee> employees = Arrays.asList(new Employee("Raj", 4200, "HR"), new Employee("Rajj", 3200, "HR"),
				new Employee("Rajesh", 2000, "IT"), new Employee("Rajnikant", 3000, "Admin"));

		// sort employees based on salary and group by department
		Map<String, List<Employee>> collect = employees.stream().collect(
				Collectors.groupingBy(Employee::getDept, Collectors.collectingAndThen(Collectors.toList(), list -> list
						.stream().sorted(Comparator.comparing(Employee::getSalary)).collect(Collectors.toList()))));

		collect.entrySet().stream().forEach(e -> {
			System.out.println(e.getKey() + " " + e.getValue());
		});

	}

}
