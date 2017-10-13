/*http://www.natpryce.com/articles/000714.html*/
package DesignPatterns.BuilderPattern;

public class PersonBuilder {

	// Has an instance variable for each constructor parameter
	// Initializes its instance variables to commonly used or safe values
	private String name = "Default name";
	private int age = 40;


	// Has a `build` method that creates a new object using the values in its instance variables
	public Person build() {
		Person person = new Person();
		person.name=name;
		person.age=age;
		return person;
	}

	// Has "chainable" public methods for overriding the values in its instance variables.
	public PersonBuilder withName(String name){
		this.name=name;
		return this; //Return the same BuilderPattern_SamplePersonBuilder instance so that it will be chainable
	}
	public PersonBuilder withAge(int age){
		this.age=age;
		return this; //Return the same BuilderPattern_SamplePersonBuilder instance so that it will be chainable
	}
	
	public static void main(String[] args) {
		// Instantiate default instance
		Person defaultPerson = new PersonBuilder().build();
		Person personWithName = new PersonBuilder().withName("Custom name").build();
		Person personWithAge = new PersonBuilder().withAge(42).build();
		Person personWithAgeAndName = new PersonBuilder().withAge(42).withName("Custom Name and Age").build();
		System.out.println("Completed.");
	}
}

class Person {
	public String name;
	public int age;
}
