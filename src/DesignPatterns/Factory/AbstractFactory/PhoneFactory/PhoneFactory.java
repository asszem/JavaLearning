package DesignPatterns.Factory.AbstractFactory.PhoneFactory;

import DesignPatterns.Factory.AbstractFactory.AbstractFactory;
import DesignPatterns.Factory.AbstractFactory.TabletFactory.Tablet;

//This class provides a method to return a Phone object based on the argument of getPhone method
public class PhoneFactory extends AbstractFactory {

	@Override
	public Phone getPhone(String phoneType) {
		switch (phoneType.toLowerCase()) {
		case "iphone":
			return new IPhone();
		case "samsung":
			return new Samsung();
		case "huawei":
			return new Huawei();
		default:
			return null;
		}
	}

	@Override
	protected Tablet getTablet(String tabletType) {
		return null;
	}

}
