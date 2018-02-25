//Source: https://www.tutorialspoint.com/java8/java8_lambda_expressions.htm
package LambdaAndStream.LambdaExamples;

public class MathOperations {
	
	
	public static void main(String args[]) {
		MathOperations mathOperations = new MathOperations();

		// This will create multiple instances of MathOperation interface with different implementations of it's only method
		
		// with type declaration
		MathOperation addition = (int a, int b) -> a + b;

		// with out type declaration
		MathOperation subtraction = (a, b) -> a - b;

		// with return statement along with curly braces
		MathOperation multiplication = (int a, int b) -> {
			return a * b;
		};

		// without return statement and without curly braces
		MathOperation division = (int a, int b) -> a / b;

		System.out.println("10 + 5 = " + mathOperations.operate(10, 5, addition));
		System.out.println("10 - 5 = " + mathOperations.operate(10, 5, subtraction));
		System.out.println("10 x 5 = " + mathOperations.operate(10, 5, multiplication));
		System.out.println("10 / 5 = " + mathOperations.operate(10, 5, division));

		// without parenthesis
		GreetingService greetService1 = message -> System.out.println("Hello " + message);

		// with parenthesis
		GreetingService greetService2 = (message) -> System.out.println("Hello " + message);

		greetService1.sayMessage("Mahesh");
		greetService2.sayMessage("Suresh");
	}

	//This is the Functional Interface. The method operation will be implemented using Lambda expressions
	interface MathOperation {
		int operation(int a, int b);
	}

	interface GreetingService {
		void sayMessage(String message);
	}

	//The parameter is a MathOperation Interface... based on it's implementation, different results will be returned
	private int operate(int a, int b, MathOperation mathOperation) {
		return mathOperation.operation(a, b);
	}
}
