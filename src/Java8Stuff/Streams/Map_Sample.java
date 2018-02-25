//Source: https://www.mkyong.com/java8/java-8-foreach-examples/
package Java8Stuff.Streams;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Map_Sample {

	public static void main(String[] args) {
		List<String> strings = Arrays.asList("alma", "körte", "dió", "mogyoró", "42");

		Map<String, Integer> items = new HashMap<>();
		items.put("X", 10);
		items.put("A", 10);
		items.put("B", 20);
		items.put("C", 30);
		items.put("D", 40);
		items.put("E", 50);
		items.put("F", 60);

		// Traditional method to iterate over the map
		for (Map.Entry<String, Integer> entry : items.entrySet()) {
			// System.out.println("Item : " + entry.getKey() + " Value : " + entry.getValue());
		}

		items.forEach((k, v) -> System.out.println("Item : " + k + " Count : " + v));

		items.forEach((k, v) -> {
			System.out.println("Item : " + k + " Count : " + v);
			if ("E".equals(k)) {
				System.out.println("Hello E");
			}
		});
	}
}
