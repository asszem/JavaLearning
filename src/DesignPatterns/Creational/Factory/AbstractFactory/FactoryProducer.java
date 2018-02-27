package DesignPatterns.Creational.Factory.AbstractFactory;

import DesignPatterns.Creational.Factory.AbstractFactory.PhoneFactory.PhoneFactory;
import DesignPatterns.Creational.Factory.AbstractFactory.TabletFactory.TabletFactory;

public class FactoryProducer {

	//Static method
	public static AbstractFactory getFactory(String factoryType) throws Exception {
		switch (factoryType.toLowerCase()) {
		case "phonefactory":
			return new PhoneFactory();
		case "tabletfactory":
			return new TabletFactory();
		default:
			throw new Exception("Error! No factory for provided factoryType: "+factoryType);
		}
	}

}
