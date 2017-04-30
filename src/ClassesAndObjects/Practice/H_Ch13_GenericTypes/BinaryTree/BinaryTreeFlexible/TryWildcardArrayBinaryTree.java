package ClassesAndObjects.Practice.H_Ch13_GenericTypes.BinaryTree.BinaryTreeFlexible;

/**
 *
 * @author Andras Olah (olahandras78@gmail.com)
 * Based on Horton TryWildcardArray p489
 */
public class TryWildcardArrayBinaryTree {

	public static void main(String[] args) {

		FlexibleAscDescSortableBinaryTree<?>[] trees = {new FlexibleAscDescSortableBinaryTree<Integer>(), new FlexibleAscDescSortableBinaryTree<String>()};
		LinkedList<?>[] lists = new LinkedList<?>[trees.length];

		int[] numbers = new int[30];
		for (int i = 0; i < numbers.length; i++) {
			numbers[i] = (int) (1000.0 * Math.random());                        // Random integers 0 to 999
		}

		// List starting integer values
		int count = 0;

		System.out.println("Original values are:");
		for (int number : numbers) {
			System.out.printf("%6d", number);
			if (++count % 6 == 0) {
				System.out.println();
			}
		}

		// Add the integers to first tree
		for (int number : numbers) {
			// You can't call the add() method while the reference stored in trees[0] is of type BinaryTree<?> because the compiler cannot decide on the form of the add() method without having a specific type argument available. 
			((FlexibleAscDescSortableBinaryTree<Integer>) trees[0]).add(number);
		}

		// Create an array of words to be sorted
		String[] words = {"vacillate", "procrastinate", "arboreal", "syzygy",
			"xenocracy", "zygote", "mephitic", "soporific",
			"grisly", "gristly"};

		// List the words
		System.out.println(
				"\nOriginal word sequence:");
		for (String word : words) {
			System.out.printf("%-15s", word);
			if (++count % 5 == 0) {
				System.out.println();
			}
		}

		// Insert the words into second tree
		for (String word : words) {
			((FlexibleAscDescSortableBinaryTree<String>) trees[1]).add(word);
		}

		// Sort the values in both trees
		for (int i = 0; i < lists.length; ++i) {
			lists[i] = trees[i].sort(true); //Sort ascending
		}

		// List the sorted values from both trees
		for (LinkedList<?> list : lists) {
			System.out.println("\nSorted results:");
			listAll(list);
		}
	}

// List the elements in any linked list
	public static void listAll(LinkedList<?> list) {
		int counter=1;
		for (Object obj : list) {
			System.out.print(obj+" ");
			if (counter++%3==0) {
				System.out.println("");
			}
		}
	}
}
