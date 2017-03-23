package StringsAndArrays;

import java.nio.charset.Charset;

/**
 *
 * @author Andras Olah (olahandras78@gmail.com)
 */
public class Charsets {

	public static void main(String[] args) {
		System.out.println("Default Charset=" + Charset.defaultCharset());
		Charset windows1250 = Charset.forName("Windows-1250");
		System.out.println("Windows charset: " + windows1250);
		Charset iso8859 = Charset.forName("ISO8859_1");
		System.out.println("ISO charset: " + iso8859);
	}
}
