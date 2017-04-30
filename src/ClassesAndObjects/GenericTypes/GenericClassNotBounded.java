package ClassesAndObjects.GenericTypes;

import java.util.ArrayList;

/**
 *
 * @author Andras Olah (olahandras78@gmail.com)
 * @param <T> type parameter
 */
public class GenericClassNotBounded<T> {

	T genericVariable; //type will be provided when the class is instantiated, and 'T' will be replaced to it

	//No-args constructor
	public GenericClassNotBounded(){
	}

	//Constructor with a type variable
	public GenericClassNotBounded(T constructorInput) {
		genericVariable = constructorInput;  //the variable can be anything that was given as type argument
	}

	//Method return type is a Type Parameter
	public T getGenericVariable() {
		return genericVariable;
	}

	//Method parameter is a Type parameter
	public void setGenericVariable(T newValue) {
		genericVariable = newValue;
	}

	//Static method CAN't use class definition type parameters!
	//public static T getGenericVariable() {} //ERROR!

	//Static method with their OWN type parameters - V and not T!
	public static <V> V isSomething(V input) {
		System.out.println("Input argument class: " + input.getClass().getSimpleName());
		return input;
	}

	@Override
	public String toString() {
		StringBuilder returnStr = new StringBuilder();
		returnStr.append("Generic variable value= ").append(genericVariable);
		returnStr.append("\nType parameter: ").append(this.getClass().getTypeParameters()[0]);
		returnStr.append("\nRuntime Class name: ").append(this.getClass().getSimpleName());
		return returnStr.toString();
	}
}
