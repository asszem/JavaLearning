package HortonExercises.Ch13.Stack_Andras;

/**
 *
 * @author Andras Olah (olahandras78@gmail.com)
 */
public class Try_Stack_Andras {

	public static void main(String[] args) {

//Demonstrate the operation of your Stack<> implementation by storing and retrieving 10 strings and 10 Double objects in stacks of a suitable type.
		//Instantinate objects of type string and double
		Stack_Andras<String> stringStack = new Stack_Andras<>();
		Stack_Andras<Double> doubleStack = new Stack_Andras<>();

		//Create test data for 10 strings and 10 random number
		String[] strings = {"One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten"};
		Double[] numbers = new Double[10];
		for (int i = 0; i < numbers.length; i++) {
			numbers[i] = Math.random() * 100;
		}
		//Display the content of String array for verification
		System.out.println("Original String Array:");
		for (int i = 0; i < numbers.length; i++) {
			System.out.println(strings[i]);
		}
		//Display the content of String array for verification
		System.out.println("Original Double Array:");
		for (int i = 0; i < numbers.length; i++) {
			System.out.println(numbers[i]);
		}

		//push all data to stack
		for (int i = 0; i < numbers.length; i++) {
			stringStack.push(strings[i]);
			doubleStack.push(numbers[i]);
		}

		//pop all data from Strings
		System.out.println("Pop from String array");
		for (int i = 0; i < numbers.length; i++) {
			System.out.println(stringStack.pop());
		}

		//pop all data from Numbers
		System.out.println("Pop from Double array");
		for (int i = 0; i < numbers.length; i++) {
			System.out.println(doubleStack.pop());
		}

		//Demonstrate that pop returns null when stack is empty
		System.out.println("Demonstrate that pop returns null when stack is empty");
		System.out.println(stringStack.pop());
		System.out.println(doubleStack.pop());
	}
}
