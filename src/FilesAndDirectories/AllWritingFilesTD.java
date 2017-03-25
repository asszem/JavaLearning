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
		//<editor-fold desc="Test case: open a file to write with newOutputStream()">
		//call the method that opens a file and write to it 
		if (false) {
			AllWritingFiles.writingWithOutputStream(testPath.resolve("outputStreamWriteTest.txt"), 12345);
			AllWritingFiles.writingWithOutputStream(testPath.resolve("outputStreamWriteTest.txt"), 12345);
			AllWritingFiles.writingWithOutputStreamWithOptions(testPath.resolve("outputStreamAppend.txt"), 12345);
			AllWritingFiles.writingWithOutputStreamWithOptions(testPath.resolve("outputStreamAppend.txt"), 12345);
			AllWritingFiles.writingWithOutputStreamWithOptions(testPath.resolve("outputStreamAppend.txt"), 12345);
		}
		//</editor-fold>
		//<editor-fold desc="">
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
	}

	public static void waitForEnter() {
		try {
			System.out.println("Press Enter");
			System.in.read();
		} catch (IOException e) {
			System.err.println(e);
		}
	}
}
