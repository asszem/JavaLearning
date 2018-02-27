package DesignPatterns.Creational.Prototype;

public class IPhone extends Phone {

	public IPhone() {
		// type field defined and inherited from Phone abstract class
		type = "iPhone 8S 128GB";
	}

	@Override
	public void call() {
		System.out.println("Calling an iPhone");
	}

}
