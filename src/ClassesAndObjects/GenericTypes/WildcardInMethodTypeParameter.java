package ClassesAndObjects.GenericTypes;

/**
 *
 * @author Andras Olah (olahandras78@gmail.com)
 */
public class WildcardInMethodTypeParameter {

	class GenericClass<T> {

		T value;
		String classType;
		String inputGenericString;

		//Constructor
		GenericClass(String classType) {
			this.classType = classType;
		}

		void wildcardMethodTypeParameter(GenericClass<?> input) {
			System.out.println("Class type: " + this.classType);
			System.out.println("Input type: " + input.classType);
		}

		void typeMethodTypeParameter(GenericClass<T> input) {
			System.out.println("Class type: " + this.classType);
			System.out.println("Input type: " + input.classType);
		}

	}

	public static void main(String[] args) {
		WildcardInMethodTypeParameter instance = new WildcardInMethodTypeParameter();
		GenericClass<String> instanceString = instance.new GenericClass<>("This is String");
		GenericClass<Double> instanceDouble = instance.new GenericClass<>("This is Double");
		GenericClass<Integer> instanceInteger = instance.new GenericClass<>("This is Integer");
		instanceString.wildcardMethodTypeParameter(instanceString);
		instanceString.wildcardMethodTypeParameter(instanceDouble);

		instanceString.typeMethodTypeParameter(instanceString);
		//instanceString.typeMethodTypeParameter(instanceDouble); //ERROR!  See Horton p. 489 for details
	}
}
