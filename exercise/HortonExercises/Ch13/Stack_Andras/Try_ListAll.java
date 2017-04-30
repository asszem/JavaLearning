
package HortonExercises.Ch13.Stack_Andras;

/**
 * 
 * @author Andras Olah (olahandras78@gmail.com)
 */
public class Try_ListAll {
	public static void main(String[] args) {
		
		//Instantinate objects of type string and double
		Stack_Andras<String> stringStack = new Stack_Andras<>();
		Stack_Andras<Double> doubleStack = new Stack_Andras<>();

		//Create test data for 10 strings and 10 random number
		String[] strings = {"One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten"};
		Double[] numbers = new Double[10];
		for (int i = 0; i < numbers.length; i++) {
			numbers[i] = Math.random() * 100;
		}

		stringStack.listAll();
		doubleStack.listAll();

		//push all data to stack
		for (int i = 0; i < numbers.length; i++) {
			stringStack.push(strings[i]);
			doubleStack.push(numbers[i]);
		}

		stringStack.listAll();
		doubleStack.listAll();
		
		stringStack=new Stack_Andras<>();
		for (int i = 0; i < numbers.length; i++) {
			System.out.println("Iteration: " + i);
			stringStack.push(strings[i]);
			stringStack.listAll();
			stringStack.pop();
//			if (i%2==0) stringStack.pop();
			stringStack.listAll();
			System.out.println("");
		}
	}
}
