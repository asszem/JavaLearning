package ClassesAndObjects.H_Ch13_GenericTypes.ItaratorAndras;

import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author Andras Olah (olahandras78@gmail.com)
 */
public class IterableArrayList<E> implements Iterable<E> {

	ArrayList<E> items = new ArrayList();

	//Constructor to add all values of type E added by the constructor argument to the Arry List 'items'
	public IterableArrayList(E[] values) {
		for (int i = 0; i < values.length; i++) {
			items.add(values[i]);
		}
	}

	/**
	 * Mandatory method to be implemented from the {@code Iterable<E>} interface
	 *
	 * @return returns a new {@code Iterator<E>} type object. {@code Iterator<E>} is an abstract class. There must be a class created that implements {@code Iterator<E>}
	 */
	@Override
	public Iterator<E> iterator() {
		//the ArrayListIteratorImp implements the Iterator<E> interface
		return new ArrayListIteratorImp();
	}

	/**
	 * The implementation of methods in {@code Iterator<E>} interface
	 * <br>
	 * Note: Netbeans can automatically implement the skeleton of abstract methods
	 */
	public class ArrayListIteratorImp implements Iterator<E> { //implements ITERATOR<E> interface and not ITERABLE<E> interface!

		int pointer = 0; //To get the next item

		//Constructor for the Iterator implementation class
		public ArrayListIteratorImp() {
			System.out.println("Iterator object created.");
		}

		@Override
		public boolean hasNext() {
			return (pointer < items.size());
		}

		@Override
		public E next() {
			return items.get(pointer++);
		}

	}

	public static void main(String[] args) {
		String[] stringList = {"One", "Two", "Three", "Four"};
		//Create a new instance 
		IterableArrayList<String> instance = new IterableArrayList(stringList);

		//Run the collection based for-loop
		for (String element : instance) {
			System.out.println(element);
		}

		//Instatiating a Double type object
		double[] doubleList = {1.0, 2.0, 3.0, 42}; //input array is a primitive type. Must be converted to Double wrapper object
		Double[] doubleArray = new Double[doubleList.length]; //this will be the input argument of the constructor
		for (int i = 0; i < doubleList.length; i++) {
			doubleArray[i] = Double.valueOf(doubleList[i]);
		}

		//Run the collection based for-loop with Double type
		IterableArrayList<Double> instance2 = new IterableArrayList(doubleArray);
		for (Double element : instance2) {
			System.out.println(element);
		}
	}

}
