package HortonExercises.Ch10.Ex4;

/**
 *
 * @author Andras Olah (olahandras78@gmail.com)
 */
public class arrayMethods {

	/**
	 *
	 * @param inputString the string array to check for duplicates
	 * @param narrowReturn if true, the return array length will be equal to the number of duplicates, if false to the input array
	 * @param debug verbose output if true
	 * @return an array with removed duplicates.
	 */
	public static String[] removeDuplicates(String[] inputString, boolean narrowReturn, boolean debug) {
		int duplicatesCount = 0;
		int nextResult = 0;
		String[] result = new String[inputString.length];
		for (int i = 0; i < inputString.length; i++) {
			//Check if string already included in the results array
			boolean alreadyInResults = false;
			boolean stringIsADuplicate = false;
			for (int j = 0; j < result.length; j++) {
				if (inputString[i].equals(result[j])) { //Already in results
					alreadyInResults = true;
				}
			}
			//Check if string has a duplicate in the input array
			for (int j = 0; j < result.length; j++) {
				if (i != j && inputString[i].equals(inputString[j])) { //found a duplicate in inputString array (not itself!)
					stringIsADuplicate = true;
					if (!alreadyInResults) {
						duplicatesCount++; //Count only if duplicate AND not yet counted
					}
				}
			}

			//Add the string to the result array if:
			if (!stringIsADuplicate) {
				result[nextResult++] = inputString[i];
			}
			if (stringIsADuplicate && !alreadyInResults) {
				result[nextResult++] = inputString[i];
			}
		}//end for

		//Create an array which holds only the duplicates
		String[] resultNarrow = new String[result.length - duplicatesCount]; //Store only the duplicates in this result
		int resultsCount = 0;
		for (int i = 0; i < result.length; i++) {
			if (result[i] != null) {
				resultNarrow[resultsCount++] = result[i];
			}
		}

		if (debug) {
			System.out.println("Input: ");
			for (String s : inputString) {
				System.out.print(s);
			}
			System.out.println("\nDuplicatesCount: " + duplicatesCount);
			System.out.println("removeDuplicates result:");
			for (String s : result) {
				System.out.print(s);
			}
			System.out.println("");
			System.out.println("removeDuplicates resultNarrow:");
			for (String s : resultNarrow) {
				System.out.print(s);
			}
			System.out.println("\nEnd Method ------------");
			System.out.println("");
		}

		//Return array based on argument
		if (narrowReturn) {
			return resultNarrow;
		} else {
			return result;
		}
	}

	/**
	 * Compares two string arrays and returns the number of differences
	 *
	 * @param diffA the first array to check
	 * @param diffB the second array to check
	 * @param debug verbose output if true
	 * @return the total number of differences between the two arrays
	 */
	public static int checkDiffs(String[] diffA, String[] diffB, boolean debug) {
		if (debug) {
			System.out.println("Input arrays:");
			for (String s : diffA) {
				System.out.print(s + " ");
			}
			System.out.println("");
			for (String s : diffB) {
				System.out.print(s + " ");
			}
			System.out.println("");
		}

		int totalDiffCount = 0;
		int positionDiffCount = 0; //Comparing ONLY the same index array items
		//Algorithm to get the different array items ONLY (removing duplicates from each array)
		//Create new array with removed duplicates in each array
		String diffARemovedDuplicates[] = removeDuplicates(diffA, true, false); //Array of non-duplicate items in diffA
		String diffBRemovedDuplicates[] = removeDuplicates(diffB, true, false); //Array of non-duplicate items in diffA
		//					 Second, compare the unique arrays
		//Walk through DiffA array for each items in it and
		if (debug) {
			System.out.println("Input arrays after removing duplicates:");
			for (String s : diffARemovedDuplicates) {
				System.out.print(s + " ");
			}
			System.out.println("");
			for (String s : diffBRemovedDuplicates) {
				System.out.print(s + " ");
			}
			System.out.println("");
		}
//Merge the two together and then remove the duplicates!
		String[] mergedArray = new String[diffARemovedDuplicates.length + diffBRemovedDuplicates.length];
		int mergedCounter = 0;
		for (; mergedCounter < diffARemovedDuplicates.length; mergedCounter++) {
			mergedArray[mergedCounter] = diffARemovedDuplicates[mergedCounter];
		}
		for (int b = 0; b < diffBRemovedDuplicates.length; b++) {
			mergedArray[mergedCounter++] = diffBRemovedDuplicates[b];
		}
		if (debug) {
			System.out.println("Merged array:");
			for (String s : mergedArray) {
				System.out.print(s + " ");
			}
			System.out.println("");
		}
		String[] mergedAndRemovedDuplicates = removeDuplicates(mergedArray, true, false);
		int commonItems = mergedArray.length - mergedAndRemovedDuplicates.length;
		int uniqueItemsInArrayA = diffARemovedDuplicates.length - commonItems;
		int uniqueItemsInArrayB = diffBRemovedDuplicates.length - commonItems;
		if (debug) {
			System.out.println("Merged and Removed Duplicated array");
			for (String s : mergedAndRemovedDuplicates) {
				System.out.print(s + " ");
			}
			System.out.println("");
			System.out.println("Length of merged&removed: " + mergedAndRemovedDuplicates.length);
			System.out.println("Number of common items: " + commonItems);
			System.out.println("Unique items in A array: " + uniqueItemsInArrayA);
			System.out.println("Unique items in B array: " + uniqueItemsInArrayB);
		}

		return uniqueItemsInArrayB;
	}

	public static void addToArray(String[] target, String[] pool) {
		String[] result = new String[target.length + 1];
		//Validations
		//The pool must be one item longer than the target
		//The pool must not have duplicates
		//The pool must have only one letter that is not included in the target

		//Step1 Walk through the pool. Check if current item is included in target. If not, add it and stop
		breakout:
		for (int poolIndex = 0; poolIndex < pool.length; poolIndex++) {
			boolean thereWasAMatch = false;
			//Step 2 walk through the target
			for (int targetIndex = 0; targetIndex < target.length; targetIndex++) {
				if (pool[poolIndex].equals(target[targetIndex])) {
					//Mark it as found. This is to make sure the loop goes through the full target
					thereWasAMatch = true;
				} else {
//					System.out.printf("pool:[%s], target:[%s]", pool[poolIndex], target[targetIndex]);
				}
			}
			//After going through the whole target pool, see if there was a match. 
			//If there was no match, then the current pool item is the one that needs to be added
			if (!thereWasAMatch) {
				int resultIndex = 0;
				for (; resultIndex < target.length; resultIndex++) {
					result[resultIndex] = target[resultIndex];
				}
				result[resultIndex] = pool[poolIndex];
				break breakout;
			}
		}
		for (String s : result) {
			System.out.printf(s);
		}
		System.out.println("");
	}

	public static void main(String[] args) {
		String[] pool = {"A", "B", "C", "D"};
		String[] target = {"A", "B", "C"};
		String[] target2 = {"C", "B", "A"};
		String[] target3 = {"B", "D", "A"};
		//Expected result
		addToArray(target, pool);
		addToArray(target2, pool);
		addToArray(target3, pool);
		//target1: ABCD
		//target2: CBAD
		//target3: BDAC
		System.exit(121);
		String a[][] = new String[10][1];
//		String[] test = {"A1", "A2", "A3", "A4", "A5"};
//		String[] test2 ={"A1", "A2", "A3", "A4", "A5"};
//		String[] test2 ={"xx1", "xx2", "xx3", "xx4", "xx5"};
//		String[] test2 = {"A1", "A1", "B3", "B4", "B5"};
		String[] test = {"A", "A", "A", "X", "C"};
		String[] test2 = {"A", "A", "B", "A", "A"};
//		removeDuplicates(test, true, false);
//		removeDuplicates(test2, true, false);
//		removeDuplicates(test3, true, false);
//		removeDuplicates(test4, true, false);
		//merge the different elements only
		int dif = checkDiffs(test, test2, true);
		String[] t1 = {"A", "B", "C"};
		String[] t2 = {"C", "B"};
		System.out.println("Array t1");
		for (String s : t1) {
			System.out.print(s + " ");
		}
		System.out.println("");
		System.out.println("Array t2");
		for (String s : t2) {
			System.out.print(s + " ");
		}
		System.out.println("");
		System.out.println("Diff result: " + checkDiffs(t1, t2, true));

	}

}
