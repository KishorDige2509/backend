package com.problem2.Application;

public class Application {

	public static void main(String[] args) {

		int[] elementArray = { 1, 2, 3, 4, 1, 12, 13, 2, 6, 7, 8, 2, 3 };
		int target = 3;

		Application a = new Application();

		Problem2 p2 = a.new Problem2();
		p2.subArrays(elementArray, target);

	}

	class Problem2 {
		public void subArrays(int[] nums, int target) {

			for (int i = 0; i < nums.length - 1; i++) {
				for (int j = i + 1; j < nums.length; j++) {
					if (target == nums[i] + nums[j]) {
						System.out.println("SubArray:{" + nums[i] + ", " + nums[j] + "}");
					}
				}
			}

		}
	}

}
