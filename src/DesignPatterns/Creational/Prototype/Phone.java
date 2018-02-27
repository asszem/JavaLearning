//Source: https://www.tutorialspoint.com/design_pattern/prototype_pattern.htm
package DesignPatterns.Creational.Prototype;

public abstract class Phone implements Cloneable {
	private String id;
	protected String type;	//the extending classes must see this field

	abstract void call();

	public String getType() {
		return type;
	}

	public String getID() {
		return id;
	}

	public void setID(String newID) {
		this.id = newID;
	}

	@Override
	public Object clone() {
		Object clone = null;
		try {
			clone = super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return clone;
	}
}
