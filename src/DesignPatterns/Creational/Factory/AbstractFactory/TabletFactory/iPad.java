package DesignPatterns.Creational.Factory.AbstractFactory.TabletFactory;


public class iPad implements Tablet{

	@Override
	public void drawOnTablet() {
		System.out.println("Draw on iPad");
	}

}
