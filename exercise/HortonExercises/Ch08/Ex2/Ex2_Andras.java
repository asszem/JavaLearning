/*Create a class defining an object that parses each line of input from the keyboard 
that contains items separated by an arbitrary delimiter (for example, a colon, or a comma, or a forward slash, and so on)
and return the items as an array of type String[]. 

For example, the input might be: 1/one/2/two 

The output would be returned as an array of type String[] containing “1”, “one”, “2”, “two”k
 */
package HortonExercises.Ch08.Ex2;

import java.io.StreamTokenizer;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 *
 * @author Andras Olah (olahandras78@gmail.com)
 */
public class Ex2_Andras {

//	public static String charSet = "ISO8859_1";
	private StreamTokenizer tokenKontener = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in, StandardCharsets.ISO_8859_1)));
	private char delimiter = ',';
	private String[] resultString;

	public void setDelimiter(char delimiter) {
		this.delimiter = delimiter;
	}

	public void askDelimiter() {
		System.out.println("Mi legyen az elválasztójel? Jelenleg: " + Character.toString(delimiter));
		InputStreamReader newDelimiter = new InputStreamReader(System.in, StandardCharsets.ISO_8859_1);
		try {
			int beolvasva = newDelimiter.read();
			System.out.printf("Az új elválasztó karakter: %s%n",Character.toString((char) beolvasva));
			setDelimiter((char) beolvasva);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String[] readTokens() {
		StringBuilder str = new StringBuilder(); //nem része a feladtnak, csak gyakorlás
		try {
			tokenKontener.resetSyntax();
			tokenKontener.wordChars('\u0000', '\uffff'); //minden karakter String legyen
			tokenKontener.whitespaceChars(delimiter, delimiter);
			tokenKontener.whitespaceChars('\n', '\n'); 	//New line act as a delimiter

			tokenKontener.eolIsSignificant(true);         // End-of-line to be reported as TT_EOL
			int beolvasva;
			int tokenNumber = 0;
			while ((beolvasva = tokenKontener.nextToken()) != StreamTokenizer.TT_EOL) {
//				System.out.printf("Típus: %d érték: %s%n", beolvasva, tokenKontener.sval);
				str.append(tokenKontener.sval.trim() + Character.toString(delimiter));
				tokenNumber++;
			}
			resultString = new String[tokenNumber];
			StreamTokenizer token2 = new StreamTokenizer(new StringReader(str.toString()));
			token2.resetSyntax();
			token2.wordChars('\u0000', '\uffff');
			token2.whitespaceChars(delimiter, delimiter);
			for (int i = 0; i < resultString.length; i++) {
				token2.nextToken();
				resultString[i] = token2.sval;
			}
			String vegeredmeny = str.toString();
			System.out.printf("Végredmény: %s", vegeredmeny);
			System.out.println("\nEnd of method readTokens() method.");
			return resultString;
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		String[] noresult = new String[1];
		noresult[0] = "No result";
		return noresult;
	}

	public static void main(String[] args) throws IOException {
		Ex2_Andras teszt = new Ex2_Andras();
		teszt.askDelimiter();
		String[] eredmeny = teszt.readTokens();
		for (String s : eredmeny) {
			System.out.printf("%s%n", s);
		}
	}
}
