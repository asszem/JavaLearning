package Streams.IOStreams;

import java.io.*;
import java.util.Scanner;

/**
 *
 * @author Andras Olah (olahandras78@gmail.com)
 */
public class FileScanner {

	public static void main(String[] args) throws IOException {
		String userDir = System.getProperty("user.dir");
		String inputFile = userDir + "\\Assets\\olvassbe.txt";
		String outputFile = userDir + "\\Assets\\irjalki.txt";
		Scanner szkenner = null;
		szkenner = new Scanner(new BufferedReader(new FileReader(inputFile)));
		try{
		while (szkenner.hasNext()){
			System.out.println(szkenner.next());
		}}finally{
			if (szkenner != null) szkenner.close();
		}
	}
}
