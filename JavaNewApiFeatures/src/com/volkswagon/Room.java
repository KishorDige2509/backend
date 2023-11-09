package com.volkswagon;

public class Room {
	
	private Long id;
	private String desc;
	private Long noOfBookingsTillDate;

	public Room(Long id, String desc, Long noOfBookingsTillDate) {
		this.id = id;
		this.desc = desc;
		this.noOfBookingsTillDate = noOfBookingsTillDate;
	}
	
	public Long getNoOfBookingsTillDate() {
		return this.noOfBookingsTillDate;
	}

	public String toString() {
		return "id:" + this.id + " desc:" + this.desc + " noOfBookings:" + this.noOfBookingsTillDate;
	}

}
