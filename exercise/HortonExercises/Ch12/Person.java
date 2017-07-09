/*
Requirement

Define a Person class to encapsulate a person's name and address, with the name and address being fields
of type Name and address. Write a program to allow names and addresses to be entered from the keyboard
and stored as Person objects in a file. 
After the file exists new entries should be appended to the file


 */
package HortonExercises.Ch12;

import java.io.Serializable;

/**
 *
 * @author Andras Olah (olahandras78@gmail.com)
 */
public class Person implements Serializable {

	private Name personNameObject;
	private Address personAddressObject;
	private static final long serialVersionUID = 0001L;

	public Person(String name, String address) {
		personNameObject = new Name();
		personAddressObject = new Address();
		personNameObject.name = name;
		personAddressObject.address = address;
	}

	/**
	 * @return the personNameObject
	 */
	public Name getPersonNameObject() {
		return personNameObject;
	}

	/**
	 * @param personNameObject the personNameObject to set
	 */
	public void setPersonNameObject(Name personNameObject) {
		this.personNameObject = personNameObject;
	}

	/**
	 * @return the personAddressObject
	 */
	public Address getPersonAddressObject() {
		return personAddressObject;
	}

	/**
	 * @param personAddressObject the personAddressObject to set
	 */
	public void setPersonAddressObject(Address personAddressObject) {
		this.personAddressObject = personAddressObject;
	}

	public class Name implements Serializable {

		private static final long serialVersionUID = 0001L;
		String name;
	}

	public class Address implements Serializable {

		private static final long serialVersionUID = 0001L;
		String address;
	}

	@Override
	public String toString() {
		StringBuffer returnStr= new StringBuffer();
		returnStr.append("Name: ").append(personNameObject.name);
		returnStr.append("\nAddress: ").append(personAddressObject.address);
		return returnStr.toString();
	}
}
