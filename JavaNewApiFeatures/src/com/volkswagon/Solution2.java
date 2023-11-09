package com.volkswagon;

import java.util.Arrays;
import java.util.List;

public class Solution2 {
	
	//most booked hotel room
	public static void main(String[] args) {
		List<Room> rooms = Arrays.asList(new Room(1L, "classic", 0L), new Room(2L, "classic_plus", 2L),
				new Room(3L, "collection_o", 3L), new Room(4L, "deluxe", 5L));
		Long max = 0L;
		Room maxBooked = null;
		for (Room rm : rooms) {
			if (rm.getNoOfBookingsTillDate() > max) {
				maxBooked = rm;
			}
		}
		System.out.println("Max booked room:" + maxBooked.toString());

	}

}
