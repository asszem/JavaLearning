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
		int testCase = 2;
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

		}
		/*		
//<editor-fold desc="Prepare data">
		ByteBuffer bb = ByteBuffer.allocate(1024);
		System.out.println("Byte Buffer bb created");
//		System.out.println("bb:\t\t" + bufTrace(bb));
		Charset charset = Charset.defaultCharset();
		String[] str = {"AA aa", "Árvíztűrő tükörfúrógép", "Raxacoricofallapatorius", "123"};
		String separator = System.lineSeparator();
		String concatenatedOutputString;
		byte[] byteArray;
		Path p = Paths.get("E:\\javaTest", "channelsTest.txt");
//</editor-fold>
//<editor-fold desc="Concatenate strings and ints to a byte[] array and PUT it to bb">
		concatenatedOutputString = str[0].length() + str[0] + separator + str[1].length() + str[1];
		byteArray = new byte[concatenatedOutputString.length()];
		byteArray = concatenatedOutputString.getBytes(charset);
		System.out.println("---- Start of String to write:");
		System.out.println(concatenatedOutputString);
		System.out.println("---- end of String to write:");
		System.out.println("Byte array to write:");
		for (byte b : byteArray) {
			System.out.printf("[%s]", b);
		}
		System.out.println("\nBuffer after put");
		bb.put(byteArray);
//		System.out.println("bb:\t\t" + bufTrace(bb));

		//to make sure byteArray is wiped
		for (int i = 0; i < byteArray.length; i++) {
			byteArray[i] = 0;
		}
		bb.rewind();
		bb.get(byteArray);
		System.out.println("Byte Array values retrieved");
		for (byte b : byteArray) {
			System.out.printf("[%s]", b);
		}
//</editor-fold>
//<editor-fold desc="Create a Channel and write bb">
//Flip buffer
		System.out.println("");
		System.out.println("\nCreating a channel and writing bb to it");
		System.out.println("Filename: " + p.getFileName());
		System.out.println("Bytebuffer: bb");
		System.out.println("bb position BEFORE flip: " + bb.position());
		bb.flip();
		System.out.println("bb position AFTER flip: " + bb.position());
//Create channel using try-with-resources
		try (SeekableByteChannel fileOutChannel = Files.newByteChannel(p, EnumSet.of(WRITE, CREATE, TRUNCATE_EXISTING))) {
			System.out.println("\bBefore writing");
			System.out.println("File position:\t\t" + ((FileChannel) fileOutChannel).position());
			System.out.println("Buffer bb position\t" + bb.position());
			System.out.println("\nAfter writing completed");
			fileOutChannel.write(bb);
			System.out.println("File position:\t\t" + ((FileChannel) fileOutChannel).position());
			System.out.println("Buffer bb position\t" + bb.position());
			System.out.println("The file contains " + ((FileChannel) fileOutChannel).size() + " bytes.");
		} catch (IOException e) {
			e.printStackTrace();
		}
//</editor-fold>
//<editor-fold desc="Create a Gathering file channel to write from multiple buffers">
System.out.println("Testin Gathering write method");
		ByteBuffer[] buffers = new ByteBuffer[3]; //Create the ByteBuffer Arrays to be written
		bb.clear();
		buffers[0] = bb; //reference the original bb
		buffers[1] = ByteBuffer.allocate(2); //Allocate 2 bytes for a Char
		buffers[2] = ByteBuffer.allocate(8); //Allocate 8 bytes for a Double
//		System.out.println("buffers[0]:\t\t" + bufTrace(buffers[0]));
//		System.out.println("buffers[1]:\t\t" + bufTrace(buffers[1]));
//		System.out.println("buffers[2]:\t\t" + bufTrace(buffers[2]));
		buffers[0].put("Gathering test".getBytes());
		buffers[1].putChar('X');
		buffers[2].putDouble(1234);
		System.out.println("After putting data to buffers");
//		System.out.println("buffers[0]:\t\t" + bufTrace(buffers[0]));
//		System.out.println("buffers[1]:\t\t" + bufTrace(buffers[1]));
//		System.out.println("buffers[2]:\t\t" + bufTrace(buffers[2]));
		buffers[0].flip();
		buffers[1].flip();
		buffers[2].flip();
		System.out.println("After flipping buffers");
//		System.out.println("buffers[0]:\t\t" + bufTrace(buffers[0]));
//		System.out.println("buffers[1]:\t\t" + bufTrace(buffers[1]));
//		System.out.println("buffers[2]:\t\t" + bufTrace(buffers[2]));
		Path p2 = Paths.get("E:\\javaTest", "gatheringWrite.txt");
		//Files method opens a SeekableByteChannel, so it must to be casted to FileChannel
		try (FileChannel gatheringOutput = (FileChannel) Files.newByteChannel(p2,EnumSet.of(WRITE, CREATE, TRUNCATE_EXISTING))){
			gatheringOutput.force(true);
			gatheringOutput.write(buffers);
			System.out.println("Buffers written");
		} catch (IOException e) {
			e.printStackTrace();
		}
//</editor-fold>
		 */
	}//main
}//class
