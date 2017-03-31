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
				//increment i so the next item will not be included in this 
			} else {
				newString.append(inputArray[i]);
			}
		}
		return newString.toString();
	}

	public static String[] swapArrayItems(String[] swapInputArray, int swapIndex) {
		boolean debug = false;
		if (debug) {
			System.out.println("swapArrayItems method");
			System.out.println("input array length=" + swapInputArray.length);
			System.out.println("swap index=" + swapIndex);
		}
//Creates a new array that has swapped the specified items
		if (swapIndex >= swapInputArray.length - 1) {
			System.out.printf("Error. Swap index[%d] higher than inputArray length[%d]", swapIndex, swapInputArray.length);
			return null;
		}
		String[] newArray = new String[swapInputArray.length];
		for (int i = 0; i < swapInputArray.length; i++) {
			if (i == swapIndex) {
				newArray[i] = swapInputArray[i + 1];
				newArray[i + 1] = swapInputArray[i++]; //swap items and increment i
			} else {
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
	 * Get Permutations method. Test Case 1. The methods swaps the array items starting from left and loops through the entire array
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
//			resultArray[i] = swapArrayItems(inputArray, swapPosition);
			//Create a new result array by swapping the items
			resultArray[i] = swapArrayItems(resultArray[i - 1], swapPosition);
			//check if the new array already exists. If yes, skip. Does this make an infinite loop?

//			if (getDuplicates(resultArray) > 0) {
//				System.out.println("Duplicte at " + i);
//			}
			//<editor-fold desc="shift the start array when end reached - did not work">
			/*
			//Check if the resultArray is the same as the startArray (which is initially the inputArray
			//if yes, then create a new start array with swap the first position or the previous start array
			if (checkArrayEquality(resultArray[i], startArray)) {
				System.out.println("Result array equals! i=" + i);
				System.out.println("");
//				printArray(resultArray[i], "equals!", 3);
				//ABCD -> BCDA - swiftLeft method
//				startArray = swapArrayItems(startArray, 0);
				startArray = leftShiftArray(startArray);
				printArray(startArray, "new start array:", 2);
				resultArray[i] = startArray;
				printArray(resultArray[i], "new result array:", 2);
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

	public static void main(String[] args) {
		String[] inputArray = {"A", "B", "C", "D"};
//		String[] inputArray = {"A", "B", "C","D", "E"};
		int testCase = 1;
		switch (testCase) {
			case 1:
				//<editor-fold desc="TC1 - swapping array items from left to right">
				System.out.println("Test Case 1 results");
				Path path = Paths.get("E:\\javaFileOpTest\\Permutations\\TC1");
				Path file = path.resolve("TC1-results.txt");
//		printArray(leftShiftArray(inputArray));
//		System.out.println(checkArrayEquality(inputArray, inputArray2));
				String[][] resultArray = new String[maxResults(inputArray)][1];
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
				break;
		}//switch

	}//main
}//class
