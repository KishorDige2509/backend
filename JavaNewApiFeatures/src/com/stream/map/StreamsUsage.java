package com.stream.map;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class StreamsUsage {

	private static final List<Integer> arrayListOfNumbers = new ArrayList<>();
	private static final List<Integer> linkedListOfNumbers = new LinkedList<>();

	static {
		IntStream.rangeClosed(1, 1_000).forEach(i -> {
			arrayListOfNumbers.add(i);
			linkedListOfNumbers.add(i);
		});
	}

	public static void main(String[] args) {
		UserMaster userMaster = new UserMaster();

		List<UserRoleMapping> mappings = Arrays.asList(new UserRoleMapping(1L, RoleType.CPA),
				new UserRoleMapping(2L, RoleType.CREDIT_MANAGER));

		userMaster.setUserRoleMappings(mappings);

		List<Long> ids = userMaster.getUserRoleMappings().stream().map(UserRoleMapping::getRoleType)
				.map(RoleType::getId).collect(Collectors.toList());

		System.out.println("Ids: " + ids);

		Long sumNormal = mappings.stream().map(UserRoleMapping::getId).mapToLong(i -> i).sum();

		System.out.println("sumNormal: " + sumNormal);

		// square and list to map of numbers in list
		List<Long> numbers = new ArrayList<>(Arrays.asList(1L, 2L, 3L, 4L));

		List<Long> squareWithLimit = numbers.stream().map(i -> i * i).limit(2).toList();

		Map<Long, String> mapNumbers = numbers.stream()
				.collect(Collectors.toMap(Function.identity(), v -> v.toString()));

		System.out.println("------------> squareLimit: " + squareWithLimit.toString());
		System.out.println("------------> mapNumbers: " + mapNumbers.toString());

		List<Long> squaredList = numbers.stream().map(i -> i * i).toList();

		System.out.println("--------------> Squared List: " + squaredList);

		numbers.stream().map(i -> i * i).forEach(v -> System.out.print(v + ", "));
		Long sumOfSquare = numbers.stream().map(i -> i * i).reduce(0L, Long::sum);

		System.out.println("----->  sum of square: " + sumOfSquare);

		Long sumWithReduceLambda = mappings.stream().map(UserRoleMapping::getId).reduce(0L, (a, b) -> a + b);

		System.out.println("sumWithReduceLambda: " + sumWithReduceLambda);

		Long sumWithMehodRef = mappings.stream().map(UserRoleMapping::getId).reduce(0L, Long::sum);

		System.out.println("sumWithMehodRef: " + sumWithMehodRef);

		// optional
		Optional<Long> sumWithRedWithoutIdentityOptional = mappings.stream().map(UserRoleMapping::getId)
				.reduce(Long::sum);

		System.out.println(sumWithRedWithoutIdentityOptional.get());

		Long sumWithRedWithoutIdentity = mappings.stream().map(UserRoleMapping::getId).reduce(Long::sum).get();

		System.out.println(sumWithRedWithoutIdentity);

		// multiplication
		Long product = mappings.stream().map(UserRoleMapping::getId).reduce(1L, (a, b) -> a * b);

		System.out.println(product);

		// average
		Double average = mappings.stream().map(UserRoleMapping::getId).mapToDouble(i -> i).average().getAsDouble();

		System.out.println("average: " + average);

		// max
		Long max = mappings.stream().map(UserRoleMapping::getId).mapToLong(i -> i).max().getAsLong();
		System.out.println(max);

		Long max1 = mappings.stream().map(UserRoleMapping::getId).reduce(Long::max).get();

		System.out.println(max1);

		Long max2 = mappings.stream().map(UserRoleMapping::getId).reduce(0L, (a, b) -> a > b ? a : b);

		System.out.println(max2);

		// sorting
		System.out.println("sorting: ");
		mappings.stream().map(UserRoleMapping::getId).sorted().forEach(System.out::print);
		System.out.println();
		mappings.stream().map(UserRoleMapping::getId).sorted(Comparator.reverseOrder()).forEach(System.out::print);

		List<String> words = Arrays.asList("Spring", "react", "hibernate");

		System.out.println();
		String maxWord = words.stream().reduce((a, b) -> a.length() > b.length() ? a : b).get();

		System.out.println(maxWord);

		List<Integer> disTestList = List.of(1, 2, 3, 4, 4, 5);

		Object someSet = disTestList.stream().collect(Collectors.toSet());

		System.out.println("someSet 0===" + someSet);

		Object someDis = disTestList.stream().distinct().collect(Collectors.toList());

		System.out.println("someDis 0===" + someDis);

		// flat map
		List<String> productlist1 = Arrays.asList("Printer", "Mouse", "Keyboard", "Motherboard");
		List<String> productlist2 = Arrays.asList("Scanner", "Projector", "Light Pen");
		List<String> productlist3 = Arrays.asList("Pen Drive", "Charger", "WIFI Adapter", "Cooling Fan");
		List<String> productlist4 = Arrays.asList("CPU Cabinet", "WebCam", "USB Light", "Microphone", "Power cable");
		List<List<String>> allproducts = new ArrayList<List<String>>();
		// adding elements to the list
		allproducts.add(productlist1);
		allproducts.add(productlist2);
		allproducts.add(productlist3);
		allproducts.add(productlist4);

		List<String> flatMapStreamEx = allproducts.stream()
				.flatMap(prodList -> prodList.stream().map(String::toUpperCase)).collect(Collectors.toList());
//		System.out.println("flatMapStreamEx ---> " + flatMapStreamEx);

//		arrayListOfNumbers.stream().forEach(System.out::println);

		// find values in list that are duplicates
		disTestList.stream().filter(i -> Collections.frequency(disTestList, i) > 1).distinct()
				.forEach(System.out::println);

		// duplicate values in list can be collected in map or printed
		disTestList.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting())).entrySet()
				.stream().filter(m -> m.getValue() > 1).map(Map.Entry::getKey).forEach(System.out::println);

	}

}
