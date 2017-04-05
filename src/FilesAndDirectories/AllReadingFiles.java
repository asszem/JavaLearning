package FilesAndDirectories;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.LongBuffer;
import java.nio.file.Files;
import java.nio.file.Path;

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
	 */
	public static void readByteWithInputStream(Path filename) {
		if (!checkFileExists(filename)) {
			System.out.println("File does not exists.");
		} else {
			int readResult = 0;
			try {
				BufferedInputStream bufferedInputStream = new BufferedInputStream(Files.newInputStream(filename));
				while (readResult != -1) {
					//Read result is the number of bytes read and NOT THE VALUES OF BYTES
					readResult = bufferedInputStream.read();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}//end else
	}//end method

	/**
	 * Read INT values from a binary file using read() method while EOF (-1) is reached
	 *
	 * @param filename
	 */
	public static void readIntWithInputStream(Path filename) {
		if (!checkFileExists(filename)) {
			System.out.println("File does not exists.");
		} else {
			int readResult = 0;
			ByteBuffer buf = ByteBuffer.allocate(4);
			IntBuffer ibuf = buf.asIntBuffer();
			int intValueRead;
			try {
				BufferedInputStream bufferedInputStream = new BufferedInputStream(Files.newInputStream(filename));
				while (readResult != -1) {
					readResult = bufferedInputStream.read(buf.array()); //Returns the data to the byte array provided
					intValueRead=ibuf.get();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}//end else
	}//end method
} //class
