package Streams;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Locale;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Scanner_Samples {

	public static Scanner getAFileScanner(Path file, Charset charset, Locale locale) throws IOException {
		Scanner fileScanner = new Scanner(file, charset.toString());
		fileScanner.useLocale(locale);
		return fileScanner;
	}

	public static void main(String[] args) {
		// Scanning a String
		String stringToScan = "This is a String | to be | used by | the Scanner.";
		String regex = "\\s\\|\\s"; // whitespace | whitespace
		Scanner stringScanned = new Scanner(stringToScan);
		Pattern delimiterPattern = Pattern.compile(regex);
		stringScanned.useDelimiter(delimiterPattern);
		while (stringScanned.hasNext()) {
			System.out.println(stringScanned.next());
		}
		stringScanned.close();
		// Scanning a String for a specific match
		stringScanned = new Scanner(stringToScan);
		stringScanned.useDelimiter(delimiterPattern);
		while (stringScanned.hasNext()) {
			if (stringScanned.hasNext("to be")) {
				System.out.println("Specific pattern match:" + stringScanned.next());
			} else
				stringScanned.next();
		}

		// Scanning a file with specific Locale and charset
		Path file = Paths.get("J:/Scanner/scanner.txt");
		try (Scanner fileScanner = new Scanner(file, "utf-8")) {
			fileScanner.useLocale(Locale.ENGLISH);
			while (fileScanner.hasNext()) {
				if (fileScanner.hasNextInt()) {
					// Process integer input...
					System.out.println("Integer! " + fileScanner.nextInt());
				} else if (fileScanner.hasNextDouble()) {
					// Process floating-point input...
					System.out.println("Double! " + fileScanner.nextDouble());
				} else if (fileScanner.hasNextBoolean()) {
					// Process boolean input...
					System.out.println("Boolean! " + fileScanner.nextBoolean());
				} else {
					System.out.println("String... " + fileScanner.next());
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	
		//Get a file scanner using the method
		try (Scanner anotherFileScanner=getAFileScanner(file, Charset.defaultCharset(), Locale.ENGLISH);
){
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
