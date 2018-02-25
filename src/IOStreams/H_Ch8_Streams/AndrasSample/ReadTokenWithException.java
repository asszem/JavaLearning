package IOStreams.H_Ch8_Streams.AndrasSample;
//TODO add comment for each import with explanation why it is needed

import java.io.InputStreamReader; //used to read input from specified stream
import java.io.BufferedReader;	//used to read character from stream
import java.io.StreamTokenizer; //used to call the tokenizer on the input
import java.io.IOException; //used because Reader throws IOException
import java.nio.charset.StandardCharsets;

/**
 *
 * @author Andras Olah (olahandras78@gmail.com)
 */
public class ReadTokenWithException {

	private int tokenType;
	private StreamTokenizer tokenizer = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in, StandardCharsets.ISO_8859_1)));

	//Method to handle the IOException for nextToken();
	public int readToken() {
		try {
			tokenType = tokenizer.nextToken();
			return tokenType;
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		return 0; //if no token type identified
	}

	public int readIntToken() {
		for (int i = 0; i < 5; i++) {
			if (readToken() == StreamTokenizer.TT_NUMBER) {
				System.out.println("Gratulálunk. Ön számot vitt be, mégpedig: " + tokenizer.nval);
				return (int) tokenizer.nval;
			} else {
				System.out.println("Ön nem számot adott meg, kérem próbálja újra");
				System.out.println("Az ön által megadott token: " + tokenizer.toString());
				continue;
			}
		}
		System.out.println("Ide csak akkor jut, ha az összes próbálkozást ellőtte. Ön teljesen idióta.");
		System.exit(1);
		return 0;
	}

	//Ugyanaz a metódus, de InvalidInputException-t dobunk user hiba esetén
	public int readIntToken2() throws InvalidInputException {
		for (int i = 0; i < 5; i++) {
			if (readToken() == StreamTokenizer.TT_NUMBER) {
				System.out.println("Gratulálunk. Ön számot vitt be, mégpedig: " + tokenizer.nval);
				return (int) tokenizer.nval;
			} else {
				System.out.format("Próbálkozás %d. Token: %s. Próbálja újra%n", i+1,tokenizer.toString());
//				System.out.println("Ön nem számot adott meg, kérem próbálja újra");
//				System.out.println("Az ön által megadott token: " + tokenizer.toString());
//				System.out.println("Az ön próbálkozásának száma: "+i);
				continue;
			}
		}
		throw new InvalidInputException("Exception! Ön ötször rossz számot adott meg!", tokenizer.toString());
	}

	public static void main(String[] args) throws IOException {
		ReadTokenWithException test = new ReadTokenWithException();
//		test.readIntToken();

		try {
			test.readIntToken2();
		} catch (InvalidInputException e) {
			System.out.println("Hiba!");
			System.out.println(e.getMessage());
			System.out.println(e.token);

		}
	}
}
