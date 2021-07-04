package com.mockTests.Application;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;



interface Age {
	int age = 21;

	int getAge();
}

public class Application {

	public enum EmploymentType {
		PERMANENT, CONTRACTUAL;
	}

	class Role {
		private int id;
		private String roleName;

		public Role(int id, String roleName) {
			super();
			this.id = id;
			this.roleName = roleName;
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getRoleName() {
			return roleName;
		}

		public void setRoleName(String roleName) {
			this.roleName = roleName;
		}

	}

	private class Inner {
		private int i;
	}

	public static void main(String[] args) {
		System.out.println("Hello World");

		// Anonymous Inner class
		Age age = new Age() {
			@Override
			public int getAge() {
				System.out.println("Age is:" + age);
				return age;
			}
		};

		System.out.println("Returned Age is:" + age.getAge());

		List<String> roleDtoList = new ArrayList<>();
		roleDtoList.add("superadmin");
		roleDtoList.add("outletadmin");
		roleDtoList.add("visitor");
		roleDtoList.add("subadmin");

		Boolean flag = Boolean.FALSE;

		if (roleDtoList.contains("outletadmin") || roleDtoList.contains("subadmin")) {
			flag = Boolean.TRUE;
			System.out.println(flag);
		}

		String tableName = "";

		for (int i = 0; i < 2; i++) {
			tableName += "word" + i + ", ";
		}

		String sub = tableName.substring(0, tableName.length() - 2);
		int i = sub.lastIndexOf(",");

		String newName = sub.substring(0, i) + " and" + sub.substring(i + 1);
		System.out.println(newName);

		System.out.println(new Date());
		String dateString = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		System.out.println(dateString);

		LocalDate createDate = LocalDate.parse(dateString);
		System.out.println(createDate);

		System.out.println(LocalDate.now());

		Date someDate = new Date();

		LocalDate createdDate = LocalDate.of(2020, Month.JUNE, 1);

		LocalDate today = LocalDate.now();

		LocalDate dateOfAnniversary = Application.convertToLocalDateViaSqlDate(someDate);

		LocalDate scheDate = LocalDate.of(today.getYear(), dateOfAnniversary.getMonth(),
				dateOfAnniversary.getDayOfMonth());

		System.out.println("Date:" + someDate);
		System.out.println("Anniversary Date:" + dateOfAnniversary);
		System.out.println("Scheculed Date:" + scheDate);

		LocalDate dateOfBirth = LocalDate.of(1992, Month.AUGUST, 2);

		Period periodBetween = Period.between(dateOfBirth, today);

		LocalDate qualifyingDate = today.minusDays(365);

		System.out.println("CreatedOn: " + createdDate);
		System.out.println("Today's Date: " + today);
		System.out.println("Qualifying Date: " + qualifyingDate);

		if (createdDate.isAfter(qualifyingDate))
			System.out.println("after");
		else
			System.out.println("before");

		Long visits = 94L;
		Long zero = 0L;
		Date d = Date.from(qualifyingDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
		System.out.println(visits.toString());
		System.out.println(zero.toString());
		String dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(d);

		System.out.println("Date:" + d);
		System.out.println("Date format:" + dateFormat);

		byte b = -128;
		System.out.println("byte is:" + b);

		System.out.println("Date of Birth:" + dateOfBirth);
		System.out.println("Today's Date:" + today);
		System.out.println("Period between:" + periodBetween.toTotalMonths());

		List<Integer> numbers = Arrays.asList(2, 3, 4, 5, 6, 7, 8, 9, 10);
		List squared = numbers.stream().map(x -> x * x).collect(Collectors.toList());

		System.out.println("Squared: " + squared);

		List<String> words = Arrays.asList("SUPERADMIN", "SUBADMIN", "OUTLETADMIN", "VISITOR");
		Set<String> superAdmin = words.stream().filter(x -> x.toUpperCase().contains("SUPERADMIN"))
				.collect(Collectors.toSet());

		Boolean status = words.stream().filter(x -> x.toUpperCase().contains("SUPERADMIN")).collect(Collectors.toSet())
				.contains("SUPERADMIN");
		Boolean rage = words.stream().distinct().collect(Collectors.toSet()).contains("SUPERADMIN");

		System.out.println("status: " + status);
		System.out.println("rage: " + rage);

		words.stream().forEach(System.out::println);

		Application a = new Application();

		Role role = a.new Role(1, "superadmin");
		Role rolee = a.new Role(2, "outletadmin");

		List<Role> roleList = new ArrayList<>();
		roleList.add(role);
		roleList.add(rolee);

		// method reference is used to call method of Role class
		System.out.println(roleList.stream().map(Role::getRoleName).collect(Collectors.toList()));

		System.out.println(roleList.stream().map(Role::getId).collect(Collectors.toList()).stream()
				.filter(x -> x.equals(1)).collect(Collectors.toList()));

		System.out.println("Enum:" + EmploymentType.PERMANENT);

		System.out.println(numbers.stream().filter(x -> x % 2 == 0).reduce(0, (ans, s) -> ans + s)); // or
		System.out.println(numbers.stream().filter(x -> x % 2 == 0).reduce(Integer::sum)); // or
		System.out.println(numbers.stream().filter(x -> x % 2 == 0).reduce(0, Integer::sum));

		List<Integer> list1 = new ArrayList<Integer>();
		List<Integer> list2 = new ArrayList<Integer>();

		list1.add(2);
		list1.add(4);
		list1.add(3);

		list2.add(5);
		list2.add(6);
		list2.add(4);

		SolutionTwo st = a.new SolutionTwo();

		System.out.println("Added Numbers:" + st.addTwoNumbers(list1, list2));
		
		Predicate<String> p = (s) -> s.startsWith("G");
		
		System.out.println("G test:"+p.test("Geeks"));

	}

	class SolutionTwo {
		public Integer addTwoNumbers(List<Integer> list1, List<Integer> list2) {
			Integer num1 = null;
			Integer num2 = null;
			Integer sum = null;

			num1 = list1.get(0);
			int j = 1;
			for (int i = 1; i < list1.size(); i++) {
				num1 += list1.get(i) * (10 * j);
				j *= 10;
			}

			num2 = list2.get(0);
			int k = 1;
			for (int i = 1; i < list2.size(); i++) {
				num2 += list2.get(i) * (10 * k);
				k *= 10;
			}
			
			return sum=num1+num2;
		}
	}

	class Solution {
	    public int[] twoSum(int[] nums, int target) {
	       int tar=0;
	        int[] out= new int[2];
	        for(int i=0; i<nums.length-1; i++){
	            for(int j=i+1; j<nums.length; j++){
	                if(target==nums[i] + nums[j]){
	                out[0] = i;
	                out[1] = j;
	                }            
	            }
	        }
	        return out;
	    }
	}

	public static LocalDate convertToLocalDateViaSqlDate(Date dateToConvert) {
		return new java.sql.Date(dateToConvert.getTime()).toLocalDate();
	}

}
