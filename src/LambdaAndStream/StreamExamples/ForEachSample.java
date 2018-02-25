package LambdaAndStream.StreamExamples;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class ForEachSample {

	public static void main(String[] args) {
		List<String> strings = Arrays.asList("alma", "körte", "dió", "mogyoró", "42");

		// Every element in the strings array will be provided as an argument to the method after ::

		// Using System.out::println
		strings.forEach(System.out::println);
		
		// Using a custom class as an Action for forEach - it must implement the Iterable
		strings.forEach(PrintToConsole::printMessage);
	}
}

class PrintToConsole<T> implements Iterable<T> {

	public static void printMessage(String message) {
		System.out.println(message);
	}

	@Override
	public Iterator<T> iterator() {
		return new IteratorForPrintToConsole<T>();
	}
	
	class IteratorForPrintToConsole<T> implements Iterator<T>{

		@Override
		public boolean hasNext() {
			return hasNext();
		}

		@Override
		public T next() {
			return next();
		}
		
	}
}
