package DesignPatterns.Structural.Decorator;

public class PhoneColorDecorator extends PhoneDecorator{

	public PhoneColorDecorator(Phone phoneToBeDecorated) {
		super(phoneToBeDecorated);
	}
	
	@Override
	public void call() {
		//Original functionality
		decoratedPhone.call();		// field inherited from PhoneDecorator class
		
		//Decorator-specific added functionality 
		doSomethingSpecific(decoratedPhone);
	}
	
	@Override 
	public void displayProperties() {
		decoratedPhone.displayProperties();
		System.out.println("Color is Red");
		
	}
	
	public void doSomethingSpecific(Phone phoneToBeDecoratedWithSomethingSpecific) {
		System.out.println(" which is RED");
	}

}
