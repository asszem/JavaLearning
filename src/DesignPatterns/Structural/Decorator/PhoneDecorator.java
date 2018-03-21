package DesignPatterns.Structural.Decorator;

public abstract class PhoneDecorator implements Phone {

	protected Phone decoratedPhone;

	public PhoneDecorator(Phone phoneToBeDecorated) {
		this.decoratedPhone = phoneToBeDecorated;
	}

	public void call() {
		decoratedPhone.call();
	}
	
	public void displayProperties() {
		decoratedPhone.displayProperties();
	}
}
