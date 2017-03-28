package FilesAndDirectories;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.channels.FileChannel;
import java.nio.channels.SeekableByteChannel;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.EnumSet;
import static java.nio.file.StandardOpenOption.*;

/**
 *
 * @author Andras Olah (olahandras78@gmail.com)
 */
public class ChannelsTD {

	public static void main(String[] args) {
		int testCase = 5;
		System.out.println("Test Case " + testCase);
		Path path = Paths.get("E:\\javaFileOpTest\\Channels");
		String testString = "Árvíztűrő tükörfúrógép";
		System.out.println("Default charset: " + Charset.defaultCharset());
		int testInt = 12345;
		double testDouble = 12345.6789;
		switch (testCase) {
			case 1:
				//<editor-fold desc="Writing byte buffer created with .put to WritableByteChannel">
				Path pathTc1 = path.resolve("TC1\\testfile.txt");
//				System.out.println(pathTc2.getParent());
//				System.out.println("Path: " + pathTc2);
				ByteBuffer bbufTc1 = ByteBuffer.allocate(1024);
				bbufTc1.put(testString.getBytes(Charset.defaultCharset()));
				Channels.writeToWritableByteChannel(bbufTc1, pathTc1);
				System.out.println("File written. Please verify");
				//</editor-fold>
				break;
			case 2:
				//<editor-fold desc="Writing byte buffer created with .wrap to WritableByteChannel">
				Path pathTc2 = path.resolve("TC2\\testfile.txt");
				ByteBuffer bbufTc2 = ByteBuffer.wrap(testString.getBytes(Charset.defaultCharset()));
				Buffers.bufferStatus(bbufTc2, "Before write");
				System.out.println("Manaully set position for the flip in the method...");
				bbufTc2.position(bbufTc2.limit());
				Buffers.bufferStatus(bbufTc2, "New position");
				Channels.writeToWritableByteChannel(bbufTc2, pathTc2);
				System.out.println("File written. Please verify");
				System.out.println("The result file should be readable");
				//</editor-fold>
				break;
			case 3:
				//<editor-fold desc="Writing byte buffer created with .putChar to WritableByteChannel">
				Path pathTc3 = path.resolve("TC3\\testfile.txt");
				ByteBuffer bbufTc3 = ByteBuffer.allocate(1024);
//				bbufTc3.put(testString.getBytes(Charset.defaultCharset()));
				for (char c : testString.toCharArray()) {
					System.out.println(c);
					bbufTc3.putChar(c);
				}
				Buffers.bufferStatus(bbufTc3, "Before writing");
				Channels.writeToWritableByteChannel(bbufTc3, pathTc3);
				System.out.println("File written. Please verify");
				System.out.println("The result file should be readable");
				//</editor-fold>
				break;
			case 4:
				//<editor-fold desc="Write to a specific position in a file using SeekableFileChannel">
				System.out.println("Writes to a specific position provded as an argument of the method");
				Path pathTc4 = path.resolve("TC4\\testfile.txt");
				ByteBuffer bbufTc4 = ByteBuffer.allocate(1024);
				bbufTc4.put("Ez egy másik teszt".getBytes(Charset.defaultCharset()));
				Channels.writeToSeekableByteChannel(bbufTc4, 0, pathTc4);
				Channels.writeToSeekableByteChannel(bbufTc4, 20, pathTc4);
				Channels.writeToSeekableByteChannel(bbufTc4, 30, pathTc4);
				Channels.writeToSeekableByteChannel(bbufTc4, 40, pathTc4);
				System.out.println("Check " + pathTc4);
				//</editor-fold>
				break;
			case 5:
				//<editor-fold desc="Write to a GatheringFileChannel from multiple buffers">
				System.out.println("Writes to a file from multiple arrays");
				Path pathTc5 = path.resolve("TC5\\testfile.txt");
				ByteBuffer[] bbTc5array = new ByteBuffer[5];
				bbTc5array[0] = ByteBuffer.wrap("ByteBuffer test0\n".getBytes(Charset.defaultCharset()));
				bbTc5array[1] = ByteBuffer.wrap("ByteBuffer test1\n".getBytes(Charset.defaultCharset()));
				bbTc5array[2] = ByteBuffer.wrap("ByteBuffer test2\n".getBytes(Charset.defaultCharset()));
				bbTc5array[3] = ByteBuffer.wrap("ByteBuffer test3\n".getBytes(Charset.defaultCharset()));
				bbTc5array[4] = ByteBuffer.wrap("ByteBuffer test4\n".getBytes(Charset.defaultCharset()));
				Channels.writeToGatheringByteChannel(bbTc5array, pathTc5, false);
				Channels.writeToGatheringByteChannel(bbTc5array, pathTc5, false);
				Channels.writeToGatheringByteChannel(bbTc5array, pathTc5, false);
				Channels.writeToGatheringByteChannel(bbTc5array, pathTc5, false);
			//</editor-fold>
		}
	}//main
}//class
