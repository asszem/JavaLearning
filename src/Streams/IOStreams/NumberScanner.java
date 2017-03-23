package Streams.IOStreams;

import java.io.*;
import java.util.Scanner;
import java.util.Locale;

/**
 *
 * @author Andras Olah (olahandras78@gmail.com)
 */
public class NumberScanner {

	public static void main(String[] args) throws IOException {
		String userDir = System.getProperty("user.dir");
		String inputFile = userDir + "\\Assets\\olvassbe.txt";
		String inputFile2 = userDir + "\\Assets\\usnumbers.txt";
		String outputFile = userDir + "\\Assets\\irjalki.txt";
		Scanner sc = null;
		double sum = 0;
		try {
		sc =new Scanner (new BufferedReader(new FileReader(inputFile2)));
		sc.useLocale(Locale.US);
		while (sc.hasNext()){
			if (sc.hasNextDouble()){
				sum+=sc.nextDouble();
			}else {
				sc.next();
			}
		}
		} finally {
			if (sc != null) {
				sc.close();
			}
		}
		System.out.println("A végösszeg: "+sum);
	}
}
