package DesignPatterns.Structural.Decorator;

public class PhoneStorageDecorator extends PhoneDecorator{

	public PhoneStorageDecorator(Phone phoneToBeDecorated) {
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
		System.out.println("Storage is 64GB");
		
	}
	
	public void doSomethingSpecific(Phone phoneToBeDecoratedWithSomethingSpecific) {
		System.out.println(" which has 64GB Storage");
	}

}
