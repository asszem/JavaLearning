/*
Don’t confuse the file position with the position for a buffer  the two are quite independent.
When writing, the byte at the buffer’s current position is written to the file at the file’s current position.

The file channel object keeps track of the current position in the file 
The file position is generally incremented by the number of bytes written each time you write to the file 

Bytes from the buﬀer are written starting at the buﬀer’s current position, and buf.remaining() bytes are written.

remaining() = limit - position         AND NOT capacity-position!

writing also updates the buffer position

Force to write a channel:
channelReference.force(true);

Sources:
https://www.youtube.com/watch?v=2LJ6YSwAH6Y
 */
package FilesAndDirectories;

import static FilesAndDirectories.BuffersTD.bufTrace;
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
public class Channels {

	public static void main(String[] args) {
//<editor-fold desc="Prepare data">
		ByteBuffer bb = ByteBuffer.allocate(1024);
		System.out.println("Byte Buffer bb created");
		System.out.println("bb:\t\t" + bufTrace(bb));
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
		System.out.println("bb:\t\t" + bufTrace(bb));

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
		System.out.println("buffers[0]:\t\t" + bufTrace(buffers[0]));
		System.out.println("buffers[1]:\t\t" + bufTrace(buffers[1]));
		System.out.println("buffers[2]:\t\t" + bufTrace(buffers[2]));
		buffers[0].put("Gathering test".getBytes());
		buffers[1].putChar('X');
		buffers[2].putDouble(1234);
		System.out.println("After putting data to buffers");
		System.out.println("buffers[0]:\t\t" + bufTrace(buffers[0]));
		System.out.println("buffers[1]:\t\t" + bufTrace(buffers[1]));
		System.out.println("buffers[2]:\t\t" + bufTrace(buffers[2]));
		buffers[0].flip();
		buffers[1].flip();
		buffers[2].flip();
		System.out.println("After flipping buffers");
		System.out.println("buffers[0]:\t\t" + bufTrace(buffers[0]));
		System.out.println("buffers[1]:\t\t" + bufTrace(buffers[1]));
		System.out.println("buffers[2]:\t\t" + bufTrace(buffers[2]));
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
	}
}
