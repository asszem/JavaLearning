package HortonExercises.Ch14.Ex3.Ex34_Andras;

import java.io.*;

public class Person implements Comparable<Person>, Serializable {

	private String firstName;                                            // First name of person
	private String surname;                                              // Second name of person
	private static final long serialVersionUID = 1001L;
	private static BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));

	// Constructor
	public Person(String firstName, String surname) {
		this.firstName = firstName;
		this.surname = surname;
	}

	// Read a person from the keyboard
	public static Person readPerson() {
		String firstName = null;
		String surname = null;
		try {
			System.out.print("Enter first name: ");
			firstName = keyboard.readLine().trim();
			System.out.print("Enter surname: ");
			surname = keyboard.readLine().trim();
		} catch (IOException e) {
			System.err.println("Error reading a name.");
			e.printStackTrace();
			System.exit(1);
		}
		return new Person(firstName, surname);
	}

	// Read a person SURNAME only from the keyboard
	public static String readPersonSurname() {
		String surname = null;
		try {
			System.out.print("Enter surname: ");
			surname = keyboard.readLine().trim();
		} catch (IOException e) {
			System.err.println("Error reading a name.");
			e.printStackTrace();
			System.exit(1);
		}
		return surname;
	}

	@Override
	public boolean equals(Object comparedToPerson) {							//Uses the compareTo method
		return this.compareTo((Person) comparedToPerson) == 0;					//Evaulates TRUE if compareTo returns 0
	}

	@Override
	public int hashCode() {
		return 7 * firstName.hashCode() + 13 * surname.hashCode();				//Generates hashcode based on first name and last name
	}

	// Compare Person objects
	public int compareTo(Person comparedToPerson) {
		int result = this.surname.compareTo(comparedToPerson.surname);							//Compares Person objects by surname field
		return result == 0 ? this.firstName.compareTo(comparedToPerson.firstName) : result;		//Compares firstname if surname is the same
	}

	@Override
	public String toString() {
		return firstName + " " + surname;
	}

	public String getSurname(){
		return surname;
	}

	public String getFirstName(){
		return firstName;
	}
}
