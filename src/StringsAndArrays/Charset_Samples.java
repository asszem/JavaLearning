package StringsAndArrays;

import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;

/**
 *
 * @author Andras Olah (olahandras78@gmail.com)
 */
public class Charset_Samples {

	/*
	Supported Encodng - Charset codes:
	https://docs.oracle.com/javase/8/docs/technotes/guides/intl/encoding.doc.html
	 */
	public static void main(String[] args) throws IOException {
		System.out.println("Default Charset=" + Charset.defaultCharset());
		Charset windows1250 = Charset.forName("Windows-1250");
		System.out.println("Windows charset: " + windows1250);
		Charset iso8859 = Charset.forName("ISO8859_1");
		System.out.println("ISO charset: " + iso8859);
		Charset utf8 = Charset.forName("UTF-8");
		System.out.println("UTF-8: " + utf8);
		Charset utf16 = Charset.forName("UTF-16");
		System.out.println("UTF-16: " + utf16);

		CharsetDecoder decoder = Charset.forName("Windows-1250").newDecoder();  //To Read
		CharsetEncoder encoder = Charset.forName("UTF-8").newEncoder();  //To Write
		InputStreamReader inputStream = new InputStreamReader(System.in, decoder);
		System.out.println("Read value was: " + inputStream.read());
	}
}
