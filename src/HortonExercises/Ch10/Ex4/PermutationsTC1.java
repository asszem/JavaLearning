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
 * To calculate permutations of list with 3 items only
 *
 * @author Andras Olah (olahandras78@gmail.com)
 */
public class PermutationsTC1 {

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

	public static String[] swapArrayItems(String[] inputArray, int swapIndex) {
//Creates a new array that has swapped the specified items
		if (swapIndex >= inputArray.length - 1) {
			System.out.printf("Error. Swap index[%d] higher than inputArray length[%d]", swapIndex, inputArray.length);
			return null;
		}
		String[] newArray = new String[inputArray.length];
		for (int i = 0; i < inputArray.length; i++) {
			if (i == swapIndex) {
				newArray[i] = inputArray[i + 1];
				newArray[i + 1] = inputArray[i++]; //swap items and increment i
			} else {
				newArray[i] = inputArray[i];
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

	public static void main(String[] args) {
		String[] inputArray = {"A", "B", "C", "D", "E"};
//		String[] inputArray = {"A", "B", "C","D", "E"};
		String[][] resultArray = new String[maxResults(inputArray)][1];
		Path path = Paths.get("E:\\javaFileOpTest\\Permutations\\TC1");
		Path file = path.resolve("TC1-results.txt");
//		System.out.println(file);
//		System.out.println(maxResults(inputArray));

		resultArray[0] = inputArray;
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
			resultArray[i] = swapArrayItems(resultArray[i - 1], swapPosition);
			if (false) {
				System.out.printf("iteration:[%d], swap pos:[%d], array:", i, swapPosition);
				System.out.println("");
				System.out.print("\t");
				printArray(resultArray[i - 1], "prev", 3);
				System.out.println("");
				System.out.print("\t");
				printArray(resultArray[i], "next", 3);
				System.out.println("");
			}
		}
		System.out.println("Test Case 1 results");
		printEndResult(resultArray);
		writeResultsToFile(resultArray, file);

	}//main
}//class