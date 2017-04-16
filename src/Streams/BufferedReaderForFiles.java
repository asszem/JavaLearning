package Streams;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 *
 * @author Andras Olah (olahandras78@gmail.com)
 */
public class BufferedReaderForFiles {

	public static void readFileFromBufferedReader() {
		Path filename = Paths.get("J:\\Exercises\\Ch11\\Ex6\\Name And Address - Main.txt");
		try {
			BufferedReader bufferedReader = Files.newBufferedReader(filename, Charset.forName("UTF-16"));
			String currentLine;
			while ((currentLine = bufferedReader.readLine()) != null) {
				System.out.println(currentLine);
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	public static void main(String[] args) {
		readFileFromBufferedReader();
	}

}
