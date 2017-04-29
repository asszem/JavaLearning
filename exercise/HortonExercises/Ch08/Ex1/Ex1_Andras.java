/*Use a StreamTokenizer object to parse a string entered from the keyboard containing a series of data
items separated by commas and output each of the items on a separate line.
 */
package HortonExercises.Ch08.Ex1;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 *
 * @author Andras Olah (olahandras78@gmail.com)
 */
public class Ex1_Andras {

	public static void main(String[] args) throws IOException {
		StreamTokenizer tokenizer = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in, StandardCharsets.ISO_8859_1)));
		System.out.println("Enter input string:");
		try {
			tokenizer.resetSyntax();

			char separator = ','; //Taken from official Horton solution
			System.out.printf("Szeparátor karakter: [%c]%n(int) kasztolva: %d%nunicode értéke: %<04x%n", separator, (int) separator);
			char test = '\u002c';
			System.out.println("Test: " + test);
			// Characters other than comma and special characters are word characters
			tokenizer.wordChars('\u0000', (char) (separator - 1));   // Everything is a word character
			tokenizer.wordChars((char) (separator + 1), '\uffff');   // except for the separator
			tokenizer.whitespaceChars('\n', '\n');   				// Make end-of-line whitespace
			//(and therefore a word delimiter)
			tokenizer.whitespaceChars(separator, separator);       // Delimiter separates words
			tokenizer.eolIsSignificant(true);                      // End-of-line to be reported as TT_EOL
			int beolvasas;
			while ((beolvasas = tokenizer.nextToken()) != tokenizer.TT_EOL) {
				System.out.println(tokenizer.sval.trim()); //Mivel minden unicode word
			}
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
}
/*
  red  , "red and yellow", red and yellow, 123, 123.456, 1.2E10
    red
    "red and yellow"
    red and yellow
    123
    123.456
    1.2E10
 */
