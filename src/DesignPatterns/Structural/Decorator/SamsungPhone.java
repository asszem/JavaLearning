package DesignPatterns.Structural.Decorator;

public class SamsungPhone implements Phone {

	@Override
	public void call() {
		System.out.println("Calling Samsung Phone");
	}

	@Override
	public void displayProperties() {
	System.out.println("Samsung Phone");	
	}

}
