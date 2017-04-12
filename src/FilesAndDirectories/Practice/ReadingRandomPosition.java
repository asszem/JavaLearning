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
			final long LONG_TO_WRITE=99999L;
			final int LONG_BYTES = 8;
			final int TOTAL_NUMBER_OF_LONGS = (int) resultFileSize / LONG_BYTES; //should not be changing
			totalNumberOfLongsInTheFile = TOTAL_NUMBER_OF_LONGS; //static variable that can be tested
			ByteBuffer bufferForAll = ByteBuffer.allocate(LONG_BYTES * thisManyLongsToReplace); //buffer to store all longs
			ByteBuffer bufferForCurrent = ByteBuffer.allocate(LONG_BYTES); //buffer to store current only
			//Loop through required number of replaces
			for (int replacedLongs = 0; replacedLongs < thisManyLongsToReplace; replacedLongs++) {
				//Pick a random number of long, and multiply by 8 to get the position of that long
				long randomPosition = (long) (Math.random() * TOTAL_NUMBER_OF_LONGS) * LONG_BYTES;
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
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}//end method

	//This could be a JUNIT test method as well, but I keep it here to practice reading from file
	public static void compareResults(Path inputFile, Path outputFile){

	}

	public static void main(String[] args) {
		Path inputFile = Paths.get("E:\\javaFileOpTest\\ReadingFiles\\onlyLONGvalues(primes).bin");
		replaceLongAtRandomPosition(inputFile, "run1", 10);

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
