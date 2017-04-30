package ClassesAndObjects.Practice.H_Ch13_GenericTypes.BinaryTree.BinaryTreeFlexible;

/**
 *
 * @author Andras Olah (olahandras78@gmail.com)
 */
public class TryParameterizedConstructorBinaryTree {

	public static void main(String[] args) {

		Manager[] managers = {new Manager("Jane", 1), new Manager("Joe", 3),
			new Manager("Freda", 3), new Manager("Bert", 2),
			new Manager("Ann", 2), new Manager("Dave", 2)};

		// Create the tree with an array of managers
		FlexibleAscDescSortableBinaryTree<Person> people = new FlexibleAscDescSortableBinaryTree<>(managers);

		// Create and add some Person objects
		Person[] persons = {new Person("Will"), new Person("Ann"), new Person("Mary"),
			new Person("Tina"), new Person("Stan")};
		for (Person person : persons) {
			people.add(person);
		}
		final boolean ASCENDING = true;
		System.out.println("List all in ascending order");
		listAll(people.sort(ASCENDING));
		System.out.println("\nList all in descending order");
		listAll(people.sort(!ASCENDING));
	}

	// List the elements in any linked list
	public static <T> void listAll(LinkedList<T> list) {
		for (T obj : list) {
			System.out.println(obj);
		}
	}
}
