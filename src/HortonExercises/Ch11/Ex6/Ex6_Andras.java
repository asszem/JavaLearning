/*
Requirements
Modify the previous example to store an index to the name and address file in a separate file. 
The index file should contain each person’s second name, plus the position where the corresponding name and address can be found in the name and address file. 

Provide support for an optional command argument allowing a person’s second name to be entered. 
When the command-line argument is present, the program should then find the name and address 
and output it to the command line.

input:
javac Ex6_Andras andras
result:
[name]=Andras Olah [address]=address

Mainfile
[id]=1[name]=Andras Olah [address]=address
Index file
[id]=1[secondName]=Andras

Horton, Ch11, p448

 */
package HortonExercises.Ch11.Ex6;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import static java.nio.file.StandardOpenOption.*;
import java.util.Formatter;
import java.util.Scanner;

/**
 *
 * @author Andras Olah (olahandras78@gmail.com)
 */
public class Ex6_Andras {

	Path mainFile = Paths.get("J:\\Exercises\\Ch11\\Ex5\\Name And Address - Main.txt");
	Path indexFile = Paths.get("J:\\Exercises\\Ch11\\Ex5\\Name And Address - Index.txt");
	static Scanner scanner;
	String[][] userList; //the length of this object holds all the entries created in one session
	final int USER_ID_INDEX=0;
	final int FIRST_NAME_INDEX = 1;
	final int SECOND_NAME_INDEX = 2;
	final int ADDRESS_INDEX = 3;
	//userList[entry_index][USER_ID_INDEX]=UserID 123   <this is a string also
	//userList[entry_index][FIRST_NAME_INDEX]=Olah
	//userList[entry_index][SECOND_NAME_INDEX]=Andras
	//userList[entry_index][ADDRESS_INDEX]=Mordor road 1. 

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
		scanner = new Scanner(System.in, "ISO8859_1");
		return scanner.nextLine();
	}

	public void enterData() {
		String userInputFirstName;
		String userInputSecondName;
		String userInputAddress;
		System.out.println("Data input selected.");
		while (true) {
			System.out.print("Enter First Name:");
			userInputFirstName = getStringInput();
			System.out.print("Enter Second Name:");
			userInputSecondName = getStringInput();
			System.out.print("Enter address:");
			userInputAddress = getStringInput();
			boolean writeResult = writeData(userInputFirstName, userInputSecondName, userInputAddress);
			System.out.println("Writing was " + (writeResult ? "Successfull" : "Not successfull"));
			System.out.print("Add another? y/n");
			if (!getStringInput().equalsIgnoreCase("y")) {
				break;
			}
		}
	}

	public boolean writeData(String userInputFirstName, String userInputSecondName, String userInputAddress) {
		try {
			BufferedWriter bufferedWriter = Files.newBufferedWriter(mainFile, Charset.forName("UTF-16"), WRITE, CREATE, APPEND);
			Formatter formattedToWrite = new Formatter();
			formattedToWrite.format("[name]=%s[address]=%3$s%2$s", userInputFirstName, System.lineSeparator(), userInputAddress);
			bufferedWriter.write(formattedToWrite.toString());
			bufferedWriter.flush();
			return true;
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return false;
	}

	public void displayFullMainFile() {
		if (!Files.exists(mainFile) || !Files.exists(indexFile)) {
			throw new IllegalStateException("Source file does not exists");
		}
		try {
			BufferedReader bufferedReader = Files.newBufferedReader(mainFile, Charset.forName("UTF-16"));
			String currentLine;
			while ((currentLine = bufferedReader.readLine()) != null) {
				System.out.println(currentLine);

			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	//Method returns number of existing entries
	public int getExistingEntryNumberFromFile(){
		int existingEntries=0;
		if (!Files.exists(mainFile) ) {
			return 0;	
		}
		return existingEntries;
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
					displayFullMainFile();
					break;
			}
		} while (userChoice != QUIT);
	}

}
