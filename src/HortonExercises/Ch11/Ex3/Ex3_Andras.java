/*
Requirement:
Extend the ReadPrimes program further to output a given number of primes, starting at a given number.
For example, output 15 primes starting at the 30th. The existing capabilities should be retained.
Horton, p447, Chapter 11, reading files

Note: PrimesToFile3 is the program that writes the file that is to be used for reading
File: J:\Writing Files\primesMixed.bin
 */
package HortonExercises.Ch11.Ex3;

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
public class Ex3_Andras {

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

	public long displayPrime(int requestedNumber) {
		if (requestedNumber > readedLongsArrayList.size()) {
			String errorMsg = "Input number is greater than max size of " + readedLongsArrayList.size();
			throw new IllegalArgumentException(errorMsg);
		}
		long result = (long) readedLongsArrayList.get(requestedNumber);
		return result;
	}

	public void displayPrimeRange(int requestedStart, int displayRange) {
		int resultSize = readedLongsArrayList.size();
		int endRange = requestedStart + displayRange - 1;
		if (requestedStart > resultSize || endRange > resultSize) {
			throw new IllegalArgumentException("Request is out of bounds");
		}
		for (int actualIndex = requestedStart; actualIndex <=endRange; actualIndex++) {
			System.out.printf("[%d] %s[%d]%n", actualIndex, readedStringsArrayList.get(actualIndex), readedLongsArrayList.get(actualIndex));
		}
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
			ex.printStackTrace();
		}
		return result;
	}

	public void getRangedResult() {
		readPrimes();
		System.out.println("Enter start index:");
		int start = getKeyboardInput();
		System.out.println("Enter range:");
		int range = getKeyboardInput();
		displayPrimeRange(start, range);

	}

	public static void main(String[] args) {
		Ex3_Andras testObject = new Ex3_Andras();
		testObject.getRangedResult();
		/*
		testObject.readPrimes();
		int requestedPrime = getKeyboardInput();
		long result = testObject.displayPrime(requestedPrime);
		System.out.printf("The [%d] prime is: %d", requestedPrime, result);
		 */

	}
}
