package ClassesAndObjects.H_Ch13_GenericTypes;

/**
 * The abstract class {@code Number} is the superclass of platform classes representing numeric values that are convertible to the primitive types {@code byte}, {@code double},
 * {@code float}, {@code
 * int}, {@code long}, and {@code short}.
 *
 * @author Andras Olah (olahandras78@gmail.com)
 * @param <T>
 */
public class BoundedGenericClass<T extends Number> { //Number is a superclass of all wrapper classes of primitive types

	T aNumber; //A field in the class. The type can be Number or any subclass of Number

	//Constructor
	public BoundedGenericClass(T constrArgument) {
		aNumber = constrArgument;
	}

	//Method to return the fraction of the Number, ie. 3.5 -> 0.5
	public double getFraction() {
		return aNumber.doubleValue() - aNumber.intValue();  //doubleValue() and intValue() methods are defined in Numbers
	}
	
	boolean isAbsEqual(BoundedGenericClass<? extends Number> comparedWith) { //Any type within boundaries are allowed
		//Math.abs(double numberToCheck) returns a double
		if (Math.abs(this.aNumber.doubleValue()) == Math.abs(comparedWith.aNumber.doubleValue())) {
			return true;
		} else {
			return false;
		}
	}
	
	public static void main(String[] args) {
		//Instantiating classes
		BoundedGenericClass integerObject = new BoundedGenericClass(123); //because Integer is provided, Integer class is created
		BoundedGenericClass<Integer> integerObject2 = new BoundedGenericClass(456); //This also works
		BoundedGenericClass<Double> doubleObject1 = new BoundedGenericClass<>(42.42);
		BoundedGenericClass<Double> doubleObject2 = new BoundedGenericClass<>(-123.00);
//		BoundedGenericClass<String> willNotWork = new BoundedGenericClass<>("Test");

		//Test the get fraction method
		System.out.println(doubleObject1.getFraction());
		System.out.println(integerObject.getFraction());

		//Test the abs comparison method
		System.out.println(integerObject.isAbsEqual(doubleObject1));
		System.out.println(integerObject.isAbsEqual(doubleObject2));
		
	}
}
