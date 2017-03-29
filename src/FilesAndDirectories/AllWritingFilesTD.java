package FilesAndDirectories;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Test methods defined in AllWritingFiles class
 *
 * @author Andras Olah (olahandras78@gmail.com)
 */
public class AllWritingFilesTD {

	public static void main(String[] args) {
		Path testPath = Paths.get("E:\\javaFileOpTest");
		//<editor-fold desc="newOutputStream test">
		//call the method that opens a file and write to it 
		if (false) {
			AllWritingFiles.writingWithOutputStream(testPath.resolve("outputStreamWriteTest.txt"), 12345);
			AllWritingFiles.writingWithOutputStream(testPath.resolve("outputStreamWriteTest.txt"), 12345);
			AllWritingFiles.writingWithOutputStreamWithOptions(testPath.resolve("outputStreamAppend.txt"), 12345);
			AllWritingFiles.writingWithOutputStreamWithOptions(testPath.resolve("outputStreamAppend.txt"), 12345);
			AllWritingFiles.writingWithOutputStreamWithOptions(testPath.resolve("outputStreamAppend.txt"), 12345);
		}
		//</editor-fold>
		//<editor-fold desc="BufferedWriter tests">
		if (false) {
			System.out.println("Test case: write with Buffered Writer");
			String testString = "Árvíztűrő tükörfúrógép";
			Path filename = testPath.resolve("BufferedWriterTest.txt");
			AllWritingFiles.writingWithBufferedWriter(filename, testString, "default");
			AllWritingFiles.writingWithBufferedWriter(filename, testString, "default");
			AllWritingFiles.writingWithBufferedWriter(filename, testString, "default");
			waitForEnter("Check the file, is the content 3 times or 1 time written? Should be 1");
			AllWritingFiles.writingWithBufferedWriter(filename, testString, "APPEND");
			AllWritingFiles.writingWithBufferedWriter(filename, testString, "APPEND");
			AllWritingFiles.writingWithBufferedWriter(filename, testString, "append");
			waitForEnter("Check the file, is the content 3 times or 1 time written? Should be 3");
		}
		//</editor-fold>
		//<editor-fold desc="Testing of ++index%2 and 1%2">
		if (false) {
			int counter = 0;
			int divtest = 1 % 2;
			System.out.println("1%2=" + divtest);
			for (int i = 0; i < 10; i++) {
				System.out.printf("%d. counter:%d%n", i, counter);
				counter = ++counter % 2;
			}
		}
		//</editor-fold>

		//<editor-fold desc="">
		//</editor-fold>
	}//main

	public static void waitForEnter(String input) {
		try {
			System.out.println(input);
			System.in.read();
		} catch (IOException e) {
			System.err.println(e);
		}
	}
}
