/* Requirement: 

Ex3

Modify your Stack<> type to make it serializable. 

Demonstrate that this is the case by creating a Stack<String> object, adding 10 strings to it, 
serializing and deserializing the Stack<String> object, and listing the contents of the deserialized stack.
 */
package HortonExercises.Ch13.Stack_Andras;

import java.io.Serializable;

/**
 *
 * @author Andras Olah (olahandras78@gmail.com)
 */
public class Stack_Andras_Serializable<T extends Object & Serializable> implements Serializable {

	StackItem<T> rootStackItem;
	StackItem<T> topStackItem;
	private static final long serialVersionUID = 7001L;

	public Stack_Andras_Serializable() {
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

	public void listAll() {
		if (rootStackItem == null) {
			System.out.println("The stack is empty");			//The stack is empty
		} else {
			StackItem<T> currentStackItem = topStackItem;
			System.out.print("Top");
			int counter = 1;
			while (currentStackItem.prevStackItem != null) { 		//Display everything from the top
				System.out.println("Item #" + counter++ + "=\t" + currentStackItem.objectInStack);
				currentStackItem = currentStackItem.prevStackItem;
			}
			System.out.print("Root");
			System.out.println("Item #" + counter++ + "=\t" + currentStackItem.objectInStack); //Display the final item 
		}
	}

	private class StackItem<T extends Serializable> implements Serializable {

		private static final long serialVersionUID = 7001L;
		T objectInStack;
		StackItem nextStackItem;
		StackItem prevStackItem;

	}
}
