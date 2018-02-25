/*Source:
 *  https://www.codementor.io/eh3rrera/using-java-8-method-reference-du10866vx
 *  https://docs.oracle.com/javase/tutorial/java/javaOO/methodreferences.html
 *  
 *  You use lambda expressions to create anonymous methods. 
 *  
 *  Sometimes, however, a lambda expression does nothing but call an existing method. 
 *  In those cases, it's often clearer to refer to the existing method by name.
 *  
 *  Method references enable you to do this; 
 *  they are compact, easy-to-read lambda expressions for methods that already have a name.
   
	Instead of using
	AN ANONYMOUS CLASS
	you can use
	A LAMBDA EXPRESSION
	And if this just calls one method, you can use
	A METHOD REFERENCE
 */

package Java8Stuff.MethodReferences;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public class MethodReference_inForEach implements FunctionalInterfaceForMethodReference {

	@Override
	public void functionalInterfaceMethod(String s) {
		System.out.print("[" + s + "] ");
	}

	private void classInstanceMethod(String s) {
		System.out.print("{" + s + "} ");
	}

	private static void classStaticMethod(String s) {
		System.out.print("<" + s + "> ");
	}


	public static void main(String[] args) {
		MethodReference_inForEach instance = new MethodReference_inForEach();
		List<String> strings = Arrays.asList("alma", "körte", "dió", "mogyoró", "42");

		// This won't work.
		// instance::thisIsAMethod;

		example("Reference to the method defined by a functional interface: OBJECTReference::METHODName");
		strings.forEach(instance::functionalInterfaceMethod);

		example("\n\nReference to an INSTANCE method of a class: OBJECTreference::METHODName");
		strings.forEach(instance::classInstanceMethod);

		example("\n\nReference to a STATIC method of a class: CLASSName::METHODName");
		strings.forEach(MethodReference_inForEach::classStaticMethod);

	}

	private static void example(String s) {
		System.out.println(s);
	}

}

interface FunctionalInterfaceForMethodReference {

	void functionalInterfaceMethod(String s);
}
