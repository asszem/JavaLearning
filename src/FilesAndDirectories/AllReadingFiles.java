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
import java.util.Formatter;

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
			int numberOfLongToReadInOneIteration = 10; //In one read iteration this many LONG numbers to read
			final int LONG_BYTE_COUNT = 8; //A LONG number has 8 bytes of data
			ByteBuffer byteBuffer = ByteBuffer.allocate(numberOfLongToReadInOneIteration * LONG_BYTE_COUNT); //Allocate enough space in buffer
			int currentBytesReadCount = 0;
			//Read until EOF (-1) is reached
			while ((currentBytesReadCount = seekableByteChannel.read(byteBuffer)) != -1) {
				//(ByteBuffer)byteBuffer.flip() is a new ByteBuffer object without reference, will be used only as argumetn to ArrayL.
				//Because the ByteBuffer holds numberOfLongToReadInOneIteration data, all of them needs to be read in a loop
				byteBuffer.flip(); //set the byteBuffer position to 0 for the read operations
				LongBuffer viewLongBuffer = byteBuffer.asLongBuffer(); //create a viewBuffer to get the LONG values from the byteBuffer
				for (int i = 0; i < numberOfLongToReadInOneIteration; i++) { //Copy the content of bytebuffer to the result array
					//if (byteBuffer.remaining() >= 8) { //Only read if there are at least 8 more available bytes in the buffer
					//	resultsArrayList.add(byteBuffer.asLongBuffer().get());
					//}
					if (viewLongBuffer.hasRemaining()) { //Only call the .get on the viewLongBuffer if it has more values to get
						resultsArrayList.add(viewLongBuffer.get());
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

	public static ArrayList readMixedDataFromByteChannel(Path filename, int readMethod) {
		ArrayList resultArray = new ArrayList();
		Formatter formattedResult = new Formatter();
		//<editor-fold desc="Method description">
		/*
		Method 1 - determine the String length in standalone read operation - resource ineffective
		Method 2 - Create a bigger ByteBuffer, load with data and use the .compact() method when there is not enough space available
		Horton's book, Chapter 11, reading files, page 431
		File structure
		[double][string][long]
		double	= the length of the following string (stored as Double to make things even more complicated :D)
		string	= the prime values as string, example prime = 23
		long 	= the prime value as long		
		 */
		//</editor-fold>
		try (FileChannel readChannel = (FileChannel) Files.newByteChannel(filename, READ);) {
			switch (readMethod) {
				case 1: //Using two read operations in each iteration
					//<editor-fold desc="Method 1. - using multiple reads">
					ByteBuffer stringLengthBuffer = ByteBuffer.allocate(8); //It will only read an DOUBLE value, 8 bytes 
					ByteBuffer[] buffersArray = new ByteBuffer[2]; //[0] will hold the buffer for String, [1] will hold the buffer for LONG
					buffersArray[0] = null; //size of String bytebuffer unknown at this point
					buffersArray[1] = ByteBuffer.allocate(8); //It will have 8 bytes for the LONG values
					while (true) { //loop through the file
						//Get the value of [double]
						if (readChannel.read(stringLengthBuffer) == -1) { //If EOF reached, stop the WHILE cycle
							break; //stop the reading when EOF reached
						}
						//get the int value from the double read to the stringLenghtBuffer
						int currentStringLength = (int) ((ByteBuffer) stringLengthBuffer.flip()).asDoubleBuffer().get();
						buffersArray[0] = ByteBuffer.allocate(currentStringLength * 2); //as strings are in Unicode char, 2 bytes each
						//At this point we have the string length, but we need to read the remaining data
						//Which is currentStringLenght+8
						readChannel.read(buffersArray); //When the first buffer is full, it continues to write to the second 
						//At this point we have all information in the 3 buffers. Pass it to the result array
						String currentAsString = ((ByteBuffer) buffersArray[0].flip()).asCharBuffer().toString();
						long currentAsLong = ((ByteBuffer) buffersArray[1].flip()).asLongBuffer().get();
						formattedResult.format("Sting length:[%03d] String:[%s] Long:[%d]", currentStringLength, currentAsString, currentAsLong);
						resultArray.add(formattedResult);
						stringLengthBuffer.clear(); //Clear it for the next DOUBLE
						buffersArray[1].clear(); //Clear it so it can read the next LONG
					}

					//</editor-fold>
					break;
				case 2: //Using the .compact method
					//<editor-fold desc="Method 2. - using .compact()">
					ByteBuffer bigByteBuffer = ByteBuffer.allocate(65); //Allocate an aribtary amount of bytes
					int currentStringLength;
					String currentString;
					Long currentLong;
					int currentLineFullLength; //8+stringLength*2+8 -- this will be one item in the ArrayList
					while (true) { //reading through the file
						//Read data to the bigByteBuffer until EOF is reached. 
						if ((readChannel.read(bigByteBuffer)) == -1) { //READ more than one valid line data to the Buffer
							break;
						}
						bigByteBuffer.flip(); //get the buffer ready to read
						/*pseudo code At this point we have the buffer with data. What we ASSUME, the first 8 is a double
						1. read the first string length (position will be updated in bytebuffer)
						2. calculate the first required length (stringlenght*2+8)
						3. make a while cycle until the bytbuffer has enough remaining to get the requied string and long data
							get the string and long, build the next result array and increment buffer position
						after the while cycle compact the buffer, and then read again (but keep the already read data's position)
						 */
						currentStringLength = (int) bigByteBuffer.getDouble(); //this updates the Position of bigByteBuffer!
						bigByteBuffer.position(bigByteBuffer.position() - 8); //Reset the position
						currentLineFullLength = 8 + currentStringLength * 2 + 8;
						boolean enoughDataInBuffer = (bigByteBuffer.remaining() >= currentLineFullLength);
						while (enoughDataInBuffer) {
							currentStringLength = (int) bigByteBuffer.getDouble(); //this updates the Position of bigByteBuffer!
							//read all the characters from the bigbytebuffer WHILE increasing the position as well
							StringBuilder buildCurrentString = new StringBuilder();
							for (int i = 0; i < currentStringLength; i++) {
								buildCurrentString.append(bigByteBuffer.getChar()); //this updates the position
							}
							currentString = buildCurrentString.toString();
							currentLong = bigByteBuffer.getLong(); //this updates the position
							formattedResult.format("Sting length:[%03d] String:[%s] Long:[%d]", currentStringLength, currentString, currentLong);
							resultArray.add(formattedResult);

							//at this point the position should point to the next DOUBLE number
							if (bigByteBuffer.remaining() >= 8) //if there is enough data to read the next Double
							{
								currentStringLength = (int) bigByteBuffer.getDouble(); //this updates the Position of bigByteBuffer!
								//check if there is enough data to read a complete new line
								enoughDataInBuffer = bigByteBuffer.remaining() >= currentStringLength * 2 + 8;
								//If it turns out that there is not enough data to read string+long
								//but the position is already read, the byte buffer position needs to be rewind with 8
								//because the currentString position double will be read at the beginning of the cycle
								if (!enoughDataInBuffer) {
									bigByteBuffer.position(bigByteBuffer.position() - 8);
								}
							} else //there is not enough room for the next int
							{
								enoughDataInBuffer = false;
							}
						}//end buffer reading while
						bigByteBuffer.compact();
					}//end file reading while
					//</editor-fold>
					break;
				case 3: //Using the .compact method based on logic in Horton's book
					ByteBuffer buf = ByteBuffer.allocate(256);
					buf.position(buf.limit()); //why this needed
					int strLength = 0;
					byte[] strChars = null;
					long longValue=0;
					while (true) {
						if (buf.remaining() < 8) {
							//at this point, not enough data to read all bytes for next double
							if (readChannel.read(buf.compact()) == -1) {
								break; //End of file reached
							}
							buf.flip();
						}
						strLength = (int) buf.getDouble();
						if (buf.remaining() < 2 * strLength) {
							//at this point, not enough data in buffer to read full string
							if (readChannel.read(buf.compact()) == -1) {
								//At this point eOF reached while it was expecting more data for building the string. The file is corrupted
								System.err.println("EOF found while reading the string. File corrupted? Error code: 001");
								break;//ends the while loop
							}
							//at this point the data is read from the file to the compacted buffer
							buf.flip();//buffer is ready to read from
						}//end buffer string length validation
						//at this point, buffer position at the beginning of string and there is enough data in buffer to read the full string
						strChars=new byte[2*strLength]; //create a byte array for chars
						buf.get(strChars);

						//Verify if there are enough data remainig to read LONG
						if (buf.remaining()<8){
							//at this point not enough data to read LONG
							//another READ operation needed
							if (readChannel.read(buf.compact()) ==-1){
								//at this point the read operation failed to fill the buffer, while ther should be enough data in the file to read. Terminate
								System.err.println("EOF found while reading the LONG. File corrupted? Error code: 002");
								break;
							}
							//at this point new data is read to the compacted buffer
							//buff pos=position+amount of read data
							buf.flip(); //limit=position then position=0
						}
						longValue=buf.getLong();
						//at this point we have all 3 values set, create and build the result array
						Formatter fr2 =new Formatter();
						fr2.format("Sting length:[%03d] String:[%s] Long:[%d]", strLength, ByteBuffer.wrap(strChars).asCharBuffer(), longValue);
						resultArray.add(fr2);
					}//end file reading while
			}//end switch
		}//end try
		catch (IOException e) {
			e.printStackTrace();
		}//end catch
		return resultArray;
	}
} //class
