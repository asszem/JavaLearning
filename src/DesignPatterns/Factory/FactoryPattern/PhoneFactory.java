package DesignPatterns.Factory.FactoryPattern;

//This class provides a method to return a Phone object based on the argument of getPhone method
public class PhoneFactory {

	public Phone getPhone(String phoneType) {
		switch (phoneType.toLowerCase()) {
		case "iphone":
			return new IPhone();
		case "samsung":
			return new Samsung();
		case "huawei":
			return new Huawei();
		default :
			return null;
		}
	}

}
