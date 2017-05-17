/*
 * Requirement: include a line counter to the beginning of each line in format of 00001
 * */
package HortonExercises.Ch15;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Formatter;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.nio.file.StandardOpenOption;

public class Ex56_Andras {
	public static Path appendLineCounter(Path file) {
		Charset defCharset = Charset.defaultCharset();
		String newFileName=file.getFileName().toString()+ "linecounter_added.txt";
		Path outputFile = Paths.get(file.getParent().toString(),newFileName) ;
		String lineSeparator=System.lineSeparator();
		// Open the file
		try (Scanner fileScanner = new Scanner(file);) {
			BufferedWriter writer=Files.newBufferedWriter(outputFile, defCharset, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
			// Read the file line by line
			int lineCounter = 1;
			while (fileScanner.hasNextLine()) {
				// Append counter to the beginning of each line (for practice's
				String newLine; //declare here so it can be gcd after while loop
				newLine=getCounterString(lineCounter++)+ fileScanner.nextLine()+lineSeparator;
				// Write to new file
				writer.append(newLine);
			}
			writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return outputFile;
	}
public static void removeLineCounter(Path file) {
		Charset defCharset = Charset.defaultCharset();
		String newFileName=file.getFileName().toString()+ "linecounter_removed.txt";
		Path outputFile = Paths.get(file.getParent().toString(),newFileName) ;
		String lineSeparator=System.lineSeparator();
		// Open the file
		try (Scanner fileScanner = new Scanner(file);) {
			BufferedWriter writer=Files.newBufferedWriter(outputFile, defCharset, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
			// Read the file line by line
			int lineCounter = 1;
			while (fileScanner.hasNextLine()) {
				// Append counter to the beginning of each line (for practice's
				String newLine; //declare here so it can be gcd after while loop
				newLine=getRemovedLineString(fileScanner.nextLine())+lineSeparator;
				// Write to new file
				writer.append(newLine);
			}
			writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static String getRemovedLineString(String input){
		//Pattern: 5 digits and a space to be removed
		Pattern pattern = Pattern.compile("^\\d{5} ");
		Matcher matcher = pattern.matcher(input);
		String replaceTo = "";
		String result = matcher.replaceAll(replaceTo);
		return result;
	}

	public static String getCounterString(int input) {
		Formatter formatter = new Formatter();
		return formatter.format("%05d ", input).toString();
	}

	public static void main(String[] args) {
		Path file = Paths.get("C:/atemp/SourceSource.java");
		removeLineCounter(appendLineCounter(file));
	}
}
