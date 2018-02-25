//Source: https://stackoverflow.com/questions/31044041/how-do-i-iterate-over-a-stream-in-java-using-for
package Java8Stuff.Streams;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class ForEach_Sample {

	// Method used to display messages inside a [] bracket
	private void bracketDisplay(Object msg) {
		System.out.print("[" + msg + "] ");
	}

	public static void main(String[] args) {
		List<String> strings = Arrays.asList("alma", "körte", "dió", "mogyoró", "42");

		// Every element from the strings array will be provided as an argument to the method after ::

		System.out.println("Using .forEach with System.out::println");
		strings.forEach(System.out::println);

		System.out.println("\n\nUsing .forEach with lambda expression");
		strings.forEach((stringToDisplay) -> System.out.print("{" + stringToDisplay + "} "));

		System.out.println("\n\nUsing .forEach() with custom print method");
		strings.forEach(PrintToConsole::printMessage);

		System.out.println("\n\nUsing .stream() .forEach() with System.out.println");
		strings.stream().forEach(System.out::println);

		System.out.println("\n\nUsing .stream() .forEach() with custom print method");
		strings.stream().forEach(PrintToConsole::printMessage);

		System.out.println("\n\nUsing .stream() .forEachOrdered() with System.out.println");
		strings.stream().forEachOrdered(System.out::println);

		System.out.println("\n\nUsing .stream() .forEachOrdered() with custom print method");
		strings.stream().forEachOrdered(PrintToConsole::printMessage);

		System.out.println("\n\nUsing method defined in the same class");
		ForEach_Sample thisInstance = new ForEach_Sample();
		strings.stream().forEach(thisInstance::bracketDisplay);

		System.out.println("\n\nOld method: for-each cycle");
		for (String string : strings) {
			thisInstance.bracketDisplay(string);
		}
	}
}

class PrintToConsole<T> implements Iterable<T> {

	public static void printMessage(String message) {
		System.out.print("<" + message + "> ");
	}

	@Override
	public Iterator<T> iterator() {
		return new IteratorForPrintToConsole<T>();
	}

	class IteratorForPrintToConsole<T> implements Iterator<T> {

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
