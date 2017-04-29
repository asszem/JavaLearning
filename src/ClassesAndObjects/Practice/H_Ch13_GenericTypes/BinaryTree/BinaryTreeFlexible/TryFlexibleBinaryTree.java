package ClassesAndObjects.Practice.H_Ch13_GenericTypes.BinaryTree.BinaryTreeFlexible;

public class TryFlexibleBinaryTree {

	public static void main(String[] args) {

	//	rigidBinaryTree<Manager> people = new rigidBinaryTree<>(); //ERROR
		FlexibleAscDescSortableBinaryTree<Manager> people = new FlexibleAscDescSortableBinaryTree<>(); 

		Manager[] managers = {new Manager("Jane", 1), new Manager("Joe", 3),
			new Manager("Freda", 3), new Manager("Albert", 2)};
		for (Manager manager : managers) {
			people.add(manager);
			System.out.println("Added " + manager);
		}
		System.out.println();
		boolean ASCENDING=true;
		boolean DESCENDING=false;
		listAll(people.sort(ASCENDING));
	}

	// List the elements in any linked list. Note that the method parameter uses a WILDCARD to accept any kind of linekd lists
	public static void listAll(LinkedList<?> list) {
		for (Object obj : list) {
			System.out.println(obj);
		}
	}
}
