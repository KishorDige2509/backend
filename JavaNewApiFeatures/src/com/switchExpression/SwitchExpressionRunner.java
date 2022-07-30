package com.switchExpression;

public class SwitchExpressionRunner {

	public static String findDayOftheWeek(int day) {

		String dayOfWeek = "";

		switch (day) {
		case 0: {
			dayOfWeek = "Sunday";
			break;
		}
		case 1: {
			dayOfWeek = "Monday";
			break;
		}
		case 2: {
			dayOfWeek = "Tuesday";
			break;
		}
		case 3: {
			dayOfWeek = "Wednesday";
			break;
		}
		case 4: {
			dayOfWeek = "Thurday";
			break;
		}
		case 5: {
			dayOfWeek = "Friday";
			break;
		}
		case 6: {
			dayOfWeek = "Saturday";
			break;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + day);
		}

		return dayOfWeek;
		
	}

	public static String findDayOftheWeekWithSwitchExpression(int day) {

		return switch (day) {
		case 0 -> {
			System.out.println("Do some complex logic here");
			yield "Sunday";
		}
		case 1 -> "Monday";
		case 2 -> "Tuesday";
		case 3 -> "Wednesday";
		case 4 -> "Thurday";
		case 5 -> "Friday";
		case 6 -> "Saturday";
		default -> throw new IllegalArgumentException("Unexpected value: " + day);
		};

	}

	public static void main(String[] args) {
		/* what else you want in your life except this */
		
	}

}
