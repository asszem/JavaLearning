//<editor-fold desc="Class changelog">
/*
Class updated with renamed variables and comments extended for better understanding
Class updated with introducing Ascending and Descending sort methods
Class updated to allow being instantinated by any type of objects as long as their superclass extends to Comparable<>
	Before
		public class FlexibleAscDescSortableBinaryTree<T extends Comparable<T>> {
	After
		public class FlexibleAscDescSortableBinaryTree<T extends Comparable<? super T>> {
Class updated to have the add() method accept an argument of any type that is type T, or a subclass of T. (p489)
	Before
		public <T> void add(T objectToNode) {
	After
		public <E extends T> void add(E objectToNode) {
Class updated to have a parameterized constructor that accepts an array of items to be added to the binary tree (p489)

 */
//</editor-fold>
package ClassesAndObjects.Practice.H_Ch13_GenericTypes.BinaryTree.BinaryTreeFlexible;

//<editor-fold desc="Explanation of the effect of changing the Comparable interface to a wildcard type parameter">
/*
Explanation
	Changed the type parameter for the Comparable<T> interface to a wildcard that is a superclass of T
	The effect is to allow any type argument to be accepted that implements the Comparable<T> interface
	or inherits an implementation of it. 
	This should allow the BinaryTree<> type to be used with classes such as the Manager class
	which could not be used as a type argument in the previous BinaryTree<T> implementation. 

Manager is a subclass of Person
Person is a superclass of Manager
Person implements Comparable<Person>

Intantation of a FlexibleAscDescSortableBinaryTree<Manager> -> the Manager class is T
															-> must extend to a class of that is 
															-> implementing Comparable which must be 
															-> any type that is a super class of T
 */
//</editor-fold>
public class FlexibleAscDescSortableBinaryTree<T extends Comparable<? super T>> {

	private Node rootNode;                                                   // The rootNode node

	//No-arg constructor
	public FlexibleAscDescSortableBinaryTree() {
	}

	//Parameterized constructor that accepts an array
	public <E extends T> FlexibleAscDescSortableBinaryTree(E[] inputArray) {
		for (E element:inputArray){
			this.add(element);
		}

	}

	// Add a objectToNode to the tree. Parameterized method, accepts any objects that is T or subclass of T
	public <E extends T> void add(E objectToNode) {
		if (rootNode == null) {                                               // If there's no rootNode node
			rootNode = new Node(objectToNode);                                // store it in the rootNode
		} else {                                                              // Otherwise...
			add(objectToNode, rootNode);                                      // add it recursively
		}
	}

	//<editor-fold desc="Explanation of recursive object insertion">
	/* Recursive insertion of an object
	Compares this object with the specified object for order.  Returns a
    negative integer, zero, or a positive integer as this object is less
    than, equal to, or greater than the specified object.

	current object < target object < 0 		-> current(397) target(558) comparison=-1
	current object = target object = 0
	current object > target object > 0		-> current(816) target (734) comparison=1
	 */
	//</editor-fold>
	// Parameterized method, accepts any objects that is T or subclass of T
	private <E extends T> void add(E objectToNode, Node currentNode) {
		int comparison = currentNode.objectInNode.compareTo(objectToNode);
		if (comparison == 0) {                                              // If it is equal to the current currentNode
			++currentNode.countIdentical;                                   // just increment the countIdentical
			return;
		}
		if (comparison > 0) {                                               // If it's less than the current currentNode
			if (currentNode.left == null) {                                 // and the left child currentNode is null
				currentNode.left = new Node(objectToNode);                  // Store it as the left child currentNode
			} else {                                                        // Otherwise...
				add(objectToNode, currentNode.left);                        // RECURSIVE... call add() again at the left currentNode
			}
		} else // It must be greater than the current currentNode
		 if (currentNode.right == null) {                                // so it must go to the right...
				currentNode.right = new Node(objectToNode);                 // store it as the right currentNode
			} else {                                                        // ...or when right currentNode is not null
				add(objectToNode, currentNode.right);                       // RECURSIVE... call add() again at the right currentNode
			}
	}

	// Create a list containing the linkedListOfNodes from the tree in sequence
	public LinkedList<T> sort(boolean sortAscending) {
		LinkedList<T> linkedListOfNodes = new LinkedList<>();                // Create a linked list
		treeSort(rootNode, linkedListOfNodes, sortAscending);                // Sort the objects into the list
		return linkedListOfNodes;
	}

	// Extract the tree nodes in ascending or descending sequence
	private void treeSort(Node nodeToInsert, LinkedList<T> linkedListOfNodes, boolean sortAscending) {
		if (nodeToInsert != null) {                                                 // If the current nodeToInsert isn't null
			if (sortAscending) {
				treeSort(nodeToInsert.left, linkedListOfNodes, sortAscending);          // process its left child nodeToInsert
			} else {
				treeSort(nodeToInsert.right, linkedListOfNodes, sortAscending);         // or if descending order, process the rigth branch first
			}
			
			//The recursive call stops when the nodeToInsert.left is null and then continues to add the nodeToInsert to the linkedListOfNodes	

			// List the duplicate objects for the current nodeToInsert
			for (int i = 0; i < nodeToInsert.countIdentical; ++i) {
				linkedListOfNodes.addItem(nodeToInsert.objectInNode);
			}
			if (sortAscending) {
				treeSort(nodeToInsert.right, linkedListOfNodes, sortAscending);         // Now process the right child nodeToInsert
			} else {
				treeSort(nodeToInsert.left, linkedListOfNodes, sortAscending);          // or if descending order, process the left branch second
			}
		}
	}

	// Private inner class defining nodes
	private class Node {

		Node(T objectToNode) {
			objectInNode = objectToNode;
			countIdentical = 1;
		}

		T objectInNode;                                                    // Object stored in the node
		int countIdentical;                                                // Count of identical objects
		Node left;                                                         // The left child node
		Node right;                                                        // The right child node
	}
}
