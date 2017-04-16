package Streams;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.Scanner;

public class KeyboardInputSamples {

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
		get_INT_OnlyUsingScanner();
	}
}
