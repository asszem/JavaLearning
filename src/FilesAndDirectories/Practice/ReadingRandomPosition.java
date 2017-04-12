package FilesAndDirectories.Practice;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import static java.nio.file.StandardOpenOption.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Andras Olah (olahandras78@gmail.com) based on Hortons chapter 11, page 438
 */
public class ReadingRandomPosition {

	public static int totalNumberOfLongsInTheFile;

	public static ArrayList readLongFromRandomPosition(Path inputFile) {
		ArrayList resultList = new ArrayList();
		if (!Files.exists(inputFile)) {
			throw new IllegalArgumentException("File not found!");
		}
		try (FileChannel readChannel = (FileChannel) Files.newByteChannel(inputFile);) {
			final int LONG_BYTES = 8;
			int numbersToRead = 10;
			long totalBytesInFile = readChannel.size();
			long totalLongsInFile = totalBytesInFile / 8; //should not get any remainder!
			ByteBuffer byteBuffer = ByteBuffer.allocate(LONG_BYTES);
			for (int i = 0; i < numbersToRead; i++) {
				long newPosition = (long) (Math.random() * totalLongsInFile) * LONG_BYTES;
				readChannel.position(newPosition).read(byteBuffer);
				byteBuffer.flip();
				resultList.add(byteBuffer.getLong());
				byteBuffer.clear();
			}

		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return resultList;
	}

	/**
	 * Replaces the LONG value read from a random position with 999999L.
	 *
	 * but not in the original file, it creates a new file, called _longReplaced using the createBackup helper class1s method
	 *
	 * @param inputFile the input file (must contain 8 byte long LONG values only)
	 * @param toAppendToFilename a String to be appended to the result filename
	 * @param thisManyLongsToReplace the number of LONG values to be replaced
	 */
	public static void replaceLongAtRandomPosition(Path inputFile, String toAppendToFilename, int thisManyLongsToReplace) {
		if (!Files.exists(inputFile)) {
			throw new IllegalArgumentException("File not found!");
		}
		//Create the result file
		Path resultFile = AppendStringToFileName.appendStringToFileName(inputFile, toAppendToFilename);
		CopyWithTransferToAndTransferFrom.copyFile(inputFile, resultFile);
		//Open a fileChannel to perform the replacement in the RESULT file
		try (FileChannel resultFileChannel = (FileChannel) Files.newByteChannel(resultFile, READ, WRITE)) {
			//Initialize data
			long resultFileSize = resultFileChannel.size();
			final long LONG_TO_WRITE = 99999L;
			final int LONG_BYTES = 8;
			final int TOTAL_NUMBER_OF_LONGS = (int) resultFileSize / LONG_BYTES; //should not be changing
			totalNumberOfLongsInTheFile = TOTAL_NUMBER_OF_LONGS; //static variable that can be tested
			ByteBuffer bufferForAll = ByteBuffer.allocate(LONG_BYTES * thisManyLongsToReplace); //buffer to store all longs
			ByteBuffer bufferForCurrent = ByteBuffer.allocate(LONG_BYTES); //buffer to store current only
			//Loop through required number of replaces
			long randomPosition = 0;
			long[] usedPositions = new long[thisManyLongsToReplace]; //array to hold the already used positions
			for (int replacedLongs = 0; replacedLongs < thisManyLongsToReplace; replacedLongs++) {
				//Pick a random number of long, and multiply by 8 to get the position of that long
				//make sure a position is not selected multiple times
				usedPositions[replacedLongs]=getRandomPosition(usedPositions, TOTAL_NUMBER_OF_LONGS, LONG_BYTES);
				randomPosition=usedPositions[replacedLongs];
				resultFileChannel.position(randomPosition).read(bufferForCurrent); //read current long to buffer
				bufferForAll.put(bufferForCurrent); //add the current buffr to the total buffer
				bufferForCurrent.clear();
				//Rewinding the file position not needed since I still have the randomPosition
//				resultFileChannel.position(resultFileChannel.position()-LONG_BYTES); //Rewind the FILE position for replace
				//use the already existing current buffer to hold and write the new LONG values
				bufferForCurrent.asLongBuffer().put(LONG_TO_WRITE).flip();
				//write the buffer to the current position
				resultFileChannel.position(randomPosition).write(bufferForCurrent);
				//reset the current buffer for the next read
				bufferForCurrent.clear();
			}//end for - pick the next random position
		} catch (IOException e) {
			e.printStackTrace();
		}

	}//end method

	//This could be a JUNIT test method as well, but I keep it here to practice reading from file

	/**
	 * Compares the files and returns the number of found differences
	 * @param inputFile
	 * @param resultFile
	 * @return the number of differences found between the two files
	 */
	public static int compareResults(Path inputFile, Path resultFile) {
		int differenceCounter = 0;
		if (!Files.exists(inputFile) || !Files.exists(resultFile)) {
			throw new IllegalArgumentException("Input or result file doesn't exists!");
		}
		//Open the channel to read
		try (FileChannel inputFileChannel = (FileChannel) Files.newByteChannel(inputFile); FileChannel resultFileChannel = (FileChannel) Files.newByteChannel(resultFile)) {
			ByteBuffer allResultsBuffer = ByteBuffer.allocate(16);//To hold BOTH LONG values for each comparison pair
			ByteBuffer inputBuffer = ByteBuffer.allocate(8);
			ByteBuffer resultBuffer = ByteBuffer.allocate(8);
			int comparedCounter = 0;
			while (true) {
				allResultsBuffer.limit(8); //set the limit, so the next read operation only read 8 bytes
				long inputFileBytesRead = inputFileChannel.read(allResultsBuffer);
				//set the new limit so the next read operation will have enough space in the buffer for the next 8 bytes
				allResultsBuffer.limit(allResultsBuffer.capacity());
				long resultFileBytesRead = resultFileChannel.read(allResultsBuffer);
				//flip the buffer (pos=0) for printing
				allResultsBuffer.flip();
				//If EOF reached, break before getLong gives a bufferunderrun exception
				if (inputFileBytesRead == -1 || resultFileBytesRead == -1) {
					break;
				}
				long inputCurrentValue = allResultsBuffer.getLong();
				long resultCurrentValue = allResultsBuffer.getLong();
				System.out.printf("Compared #%d%n\tinput=\t%d%n\tresult=\t%d%n\tDifference=%d%n", comparedCounter++, inputCurrentValue, resultCurrentValue, (inputCurrentValue - resultCurrentValue));
				//prepare buffer for next read
				allResultsBuffer.clear();
				if (inputCurrentValue != resultCurrentValue) {
					System.out.println("The two results are not the same!");
					++differenceCounter;
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		return differenceCounter;
	}

	/**
	 * Helper method to get a unique random position 
	 * @param usedPositions an array of the currently used positions
	 * @param TOTAL_NUMBER_OF_LONGS this defines the max number of long values stored in the file
	 * @param LONG_BYTES one long value takes 8 bytes 
	 * @return returns a long position that was not yet selected for changing the number
	 */
	public static long getRandomPosition(long[] usedPositions, final int TOTAL_NUMBER_OF_LONGS, final int LONG_BYTES) {
		boolean validRandomPositionFound = false;
		long newRandomPosition=-1;
		PickNewRandomPosition:
		while (!validRandomPositionFound) {
			newRandomPosition = (long) (Math.random() * TOTAL_NUMBER_OF_LONGS) * LONG_BYTES;
			for (long usedPosition : usedPositions) {
				if (usedPosition == newRandomPosition) {
					continue PickNewRandomPosition;
				}//end if
			}//end for
			validRandomPositionFound = true;
		}//end while
		return newRandomPosition;
	}

	public static void main(String[] args) {
		Path inputFile = Paths.get("E:\\javaFileOpTest\\ReadingFiles\\onlyLONGvalues(primes).bin");
		replaceLongAtRandomPosition(inputFile, "run1", 6);
		Path resultFile = Paths.get("E:\\javaFileOpTest\\ReadingFiles\\onlyLONGvalues(primes)run1.bin");
		System.out.println("Number of differences: " + compareResults(inputFile, resultFile));
//		compareResults(inputFile, inputFile);

		//<editor-fold desc="Reading random LONG values">
		ArrayList resultArrayList = readLongFromRandomPosition(inputFile);
		int counter = 1;
		for (Object current : resultArrayList) {
			System.out.printf("%02d= %-4d", counter++, current);
			if (counter % 6 == 0) {
				System.out.println("");
			}
		}
		//</editor-fold>
	}
}
