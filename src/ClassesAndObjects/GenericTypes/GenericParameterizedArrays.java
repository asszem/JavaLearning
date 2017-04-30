package ClassesAndObjects.GenericTypes;

import java.lang.reflect.GenericArrayType;

/**
 *
 * @author Andras Olah (olahandras78@gmail.com)
 *
 * Source Horton P489, Chapter 13: Generics Schildt p 473, Chapter 13: Generics
 */
public class GenericParameterizedArrays<T extends Number> {

	T genericObject;
	T[] genericArray;
	GenericParameterizedArrays<?>[] wildcardArray = new GenericParameterizedArrays<?>[10];

	//Cannot instantiate an array whose element type is a type parameter, the compiler can't tell what type of array to actually create
	//genericArray =new T[10]; //Error!  Can't create an array of T

	//No-arg constructor
	public GenericParameterizedArrays() {
	}

	//Constructor to pass a valid object to the genericArray field
	public GenericParameterizedArrays(T constructorParameterGenericObject, T[] constructorParameterInputArray) {
		genericObject = constructorParameterGenericObject;
		genericArray = constructorParameterInputArray; //Existing array can passed
	}

	public static void main(String[] args) {
		//Valid! Pass an existing valid array to the object constructor
		Integer[] integersArray = {1, 2, 3, 4, 5};
		GenericParameterizedArrays<Integer> instanceInteger = new GenericParameterizedArrays<>(42, integersArray);

		//Invalid! Trying to pass a different type array to the type-specific object
		Double[] doubleArray = {1.0, 2.0, 3.0, 42.42};
		//GenericParameterizedArrays<Integer> instanceInteger2 = new GenericParameterizedArrays<>(42, doubleArray); //Error!

		//Invalid! Cannot create an array of type-specific generic references
		//GenericParameterizedArrays<Integer> integers[] =new GenericParameterizedArrays<Integer>[10]; //Error! 
		//Valid! Create an array with wildcard type parameter
		GenericParameterizedArrays<?>[] wildcardArray = new GenericParameterizedArrays<?>[10];

		//Add elements to the wildcardArray
		wildcardArray[0]=new GenericParameterizedArrays<>();
		wildcardArray[1]=instanceInteger;
	

;

	}
}
