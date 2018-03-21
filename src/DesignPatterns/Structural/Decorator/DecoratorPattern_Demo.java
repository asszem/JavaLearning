// Source: https://www.tutorialspoint.com/design_pattern/decorator_pattern.htm
//https://www.youtube.com/watch?v=j40kRwSm4VE&t=0s&index=11&list=PLF206E906175C7E07
//http://web.science.mq.edu.au/~mattr/courses/object_oriented_development_practices/5/notes.html


/*Decorator pattern allows a user to add new functionality to an existing object without altering its structure.
 *This type of design pattern comes under structural pattern as this pattern acts as a wrapper to existing class.
 * */
package DesignPatterns.Structural.Decorator;

public class DecoratorPattern_Demo {

	public static void main(String[] args) {

		System.out.println("Instantiating a new Phone object without Decorators");
		Phone nonDecoratedPhone = new SamsungPhone();
		nonDecoratedPhone.call();
		nonDecoratedPhone.displayProperties();

		System.out.println("Instantiating a new Phone object using one Decorator");
		Phone decoratedPhone = new PhoneColorDecorator(new SamsungPhone());
		decoratedPhone.call();

		System.out.println("Instantiating a new Phone object using multiple Decorators");
		Phone multipleDecorators = new PhoneStorageDecorator(new PhoneColorDecorator(new PixelXLPhone()));
		multipleDecorators.displayProperties();
	}

}
