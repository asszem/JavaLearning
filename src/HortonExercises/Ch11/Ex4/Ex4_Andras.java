/*
Requirement:
Write a program that outputs the contents of a file to the command line as groups of eight hexadecimal
digits with five groups to a line, each group separated from the next by a space.

12345678 9abcdef 12345678 12345678 12345678
123
   456
	  78 9ab
Horton, Ch11, p448

 */
package HortonExercises.Ch11.Ex4;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 *
 * @author Andras Olah (olahandras78@gmail.com)
 */
public class Ex4_Andras {

	Path filename;

	int[] fileContent;

	public void readFile(Path filename) {
		if (!Files.exists(filename)) {
			throw new IllegalArgumentException("Input file doesn't exists");
		}
		try (BufferedInputStream bufInputStream = new BufferedInputStream(new FileInputStream(filename.toFile()))) {
			int currentByte;
			int displayPosition = 1;
			int displayGroupNumber = 1;
			int displayLongNumber = 0;
			System.out.printf("  [%02d] ", ++displayLongNumber);
			while ((currentByte = bufInputStream.read()) != -1) {
				String hexString = Integer.toHexString(currentByte);
				for (int currentChar = 0; currentChar < hexString.length(); currentChar++) {
					if (displayPosition % 9 == 0) {
						if ((displayGroupNumber++) % 5 == 00) { //linebreak after 5 groups
							System.out.println("");
							System.out.printf("  [%02d] ", ++displayLongNumber);
						} else {
							System.out.printf("  [%02d] ", ++displayLongNumber);
						}
					} else {
						System.out.print(hexString.charAt(currentChar));
					}
					displayPosition++;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void displayHex(int[] allIntToDisplay, int startPosition) {
		int displayPosition = startPosition;
		for (int currentInt : allIntToDisplay) {
			String hexString = Integer.toHexString(currentInt);
			for (int currentChar = 0; currentChar < hexString.length(); currentChar++) {
				if (displayPosition % 9 == 0) {
					System.out.print("_");
				} else {
					System.out.print(hexString.charAt(currentChar));
				}
				displayPosition++;
			}
		}
	}

	public static void main(String[] args) {
//		int[] test = {113125, 12123, 231, 21, 3213, 123, 1321, 231, 231, 23, 13};
//		displayHex(test, 1);
		Ex4_Andras testInstance = new Ex4_Andras();
		Path filename = Paths.get("J:\\Writing Files\\PrimesAsLong.bin");
		testInstance.readFile(filename);
		filename = Paths.get("J:\\Writing Files\\PrimesMixed.bin");
		testInstance.readFile(filename);

	}
}
