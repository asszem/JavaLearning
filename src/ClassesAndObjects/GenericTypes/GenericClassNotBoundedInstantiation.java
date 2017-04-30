package ClassesAndObjects.GenericTypes;

import java.util.ArrayList;

/**
 *
 * @author Andras Olah (olahandras78@gmail.com)
 */
public class GenericClassNotBoundedInstantiation {

	public static void main(String[] args) {

		ArrayList genericClasses = new ArrayList();

		//<editor-fold desc="Instatiation of a RAW type">
		GenericClassNotBounded rawInstance = new GenericClassNotBounded("Raw instance");
		genericClasses.add(rawInstance);
		//</editor-fold>

		//<editor-fold desc="Instantiation with a wildcard">
		GenericClassNotBounded<?> wildCard = new GenericClassNotBounded<>();
		System.out.println(wildCard.getGenericVariable());
//		System.out.println(wildCard.returnDouble(100.0));
		genericClasses.add(wildCard);
		//</editor-fold>

		//<editor-fold desc="Instatiation with and without constructor arguments">
		//Instantiation without constructor arguments
		GenericClassNotBounded<String> genericClassForStrings = new GenericClassNotBounded<>();
		genericClasses.add(genericClassForStrings);
		GenericClassNotBounded<Integer> genericClassForIntegers = new GenericClassNotBounded<>();
		genericClasses.add(genericClassForIntegers);

		//Instantiation with constructor arguments
		GenericClassNotBounded<String> genericClassForStrings2 = new GenericClassNotBounded<>("String argument to constructor");
		genericClasses.add(genericClassForStrings2);
		GenericClassNotBounded<Integer> genericClassForIntegers2 = new GenericClassNotBounded<>(123456);
		genericClasses.add(genericClassForIntegers2);
		//</editor-fold>

		//<editor-fold desc="Instantiate a String type class that has only the ArrayList reference">
		genericClasses.add(new GenericClassNotBounded<>("New String class to arrayList"));
		//</editor-fold>

		//<editor-fold desc="Use setter methods (note that appropriate arguments must be provided)">
		genericClassForStrings.setGenericVariable("Updated with setter method");
		genericClassForIntegers.setGenericVariable(42); //autoboxing, casts int 42 to wrapper class Integer
		//</editor-fold>

		//<editor-fold desc="Display the genericClasses ArrayList">
		for (int i = 0; i < genericClasses.size(); i++) {
			System.out.println("Object number: " + i);
			System.out.println(genericClasses.get(i));
			System.out.println("");
		}
		//</editor-fold>

	}
}
