package DesignPatterns.Creational.Factory.AbstractFactory;

import DesignPatterns.Creational.Factory.AbstractFactory.PhoneFactory.PhoneFactory;
import DesignPatterns.Creational.Factory.AbstractFactory.TabletFactory.TabletFactory;

public class FactoryProducer {
	
	//returning only single instance
	private static final PhoneFactory PHONE_FACTORY=new PhoneFactory();
	private static final TabletFactory TABLET_FACTORY=new TabletFactory();

	//Static method
	public static AbstractFactory getFactory(String factoryType) throws Exception {
		switch (factoryType.toLowerCase()) {
		case "new phonefactory":
			return new PhoneFactory();
		case "new tabletfactory":
			return new TabletFactory();
		case "single phonefactory":
			return PHONE_FACTORY;
		case "single tabletfactory":
			return TABLET_FACTORY;
		default:
			throw new Exception("Error! No factory for provided factoryType: "+factoryType);
		}
	}

}
