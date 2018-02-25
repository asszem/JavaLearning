package IOStreams.H_Ch8_Streams.AndrasSample;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.nio.charset.Charset;
import java.util.Locale;
import java.nio.charset.StandardCharsets;

/**
 *
 * @author Andras Olah (olahandras78@gmail.com)
 */
public class TryTokenizer {

	public static void main(String[] args) {
		int tokenType = 0;
		StreamTokenizer tokenizer = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
		int tokenCounter = 0;
		/*
		try {
			while ((tokenType = tokenizer.nextToken()) != StreamTokenizer.TT_EOF) {
				System.out.println("Token Counter: "+tokenCounter++);
				System.out.println("TokenType: " + tokenType);
				System.out.println("TokenType: " + tokenizer.ttype);
				System.out.println("Line number " + tokenizer.lineno());
				switch (tokenType) {
					case -3: //token type is a string
						System.out.println("String value " + tokenizer.sval);
						if (tokenizer.sval.equalsIgnoreCase("exit")) {
							System.exit(0);
						}
						break;
					case -2: //token type is a number
						System.out.println("Number value " + tokenizer.nval);
						break;
					default: //default value
						break;
				}
			}
		} catch (IOException e) {
			e.printStackTrace(System.err);
			System.exit(1);
		} catch (NullPointerException e) {
			System.out.println("bibi");
		}
		 */
		StreamTokenizer token2 = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in, StandardCharsets.ISO_8859_1)));
		try {
			boolean tovabb = false;
			System.out.println("Reset Syntax called");
			token2.resetSyntax();
			/*
			Resets the state of the tokenizer object so no characters have any special
			significance. This has the eﬀect that all characters are regarded as ordinary
			and are read from the stream as SINGLE characters so no tokens are identified.
			The value of each character is stored in the ttype field
			 */

			//Unicode Decimal values:
			//A-Z - 65-90
			//a-z - 97-122
			token2.whitespaceChars(10, 10); //treat EOL as delimiter
			token2.whitespaceChars(65, 65);//treat A as whitespace delimiter
			token2.wordChars(66, 122); //from B to z treat characters as words
			token2.wordChars(32, 32); //treat space 32 as word as well
			//Input: alma korte
			//output: Token[alma korte]
			//input: almaAkorte
			//output Token[alma] Token[korte]
			tovabb = true;
			while (tovabb) {
				token2.nextToken();
				System.out.println("Token toString " + token2.toString());
				System.out.println("Token type " + token2.ttype);
				System.out.println("Token value " + token2.sval);
			}

			tovabb = false;
			while (tovabb) {
				for (int i = 1; i < 5; i++) {
					if (i == 3) {
						System.out.println("Pushback called");
						token2.pushBack();
						/*
					pushback()
					Calling this method causes the next call of the nextToken() method to
					return the ttype value that was set by the previous nextToken() call 
					and to leave sval and nval unchanged
						 */
					}
					token2.nextToken();
					System.out.println("\nIteration: " + i);
					System.out.println("Token toString " + token2.toString());
					System.out.println("Token type " + token2.ttype);
					System.out.println("Token value " + token2.sval);
				}
			}


			tovabb = false;
			while (tovabb) {
				token2.ordinaryChar(10); //A space karaktert is hagyományos karakterként kezeli ezentúl
				token2.nextToken();
				System.out.println("Token Count: " + tokenCounter++);
				System.out.println("Token Type " + token2.ttype);
				System.out.println("Token toString" + token2.toString());
				switch (token2.ttype) {
					case StreamTokenizer.TT_WORD: //String 
						System.out.println("Token string: " + token2.sval);
						break;
					case StreamTokenizer.TT_NUMBER: //Number
						System.out.println("Token number: " + token2.nval);
						break;
					default:
						System.out.println("Token not number, not string " + token2.toString());
						break;
				}
				if (token2.ttype == -3 && token2.sval.equalsIgnoreCase("exit")) {
					tovabb = false;
				}
			}
		} catch (IOException e) {
			System.out.println("Hiba.");
			e.printStackTrace(System.err);
		} finally {
			System.out.println("Finally block");
		}
	}
}
