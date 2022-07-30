package com.comparator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

public class ComparatorRunner {	
	
	public static void main(String[] args) {
		
		List<Employee> list = Arrays.asList(new Employee(2L, "aplia", "zz"), new Employee(1L, "aplication", "frontEnd"));
		
		Collections.sort(list, new NameComparator());
		
		System.out.println(list);
		
		Collections.sort(list, new IdComparator());
		
		System.out.println(list);
		
		// using lambda
		Collections.sort(list, (o1, o2) -> o1.getDept().compareTo(o2.getDept())); // ascending
		
		Collections.sort(list, (o1, o2) -> o1.getDept().compareTo(o2.getDept())); // descending
		
		System.out.println(list);
		
		
		// using streams		
		list.stream().sorted((o1, o2) -> o1.getId().compareTo(o2.getId())).forEach(System.out::print);
		
		System.out.println();
		
		list.stream().sorted(Comparator.comparing(Employee::getId)).forEach(System.out::print);
		
		System.out.println();
		
		Map<String, Integer> map = new HashMap<>();
		map.put("one", 1);
		map.put("two", 2);
		map.put("three", 3);
		map.put("four", 4);
		map.put("five", 5);
		
		List<Entry<String, Integer>> listMap = new ArrayList<>(map.entrySet());
		Collections.sort(listMap, (o1, o2) -> o1.getKey().compareTo(o2.getKey()));
		
		for(Entry<String, Integer> entry: listMap) {
			System.out.println(entry.getKey() + "-" + entry.getValue());
		}
		// by using stream
		map.entrySet().stream().sorted(Map.Entry.comparingByKey()).forEach(System.out::print);
		
		System.out.println();
		
		Map<Employee, Integer> employeeMap = new TreeMap<>((o1, o2) -> o1.getId().compareTo(o2.getId()));
		employeeMap.put(new Employee(1L, "show1", "it1"), 10);
		employeeMap.put(new Employee(2L, "show2", "it2"), 20);
		employeeMap.put(new Employee(3L, "show3", "it3"), 30);
		employeeMap.put(new Employee(4L, "show4", "it4"), 40);
		employeeMap.put(new Employee(5L, "show5", "it5"), 50);
		
		employeeMap.entrySet().stream().sorted(Map.Entry.comparingByKey(Comparator.comparing(Employee::getId).reversed())).forEach(System.out::println);
		
	}

}

class NameComparator implements Comparator<Employee>{

	@Override
	public int compare(Employee o1, Employee o2) {		
		return o1.getName().compareTo(o2.getName());
	}
	
}


class IdComparator implements Comparator<Employee> {

	@Override
	public int compare(Employee o1, Employee o2) {
		return o1.getId().compareTo(o2.getId());
	}
	
}
