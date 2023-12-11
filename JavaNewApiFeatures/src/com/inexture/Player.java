package com.inexture;

public class Player {
	private static final String type = "Human";
	private int age;

	public static String getType() {
		return type;
	}

	public int getAge() {
		return age;
	}

	public static void main(String[] args) {
		Player player = null;
		System.out.println(player.getType());
		System.out.println(player.getAge());
	}

}
