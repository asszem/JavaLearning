/*
 * Simple example sources
 * http://www.oracle.com/webfolder/technetwork/tutorials/obe/java/Lambda-QuickStart/index.html
 * */
package LambdaExpressions;

public class BasicExamples {

	public static void main(String[] args) {

		// Using Anonymous class to override printMessage
		FunctionalInterfaceSampleHere anonymous = new FunctionalInterfaceSampleHere() {

			@Override
			public void printMessage(String message) {
				System.out.println("\nAnonymous message: " + message);

			}
		};

		// Using Lambda expression with single line statement
		FunctionalInterfaceSampleHere lambda1 = (String messageToPrint) -> System.out
				.println("\nLambda msg: " + messageToPrint);

		// Using Lambda expression with statement block {} in body
		FunctionalInterfaceSampleHere lambda2 = (String messageToPrint) -> {
			System.out.println("\n===============");
			System.out.println("Lambda msg: " + messageToPrint);
			System.out.println("===============");
		};

		lambda1.printMessage("Hi!");
		lambda2.printMessage("Hi!");
		anonymous.printMessage("Hi!");

	}
}

// Functional Interface = An interface with only one method
interface FunctionalInterfaceSampleHere {
	public void printMessage(String message);
}
