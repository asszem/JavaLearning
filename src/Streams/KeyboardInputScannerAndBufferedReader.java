package Streams;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class KeyboardInputScannerAndBufferedReader {

	//ÁRVÍZTŰRŐ compliant
	public static String getStringInputUsingScanner() {
		//Charset encodings
		//ISO8869_1
		//Windows-1250
		//UTF-8
		//UTF-16
		Scanner scanner = new Scanner(System.in, "ISO8859_1");
		return scanner.nextLine();
	}

	//ÁRVÍZTŰRŐ compliant
	public static String getStringUsingBufferedReader() {
		String charSet="ISO8859_1";
		String returnString=null;
		try {
			BufferedReader bufferedReader= new BufferedReader(new InputStreamReader (System.in, charSet));
			returnString=bufferedReader.readLine();
		} catch (UnsupportedEncodingException ex) {
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return returnString;
	}

	public static int getNumberUsingStreamTokenizer() {
		int result = 0;
		StreamTokenizer tokenizer = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
		try {
			while ((tokenizer.nextToken()) != StreamTokenizer.TT_NUMBER) {
			}
			result = (int) tokenizer.nval;
		} catch (IOException ex) {
		}
		return result;
	}

	public static int get_INT_OnlyUsingScanner() {
		Scanner scanner;
		while (true) {
			scanner = new Scanner(System.in);
			if (!scanner.hasNextInt()) {
				System.out.println("Error! Enter a number");
			} else {
				break;
			}
		}
		int userChoice = scanner.nextInt();
		System.out.println(userChoice);
		return userChoice;
	}

	public static char getSingleCharUsingScanner() {
		Scanner input = new Scanner(System.in);
		String inputString = input.next();

		//Egy karakter bevitele:
		char inputCharacter = inputString.charAt(0);
		return inputCharacter;
	}

	public static void main(String[] args) {
//		get_INT_OnlyUsingScanner();
System.out.println(getStringUsingBufferedReader());
	}
}
