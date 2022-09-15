package interview;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Function;
import java.util.stream.Collectors;



public class Solutions {

	// Transform to Map<Integer, String>

	public static void main(String[] args) {
		
		List<Integer> list = Arrays.asList(1,2,3,4);
		
		Map<Integer, String> collectMap = list.stream().collect(Collectors.toMap(Function.identity(), e->e.toString()));
		
		Map<Integer, String> transformMap = new HashMap<>();
		
		list.stream().forEach(i -> {
			transformMap.put(i, i + "");
		});
		
		for(Entry<Integer, String> entry:transformMap.entrySet()) {
			System.out.println(entry.getKey() + ":" + '"' + entry.getValue() + '"');
		}
		
		for(Entry<Integer, String> entry : collectMap.entrySet()) {
			System.out.println(entry.getKey() + ":" + '"' + entry.getValue() + '"');
		}

		// Today is Friday and date is 12th August

		String str = "Today is Friday and date is 12th August.";

		Map<Character, Integer> countMap = new HashMap<>();

		for (int i = 0; i < str.length(); i++) {
			if (countMap.containsKey(str.charAt(i))) {
				countMap.put(str.charAt(i), countMap.get(str.charAt(i)) + 1);
			} else {
				countMap.put(str.charAt(i), 1);
			}
			
		}
		
		System.out.println("Char content: " + countMap.toString());

	}
}
