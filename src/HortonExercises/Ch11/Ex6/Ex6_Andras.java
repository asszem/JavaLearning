/*
Requirements
Modify the previous example to store an index to the firstName and address file in a separate file. 
The index file should contain each person’s second firstName, plus the position where the corresponding firstName and address can be found in the firstName and address file. 

Provide support for an optional command argument allowing a person’s second firstName to be entered. 
When the command-line argument is present, the program should then find the firstName and address 
and output it to the command line.

input:
javac Ex6_Andras andras
result:
[firstName]=Andras Olah [address]=address

Mainfile
[idValue]=1[firstName]=Andras Olah [address]=address
Index file
[idValue]=1[secondName]=Andras

Horton, Ch11, p448

 */
package HortonExercises.Ch11.Ex6;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import static java.nio.file.StandardOpenOption.*;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Andras Olah (olahandras78@gmail.com)
 */
public class Ex6_Andras {

	Path mainFile = Paths.get("J:\\Exercises\\Ch11\\Ex6\\Name And Address - Main.txt");
	Path indexFile = Paths.get("J:\\Exercises\\Ch11\\Ex6\\Name And Address - Index.txt");
	static Scanner scanner;
	ArrayList[] userListAL = new ArrayList[4];
	String[][] userListARR = new String[0][4]; //the length of this object holds all the entries created in one session
	final static int USER_ID_INDEX = 0;
	final static int FIRST_NAME_INDEX = 1;
	final static int SECOND_NAME_INDEX = 2;
	final static int ADDRESS_INDEX = 3;
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

	//Method returns number of existing entries by reading the mainFile
	//Actually this method might be redundant
	public int getMainFileLineCount() {
		int lineCounter = 0;
		if (!Files.exists(mainFile)) {
			return 0;
		}
		try {
			BufferedReader bufferedReader = Files.newBufferedReader(mainFile, Charset.forName("UTF-16"));
			while (bufferedReader.readLine() != null) {
				lineCounter++;
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return lineCounter;
	}

	public boolean loadUserList() {
		/* File structure:
[ID01][First Name][András][Second Name][Oláh][Address][Mordor road]
[ID02][First Name][Árvíztűrő FirstName][Second Name][Tükörfúrógép SecondName][Address][Second User Address]
		 */
		if (!Files.exists(mainFile)) {
			return false;
		}
		try {
			BufferedReader bufferedReader = Files.newBufferedReader(mainFile, Charset.forName("UTF-8"));
			String currentLine;
			while ((currentLine = bufferedReader.readLine()) != null) {
				String[] currentLineSplit=splitReadedString(currentLine);
				userListARR=addToUserListArray(userListARR, currentLineSplit);
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return true;
	}

	//Increase the userList array with the new result
	public static String[][] addToUserListArray(String[][] inputUserList, String[] arrayToAdd) {
		String[][] newList = new String[inputUserList.length + 1][4];
		//Copy the original array
		for (int i = 0; i < inputUserList.length; i++) {
			for (int j = 0; j < 4; j++) {
				newList[i][j] = inputUserList[i][j];
			}
		}
		//Add the new items
		for (int i = 0; i < 4; i++) {
			newList[newList.length - 1][i] = arrayToAdd[i];
		}
		return newList;
	}

	public static String[] splitReadedString(String inputStringToSplit) {
		String[] resultString = new String[4];
		String s = inputStringToSplit;
		String idValue = nextToken(1, s);
		int parsedLength = idValue.length();
		String firstName = nextToken(parsedLength + 3, s);
		parsedLength += firstName.length();
		String firstNameValue = nextToken(parsedLength + 5, s);
		parsedLength += firstNameValue.length();
		String secondName = nextToken(parsedLength + 7, s);
		parsedLength += secondName.length();
		String secondNameValue = nextToken(parsedLength + 9, s);
		parsedLength += secondNameValue.length();
		String address = nextToken(parsedLength + 11, s);
		parsedLength += address.length();
		String addressValue = nextToken(parsedLength + 13, s);
		resultString[USER_ID_INDEX] = idValue;
		resultString[FIRST_NAME_INDEX] = firstNameValue;
		resultString[SECOND_NAME_INDEX] = secondNameValue;
		resultString[ADDRESS_INDEX] = addressValue;
		return resultString;
	}

	public static String nextToken(int startPosition, String inputString) {
		String nextToken = inputString.substring(startPosition, inputString.indexOf("]", startPosition));
		return nextToken;
	}

	//Before writing data the existing list of data to be read from file to build the userList array and then add 
	public boolean writeData(String userInputFirstName, String userInputSecondName, String userInputAddress) {
		try {
			BufferedWriter bufferedWriter = Files.newBufferedWriter(mainFile, Charset.forName("UTF-16"), WRITE, CREATE, APPEND);
			Formatter formattedToWrite = new Formatter();
			formattedToWrite.format("ffsfasfasfas[name]=%s[address]=%3$s%2$s", userInputFirstName, System.lineSeparator(), userInputAddress);
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

	public static void main(String[] args) {
		String testString1 = "[ID01][First Name][András][Second Name][Oláh][Address][Mordor road]";
		splitReadedString(testString1);
		Ex6_Andras instance = new Ex6_Andras();
		instance.loadUserList();
		instance.run(0);
	}
}
