package IOStreams;

import IOStreams.H_Ch8_Streams.HortonSample.InvalidUserInputException; //This is required for the homemade Exception
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

/**
 *
 * @author Andras Olah (olahandras78@gmail.com)
 */
public class KeyboardInputTokenizer {

	// Object to tokenize input from the standard input stream
	private StreamTokenizer tokenizer = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
	private int ttype;      // Stores the token type code

	//Only Integer is valid input
	public int readInt() throws InvalidUserInputException {
		if (readToken() != StreamTokenizer.TT_NUMBER) {
			throw new InvalidUserInputException("readInt() failed. Input data not numeric.");
		}

		if (tokenizer.nval > (double) Integer.MAX_VALUE
				|| tokenizer.nval < (double) Integer.MIN_VALUE) {
			throw new InvalidUserInputException("readInt() failed. Input outside range of type int.");
		}

		if (tokenizer.nval != (double) (int) tokenizer.nval) {
			throw new InvalidUserInputException("readInt() failed. Input not an integer");
		}
		return (int) tokenizer.nval;
	}

	//Double is valid input
	public double readDouble() throws InvalidUserInputException {
		if (readToken() != StreamTokenizer.TT_NUMBER) {
			throw new InvalidUserInputException("readDouble() failed. Input data not numeric.");
		}
		return tokenizer.nval;
	}

	//String is valid input
	public String readString() throws InvalidUserInputException {
		if (readToken() == StreamTokenizer.TT_WORD || ttype == '\"' || ttype == '\"') {
			return tokenizer.sval;
		} else {
			throw new InvalidUserInputException("readString() failed. Input data is not a string.");
		}
	}
	// Plus methods to read various other data types...

	// Helper method to read the next token. Returns the type of the token that has been read
	private int readToken() {
		try {
			ttype = tokenizer.nextToken();
			return ttype;

		} catch (IOException e) {                                    // Error reading in nextToken()
			e.printStackTrace();		
			System.exit(1);                                          // End the program
		}
		return 0;
	}

	public static void main(String[] args) {
	KeyboardInputTokenizer keyboardInput = new KeyboardInputTokenizer();
		try {
			System.out.print("Enter Int:");
			System.out.println(keyboardInput.readInt());
			System.out.print("Enter Double:");
			System.out.println(keyboardInput.readDouble());
			System.out.print("Enter String:");
			System.out.println(keyboardInput.readString());
		} catch (InvalidUserInputException ex) {
			System.out.println(ex.getMessage());
		}
	}
}
