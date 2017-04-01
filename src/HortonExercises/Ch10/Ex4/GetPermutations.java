package HortonExercises.Ch10.Ex4;

import FilesAndDirectories.Buffers;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.WritableByteChannel;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import static java.nio.file.StandardOpenOption.*;
import java.util.EnumSet;
import java.util.Formatter;

/**
 *
 * @author Andras Olah (olahandras78@gmail.com)
 */
public class GetPermutations {

	public static String strBuild(String[] input) {
		boolean debug = false;
		StringBuilder strBuild = new StringBuilder();
		for (int i = 0; i < input.length; i++) {
			strBuild.append(input[i]);
		}
		if (debug) {
			System.out.println(strBuild);
		}
		return strBuild.toString();
	}

	public static String swapStringItems(String[] inputArray, int swapIndex) {
		if (swapIndex >= inputArray.length - 1) {  //the last item index (length-1) can not be swapped
			System.out.printf("Error. Swap index[%d] higher than inputArray length[%d]", swapIndex, inputArray.length);
		}
		StringBuilder newString = new StringBuilder();
		for (int i = 0; i < inputArray.length; i++) {
			if (i == swapIndex) {
				newString.append(inputArray[i + 1]); //Add the next item to the current position in newstring
				newString.append(inputArray[i++]); //Add the current item to the next position in newString
				//increment firstIndex so the next item will not be included in this 
			} else {
				newString.append(inputArray[i]);
			}
		}
		return newString.toString();
	}

	/**
	 * Method swaps the array items from the provided position to the provided position
	 *
	 * @param swapInputArray
	 * @param swapFromIndex
	 * @param swapAmount
	 * @return an array with the swapped content
	 */
	public static String[] swapArrayItems(String[] swapInputArray, int swapFromIndex, int swapAmount) {
		boolean debug = false;
		if (debug) {
			System.out.println("swapArrayItems method");
			System.out.println("input array length=" + swapInputArray.length);
			System.out.println("swap index=" + swapFromIndex);
			System.out.println("swap to index= " + swapAmount);
		}
//Creates a new array that has swapped the specified items
		if (swapFromIndex + swapAmount >= swapInputArray.length) {
			System.out.printf("Error. Swap to index [%d] is higher than max possible index [%d]", swapFromIndex + swapAmount, swapInputArray.length - 1);
			return null;
		}
		String[] newArray = new String[swapInputArray.length];
//		newArray = swapInputArray; //to make sure they have the same values -- this will change the INPUT array passed as argument!
		//Clone the swapInputArray 
		for (int i = 0; i < swapInputArray.length; i++) {
			newArray[i] = swapInputArray[i];
		}
		for (int i = 0; i < swapInputArray.length; i++) {
			if (i == swapFromIndex) {
				String temp = swapInputArray[i];
				newArray[i] = swapInputArray[i + swapAmount];
				newArray[i + swapAmount] = temp;
				i++;
			} else if (i != swapFromIndex + swapAmount) {
				newArray[i] = swapInputArray[i];
			}
		}
		return newArray;
	}

	public static void printArray(String[] inputArray) {
		int counter = 0;
		for (String s : inputArray) {
			System.out.printf("#%d [%s]%n", counter++, s);
		}
	}

	public static void printArray(String[] inputArray, String msg) {
		int counter = 0;
		System.out.println(msg);
		for (String s : inputArray) {
			System.out.printf("#%d [%s]%n", counter++, s);
		}
	}

	public static void printArray(String[] inputArray, String msg, int listStyle) {
		int counter = 0;
		if (msg != null) {
			switch (listStyle) {
				case 3:
					System.out.print(msg + " ");
					break;
				default:
					System.out.println(msg);
			}
		}
		for (String s : inputArray) {
			switch (listStyle) {
				case 1: //list arrays horizonally, without any linebreaks
					System.out.printf("#%d[%s] ", counter++, s);
					break;
				case 2: //list arrays horizonally, without any linebreaks
					System.out.printf("[%s] ", s);
					break;
				case 3:
					System.out.printf("[%s] ", s);
					break;
				default:
					System.out.printf("#%d [%s]%n", counter++, s);
					break;
			}
		}
	}

	public static int maxResults(String[] inputArray) {
		int result = 1;
		for (int i = inputArray.length; i > 1; i--) {
			result *= i;
		}
		return result;
	}

	public static void printEndResult(String[][] endResult) {
		int counter = 0;
		for (String[] currentArray : endResult) {
			System.out.printf("resultArray[%02d]: ", counter++);
			printArray(currentArray, null, 2);
			System.out.println("");
		}
	}

	public static void writeResultsToFile(String[][] result, Path file) {
		boolean debug = false;
		try {
			Files.createDirectories(file.getParent());
		} catch (IOException e) {
			e.printStackTrace();
		}
		ByteBuffer buf = ByteBuffer.allocate(1024);
		String separator = System.getProperty("line.separator");
		try (WritableByteChannel channel = Files.newByteChannel(file, EnumSet.of(CREATE, WRITE))) {
			int counter = 0;
			for (String[] currentArray : result) {
				Formatter toBuffer = new Formatter();
				//the strBuild method creates a String from the input Array
				String toBufferString = toBuffer.format("[%02d] %s%s", counter++, strBuild(currentArray), separator).toString();
				//wrap the content of currentArray as a string to a ByteBuffer
				buf = ByteBuffer.wrap(toBufferString.getBytes(Charset.defaultCharset()));
//				buf.flip();
				if (false) {
					Buffers.bufferStatus(buf, "Buffer before writing");
				}
				channel.write(buf);
				if (false) {
					System.out.println("File written is " + ((FileChannel) channel).size() + " bytes.");
				}
			}//end for
		} catch (IOException e) {
			e.printStackTrace();
		}//end catch
	} //end method

	/**
	 * Compares two string array's content for equality
	 *
	 * @param a String array
	 * @param b String array
	 * @return true if arrays equals, false if not
	 */
	public static boolean checkArrayEquality(String[] a, String[] b) {
		boolean debug = false;
		if (debug) {
			System.out.printf("Length: a=%d, b=%d%n", a.length, b.length);
		}
		if (a.length != b.length) {
			return false;
		}
		for (int i = 0; i < a.length; i++) {
			if (!a[i].equals(b[i])) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Get the number of duplicates in the provided results[][] array
	 *
	 * @param arrayToCheck a String[][] array. The 2nd dimension will be checked
	 * @return Returns the number of duplicate items in the provided array list
	 */
	public static int getDuplicates(String[][] arrayToCheck) {
		boolean debug = false;
		int duplicates = 0;
		String[] compareTo = new String[arrayToCheck[0].length]; //All array will be the same length
		if (debug) {
			System.out.println("Result array length: " + compareTo.length);
		}
		//Outer: run through the arrayToCheck array to set the CompareTo array
		int outerCounter = 0;
		for (String[] s : arrayToCheck) {
			compareTo = s;
			if (debug) {
				printArray(compareTo, "CompareTo", 3);
				System.out.println("-");
			}
			//Inner: run through the arrayToCheck array again to compare each value to the CompareTo array
			int innerCounter = 0;
			for (String[] testS : arrayToCheck) {
				if (debug) {
					System.out.printf(" o:%di:%d%n", outerCounter, innerCounter);
					printArray(testS, "testS", 3);
					System.out.println("");
				}
				//Equality check
				// testS.length>1 - to make sure the test array is not "null"
				// checkArrayEquality - check if the two arrays are identical
				// innerCounter =! outerCounter - to skip equality check for itself
				if (testS.length > 1 && checkArrayEquality(compareTo, testS) && innerCounter != outerCounter) {
					duplicates++;
					if (debug) {
//						System.out.println("");
//						printArray(compareTo, "CompareTo", 3);
						System.out.println("");
//						printArray(testS, "CompareD to", 3);
						System.out.println("Equals! Duplicates count: " + duplicates);
						System.out.printf("Outer[%d] inner[%d]", outerCounter, innerCounter);
					}
				}
				innerCounter++;
			}//inner
			outerCounter++;
		}//outer
		return duplicates / 2; //As duplicates will be found twice in the method, only half need to be returned
	}

	/**
	 * Displays only those arrays that match the filter criteria on the given index
	 *
	 * @param inputArray The input array. The second dimension will be filtered
	 * @param filter Only results that equals with filter will be shown
	 * @param filterIndex the index of the array to be used for filter
	 */
	public static void filterArray(String[][] inputArray, String filter, int filterIndex) {
		int counter = 0;
		System.out.printf("Filter:[%s] index:[%d]%n", filter, filterIndex);
		for (String[] currentArray : inputArray) {
			if (inputArray[counter][filterIndex].equals(filter)) {
				System.out.printf("resultArray[%02d]: ", counter);
				printArray(currentArray, null, 2);
				System.out.println("");
			}
			counter++;
		}
	}

	/**
	 * Shifts left the array content. The first item becomes the last, the second the first, etc.
	 *
	 * @param inputArray
	 * @return ABCD -> BCDA
	 */
	public static String[] leftShiftArray(String[] inputArray) {
		String[] shiftArray = new String[inputArray.length];
		shiftArray[0] = inputArray[inputArray.length - 1];
		shiftArray[inputArray.length - 1] = inputArray[0];
		for (int i = 0; i < inputArray.length - 1; i++) {
			shiftArray[i] = inputArray[i + 1];
		}
		return shiftArray;
	}

	/**
	 * Test Case 1 - Algorithm: swap from left to right Get Permutations method. Test Case 1. The methods swaps the array items starting from left and loops through the entire
	 * array
	 *
	 * @param inputArray
	 * @return a two dimensional array. First dimension equals the total number of possible permutations, second dimension are the permutation array
	 */
	public static String[][] getPermutationsTC1(String[] inputArray) {
		boolean debug = false;
		if (debug) {
			System.out.println("getPermutationsTC1 debug mode");
			System.out.println("Input arary length=" + inputArray.length);
			System.out.println(maxResults(inputArray));
		}
		String[][] resultArray = new String[maxResults(inputArray)][1];
		resultArray[0] = inputArray; //The first item in the results array must be set for the algorithm to work
		String[] startArray = inputArray; //This will be changed once the swapping will results identical to the start
		int swapPosition = -1;
		for (int i = 1; i < resultArray.length; i++) {
			//inputArray length=3 
			//max swap position: 0, 1
			if (swapPosition >= inputArray.length - 2) {
				swapPosition = 0;
			} else {
				swapPosition++;
			}
//			resultArray[firstIndex] = swapArrayItems(inputArray, swapPosition);
			//Create a new result array by swapping the items
			resultArray[i] = swapArrayItems(resultArray[i - 1], swapPosition, 1);
			//check if the new array already exists. If yes, skip. Does this make an infinite loop?

//			if (getDuplicates(resultArray) > 0) {
//				System.out.println("Duplicte at " + firstIndex);
//			}
			//<editor-fold desc="shift the start array when end reached - did not work">
			/*
			//Check if the resultArray is the same as the startArray (which is initially the inputArray
			//if yes, then create a new start array with swap the first position or the previous start array
			if (checkArrayEquality(resultArray[firstIndex], startArray)) {
				System.out.println("Result array equals! firstIndex=" + firstIndex);
				System.out.println("");
//				printArray(resultArray[firstIndex], "equals!", 3);
				//ABCD -> BCDA - swiftLeft method
//				startArray = swapArrayItems(startArray, 0);
				startArray = leftShiftArray(startArray);
				printArray(startArray, "new start array:", 2);
				resultArray[firstIndex] = startArray;
				printArray(resultArray[firstIndex], "new result array:", 2);
				swapPosition = -1;
				System.out.println("New swapPosition: " + swapPosition);
			}
			 */
			//</editor-fold>
			if (false) {
				System.out.printf("iteration:[%d], swap pos:[%d], array:", i, swapPosition);
				System.out.println("");
				System.out.print("\t");
				printArray(resultArray[i - 1], "prev", 3);
				System.out.println("");
				System.out.print("\t");
				printArray(resultArray[i], "next", 3);
				System.out.println("");
				System.out.println("Number of duplicates: " + getDuplicates(resultArray));
			}
		}
		return resultArray;
	}//end method

	//Helper methods for TC2
	/**
	 *
	 * @param stringToCheck
	 * @param arrayToCheck
	 * @return
	 */
	public static boolean checkAlreadyExists(String stringToCheck, String[] arrayToCheck) {
		for (String s : arrayToCheck) {
			if (s.equals(stringToCheck)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Test Case 2 - Algorithm: Build 2-item 'molecules'
	 *
	 * @param inputArray
	 * @return
	 */
	public static String[][] getPermutationsTC2(String[] inputArray, boolean debug) {
		int len = inputArray.length;
		String[][] molecules = new String[len * len][2];
		String[][] resultArray = new String[maxResults(inputArray)][1];
//		String[][] resultArray = new String[totalVariations][1];
//		int totalVariations = (int) Math.pow(inputArray.length, inputArray.length);
		if (debug) {
			System.out.println("**getPermutationsTC2 debug mode START**");
			System.out.println("Input array length=" + len);
			System.out.println("Result array length=" + resultArray.length);
			System.out.println("Molecule first array length=" + molecules.length);
		}
		/*
		0 molecules[0][0]=A 
		1 molecules[0][1]=B 		molecules[0][0] + [0][1] = AB
		2 molecules[1][0]=A 
		3 molecules[1][1]=C 
								molecules[1][0] + [1][1] = AC
		4 molecules[2][0]=A 
		5 molecules[2][1]=D 
								molecules[2][0] + [2][1] = AD
		 */
		int counter = 0; //the counter for Molecules array length. Equals to len*len
		int firstIndex = 0; //The first index. Should be incremented only when all second index is used
		int secondIndex = 0; //The second index. 
		while (counter < molecules.length) {
			molecules[counter][0] = inputArray[firstIndex];
			molecules[counter][1] = inputArray[secondIndex];
			if (secondIndex >= len - 1) {
				secondIndex = 0;
				firstIndex++;
			} else {
				secondIndex++;
			}
			counter++;
		}
		if (debug) {
			System.out.println("The molecule array:");
			for (firstIndex = 0; firstIndex < molecules.length; firstIndex++) {
//				System.out.printf("%d. molecules[%d][0]=%s%n", counter++, firstIndex, molecules[firstIndex][0]);
//				System.out.printf("%d. molecules[%d][1]=%s%n", counter++, firstIndex, molecules[firstIndex][1]);
				System.out.printf("The %d. molecule:[%d] [0]+[1] =%s%n", firstIndex, firstIndex, molecules[firstIndex][0] + molecules[firstIndex][1]);
			}
		}//end debug
		//Walk through the list and 'stich' together all possible variations
		if (debug) {
			System.out.println("Comparing molecules...\n");
		}
		int resultCounter=0;
		for (int currentMolecule = 0; currentMolecule < molecules.length; currentMolecule++) {
			//Walk through the array again and compare each molecule to current molecule	
			boolean isMoleculeDifferent = false;
			for (int comparedMolecule = 0; comparedMolecule < molecules.length; comparedMolecule++) {
				String currentStringAtom0 = molecules[currentMolecule][0];
				String currentStringAtom1 = molecules[currentMolecule][1];
				String comparedStringAtom0 = molecules[comparedMolecule][0];
				String comparedStringAtom1 = molecules[comparedMolecule][1];
				if (currentStringAtom0.equals(comparedStringAtom0) || currentStringAtom1.equals(comparedStringAtom1) || currentStringAtom0.equals(currentStringAtom1) || comparedStringAtom0.equals(comparedStringAtom1) || currentStringAtom0.equals(comparedStringAtom1) || currentStringAtom1.equals(comparedStringAtom0)) {
					isMoleculeDifferent = false;
				} else { //This is it! The atoms are different, lets built the final string!
					isMoleculeDifferent = true;
					//build a result string
					String[] actualResult =new String[len];
					actualResult[0]=currentStringAtom0;
					actualResult[1]=currentStringAtom1;
					actualResult[2]=comparedStringAtom0;
					actualResult[3]=comparedStringAtom1;
					resultArray[resultCounter++]=actualResult;
				}
				if (debug) {
					if (isMoleculeDifferent) {
						System.out.println("Current molecule" + currentStringAtom0 + currentStringAtom1);
						System.out.println("Compared molecule" + comparedStringAtom0 + comparedStringAtom1);
						System.out.println("isMoleculeDifferent: " + isMoleculeDifferent);
						System.out.println("");
					}
				}//end debug
			}//end ComparedMolecule
		}
		if (debug) {
			System.out.println("End of Comparing molecules...\n");
		}
		//<editor-fold desc="Attempt to solve with with a while loop">
		/*

		//Walk through the input array. inputIndex = horizontal walkthrough
		int currentVariation = 0;
		//Repeat until all possible permutations (=MaxResults) are found
//		String[] nextValidPermutation = new String[inputArray.length]; //To store the next valid permutation
		String[] nextValidPermutation = resultArray[0];
		while (currentVariation < maxResults(inputArray) - 1) { //-1 because the first one is already found
			boolean validPermutationFound = false;
			while (!validPermutationFound) {
				//create new proposed array configuration
				//validate if it's a duplicate or not
				validPermutationFound = true;
			}
			int swapStartIndex = 0;
			int swapAmount = 1;
			if (swapStartIndex + swapAmount >= inputArray.length - 1) {
				swapStartIndex = 0;
			} else {
				System.out.println(++swapStartIndex);
			}
//				nextValidPermutation = swapArrayItems(resultArray[currentVariation], swapStartIndex, swapAmount);
			resultArray[++currentVariation] = swapArrayItems(resultArray[currentVariation], swapStartIndex++, swapAmount);
//			resultArray[++currentVariation] = nextValidPermutation;
		}//end while
		 */
		//</editor-fold>
		if (debug) {
			System.out.println("**getPermutationsTC2 debug mode END**");
		}
		return resultArray;
	}

	public static void main(String[] args) {
		String[] inputArray = {"A", "B", "C", "D"};
//		String[] inputArray = {"A", "B", "C","D", "E"};
//		String[] inputArray = {"A", "B", "C","D", "E", "F"};
		String[][] resultArray = new String[maxResults(inputArray)][1];
		Path path;
		Path file;
		int testCase = 2;
		switch (testCase) {
			case 1:
				//<editor-fold desc="TC1 - swapping array items from left to right">
				System.out.println("Test Case 1 results");
				path = Paths.get("E:\\javaFileOpTest\\Permutations\\TC1");
				file = path.resolve("TC1-results.txt");
//		printArray(leftShiftArray(inputArray));
//		System.out.println(checkArrayEquality(inputArray, inputArray2));
				printArray(inputArray, "input array=", 3);
				System.out.println("\nMax permutations=" + maxResults(inputArray));
				System.out.println("Test result");
				resultArray = getPermutationsTC1(inputArray);
				printEndResult(resultArray);
				System.out.println("Number of duplicates: " + getDuplicates(resultArray));
//		filterArray(resultArray, "A", 0);
				writeResultsToFile(resultArray, file);
				System.out.println("File written: " + file);
				//</editor-fold>
				break;
			case 2:
				//<editor-fold desc="TC2 - ">
				System.out.println("Test Case 2 results");
				path = Paths.get("E:\\javaFileOpTest\\Permutations\\TC2");
				file = path.resolve("TC2-results.txt");
				printArray(inputArray, "input array=", 3);
				System.out.println("\nMax permutations=" + maxResults(inputArray));
				System.out.println("Test result");
				//call the new method
				resultArray = getPermutationsTC2(inputArray, true); //second parameter = debug mode
				//print results
				printEndResult(resultArray);
				filterArray(resultArray, "A", 0);
				writeResultsToFile(resultArray, file);
				System.out.println("File written: " + file);
				//</editor-fold>
				break;
		}//switch

	}//main
}//class
