package DesignPatterns.Structural.Decorator;

public class PixelXLPhone implements Phone {

	@Override
	public void call() {
		System.out.println("Calling PixelXL Phone");
	}

	@Override
	public void displayProperties() {
	System.out.println("Pixel Phone");	
	}

}
