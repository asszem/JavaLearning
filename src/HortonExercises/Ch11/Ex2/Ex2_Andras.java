/*
Requirement:
Extend the ReadPrimes example that you produced in this chapter to optionally display the nth prime,
when n is entered from the keyboard.
Horton, p447, Chapter 11, reading files

Note: PrimesToFile3 is the program that writes the file that is to be used for reading
File: J:\Writing Files\primesMixed.bin
 */
package HortonExercises.Ch11.Ex2;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import static java.nio.file.StandardOpenOption.READ;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Andras Olah (olahandras78@gmail.com)
 */
public class Ex2_Andras {

	Path sourceFile = Paths.get("J:\\Writing Files\\primesMixed.bin");
	long sourceFileSize;
	String[] readedStrings;
	long[] readedLongs;
	ArrayList readedStringsArrayList = new ArrayList();
	ArrayList readedLongsArrayList = new ArrayList();
	static final int LONG_BYTES = 8;
	static final int CHAR_BYTES = 2;
	static final int DOUBLE_BYTES = 8;

	public void readPrimes() {
		if (!Files.exists(sourceFile)) {
			throw new IllegalStateException("File does not exists!");
		}
		int currentStringLength;
		String currentString;
		long currentLong;
		try (FileChannel fileChannel = (FileChannel) Files.newByteChannel(sourceFile, READ)) {
			ByteBuffer byteBuffer = ByteBuffer.allocate(100);
//			byteBuffer.position(byteBuffer.capacity()); //preparing for .compact
byteBuffer.flip();
			sourceFileSize = fileChannel.size();
			while (true) { //exit if EOF reached
				//Read to the bytebuffer
				if (fileChannel.read(byteBuffer.compact()) == -1) {
					break; //EOF reached, exit the loop
				}
				byteBuffer.flip();
				while (true) { //exit if not enough data in buffer
//check if there is next double
					if (byteBuffer.remaining() < DOUBLE_BYTES) {
						break; //skip the buffer reading, continue with next file read
					}
					currentStringLength = (int) byteBuffer.getDouble();
//check if there is enough for next string
					if (byteBuffer.remaining() < currentStringLength * CHAR_BYTES) {
						byteBuffer.position(byteBuffer.position() - DOUBLE_BYTES);
						break;
					}
					byte[] byteArrayForStrings = new byte[currentStringLength * CHAR_BYTES];
					byteBuffer.get(byteArrayForStrings);
					ByteBuffer byteBufferForByteArray = ByteBuffer.wrap(byteArrayForStrings);
					currentString = byteBufferForByteArray.asCharBuffer().toString();
//check if there is enough for next long
					if (byteBuffer.remaining() < LONG_BYTES) {
						byteBuffer.position(byteBuffer.position() - (DOUBLE_BYTES + (currentStringLength * CHAR_BYTES)));
						break;
					}
					currentLong = byteBuffer.getLong();
//generate the next string
				readedStringsArrayList.add(currentString);
				readedLongsArrayList.add(currentLong);
				}//end byteBuffer reading loop
			}//end file channel reading loop

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void displayPrime() {

	}

	public static int getKeyboardInput() {
		int result = 0;
		System.out.println("Enter a number:");
		StreamTokenizer tokenizer = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
		try {
			while ((tokenizer.nextToken()) != StreamTokenizer.TT_NUMBER) {
			}
			result = (int) tokenizer.nval;
		} catch (IOException ex) {
			Logger.getLogger(Ex2_Andras.class.getName()).log(Level.SEVERE, null, ex);
		}
		return result;
	}

	public static void main(String[] args) {
		Ex2_Andras testObject = new Ex2_Andras();
		testObject.readPrimes();

//		int input = getKeyboardInput();
//		System.out.println("The input was:" + input);
	}
}
