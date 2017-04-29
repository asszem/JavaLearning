/*
Requirement:
Write a program that allows either one or more names and addresses to be entered from the keyboard and
appended to a file, or the contents of the file to be read back and output to the command line.

Horton, Chapter 11, p448

 */
package HortonExercises.Ch11.Ex5;

import FilesAndDirectories.AllFileOperations;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import static java.nio.file.StandardOpenOption.*;
import java.util.Formatter;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Andras Olah (olahandras78@gmail.com)
 */
public class Ex5_Andras {

	Path filename = Paths.get("J:\\Exercises\\Ch11\\Ex4\\nameAndAddress.txt");
	static Scanner scanner;

	public static int getNumberInput() {
		while (true) {
			scanner = new Scanner(System.in);
			if (!scanner.hasNextInt()) {
				System.out.println("Error! Enter a number");
			} else {
				break;
			}
		}
		int userChoice = scanner.nextInt();
		return userChoice;
	}

	public static String getStringInput() {
		scanner = new Scanner(System.in,"ISO8859_1");
		return scanner.nextLine();
	}


	public void enterData() {
		String userInputName;
		String userInputAddress;
		System.out.println("Data input selected.");
		while (true) {
			System.out.print("Enter name:");
			userInputName = getStringInput();
			System.out.print("Enter address:");
			userInputAddress = getStringInput();
			boolean writeResult = writeData(userInputName, userInputAddress);
			System.out.println("Writing was " + (writeResult ? "Successfull" : "Not successfull"));
			System.out.print("Add another? y/n");
			if (!getStringInput().equalsIgnoreCase("y")) {
				break;
			}
		}
	}

	public boolean writeData(String userInputName, String userInputAddress) {
		try {
			BufferedWriter bufferedWriter = Files.newBufferedWriter(filename, Charset.forName("UTF-16"), WRITE, CREATE, APPEND);
			Formatter formattedToWrite = new Formatter();
			formattedToWrite.format("[name]=%s[address]=%3$s%2$s", userInputName, System.lineSeparator(), userInputAddress);
			bufferedWriter.write(formattedToWrite.toString());
			bufferedWriter.flush();
			return true;
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return false;
	}

	public void readData() {
		if (!Files.exists(filename)) {
			throw new IllegalStateException("Source file does not exists");
		}
		try {
			BufferedReader bufferedReader = Files.newBufferedReader(filename, Charset.forName("UTF-16"));
			String currentLine;
			while ((currentLine = bufferedReader.readLine()) != null) {
				System.out.println(currentLine);

			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}

	}

	public void run(int startWithThis) {
		int userChoice = -1;
		final int QUIT = 0;
		final int ENTER_DATA = 1;
		final int READ_DATA = 2;
		do {
			System.out.println("1. - Enter data\n2. - Read data\n0. - Quit");
			System.out.print("Please choose:");
			if (startWithThis != 0) {
				userChoice = startWithThis;
				startWithThis = 0;
			} else { //ask for user input only when there is no more predefined starting point
				userChoice = getNumberInput();
			}
			switch (userChoice) {
				case ENTER_DATA:
					enterData();
					break;
				case READ_DATA:
					readData();
					break;
			}
		} while (userChoice != QUIT);
	}

	public static void main(String[] args) {
		Ex5_Andras instance = new Ex5_Andras();
		instance.run(0);
//AllFileOperations.createSingleFileWithParentDirectories(instance.filename);

	}
}
