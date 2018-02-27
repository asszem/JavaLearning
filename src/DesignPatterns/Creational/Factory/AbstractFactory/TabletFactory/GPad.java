package DesignPatterns.Creational.Factory.AbstractFactory.TabletFactory;


public class GPad implements Tablet{

	@Override
	public void drawOnTablet() {
		System.out.println("Draw on GPad");
	}

}
