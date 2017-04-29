
package ClassesAndObjects.GenericTypes;

import java.util.ArrayList;

/**
 * 
 * @author Andras Olah (olahandras78@gmail.com)
 * @param <T> type parameter
 */
public class GenericClassDefinition<T> {
	T genericVariable; //type will be provided when the class is instantiated, and 'T' will be replaced to it

	//No-args constructor
	public GenericClassDefinition(){
	}

	//Constructor with a type variable
	public GenericClassDefinition(T constructorInput){
		genericVariable= constructorInput;  //the variable can be anything that was given as type argument
	}

	//Getter method for the generic variable
	public T getGenericVariable(){
		return genericVariable;
	}

	//Setter method for the generic variable
	public void setGenericVariable(T newValue){
		genericVariable=newValue;
	}

	//Static method CAN't use class definition type parameters!
//	static T V notGood(R parameter){

	//Static method can have their own parameteres
	public static <V> V isSomething(V input){
		System.out.println("Input argument class: " + input.getClass().getSimpleName());
		return input;
	}

	@Override
	public String toString(){
		StringBuilder returnStr=new StringBuilder();
		returnStr.append("Runtime Class name: ").append(this.getClass().getSimpleName());
		returnStr.append("\nType variable: ").append(this.getClass().getTypeParameters()[0]);
		returnStr.append("\nGeneric variable value= ").append(genericVariable);
		return returnStr.toString();
	}

	public static void main(String[] args) {
		ArrayList genericClasses =new ArrayList();
		//Instantiation without arguments
		GenericClassDefinition<String> genericClassForStrings =new GenericClassDefinition<>();
		GenericClassDefinition<Integer> genericClassForIntegers = new GenericClassDefinition<>();
		//Instantiation with arguments
		GenericClassDefinition<String> genericClassForStrings2 =new GenericClassDefinition<>("String argument to constructor");
		GenericClassDefinition<Integer> genericClassForIntegers2= new GenericClassDefinition<>(123456);
		//Use setter methods -> note that appropriate arguments must be provided
		genericClassForStrings.setGenericVariable("Updated with setter method");
		genericClassForIntegers.setGenericVariable(42); //autoboxing, casts int 42 to wrapper class Integer

		
		//Instantiate a String type class that has only the ArrayList reference
		genericClasses.add(new GenericClassDefinition<>("New String class to arrayList"));
		//Add the existing classes to the ArrayList
		genericClasses.add(genericClassForStrings);
		genericClasses.add(genericClassForStrings2);
		genericClasses.add(genericClassForIntegers);
		genericClasses.add(genericClassForIntegers2);
		//Display the genericClasses array
		for (int i=0;i<genericClasses.size();i++){
			System.out.println("Object number: " + i);
			System.out.println(genericClasses.get(i));
			System.out.println("");
		}

		//Generating compiler warning
		GenericClassDefinition<String> stringTypeGCS =new GenericClassDefinition<>();
		Object obj = (Object) stringTypeGCS;  //cast the string generic cast to Object 
		System.out.println("String cast to object:");
		System.out.println(obj);
		GenericClassDefinition<Double> doubleTypeGCS;
		doubleTypeGCS = (GenericClassDefinition<Double>)obj;


	}

}
