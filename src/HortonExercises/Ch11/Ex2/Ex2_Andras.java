/*
Requirement:
Extend the ReadPrimes example that you produced in this chapter to optionally display the nth prime,
when n is entered from the keyboard.
Horton, p447, Chapter 11, reading files

Note: PrimesToFile3 is the program that writes the file that is to be used for reading
File: J:\Writing Files\primesMixed.bin
 */
package HortonExercises.Ch11.Ex2;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Andras Olah (olahandras78@gmail.com)
 */
public class Ex2_Andras {

	public static int getKeyboardInput() {
		int result = 0;

		BufferedInputStream inputStream = new BufferedInputStream(System.in);
		System.out.println("Enter a number:");
		StreamTokenizer tokenizer = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
		try {
			while ((tokenizer.nextToken()) != StreamTokenizer.TT_NUMBER) {
			}
			result = (int) tokenizer.nval;
		} catch (IOException ex) {
			Logger.getLogger(Ex2_Andras.class.getName()).log(Level.SEVERE, null, ex);
		}
		return result;
	}

	public static void main(String[] args) {
		int input = getKeyboardInput();
		System.out.println("The input was:" + input);
	}
}
