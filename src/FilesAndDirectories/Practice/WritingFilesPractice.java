package FilesAndDirectories.Practice;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.nio.file.*;
import java.nio.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import static java.nio.file.StandardOpenOption.*; //to import all available options

/**
 *
 * @author Andras Olah (olahandras78@gmail.com)
 */
public class WritingFilesPractice {

	public static void main(String[] args) {
		Path p = Paths.get("E:\\javaTest\\writingFiles");
		try {
			Files.createDirectory(p);
		} catch (IOException e) {
			System.out.printf("%s mappa már van%n", p);
		}
		//This try will automatically close!
		//Try (valami; valami2; valami3;) {}
		//The stream classes implement the AutoCloseable interface so you can use a try block
		//with resources to automatically close any stream when you are done with it.
		try (BufferedWriter fileOutCharacters = Files.newBufferedWriter(p.resolve("newBufferedWriter.txt"), Charset.forName("UTF-16"), CREATE, java.nio.file.StandardOpenOption.WRITE, TRUNCATE_EXISTING);
				OutputStream fileOutByte = Files.newOutputStream(p.resolve("newOutputStream.bin"), CREATE, APPEND)) {

			//It works without any options specified
			InputStream fileOutByte2 = Files.newInputStream(p.resolve("newOutputStream.bin"), READ);
//			fileOutByte2.write(1423423423);
//			fileOutByte2.flush();
			int testCase = 2;
			switch (testCase) {
				case 1: //Testing newOutputStream method to write bytes
					BufferedOutputStream fileOutBuffered = new BufferedOutputStream(fileOutByte);
					fileOutBuffered.write(123);
					fileOutBuffered.flush();
					System.out.println("File written sucessfully.");
					break;
				case 2: //Testing newBufferedWriter to write characters
					int counter = (int) (Math.random() * 20);
					fileOutCharacters.write("Random number: " + Integer.toString(counter) + "\n");
					System.out.println(counter);
					String testString = "tökmindegy";
					for (int i = 1; i < counter; i++) {
						testString = testString.format("Line %d, text:%s%s%n", i, Integer.toString(i), Integer.toString(i));
						System.out.println(testString);
						fileOutCharacters.write(testString);
					}
					fileOutCharacters.flush();
					break;
				default:
					break;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
