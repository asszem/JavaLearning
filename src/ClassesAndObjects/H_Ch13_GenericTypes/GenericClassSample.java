
package ClassesAndObjects.H_Ch13_GenericTypes;

import java.util.ArrayList;

/**
 * 
 * @author Andras Olah (olahandras78@gmail.com)
 * @param <T> type parameter
 */
public class GenericClassSample<T> {
	T genericVariable; //type will be provided when the class is instantiated, and 'T' will be replaced to it

	//No-args constructor
	public GenericClassSample(){
	}

	//Constructor with a type variable
	public GenericClassSample(T constructorInput){
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

	@Override
	public String toString(){
		StringBuilder returnStr=new StringBuilder();
		returnStr.append("Class name: ").append(this.getClass().getSimpleName());
		returnStr.append("\nType variable: ").append(this.getClass().getTypeParameters()[0]);
		returnStr.append("\nGeneric variable value= ").append(genericVariable);
		return returnStr.toString();
	}

	public static void main(String[] args) {
		ArrayList genericClasses =new ArrayList();
		//Instantiation without arguments
		GenericClassSample<String> genericClassForStrings =new GenericClassSample<>();
		GenericClassSample<Integer> genericClassForIntegers = new GenericClassSample<>();
		//Instantiation with arguments
		GenericClassSample<String> genericClassForStrings2 =new GenericClassSample<>("String argument to constructor");
		GenericClassSample<Integer> genericClassForIntegers2= new GenericClassSample<>(123456);
		//Use setter methods -> note that appropriate arguments must be provided
		genericClassForStrings.setGenericVariable("Updated with setter method");
		genericClassForIntegers.setGenericVariable(42);

		
		//Instantiate a String type class that has only the ArrayList reference
		genericClasses.add(new GenericClassSample<>("New String class to arrayList"));
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

	}

}
