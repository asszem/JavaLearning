package FilesAndDirectories;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.LongBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.SeekableByteChannel;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import static java.nio.file.StandardOpenOption.*;
import java.util.ArrayList;

/**
 *
 * @author Andras Olah (olahandras78@gmail.com)
 */
public class AllReadingFiles {

	//TODO update try blocks in every methods to try-with resources
	public static boolean checkFileExists(Path filename) {
		return (Files.exists(filename));
	}

	/**
	 * Reads all LONG data from BINARY file with InputStream
	 *
	 * Using a longViewBuffer and a byter[] array to back the byteBuffer
	 *
	 * @param filename
	 */
	public static void readLongWithInputStream(Path filename) {
		if (!checkFileExists(filename)) {
			System.out.println("File does not exists.");
		} else {
			int readCycleCounter = 0;
			ByteBuffer buff = ByteBuffer.allocate(8); //ONE long variable will be read in each cycle
			LongBuffer longViewBuffer = buff.asLongBuffer();
			byte[] bytesArray = buff.array();  //this array will back the bytebuffer
			try {
				BufferedInputStream bufferedInputStream = new BufferedInputStream(Files.newInputStream(filename));
				int bytesReadInt;
				while (true) {
					bytesReadInt = bufferedInputStream.read(bytesArray, 0, bytesArray.length); //read bytes to the ByteArray buffer
					readCycleCounter++;
					if (bytesReadInt == -1) {
						break; //stop the while loop when EOF reached
					}
					long longBufferGet = longViewBuffer.get();
					System.out.printf("ReadCylce:[%d] long: [%d]%n", readCycleCounter, longBufferGet);
					longViewBuffer.flip();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}//else
	} //method

	/**
	 * Read all BYTE values from a BINARY file with InputStream
	 *
	 * @param filename
	 * @return returns the read bytes in an ArrayList
	 */
	public static ArrayList readByteWithInputStream(Path filename) {
		ArrayList results = new ArrayList();
		//Both methods are correct to store the retrieved data
		//Method 1:
		ByteBuffer byteBuffer = ByteBuffer.allocate(1); //create a buffer to hold the read byte
		//Method 2:
		byte[] readBytesArray = new byte[1];  //Create a single byte array to hold the read byte
		int method = 1;
		if (!checkFileExists(filename)) {
			System.out.println("File does not exists.");
		} else {
			int readBytes = 0;
			try {
				BufferedInputStream bufferedInputStream = new BufferedInputStream(Files.newInputStream(filename));
				while (readBytes != -1) {
					switch (method) {
						case 1:
							readBytes = bufferedInputStream.read(readBytesArray);
							break;
						case 2:
							readBytes = bufferedInputStream.read(byteBuffer.array());
							break;
					}
					if (readBytes != -1) { //To prevent reading the last element twice
						switch (method) {
							case 1:
								results.add(readBytesArray[0]);
								break;
							case 2:
								results.add(byteBuffer.get());
								byteBuffer.flip();
								break;
						}
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}//end else
		return results;
	}//end method

	/**
	 * Read all INT values from a BINARY file with InputStream
	 *
	 * All data is read from the file, method does not stop until EOF (-1) is reached
	 *
	 * @param filename
	 * @return ArrayList with the values read
	 */
	public static ArrayList readIntWithInputStream(Path filename) {
		ArrayList intValueRead = new ArrayList();
		if (!checkFileExists(filename)) {
			System.out.println("File does not exists.");
		} else {
			int numberOfBytesRead = 0;
			ByteBuffer buf = ByteBuffer.allocate(4); //Integer takes 4 bytes
			IntBuffer ibuf = buf.asIntBuffer();
			try {
				BufferedInputStream bufferedInputStream = new BufferedInputStream(Files.newInputStream(filename));
				do {
					numberOfBytesRead = bufferedInputStream.read(buf.array()); //Returns the data to the byte array provided
					if (numberOfBytesRead != -1) { //IF EOF reached, do not add the buffer again!
						intValueRead.add(ibuf.get()); //Gets the data from the underlying byte array
						ibuf.flip(); //Prepares the buffer for the next read (position set to 0, limit to 1)
					}
				} while (numberOfBytesRead != -1);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}//end else
		return intValueRead;
	}

	/**
	 * Read all data from TEXT file with BufferedReader
	 *
	 * This method does not have a try-catch block, so the calling method must have it to catch possible IOException
	 *
	 * @param filename
	 * @param readMethod 1- reads a single char; 2-reads everyting to a char array; 3- reads all lines
	 * @return the result of reading operation as a String
	 * @throws IOException
	 */
	public static String readStringWithBufferedReader(Path filename, int readMethod) throws IOException {
		String result = "Nothing was read";
		BufferedReader bufferedReader = Files.newBufferedReader(filename, Charset.forName("UTF-16"));
		//Read methods
		switch (readMethod) {
			case 1: //Method 1 - read() single char
				int readResult = bufferedReader.read();
				result = Character.toString((char) readResult);
				break;
			case 2: //Method 2 - read(char[]...) - reads char array, including the newline as well
				char[] charArray = new char[100];
				int numberOfCharsRead = bufferedReader.read(charArray);
				result = "";
				for (int i = 0; i < numberOfCharsRead; i++) { //Don't need to add the full array, only the numbers read
					result += charArray[i];
				}
				break;
			case 3: //Method 3 - readLine() - reads EVERY line
				String currentLine;
				result = "";
				while ((currentLine = bufferedReader.readLine()) != null) {
					result += currentLine + "\n";
				}
				break;
		}
		return result;
	}

	/**
	 * Read all data from TEXT file with newByteChannel
	 *
	 * @param filename
	 * @param readMethod Different methods to read, but should produce the same results. method 1 - reads every character into a separate index to the arraylist method 2 - reads
	 * all content to one single index to the arraylist
	 * @return an ArrayList of the data read. Each item of the list is one line
	 */
	public static ArrayList readCharFromByteChannel(Path filename, int readMethod) {
		ArrayList readResult = new ArrayList();
		try {
			switch (readMethod) {
				default:
				case 1: //read to a ByteBuffer, use getChar and add each character as a new item to the ArrayList
					ByteBuffer byteBufferToRead = ByteBuffer.allocate(2); //Allow only 2 bytes to be read at a time
					SeekableByteChannel seekableByteChannel = Files.newByteChannel(filename, READ);
					int readByteCounter;
					long filePosition;
					while ((readByteCounter = seekableByteChannel.read(byteBufferToRead)) != -1) {
						byteBufferToRead.flip();
						filePosition = seekableByteChannel.position();
						if (readByteCounter >= 2) { //To avoid BufferUnderRun exception, less bytes read than should
							readResult.add(byteBufferToRead.getChar());
						}
					}
					break;
				case 2: //Read to a ByteBuffer using StringBuilder and charViewBuffer and add built string to ArrayList
					ByteBuffer byteBufferToRead2 = ByteBuffer.allocate(2); //Allow only 2 bytes to be read at a time
					SeekableByteChannel seekableByteChannel2 = Files.newByteChannel(filename, READ);
					StringBuilder builder = new StringBuilder();
					while ((seekableByteChannel2.read(byteBufferToRead2)) != -1) {
						builder.append(((ByteBuffer) byteBufferToRead2.flip()).asCharBuffer().toString());
						byteBufferToRead2.clear(); //get the buffer ready for the next cycle
					}
					readResult.add(builder);
					long fileSize = seekableByteChannel2.size();
					break;
				case 3: //TODO Read to a ByteBuffer[] array - to be completed
					int numBuffers = 5;
					ByteBuffer[] bbuffers = new ByteBuffer[numBuffers]; //Create the array for the bytebufs
					for (int i = 0; i < numBuffers; i++) { //Create the bytebuffers in the array
						bbuffers[i] = ByteBuffer.allocate(2);
					}
					//newByteChannel need to be cast to FileChannel to be able to use ByteBuffers[] method
					FileChannel fileChannel = (FileChannel) Files.newByteChannel(filename, READ);
					fileChannel.read(bbuffers);
					for (ByteBuffer buffer : bbuffers) {
						if (buffer.remaining() >= 2) {
							readResult.add(buffer.getChar());
						}
					}
					break;
				case 4:
					break;
			}//end switch
		} catch (IOException e) {
			e.printStackTrace();
		}
		return readResult;
	}

	/**
	 * Read all data from BINARY file with newByteChannel
	 *
	 * @param filename
	 * @param readMethod
	 * @return an ArrayList of all read bytes
	 */
	public static ArrayList readLongFromByteChannel(Path filename, int readMethod) {
		ArrayList resultsArrayList = new ArrayList();
		//Use try-with-resources to automatically close the stream after reading is completed
		try (SeekableByteChannel seekableByteChannel = Files.newByteChannel(filename, READ)) {
			int numberOfLongToReadInOneIteration = 4; //In one read iteration this many LONG numbers to read
			final int LONG_BYTE_COUNT = 8; //A LONG number has 8 bytes of data
			ByteBuffer byteBuffer = ByteBuffer.allocate(numberOfLongToReadInOneIteration * LONG_BYTE_COUNT); //Allocate enough space in buffer
			int currentBytesReadCount = 0;
			//Read until EOF (-1) is reached
			while ((currentBytesReadCount = seekableByteChannel.read(byteBuffer)) != -1) {
				//(ByteBuffer)byteBuffer.flip() is a new ByteBuffer object without reference, will be used only as argumetn to ArrayL.
				//Because the ByteBuffer holds numberOfLongToReadInOneIteration data, all of them needs to be read in a loop
				byteBuffer.flip(); //set the byteBuffer position to 0 for the read operations
				for (int i = 0; i < numberOfLongToReadInOneIteration; i++) { //Copy the content of bytebuffer to the result array
					if (byteBuffer.remaining() >= 8) { //Only read if there are at least 8 more available bytes in the buffer
						resultsArrayList.add(byteBuffer.asLongBuffer().get());
						byteBuffer.position(byteBuffer.position() + 8);
					}
				}
				byteBuffer.clear(); //Set the buffer positon to 0, limit to capacity, so the buffer is empty for the next iteration 
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return resultsArrayList;
	}
} //class
