package Streams;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.StreamTokenizer;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

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

	//ÁRVÍZTŰRŐ compliant
	public static String getStringUsingBufferedReader() {
		String charSet = "ISO8859_1";
		String returnString = null;
		try {
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in, charSet));
			returnString = bufferedReader.readLine();
		} catch (UnsupportedEncodingException ex) {
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return returnString;
	}

	/**
	 * Reads a line from the provided InputStream and displays to the provided outputtream
*	
* The input and output streams need to be opened before calling the method
* The outputstream parameter need to be manually updated if System.out or OutputStreamWriter is to be used
	 *
	 * @param inputStream System.in for console input, ByteArrayInputStream for Junit mock input
	 * @param outputStream System.out for console output, OutputStreamWriter for file output
	 * @return a String that was provided to the inputStream
	 */
	public static String getStringUsingScannerWithInputOutputStreamParameter(InputStream inputStream, String outputStreamChoice){
		System.out.printf("Charset:%s, output stream:%s",charset, outputStreamChoice
		System.out.println("Charset for input: " + charset);
		System.out.print("Enter something:");
		Scanner sc = new Scanner(inputStream, charset);
		String result = sc.nextLine();
		switch (outputStreamChoice){
			case "console":
				break;
		}
		try {
			//cast the outputStream to a PrintStream so that it can be displayed to the console
//		PrintStream printStream = (PrintStream) outputStream;
//		printStream.println("The input was:" + result); //use the provided output instead of System.out
			outputStream.write(result);
			outputStream.flush();

		} catch (IOException ex) {
			ex.printStackTrace();
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
//		get_INT_OnlyUsingScanner();
//		System.out.println(getStringUsingBufferedReader());
//		getStringUsingScannerWithInputOutputStreamParameter(System.in, System.out);
		ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream("Árvíztűrő tükörfúrógép".getBytes(charset));
		String outputFile = "J:\\outputstreamwriter.txt";
		OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(outputFile), charset);
		getStringUsingScannerWithInputOutputStreamParameter(byteArrayInputStream, osw);
	}
}
