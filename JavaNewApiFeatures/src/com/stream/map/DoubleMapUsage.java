package com.stream.map;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class DoubleMapUsage {

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
		
		Double average = mappings.stream().map(UserRoleMapping::getId).mapToDouble(i->i).average().getAsDouble();
		
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

	}

}
