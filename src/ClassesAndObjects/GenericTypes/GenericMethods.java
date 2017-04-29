package ClassesAndObjects.GenericTypes;

/**
 *
 * @author Andras Olah (olahandras78@gmail.com)
 */
public class GenericMethods {  //Note that this class is NOT generic

	//Static method with Type paramaters
	static <T extends Comparable<T>, V extends T> boolean areArraysEqual(T[] arrayOne, V[] arrayTwo) {
		//If the arrays length differ, they are ot the same
		if (arrayOne.length != arrayTwo.length) {
			return false;
		}
		//Iterate throug arrays and compare each items
		for (int i = 0; i < arrayOne.length; i++) {
			if (!arrayOne[i].equals(arrayTwo[i])) { //If any of the elements are not equal. Element can be any type of comparable object
				return false;
			}
		}
		return true; //if this is reached, the arrays must be equal
	}

	//Static method can have their own parameteres
	public static <V> V isSomething(V input){
		System.out.println("Input argument class: " + input.getClass().getSimpleName());
		return input;
	}

	public static void main(String[] args) {
		//Create arrays
		Integer[] integers1 = {1,2,3,4,5};
		Integer[] integers2 = {1,2,3,4,5};
		Integer[] integers3 = {1,2,3,4};
		Integer[] integers4 = {1,2,3,4,999};

		//Will not work
		int[] ints = {1,2,3,4,5}; //auto-boxing for arrays is nor working
		Double[] double1 = {1.0,2.0,3.0,4.0,5.0};

		//Run the method with valid type arguments
		System.out.println(areArraysEqual(integers1,  integers2));
		System.out.println(areArraysEqual(integers1,  integers3));
		System.out.println(areArraysEqual(integers1,  integers4));
//		System.out.println(areArraysEqual(integers1,  double1)); //ERROR! Double class is out of bound
//		System.out.println(areArraysEqual(integers1,  ints)); 	 //ERROR! int primitive is not autoboxed to Integers array

		//Call static method, that will return whatever type was provided as method argument
		System.out.println(isSomething("This is a string"));
		System.out.println(isSomething(42));
	}

}
