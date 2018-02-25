package DesignPatterns.Factory.FactoryPattern;

public class Huawei implements Phone {

	@Override
	public void call() {
		System.out.println("Calling a Samsung");
	}

}
