package ClassesAndObjects;


/**
 * This class demonstrates that multiple default interface methods with same signature must be overriden in implementing class
 * Based on this:
 *https://blog.idrsolutions.com/2015/01/java-8-default-methods-explained-5-minutes/
 *
 * @author András
 */
public class DefaultMethodsInInterfaces implements InterfaceA, InterfaceB {

	public static void main(String[] args) {
		// TODO code application logic here
		DefaultMethodsInInterfaces instance =new DefaultMethodsInInterfaces();
		instance.saySomething();
		instance.sayHi();
	}

	//This is the non-default method from InterfaceA that must be implemented
	@Override
	public void saySomething() {
		System.out.println("Hello World");
	}

	//This is an overriden method from InterfaceA
	//If you comment this out, there will be a compiler error of unrelated method signatures:
	//class DefaultMethodsInInterfaces inherits unrelated defaults for sayHi() from types InterfaceA and InterfaceB
	@Override
	public void sayHi() {
		InterfaceA.super.sayHi(); //the super keyword is needed to access the methods of InterfaceA
	}

}

interface InterfaceA {

	public void saySomething(); //this is a method that needs to be implemented in every implementing class

	default public void sayHi() {
		System.out.println("Hi from InterfaceA");
	}

}

interface InterfaceB {

	default public void sayHi() {
		System.out.println("Hi from InterfaceB");
	}
}
