/*
Requirement: 

A stack is a container that stores objects in a manner indicated by its name — a vertical stack where only
the object at the top of the stack is accessible. 

It works rather like a sprung stack of plates in a cafeteria.
Only the top plate is at counter level and, therefore, is the only one you can access. 
When you add a plate to the stack, the existing plates are pushed down so the new plate is now the one that you can access.

Define a generic Stack<> type with a method push() that adds the object that is passed as an argument
to the top of the stack, and with a method pop() that removes and returns the object that is currently at
the top of the stack. 

The pop() method should return null when the stack is empty. 

Demonstrate the operation of your Stack<> implementation by storing and retrieving 10 strings and 10 Double objects in
stacks of a suitable type.

 */
package HortonExercises.Ch13.Ex1;

/**
 *
 * @author Andras Olah (olahandras78@gmail.com)
 */
public class Stack_Andras<T> {

	StackItem<T> rootStackItem;
	StackItem<T> topStackItem;

	//Constructor
	public Stack_Andras() {
		rootStackItem = null;
		topStackItem = null;
	}

	public void push(T objectToPush) {
		if (rootStackItem == null) {
			rootStackItem = new StackItem<>(); //Create the root stack item
			rootStackItem.objectInStack = objectToPush;		//Add the object to the root stack item
			topStackItem = rootStackItem;					//Set the root item as the top item
		} else {
			StackItem<T> newStackItem = new StackItem<>();  	//create a new Stack Item object
			newStackItem.objectInStack = objectToPush;			//add the object to the new stack item
			newStackItem.prevStackItem = topStackItem;			//set reference in new stack item to point to previous top
			topStackItem.nextStackItem = newStackItem;			//set reference in old topstack item to point to new 
			//Problem... how to set new stack item to top, 
			topStackItem = newStackItem;
		}
	}

	public T pop() {
		if (rootStackItem == null) {
			return null;
		} else {
			final T objectToReturn = topStackItem.objectInStack; 	//Get the object to return
			if (topStackItem.prevStackItem != null) {				//If there is available preve item
				topStackItem = topStackItem.prevStackItem;			//Set the topStackItem to the prevStackItem
			} else {												//There are no more items in stack. Set everything null
				topStackItem = null;
				rootStackItem = null;
			}
			return objectToReturn;
		}
	}

	public T getStackItemObject(StackItem<T> itemToGet) {
		if (itemToGet != null) {
			return itemToGet.objectInStack;
		} else {
			return null;						//For the Junit test assertion
		}
	}

	private class StackItem<T> {

		T objectInStack;
		StackItem nextStackItem;
		StackItem prevStackItem;

	}

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
		for (int i=0;i<numbers.length;i++){
			System.out.println(strings[i]);
		}
		//Display the content of String array for verification
		System.out.println("Original Double Array:");
		for (int i=0;i<numbers.length;i++){
			System.out.println(numbers[i]);
		}

		//push all data to stack
		for (int i = 0; i < numbers.length; i++) {
			stringStack.push(strings[i]);
			doubleStack.push(numbers[i]);
		}
		
		//pop all data from Strings
		System.out.println("Pop from String array");
		for (int i=0;i<numbers.length;i++){
			System.out.println(stringStack.pop());	
		}

		//pop all data from Numbers
		System.out.println("Pop from Double array");
		for (int i=0;i<numbers.length;i++){
			System.out.println(doubleStack.pop());	
		}
	}
}
