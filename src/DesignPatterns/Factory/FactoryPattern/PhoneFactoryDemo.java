//Source: https://www.tutorialspoint.com/design_pattern/factory_pattern.htm
package DesignPatterns.Factory.FactoryPattern;

public class PhoneFactoryDemo {

	public static void main(String[] args) {
		//Using an instance of PhoneFactory
		PhoneFactory phoneFactory = new PhoneFactory();
		Phone iPhone = phoneFactory.getPhone("iphone");
		Phone samsung = phoneFactory.getPhone("samsung");
		Phone huawei = phoneFactory.getPhone("huawei");
		iPhone.call();
		samsung.call();
		huawei.call();

		//Using static method
		Phone iPhoneX = PhoneFactory.getPhoneStatic("iphone");
		iPhoneX.call();
	}
}
