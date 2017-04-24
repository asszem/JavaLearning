package ClassesAndObjects.H_Ch13_GenericTypes.LinkedListGeneric;

/**
 *
 * @author Andras Olah (olahandras78@gmail.com)
 */
public class BoundedWildcards {

	public class GenericInnerClass<T> {

		T aGenericField;

		public GenericInnerClass(T value) {
			aGenericField = value;
		}
	}

	public class A {
	}

	public class B extends A {
	}

	public class C extends A {
	}

	public class D {
	}

	public void testUpperBoundWildcard(GenericInnerClass<? extends A> testObject) { //only A, B or C are valid arguments
	}

	public void testLowerBoundWildcard(GenericInnerClass<? super B> testObject) { //only B and A are valid
	}

	public static void main(String[] args) {
		//Create a BoundedWildcard objects that will hold the inner classes
		BoundedWildcards instance = new BoundedWildcards();
		//Create instances of the inner classes
		A a = instance.new A();
		B b = instance.new B();
		C c = instance.new C();
		D d = instance.new D();

		//Create instances of the Generic Inner Class for different types
		GenericInnerClass<A> typeA = instance.new GenericInnerClass<>(a); //creates a GIC with generic filed of type A
		GenericInnerClass<B> typeB = instance.new GenericInnerClass<>(b);
		GenericInnerClass<C> typeC = instance.new GenericInnerClass<>(c);
		GenericInnerClass<D> typeD = instance.new GenericInnerClass<>(d); //No problem as GenericinnerClass has NO boundaries

		//Test methods with upper bound wildcards
		instance.testUpperBoundWildcard(typeA);  	//valid
		instance.testUpperBoundWildcard(typeB);  	//valid
		instance.testUpperBoundWildcard(typeC);  	//valid
//		instance.testUpperBoundWildcard(typeD); 	//ERROR! class D is out of boundary! as it is not a subclass of A
		
		//Test methods with lower bound wildcards
		instance.testLowerBoundWildcard(typeA);  	//valid
		instance.testLowerBoundWildcard(typeB);  	//valid
//		instance.testLowerBoundWildcard(typeC); 	//ERROR! class C is not a superclass of B!
//		instance.testUpperBoundWildcard(typeD); 	//ERROR! class D is not a superclass of B!
	}

}
