package ClassesAndObjects.GenericTypes;

/**
 *
 * @author Andras Olah (olahandras78@gmail.com)
 */
public class GenericClassBoundedInstantiation {

	public static void main(String[] args) {
		//Instantiating classes
		GenericClassBounded integerObject = new GenericClassBounded(123); //because Integer is provided, Integer class is created
		GenericClassBounded<Integer> integerObject2 = new GenericClassBounded(456); //This also works
		GenericClassBounded<Double> doubleObject1 = new GenericClassBounded<>(42.42);
		GenericClassBounded<Double> doubleObject2 = new GenericClassBounded<>(-123.00);
//		GenericClassBounded<String> willNotWork = new GenericClassBounded<>("Test");

		//Test the get fraction method
		System.out.println(doubleObject1.getFraction());
		System.out.println(integerObject.getFraction());

		//Test the abs comparison method
		System.out.println(integerObject.isAbsEqual(doubleObject1));
		System.out.println(integerObject.isAbsEqual(doubleObject2));

	}
}
