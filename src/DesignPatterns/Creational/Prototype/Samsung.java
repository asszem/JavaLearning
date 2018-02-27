package DesignPatterns.Creational.Prototype;

public class Samsung extends Phone {

	public Samsung() {
		// type field defined and inherited from Phone abstract class
		type = "Samsung Galaxy S9+";
	}

	@Override
	public void call() {
		System.out.println("Calling a Huawei");
	}

}
