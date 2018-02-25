package Java8Stuff.Streams;

import java.util.Arrays;
import java.util.List;

public class Filter_Sample {

	private void displayInBracket(Object toDisplay) {
		System.out.print("[" + toDisplay + "] ");
	}

	public static void main(String[] args) {
		Filter_Sample instance = new Filter_Sample();
		List<String> strings = Arrays.asList("alma", "körte", "dió", "mogyoró", "42");
		List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

		System.out.println("Filter and print even numbers");
		System.out.println("Original list: ");
		numbers.stream().forEach(instance::displayInBracket);
		System.out.println("\nFiltered list: ");
		numbers.stream()
				// Filter to even numbers only
				.filter(
						// A lambda expression is provided as parameter to .filter()
						(n) -> n % 2 == 0)
				// Print out the result of .filter()
				.forEach(instance::displayInBracket);
		; // this ends the chained stream() methods

		System.out.println("\n\nFilter to strings with greater than 4 length");
		strings.stream().filter((String s) -> s.length() > 4).forEach(instance::displayInBracket);

		System.out.println("\n\nFilter to strings that ends with ó");
		strings.stream().filter((String s) -> {
			if (s.endsWith("ó"))
				return true;
			else
				return false;
		}).forEach(instance::displayInBracket);
	}
}
