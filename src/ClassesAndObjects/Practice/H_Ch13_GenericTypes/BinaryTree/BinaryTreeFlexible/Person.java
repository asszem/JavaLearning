/*
Horton p. 512
Class updated to check the class name for people comparison

If this.person class name equals the target.person class name - do the regular lexicografical comparison
If this.person's class name is not the same as the target.person's class name
	and this.person is a Manager type then return 1 - meaning this.person is GREATER than target.person
	else this.person is not a manager type then return -1 - meaning this.person is LESS than target.person

 */
package ClassesAndObjects.Practice.H_Ch13_GenericTypes.BinaryTree.BinaryTreeFlexible;

public class Person implements Comparable<Person> {

	protected String name;

	public Person(String name) {
		this.name = name;
	}

	//<editor-fold desc="This provides the result for the comparison call in the add() method in BinaryTree class">
	/*
	this.person 		compared to person		returns   
	Andras			=	Andras					0
	Andras			<	Bob						-1 
	Bob				>	Andras					+1

compareTo() method description: 
	returns
	<0 if this string is lexicographically less than the string argument; 
	>0 if this string is lexicographically greater than the string argument

	 */
	//</editor-fold>
	@Override
	public int compareTo(Person person) {
		if (person == this) {
			return 0;
		}
//		return this.name.compareTo(person.name);
		if (this.getClass().getName().equals(person.getClass().getName())) {
			return this.name.compareTo(person.name);
		} else if (this.getClass().getName().equals("Manager")) {
			return 1;
		} else {
			return -1;
		}
	}

	@Override
	public String toString() {
		return name;
	}

	public static void main(String[] args) {
		System.out.println("CompareTo test");
		String[] names = {"Andras", "Bob"};
		System.out.printf("%3$02d = %s.compareTo(%s)%n", names[0], names[0], names[0].compareTo(names[0]));
		System.out.printf("%3$02d = %s.compareTo(%s)%n", names[0], names[1], names[0].compareTo(names[1]));
		System.out.printf("%3$02d = %s.compareTo(%s)%n", names[1], names[0], names[1].compareTo(names[0]));
	}

}
