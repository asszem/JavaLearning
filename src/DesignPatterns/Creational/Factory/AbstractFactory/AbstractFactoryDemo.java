//Source: https://www.tutorialspoint.com/design_pattern/abstract_factory_pattern.htm 
package DesignPatterns.Creational.Factory.AbstractFactory;

import DesignPatterns.Creational.Factory.AbstractFactory.PhoneFactory.Phone;
import DesignPatterns.Creational.Factory.AbstractFactory.TabletFactory.Tablet;

public class AbstractFactoryDemo {

	public static void main(String[] args) throws Exception {
		AbstractFactory phoneFactory = FactoryProducer.getFactory("phoneFactory");
		Phone iPhone = phoneFactory.getPhone("iphone");
		Phone samsung = phoneFactory.getPhone("samsung");
		Phone huawei = phoneFactory.getPhone("huawei");
		iPhone.call();
		samsung.call();
		huawei.call();

		AbstractFactory tabletFactory = FactoryProducer.getFactory("tabletFactory");
		Tablet iPad = tabletFactory.getTablet("ipad");
		Tablet nexus = tabletFactory.getTablet("nexus");
		Tablet gPad = tabletFactory.getTablet("gpad");
		iPad.drawOnTablet();
		nexus.drawOnTablet();
		gPad.drawOnTablet();
	}
}
