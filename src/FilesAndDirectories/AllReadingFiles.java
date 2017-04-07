package FilesAndDirectories;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.LongBuffer;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

/**
 *
 * @author Andras Olah (olahandras78@gmail.com)
 */
public class AllReadingFiles {

	public static boolean checkFileExists(Path filename) {
		return (Files.exists(filename));
	}

	/**
	 * Reads LONG values from a BINARY file using a longViewBuffer and a byter[] array to back the byteBuffer
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
	 * Read BYTE values from a binary file using read() method while EOF (-1) is reached
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
	 * Read INT values from a binary file using read() method while EOF (-1) is reached
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
	}//end method

	/**
	 * This method does not have a try-catch block, so the calling method must have it to catch possible IOException
	 * @param filename
	 * @param readMethod 1- reads a single char; 2-reads everyting to a char array; 3- reads a single line
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
				result="";
				for (int i=0;i<numberOfCharsRead;i++) {
					result += charArray[i];
				}
				break;
			case 3: //Method 3 - readLine() - reads line
				result = bufferedReader.readLine();
				break;
		}
		return result;
	}
} //class
