package ClassesAndObjects.GenericTypes;

/**
 * The abstract class {@code Number} is the superclass of platform classes representing numeric values that are convertible to the primitive types {@code byte}, {@code double},
 * {@code float}, {@code
 * int}, {@code long}, and {@code short}.
 *
 * @author Andras Olah (olahandras78@gmail.com)
 * @param <T>
 */
public class GenericClassBounded<T extends Number> { //Number is a superclass of all wrapper classes of primitive types

	T aNumber; //A field in the class. The type can be Number or any subclass of Number

	//Constructor
	public GenericClassBounded(T constrArgument) {
		aNumber = constrArgument;
	}

	//Method to return the fraction of the Number, ie. 3.5 -> 0.5
	public double getFraction() {
		return aNumber.doubleValue() - aNumber.intValue();  //doubleValue() and intValue() methods are defined in Numbers
	}
	
	boolean isAbsEqual(GenericClassBounded<? extends Number> comparedWith) { //Any type within boundaries are allowed
		//Math.abs(double numberToCheck) returns a double
		if (Math.abs(this.aNumber.doubleValue()) == Math.abs(comparedWith.aNumber.doubleValue())) {
			return true;
		} else {
			return false;
		}
	}
	
}
