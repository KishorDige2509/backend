import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Solution {
	public static void main(String[] args) {
		Integer[] arr = { 1, 2, 37, 3, 42, 12 };
		System.out.println("Unsorted array:" + arr);
		List<Integer> sorted = Stream.of(arr).sorted().collect(Collectors.toList());
		System.out.println("Sorted Array: " + sorted);
	}

}

//most booked hotel room
class Solution1 {
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

class Room {

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