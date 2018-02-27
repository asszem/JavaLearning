package DesignPatterns.Creational.Prototype;

public class Huawei extends Phone {

	public Huawei() {
		// type field defined and inherited from Phone abstract class
		type = "Huawei Mate 10 Pro";
	}

	@Override
	public void call() {
		System.out.println("Calling a Samsung");
	}

}
