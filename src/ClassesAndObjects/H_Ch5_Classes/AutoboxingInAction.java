package ClassesAndObjects.H_Ch5_Classes;
/*
Circumstances can arise surprisingly often where you want to pass values of a primitive type to a method
that requires the argument to be a reference to an object. 

The compiler supplies automatic conversions of primitive values to the corresponding class type when circumstances permit this.
This can arise when you pass a value of type int to a method where the parameter type is type Integer, for example.
Conversions from a primitive type to the corresponding class type are called boxing conversions
Automatic conversions of this kind are described as autoboxing.

The compiler also inserts unboxing conversions when necessary to convert a reference to an object of a wrapper
class for a primitive type such as double to the value that it encapsulates.

*/

public class AutoboxingInAction {

	public static void main(String[] args) {
		int[] values = {3, 97, 55, 22, 12345};
		Integer[] objs = new Integer[values.length];                       // Array to store Integer objects

		// Call method to cause boxing conversions
		for (int i = 0; i < values.length; ++i) {
	// The compiler arranges for autoboxing to occur by inserting a boxing conversion to convert the integer value to an object of type Integer
			objs[i] = values[i];  //így is működik, ide nem értem, minek rakott metódust.
					//boxInteger(values[i]); //values[i] egy int tömb literál, de az autoboxing Integer objektet csinál belőle
		}

		// Use method to cause unboxing conversions
		for (Integer intObject : objs) {// passes each reference to an Integer object from the objs array to the unboxInteger() method
			unboxInteger(intObject);
			/*
			Because you have specified the method parameter type as type int, the method
			cannot accept a reference to an object of type Integer as the argument directly. The compiler inserts an
			unboxing conversion to obtain the value of type int that the object encapsulates. This value is then passed to
			the method, and you output it
			*/
		}
	}

	// Method to cause boxing conversion
	public static Integer boxInteger(Integer obj) {
		return obj;
	}

	// Method to cause unboxing conversion
	public static void unboxInteger(int n) {
		System.out.println("value = " + n);
	}
}
