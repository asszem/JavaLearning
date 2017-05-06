package Streams;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.StreamTokenizer;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;

public class KeyboardInputScannerAndBufferedReader {

	/*
	Supported Encodng - Charset codes:
	https://docs.oracle.com/javase/8/docs/technotes/guides/intl/encoding.doc.html
	 */
	public static String charset = "Windows-1250";

	//ÁRVÍZTŰRŐ compliant
	public static String getStringInputUsingScanner() {
		//Charset encodings
		//ISO8869_1
		//Windows-1250
		//UTF-8
		//UTF-16
		Scanner scanner = new Scanner(System.in, "ISO8859_1");
		return scanner.nextLine();
	}

	//ÁRVÍZTŰRŐ compliant with trim()
	public static String getStringUsingBufferedReader() {
		String charSet = "ISO8859_1";
		String returnString = null;
		try {
			BufferedReader keyboardInput = new BufferedReader(new InputStreamReader(System.in, charSet));
			returnString = keyboardInput.readLine().trim();
		} catch (UnsupportedEncodingException ex) {
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return returnString;
	}

	/**
	 * Reads a line with Scanner from provided InputStream and writes result to a file or displays to the console
	 * <br>
	 * <br>
	 * The inputStream must be opened before calling the method.
	 * <br> {@code ByteArrayInputStream bais = new ByteArrayInputStream("String".getBytes(charset)) }
	 * <br>
	 * The output stream will be opened by the method, unless the second parameter was "<i>console</i>"
	 *
	 * @param inputStream {@code System.in} for keyboard input from console, {@code ByteArrayInputStream} for code input
	 * @param outputStreamFile Filename to write result, or "<i>console</i>" to write result to console
	 * @return a String that was provided to the inputStream
	 */
	public static String getStringUsingScannerWithInputOutputStreamParameter(InputStream inputStream, String outputStreamFile) {
		System.out.printf("Charset: %s%ninputstream: %s%noutput stream: %s%n", charset, inputStream, outputStreamFile);
		System.out.print("Enter something:");
		Scanner sc = new Scanner(inputStream, charset);
		String result = sc.nextLine();
		switch (outputStreamFile) {
			case "console":
				System.out.println("The input was: " + result);
				break;
			default:  //if "console" was not the parameter
				try (OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(outputStreamFile), charset)) {
					osw.write(result);
					osw.flush();
				} catch (IOException ex) {
					ex.printStackTrace();
				}
				break;
		}
		return result;
	}

	public static int getNumberUsingStreamTokenizer() {
		int result = 0;
		StreamTokenizer tokenizer = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
		try {
			while ((tokenizer.nextToken()) != StreamTokenizer.TT_NUMBER) {
			}
			result = (int) tokenizer.nval;
		} catch (IOException ex) {
		}
		return result;
	}

	public static int get_INT_OnlyUsingScanner() {
		Scanner scanner;
		while (true) {
			scanner = new Scanner(System.in);
			if (!scanner.hasNextInt()) {
				System.out.println("Error! Enter a number");
			} else {
				break;
			}
		}
		int userChoice = scanner.nextInt();
		System.out.println(userChoice);
		return userChoice;
	}

	public static char getSingleCharUsingScanner() {
		Scanner input = new Scanner(System.in);
		String inputString = input.next();

		//Egy karakter bevitele:
		char inputCharacter = inputString.charAt(0);
		return inputCharacter;
	}

	public static void main(String[] args) throws UnsupportedEncodingException, FileNotFoundException {
		String trimTest = getStringUsingBufferedReader();
		System.out.println(trimTest);

//		get_INT_OnlyUsingScanner();
//		System.out.println(getStringUsingBufferedReader());
//		getStringUsingScannerWithInputOutputStreamParameter(System.in, System.out);
//		ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream("Árvíztűrő tükörfúrógép".getBytes(charset));
//		String outputFile = "J:\\outputstreamwriter.txt";
//		getStringUsingScannerWithInputOutputStreamParameter(byteArrayInputStream, "console");
//		 byteArrayInputStream = new ByteArrayInputStream("Árvíztűrő tükörfúrógép 2".getBytes(charset));
//		getStringUsingScannerWithInputOutputStreamParameter(byteArrayInputStream, outputFile);
	}
}
