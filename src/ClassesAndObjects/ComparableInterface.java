/*
CompareTo with Strings
	00 = Andras.compareTo(Andras)
	-1 = Andras.compareTo(Bob)
	01 = Bob.compareTo(Andras)

CompareTo with Numbers
	00 = 1.compareTo(1)
	-1 = 1.compareTo(2)
	01 = 2.compareTo(1)

CompareTo with Booleans
	00 = true.compareTo(true)
	01 = true.compareTo(false)
	-1 = false.compareTo(true)
*/
package ClassesAndObjects;

/**
 *
 * @author Andras Olah (olahandras78@gmail.com)
 */
public class ComparableInterface {

	//Any type of instantiation is allowed, so the Comparable method must be able to handle any type of comparison
	//TODO find if there is a way to determine the TYPE of a generic class
	class ComparableGenericClass<T> implements Comparable<T> {

		T objectInClass;

		public ComparableGenericClass(T object) {
			this.objectInClass = object;
		}

		//it returns –1, 0, or +1 
		//depending on whether the current object is less than, equal to, or greater than the argument. 
		@Override
		public int compareTo(T targetObject) {
			if (this.equals(targetObject)) {
				return 0;
			}
			//if class type is String -- can not be determined because of type erasure...
			if (this.getClass().getName().equals("String")) {
				return this.compareTo(targetObject);

			}
			//all other possible class type comparison methods should be implemented here...
//			return this.compareTo(targetObject); //This recursion will generate an infinite loop
			return 0; //just to make the method run
		}
	}//end of inner class

	//Only ComparableSpecificClass type of instantiation is enabled, so the Comparable method can be written specifically
	class ComparableSpecificClass implements Comparable<ComparableSpecificClass> {

		boolean isManager;

		//Constructor
		public ComparableSpecificClass(boolean constructorInput) {
			this.isManager = constructorInput;
		}

		/*
			CompareTo with Booleans
			00 = true.compareTo(true)
			01 = true.compareTo(false)
			-1 = false.compareTo(true)
		*/
		@Override
		public int compareTo(ComparableSpecificClass argumentObject) {
			if (this.isManager == argumentObject.isManager) { 			//Both objects are Manager
				return 0;
			} else if (this.isManager && !argumentObject.isManager) { 	//This object is Manager (This is Greater)
				return 1;
			} else if (!this.isManager && argumentObject.isManager) { 	//Argument object is Manager (Argument is Greater)
				return -1;
			}
			//should not reach here
			return 0;
		}

	}

	public static void main(String[] args) {
		//<editor-fold desc="Compare Strings this.compareTo(that) -1 0 +1">
		/*
		this.person 		compared to person		returns   
		Andras			=	Andras					0
		Andras			<	Bob						-1 
		Bob				>	Andras					+1

		compareTo() method description: 
		-1 if this string is lexicographically less than the string argument; 
		+1 if this string is lexicographically greater than the string argument

		 */
		//</editor-fold>
		System.out.println("CompareTo with Strings");
		String[] names = {"Andras", "Bob"};
		System.out.printf("%3$02d = %s.compareTo(%s)%n", names[0], names[0], names[0].compareTo(names[0]));
		System.out.printf("%3$02d = %s.compareTo(%s)%n", names[0], names[1], names[0].compareTo(names[1]));
		System.out.printf("%3$02d = %s.compareTo(%s)%n", names[1], names[0], names[1].compareTo(names[0]));
		System.out.println("");

		System.out.println("CompareTo with Numbers");
		Integer[] numbers = {1, 2};
		System.out.printf("%3$02d = %s.compareTo(%s)%n", numbers[0], numbers[0], numbers[0].compareTo(numbers[0]));
		System.out.printf("%3$02d = %s.compareTo(%s)%n", numbers[0], numbers[1], numbers[0].compareTo(numbers[1]));
		System.out.printf("%3$02d = %s.compareTo(%s)%n", numbers[1], numbers[0], numbers[1].compareTo(numbers[0]));
		System.out.println("");

		Boolean[] booleans ={true, false};
		System.out.println("CompareTo with Booleans");
		System.out.printf("%3$02d = %s.compareTo(%s)%n", booleans[0], booleans[0], booleans[0].compareTo(booleans[0]));
		System.out.printf("%3$02d = %s.compareTo(%s)%n", booleans[0], booleans[1], booleans[0].compareTo(booleans[1]));
		System.out.printf("%3$02d = %s.compareTo(%s)%n", booleans[1], booleans[0], booleans[1].compareTo(booleans[0]));
		System.out.println("");
	}
}
