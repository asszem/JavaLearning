
/*
Requirement

Define a Person class to encapsulate a person's name and address, with the name and address being fields
of type Name and address. Write a program to allow names and addresses to be entered from the keyboard
and stored as Person objects in a file. 
After the file exists new entries should be appended to the file


 */
package HortonExercises.Ch12.Ex1;

import java.io.Serializable;

/**
 *
 * @author Andras Olah (olahandras78@gmail.com)
 */
public class Person implements Serializable {

	private Name personName;
	private Address personAddress;
	private static final long serialVersionUID = 0001L;

	public Person(String name, String address) {
		personName = new Name();
		personAddress = new Address();
		personName.name = name;
		personAddress.address = address;
	}

	/**
	 * @return the personName
	 */
	public Name getPersonName() {
		return personName;
	}

	/**
	 * @param personName the personName to set
	 */
	public void setPersonName(Name personName) {
		this.personName = personName;
	}

	/**
	 * @return the personAddress
	 */
	public Address getPersonAddress() {
		return personAddress;
	}

	/**
	 * @param personAddress the personAddress to set
	 */
	public void setPersonAddress(Address personAddress) {
		this.personAddress = personAddress;
	}

	private class Name implements Serializable {

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
		returnStr.append("Name: ").append(personName.name);
		returnStr.append("\nAddress: ").append(personAddress.address);
		return returnStr.toString();
	}
}
