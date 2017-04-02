package HortonExercises.Ch10.Ex4;

/**
 *
 * @author Andras Olah (olahandras78@gmail.com)
 */
public class arrayTemp {

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

	public static int checkDiffs(String[] diffA, String[] diffB) {
		//walk through the diffA array and check each element
		int totalDiffCount = diffA.length; //Comparing EACH array item with EACH other array items
		int positionDiffCount = 0; //Comparing ONLY the same index array items
		for (int diffAIndex = 0; diffAIndex < diffA.length; diffAIndex++) {
			//walk through the diffB array and check each element
			for (int diffBIndex = 0; diffBIndex < diffB.length; diffBIndex++) {
				if (!diffA[diffAIndex].equals(diffB[diffBIndex])) {//Found a different item!
					totalDiffCount++;
					if (diffAIndex == diffBIndex) {
						positionDiffCount++;
					}
				} else {
					totalDiffCount--;
				}
			}
		}
		//Algorithm to get the different array items ONLY
		//Two step method... First get two temp arrays to hold only the unique items in both arrays
		//					 Second, compare the unique arrays
		String diffAunique[] = new String[diffA.length]; //Array of items unique only to diffA
		String diffBunique[] = new String[diffB.length]; //Array of items unique only to diffB
		String diffARemovedDuplicates[] = new String[diffA.length]; //Array of non-duplicate items in diffA
		String diffBRemovedDuplicates[] = new String[diffB.length]; //Array of non-duplicate items in diffA
		int diffBcount = 0;
		for (int diffBIndex = 0; diffBIndex < diffB.length; diffBIndex++) {
			for (int diffAIndex = 0; diffAIndex < diffA.length; diffAIndex++) {
				if (diffA[diffAIndex].equals(diffB[diffBIndex])) { //index not considered. equality found

				} else { //if they are the same
				}
			}
		}
		System.out.println("Total diff count: " + totalDiffCount);
		System.out.println("Position diff count: " + positionDiffCount);
		System.out.println("Non-position diff count: " + (totalDiffCount - positionDiffCount));
		System.out.println("DiffB unique item cout: " + diffBcount);
		for (String s : diffBunique) {
			System.out.println(s);
		}
		return positionDiffCount;
	}

	public static String[] mergeArrayBtoA(String[] mergeTo, String[] mergeFrom) {

	}

	public static void main(String[] args) {
		String a[][] = new String[10][1];
		String[] test = {"A1", "A2", "A3", "A4", "A5"};
//		String[] test2 ={"A1", "A2", "A3", "A4", "A5"};
		String[] test2 = {"A1", "A1", "B3", "B4", "B5"};
		String[] test3 = {"A", "A", "A", "A", "A"};
		String[] test4 = {"A", "A", "B", "A", "A"};
		removeDuplicates(test, true, false);
		removeDuplicates(test2, true, false);
		removeDuplicates(test3, true, false);
		removeDuplicates(test4, true, false);
		//merge the different elements only
		int dif = checkDiffs(test, test2);
		a[0] = test;
		a[1] = test;
		a[2] = a[1];
		int c1, c2;
		c1 = 0;
		c2 = 0;
		for (String[] firstIndex : a) {
			for (String secondIndex : firstIndex) {
				System.out.printf("[%d][%d]=%s%n", c1, c2++, secondIndex);
			}
			c2 = 0;
			c1++;
		}
	}

}
