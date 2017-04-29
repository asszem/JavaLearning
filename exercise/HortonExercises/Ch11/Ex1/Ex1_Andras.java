/*
Requirement:
Write a program to read back the contents of the f les written by the first exercise in the previous chapter
and output the proverb length that was read and the proverb itself for each of the proverbs

Horton, chapter 11, p448
 */
package HortonExercises.Ch11.Ex1;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 *
 * @author Andras Olah (olahandras78@gmail.com)
 */
public class Ex1_Andras {

	Path sourceFile;
	String[] readLines;

	//Constructor for file object
	public Ex1_Andras() {
		sourceFile = Paths.get("E:\\javaTest", "Exercises", "H10", "Ex1", "proverbsAndras.txt");
	}

	//method to read file
	public void readLines() {
		if (!Files.exists(sourceFile)) {
			System.exit(1);
		}
		try {
			BufferedReader bufferedReader = Files.newBufferedReader(sourceFile, Charset.forName("UTF-16"));
			String currentLine;
			String[] resultArray = new String[0];
			while ((currentLine = bufferedReader.readLine()) != null) {
				resultArray = addToArray(resultArray, currentLine);
			}
			readLines = resultArray;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	//method to display results

	public void displayResult() {
		for (String proverb:readLines){
			System.out.printf("[%d] %s%n",proverb.length(),proverb);
		}
	}

//helper method
	public static String[] addToArray(String[] inputArray, String stringToAdd) {
		String[] resultArray = new String[inputArray.length + 1];
		int newArrayIndex;
		for (newArrayIndex = 0; newArrayIndex < inputArray.length; newArrayIndex++) {
			resultArray[newArrayIndex] = inputArray[newArrayIndex];
		}
		resultArray[newArrayIndex] = stringToAdd;
		return resultArray;
	}

	public static void main(String[] args) {
		Ex1_Andras object = new Ex1_Andras();
		object.readLines();
		object.displayResult();
	}

}
