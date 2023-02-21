package com.stream.map;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class ForEachCheck {

	public static void main(String[] args) {

		List<Integer> userList1 = new ArrayList<>();
		List<Integer> userList2 = new ArrayList<>();

		IntStream.rangeClosed(1, 100).forEach(n -> {
			if (n % 2 == 0) {
				userList1.add(n);
			} else {
				userList2.add(n);
			}

		});

		System.out.println("User List1: " + userList1.toString() + "\n" + "UserList2: " + userList2.toString());

	}

}
