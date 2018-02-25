/*
 * Simple example sources
 * http://www.oracle.com/webfolder/technetwork/tutorials/obe/java/Lambda-QuickStart/index.html
 * */
package LambdaAndStream;

public class BasicExamples {

	public static void main(String[] args) {

		// Using a standard named class which implements the Functional Interface
		ClassWithAName classWithAName = new ClassWithAName();

		// Using an Anonymous class to implement (override) the method defined in the Functional Interface
		FunctionalInterfaceSampleHere anonymous = new FunctionalInterfaceSampleHere() {

			@Override
			public void printMessage(String message) {
				System.out.println("\nAnonymous message: " + message);

			}
		};

		// Using Lambda expression with single line statement to implement (override) the method
		FunctionalInterfaceSampleHere lambda1 = (String messageToPrint) -> System.out
				.println("\nLambda msg: " + messageToPrint);

		// Using Lambda expression with statement block {} in body to implement (override) method and add some more code
		FunctionalInterfaceSampleHere lambda2 = (String messageToPrint) -> {
			System.out.println("\n===============");
			System.out.println("Lambda msg: " + messageToPrint);
			System.out.println("===============");
		};

		classWithAName.printMessage("Hi!");
		anonymous.printMessage("Hi!");
		lambda1.printMessage("Hi!");
		lambda2.printMessage("Hi!");
	}
}

// Functional Interface = An interface with only one method
interface FunctionalInterfaceSampleHere {
	public void printMessage(String message);
}

// This is a standard class that implements the Functional Interface
class ClassWithAName implements FunctionalInterfaceSampleHere {

	@Override
	public void printMessage(String message) {
		System.out.println("\nclassWithAName message: " + message);
	}

}
