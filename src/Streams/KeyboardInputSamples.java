package Streams;

import java.util.Scanner;

public class KeyboardInputSamples {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		String inputString = input.next();

		//Egy karakter bevitele:
		char inputCharacter = inputString.charAt(0);
		System.out.println("inputCharacter: " + inputCharacter);
	}
}
