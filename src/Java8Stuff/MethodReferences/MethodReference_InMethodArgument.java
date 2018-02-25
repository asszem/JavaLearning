package Java8Stuff.MethodReferences;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public class MethodReference_InMethodArgument {

	private String doSomething(String input) {
		return "\t[" + input + "]";
	}

	private String doSomethingElse(String input) {
		return "\t{" + input + "}";
	}

	// The Function<T,R> represents a function that accepts one argument T and returns a result R
	// .function.apply(input) will apply the function on input and produce the output
	private void showResults(List<String> inputList, Function<String, String> function) {
		for (String string : inputList) {
			// apply whatever implementation is passed as an argument on every item in the inputList
			System.out.println(function.apply(string));
		}

	}

	public static void main(String[] args) {
		MethodReference_InMethodArgument instance = new MethodReference_InMethodArgument();
		List<String> strings = Arrays.asList("alma", "körte", "dió", "mogyoró", "42");

		example("Using Method Reference");
		example("instance::doSomething");
		instance.showResults(strings, instance::doSomething);

		example("\n\nSame method could be called using Lambda expression");
		example("s -> instance.doSomething(s)");
		instance.showResults(strings, s -> instance.doSomething(s));

		example("\n\nUsing Method Reference for a different implementation");
		example("instance::doSomethingElse");
		instance.showResults(strings, instance::doSomethingElse);

		example("\n\nUsing old method with implementing an anonymous inner class");
		instance.showResults(strings, new Function<String, String>() {

			// Inner class implementation
			@Override
			public String apply(String input) {
				String updatedString = "\t<" + input + ">";
				return updatedString;
			}
		});
	}

	private static void example(String s) {
		System.out.println(s);
	}

}
