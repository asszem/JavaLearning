package ClassesAndObjects.Practice.H_Ch15_UsefulClasses;

public class Person implements Comparable<Person> {
	private String firstName; // First name of person
	private String surname; // Second name of person
	public String name;

	// Constructor
	public Person(String firstName, String surname) {
		this.firstName = firstName;
		this.surname = surname;
		this.name = firstName + " " + surname;
	}

	public Person(String fullname) {
		this.name = fullname;
	}

	@Override
	public String toString() {
		return firstName + " " + surname;
	}

	// Compare Person objects
	public int compareTo(Person person) {
		int result = surname.compareTo(person.surname);
		return result == 0 ? firstName.compareTo(person.firstName) : result;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getSurname() {
		return surname;
	}
}
