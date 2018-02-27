package DesignPatterns.Creational.Factory.AbstractFactory.PhoneFactory;

public class Huawei implements Phone {

	@Override
	public void call() {
		System.out.println("Calling a Samsung");
	}

}
