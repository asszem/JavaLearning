package Streams;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.Scanner;

public class KeyboardInputSamples {

	public static int getDoubleOnlyKeyboardInput() {
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

	public static char getSingleCharWithScanner() {
		Scanner input = new Scanner(System.in);
		String inputString = input.next();

		//Egy karakter bevitele:
		char inputCharacter = inputString.charAt(0);
		return inputCharacter;
	}

	public static void main(String[] args) {
		System.out.println("Enter a string:");
		System.out.println("First character of entered string:" + getSingleCharWithScanner());
		System.out.println("Enter a number:");
		System.out.println("Get double input result:\n"+ getDoubleOnlyKeyboardInput());
	}
}
