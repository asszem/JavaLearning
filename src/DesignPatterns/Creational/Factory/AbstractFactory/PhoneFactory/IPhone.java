package DesignPatterns.Creational.Factory.AbstractFactory.PhoneFactory;

public class IPhone implements Phone {

	@Override
	public void call() {
		System.out.println("Calling an iPhone");
	}

}
