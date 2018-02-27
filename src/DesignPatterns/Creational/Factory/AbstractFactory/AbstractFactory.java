package DesignPatterns.Creational.Factory.AbstractFactory;

import DesignPatterns.Creational.Factory.AbstractFactory.PhoneFactory.Phone;
import DesignPatterns.Creational.Factory.AbstractFactory.TabletFactory.Tablet;

public abstract class AbstractFactory {

	protected abstract Phone getPhone(String phoneType);

	protected abstract Tablet getTablet(String tabletType);
}
