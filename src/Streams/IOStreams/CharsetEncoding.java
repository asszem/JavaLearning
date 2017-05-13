//To test the effect of various Charset_Samples and Encoding in IO Streams
//ISO8859_1
//UTF-8
//Windows-1250

//For correct System.in, use ISO8859_1 encoding with any of the following methods:
/*
charSet = "ISO8859_1";
InputStreamReader inputStream1 = new InputStreamReader(System.in, charSet);

InputStreamReader inputStream2 = new InputStreamReader(System.in, Charset.forName(charSet));

InputStreamReader inputStream3 = new InputStreamReader(System.in, StandardCharsets.UTF_8);

CharsetDecoder decoder = Charset.forName("UTF-8").newDecoder();
CharsetEncoder encoder = Charset.forName("UTF-8").newEncoder();
InputStreamReader inputStream4 = new InputStreamReader(System.in, decoder);
*/
package Streams.IOStreams;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.StandardCharsets; //fontos, hogy ez és NE a sun-os legyen import

/**
 *
 * @author Andras Olah (olahandras78@gmail.com)
 */
public class CharsetEncoding {

	public static String charSet;

	public static void setCharSet(String inputKarset) {
		charSet = inputKarset;
	}

	public static void main(String[] args) throws IOException {
		String userDir = System.getProperty("user.dir");
		String inputFile = userDir + "\\Assets\\sampletext.txt";
		String outputFile = userDir + "\\Assets\\sampleoutput.txt";
		int beolvasas;
		int testType = 4;
//Test 1 - InputStreamReader with String 
//Test 2 - InputStreamReader with Charset.forName
//Test 3 - Inputstreamreader with StandardCharset
		switch (testType) {
			case 1:
//Test 1 - InputStreamReader with String 
				setCharSet("ISO8859_1");
				System.out.println("InputStreamREader(System.in, charSet)");
				InputStreamReader inputStream1 = new InputStreamReader(System.in, charSet);
				System.out.print("Teszt 1/a. Charset: " + charSet);
				beolvasas = inputStream1.read();
				System.out.println("inputStream1.read() = " + beolvasas);

				//NE adjunk meg a System.out-nak encodingot, különben rossz lesz!
//				OutputStreamWriter outputStream1 = new OutputStreamWriter(System.out);
//				outputStream1.write("Teszt 1/a: "+(char) beolvasas);
//				outputStream1.flush();
				//File outputnál fontos az UTF-8 encoding, különben Notepad++ rossz lesz.
				OutputStreamWriter outputStream1 = new OutputStreamWriter(new FileOutputStream(outputFile), "UTF-8");
				System.out.println("output stream encoding: " + outputStream1.getEncoding());
				outputStream1.write("Teszt 1/a: " + (char) beolvasas);
				outputStream1.flush();

				setCharSet("UTF-8");
				System.out.print("\nTeszt 1/b. Charset: " + charSet);
				inputStream1 = new InputStreamReader(System.in, charSet);
				beolvasas = inputStream1.read();
				System.out.println("inputStream1.read() = " + beolvasas);
				outputStream1.write("Teszt 1/b: " + (char) beolvasas);
				outputStream1.flush();

				setCharSet("Windows-1250");
				System.out.print("\nTeszt 1/c. Charset: " + charSet);
				inputStream1 = new InputStreamReader(System.in, charSet);
				beolvasas = inputStream1.read();
				System.out.println("inputStream1.read() = " + beolvasas);
				outputStream1.write("Teszt 1/c: " + (char) beolvasas);
				outputStream1.flush();

				inputStream1.close();
				outputStream1.close();
				break;
			case 2:
//Test 2 - InputStreamReader with Charset.forName
				System.out.println("InputStreamREader(System.in, Charset.forName(charSet)");
				setCharSet("ISO8859_1");
				System.out.print("Teszt 2/a. Input Charset: " + charSet);
				InputStreamReader inputStream2 = new InputStreamReader(System.in, Charset.forName(charSet));
				//Must use UTF-8 if the output file is UTF-8
				//Argument can be "UTF-8" or "UTF8"
				OutputStreamWriter outputStream2 = new OutputStreamWriter(new FileOutputStream(outputFile), "UTF-8");
				beolvasas = inputStream2.read();
				System.out.println("inputStream2.read() = " + beolvasas);
				System.out.println("char cast: " + (char) beolvasas);
				System.out.println("output stream encoding: " + outputStream2.getEncoding());
				outputStream2.write("Teszt 2/a: " + (char) beolvasas);
				outputStream2.flush();

				setCharSet("UTF-8");
				System.out.print("Teszt 2/b. Input Charset: " + charSet);
				inputStream2 = new InputStreamReader(System.in, Charset.forName(charSet));
//				outputStream2 = new OutputStreamWriter(new FileOutputStream(outputFile));
				beolvasas = inputStream2.read();
				System.out.println("inputStream2.read() = " + beolvasas);
				System.out.println("char cast: " + (char) beolvasas);
				System.out.println("output stream encoding: " + outputStream2.getEncoding());
				outputStream2.write("Teszt 2/b: " + (char) beolvasas);
				outputStream2.flush();

				setCharSet("Windows-1250");
				System.out.print("Teszt 2/c. Input Charset: " + charSet);
				inputStream2 = new InputStreamReader(System.in, Charset.forName(charSet));
//				outputStream2 = new OutputStreamWriter(new FileOutputStream(outputFile));
				beolvasas = inputStream2.read();
				System.out.println("inputStream2.read() = " + beolvasas);
				System.out.println("char cast: " + (char) beolvasas);
				System.out.println("output stream encoding: " + outputStream2.getEncoding());
				outputStream2.write("Teszt 2/c: " + (char) beolvasas);
				outputStream2.flush();

				inputStream2.close();
				outputStream2.close();
				break;
			case 3:
//Test 3 - Inputstreamreader with StandardCharset.UTF_8
				System.out.println("InputStreamReader(System.in, StandardCharset.UTF/ISO=WIN");
				InputStreamReader inputStream3 = new InputStreamReader(System.in, StandardCharsets.UTF_8);
				OutputStreamWriter outputStream3 = new OutputStreamWriter(new FileOutputStream(outputFile), "UTF-8");
				System.out.print("Teszt 3/a. Input Charset: StandardCharset.UTF_8");
				beolvasas = inputStream3.read();
				System.out.println("inputStream3.read() = " + beolvasas);
				System.out.println("char cast: " + (char) beolvasas);
				System.out.println("output stream encoding: " + outputStream3.getEncoding());
				outputStream3.write("Teszt 3/a: StandardCharset.UTF_8:"
									+ (char) beolvasas
									+ "\n");
				outputStream3.flush();

				System.out.print("Teszt 3/b. Input Charset: StandardCharset.ISO_8859_1");
				inputStream3 = new InputStreamReader(System.in, StandardCharsets.ISO_8859_1);
				beolvasas = inputStream3.read();
				System.out.println("inputStream3.read() = " + beolvasas);
				System.out.println("char cast: " + (char) beolvasas);
				System.out.println("output stream encoding: " + outputStream3.getEncoding());
				outputStream3.write("Teszt 3/b: StandardCharset.ISO_8859_1:"
									+ (char) beolvasas
									+ "\n");
				outputStream3.flush();

				System.out.print("Teszt 3/c. Input Charset: StandardCharset.US_ASCII");
				inputStream3 = new InputStreamReader(System.in, StandardCharsets.US_ASCII);
				beolvasas = inputStream3.read();
				System.out.println("inputStream3.read() = " + beolvasas);
				System.out.println("char cast: " + (char) beolvasas);
				System.out.println("output stream encoding: " + outputStream3.getEncoding());
				outputStream3.write("Teszt 3/c: StandardCharset.US_ASCII:"
									+ (char) beolvasas
									+ "\n");
				outputStream3.flush();
				//Closing streams
				inputStream3.close();
				outputStream3.close();
				break;
			case 4:
//Test 4 - Inputstreamreader with Decoder/Encoder
				System.out.println("InputStreamReader(System.in, decoder)");
				CharsetDecoder decoder = Charset.forName("UTF-8").newDecoder();
				CharsetEncoder encoder = Charset.forName("UTF-8").newEncoder();
				InputStreamReader inputStream4 = new InputStreamReader(System.in, decoder);
				OutputStreamWriter outputStream4 = new OutputStreamWriter(new FileOutputStream(outputFile), "UTF-8");
				System.out.print("Teszt 4/a. Input Charset decoder: "+decoder.charset());
				inputStream4 = new InputStreamReader(System.in, StandardCharsets.US_ASCII);
				beolvasas = inputStream4.read();
				System.out.println("inputStream4.read() = " + beolvasas);
				System.out.println("char cast: " + (char) beolvasas);
				System.out.println("output stream encoding: " + outputStream4.getEncoding());
				outputStream4.write("Teszt 4/a: UTF-8 decoder:"
									+ (char) beolvasas
									+ "\n");
				outputStream4.flush();

				decoder = Charset.forName("ISO8859_1").newDecoder();
				System.out.print("\nTeszt 4/b. Input Charset decoder: "+decoder.charset());
				inputStream4 = new InputStreamReader(System.in, decoder);
				beolvasas = inputStream4.read();
				System.out.println("inputStream3.read() = " + beolvasas);
				System.out.println("char cast: " + (char) beolvasas);
				System.out.println("output stream encoding: " + outputStream4.getEncoding());
				outputStream4.write("Teszt 4/b: ISO8859_1 decoder:"
									+ (char) beolvasas
									+ "\n");
				outputStream4.flush();

				decoder = Charset.forName("Windows-1250").newDecoder();
				System.out.print("\nTeszt 4/c. Input Charset decoder: "+decoder.charset());
				inputStream4 = new InputStreamReader(System.in, StandardCharsets.US_ASCII);
				beolvasas = inputStream4.read();
				System.out.println("inputStream4.read() = " + beolvasas);
				System.out.println("char cast: " + (char) beolvasas);
				System.out.println("output stream encoding: " + outputStream4.getEncoding());
				outputStream4.write("Teszt 4/c: Windows-1250 decoder:"
									+ (char) beolvasas
									+ "\n");
				outputStream4.flush();

				//Closing streams
				inputStream4.close();
				outputStream4.close();
				break;
		}

	}
}
