/*
DERIVED CLASSES - INHERITANCE
	Defining a new class based on an existing class is called derivation. 
	Derived class = subcass, inherited class
	Original class = superclass, base class

	A derived class object is a specialized object of the base class.
	A derived class EXTENDS a base class
	The members of the derived class define the properties that differentiate it from the base type
	 Any members that you define in the new class are in addition to those that are already members of the base class
	Objects of the derived class type contain all the inherited members of the base class — both fields and methods, as well as the members that are specific to the derived class

Access inheritance Data Members and Methods:
	- no modifier(package private): inherited in same package, NOT inherited in different package. 
	- private: not inherited
	- protected: inherited in both same and different package (base class must be PUBLIC!)
	- public: inherited in both same and different package
	-If the base class is not declared as public, it cannot be reached directly from outside the package.
	- Constructors are NOT inherited.

Objects in a Derived class
	An object of a subclass contains all the members of the original base class, plus any new members that you have defined in the derived class
	The private members and constructors of base class are in the derived object, but inaccessible. 
	The base class methods that are inherited in a derived class can access all the base class members, including those that are not inherited
	Constructing object in a derived class need to call the constructors of base clas objects because in every derived object there is a base class object inside it as well


ABSTRACT CLASSES
	An abstract class is a class in which one or more methods are declared, but not defined
	The declaration for an abstract method ends with a semicolon and you specify the method with the keyword abstract to identify it as such 

	public abstract class Animal {
	public abstract void sound();  //Semicolon!
	}

	You cannot instantiate an object of an abstract class, but you can declare a variable of an abstract class type.  You cannot instantiate an object of an abstract class, but you can declare a variable of an abstract class type.
	AbstractClass variable = null;
	variable = dogObject;		//dogObject is a class that inherits the Abstract class.
	An abstract method cannot be private because a private method cannot be inherited and therefore cannot be redefi ned in a subclass.


INTERFACES
	Specifying a set of methods or constants that represent a particular class interface, which can then be implemented appropriately in a number of different classes

****************************************************************************************************
	All of the classes then share this common interfacethe methods in it 
	can be called polymorphically through a variable of the interface type
****************************************************************************************************
	RemoteControl taviranyito;
	taviranyito = new DVD();
	taviranyito.turnOff();
	taviranyito = new Microwave();
	taviranyito.turnOff();

Interfaces
	- always public, abstract
Methods
	- always PUBLIC, ABSTACT
	- always INSTANCE
	- never static
Variables 
	- always public, static, final
	- always must be initialized with value
No constructors

DECLARE and EXTEND interfaces
	 The interface doing the extending acquires all the methods and constants from the interface it extends
	Unlike a class, which can extend only one other class, an interface can extend any number of other interfaces
	public interface MyInterface extends MyInterface2, MyInterface3... {}

IMPLEMENT in Class declaration	j
	You declare that the class implements the interface using the implements keyword and you write the code for each of the methods that the interface declares as part of the class definition
A class can implement more than one interface, separate with comas
	Class MyClass implements MyInterface1, MyInterface2... {}

@Override
	For a class that implements an interface and is not abstract, there is no need to use the @Override annotation because the compiler always generates an error message if you fail to implement any interface method with the correct signature






*/
package ClassesAndObjects.H_Ch6_Inheritance;

/**
 * 
 * @author Andras Olah (olahandras78@gmail.com)
 */
public class H_Chapter_6_Extending_and_Interfaces {

}
