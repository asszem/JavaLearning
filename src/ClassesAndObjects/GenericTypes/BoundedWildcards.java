package ClassesAndObjects.GenericTypes;

import java.io.Serializable;

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

	//Instantiation of this class must extend to Alfa and implement the Serializable interface
	class MultipleTypeParameterBounds<X extends Alfa & Serializable> {
	}

	public class Alfa {
	}

	public class Beta extends Alfa {
	}

	public class Gamma extends Alfa implements Serializable{
	}

	public class Delta {
	}

	public void testUpperBoundWildcard(GenericInnerClass<? extends Alfa> testObject) { //only Alfa, Beta or Gamma are valid arguments
	}

	public void testLowerBoundWildcard(GenericInnerClass<? super Beta> testObject) { //only Beta and Alfa are valid
	}

	public static void main(String[] args) {
		//Create a BoundedWildcard objects that will hold the inner classes
		BoundedWildcards instance = new BoundedWildcards();

//		MultipleTypeParameterBounds<Alfa> error = new MultipleTypeParameterBounds<Alfa>();  //ERROR - ALFA is not implementing Serializable!
		MultipleTypeParameterBounds<Gamma> correct =instance.new MultipleTypeParameterBounds<>();

		//Create instances of the inner classes
		Alfa a = instance.new Alfa();
		Beta b = instance.new Beta();
		Gamma c = instance.new Gamma();
		Delta d = instance.new Delta();

		//Create instances of the Generic Inner Class for different types
		GenericInnerClass<Alfa> typeA = instance.new GenericInnerClass<>(a); //creates a GIC with generic filed of type Alfa
		GenericInnerClass<Beta> typeB = instance.new GenericInnerClass<>(b);
		GenericInnerClass<Gamma> typeC = instance.new GenericInnerClass<>(c);
		GenericInnerClass<Delta> typeD = instance.new GenericInnerClass<>(d); //No problem as GenericinnerClass has NO boundaries

		//Test methods with upper bound wildcards
		instance.testUpperBoundWildcard(typeA);  	//valid
		instance.testUpperBoundWildcard(typeB);  	//valid
		instance.testUpperBoundWildcard(typeC);  	//valid
//		instance.testUpperBoundWildcard(typeD); 	//ERROR! class Delta is out of boundary! as it is not a subclass of Alfa

		//Test methods with lower bound wildcards
		instance.testLowerBoundWildcard(typeA);  	//valid
		instance.testLowerBoundWildcard(typeB);  	//valid
//		instance.testLowerBoundWildcard(typeC); 	//ERROR! class Gamma is not a superclass of Beta!
//		instance.testUpperBoundWildcard(typeD); 	//ERROR! class Delta is not a superclass of Beta!
	}

}
