package DesignPatterns.Factory.AbstractFactory;

import DesignPatterns.Factory.AbstractFactory.PhoneFactory.Phone;
import DesignPatterns.Factory.AbstractFactory.TabletFactory.Tablet;

public abstract class AbstractFactory {

	protected abstract Phone getPhone(String phoneType);

	protected abstract Tablet getTablet(String tabletType);
}
