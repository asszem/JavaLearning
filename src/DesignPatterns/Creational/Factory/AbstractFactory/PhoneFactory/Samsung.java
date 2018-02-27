package DesignPatterns.Creational.Factory.AbstractFactory.PhoneFactory;

public class Samsung implements Phone {

	@Override
	public void call() {
		System.out.println("Calling a Huawei");
	}

}
