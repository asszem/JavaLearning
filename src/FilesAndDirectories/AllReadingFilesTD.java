package FilesAndDirectories;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 *
 * @author Andras Olah (olahandras78@gmail.com)
 */
public class AllReadingFilesTD {

	public static void main(String[] args) {
		Path path = Paths.get("E:\\javaFileOpTest\\ReadingFiles");
		int testCase = 4;
		switch (testCase) {
			case 1:
				//<editor-fold desc="Writing and Reading BYTE to a binary file with Output/InputStream">
				Path pathReadByteFromBin = path.resolve("readByteFromBinaryFile.bin");
				byte bytesToWrite;
				//Delete the output file every time to make sure only the created amount of data exists
				AllFileOperations.deleteSingleFileOrDir(pathReadByteFromBin);
				bytesToWrite = -10;
				AllWritingFiles.writingByteToBinaryWithOutputStream(path.resolve(pathReadByteFromBin), bytesToWrite);
				bytesToWrite = -1;
				AllWritingFiles.writingByteToBinaryWithOutputStream(path.resolve(pathReadByteFromBin), bytesToWrite);
				bytesToWrite = 127;
				AllWritingFiles.writingByteToBinaryWithOutputStream(path.resolve(pathReadByteFromBin), bytesToWrite);
				System.out.println("Bytes read: " + AllReadingFiles.readByteWithInputStream(pathReadByteFromBin));
				//</editor-fold>
				break;
			case 2:
				//<editor-fold desc="Writing and Reading INT to a binary file with Output/InputStream">
				Path pathReadIntFromBin = path.resolve("readIntFromBin.bin");
				AllFileOperations.deleteSingleFileOrDir(pathReadIntFromBin);
				int intToWrite;
				intToWrite = 100;
				//AllWritingFiles.writingIntegersToBinaryWithOutputStream(pathReadIntFromBin, intToWrite);
				//intToWrite=-1;
				//AllWritingFiles.writingIntegersToBinaryWithOutputStream(pathReadIntFromBin, intToWrite);
				//intToWrite=1024;
				//AllWritingFiles.writingIntegersToBinaryWithOutputStream(pathReadIntFromBin, intToWrite);
				//This generates a wrong input as only one byte is read, the integer gets written as one byte
				//AllWritingFiles.writingWithOutputStream(pathReadIntFromBin, intToWrite);
				System.out.println("Int read: " + AllReadingFiles.readIntWithInputStream(pathReadIntFromBin));
				//</editor-fold>
				break;
			case 3:
				//<editor-fold desc="Reading LONG data from binary file using InputStream">
				//Note: the ReadLongFromBinaryFile must have LONG values stored in it in order to work correctly
				//See Hortons StreamOutputToFile class, that writes LONG values as binary to the file
				Path pathReadLongFromBinary = path.resolve("readLongFromBinaryFile.bin");
				AllReadingFiles.readLongWithInputStream(pathReadLongFromBinary);
				//</editor-fold>
				break;
			case 4:
				//<editor-fold desc="Reading LONG data from BINARY file using newByteChannel">
				//Note: the ReadLongFromBinaryFile must have LONG values stored in it in order to work correctly
				//See Hortons StreamOutputToFile class, that writes LONG values as binary to the file
				pathReadLongFromBinary = path.resolve("readLongFromBinaryFile.bin");
				displayArrayList(AllReadingFiles.readLongFromByteChannel(pathReadLongFromBinary, 1));
				//</editor-fold>
				break;
			case 5:
				//<editor-fold desc="Writing and Reading string/char data from text file with BufferedREader">
				//To test throwing exceptions, I will intentionally use try-catch block here and not in the method
				Path fileCharacterToRead = path.resolve("readCharFromTxt.txt");
				AllFileOperations.deleteSingleFileOrDir(fileCharacterToRead);
				String testString = "Árvíztűrő tükörfúrógép";
				ArrayList resultStrings = new ArrayList();
				AllWritingFiles.writingWithBufferedWriter(fileCharacterToRead, testString, "append");
				AllWritingFiles.writingWithBufferedWriter(fileCharacterToRead, testString, "append");
				AllWritingFiles.writingWithBufferedWriter(fileCharacterToRead, testString, "append");
				try {
					resultStrings.add(AllReadingFiles.readStringWithBufferedReader(fileCharacterToRead, 1));
					resultStrings.add(AllReadingFiles.readStringWithBufferedReader(fileCharacterToRead, 2));
					resultStrings.add(AllReadingFiles.readStringWithBufferedReader(fileCharacterToRead, 3));
					System.out.println(resultStrings);
				} catch (IOException e) {
					e.printStackTrace();
				}
				//</editor-fold>
				break;
			case 6:
				//<editor-fold desc="Writing and Reading string/char data from text files with newByteChannel">
				Path fileChannelRead = path.resolve("fileChannelRead.txt");
				AllFileOperations.deleteSingleFileOrDir(fileChannelRead);
				AllWritingFiles.writingWithBufferedWriter(fileChannelRead, "Árvíztűrő tükörfúrógép", "append");
				AllWritingFiles.writingWithBufferedWriter(fileChannelRead, "1234432   00", "append");
				AllWritingFiles.writingWithBufferedWriter(fileChannelRead, "Árvíztűrő tükörfúrógép", "append");
				System.out.println("\nReading operations");
				System.out.println("Read to byteBuffer method 1 (ArrayList)");
				ArrayList testResult = AllReadingFiles.readCharFromByteChannel(fileChannelRead, 1);
				displayArrayList(testResult);
				System.out.println("Read to byteBuffer method 2 (StringBuilder)");
				testResult = AllReadingFiles.readCharFromByteChannel(fileChannelRead, 2);
				displayArrayList(testResult);
			//</editor-fold>
		}
	}

	public static void displayArrayList(ArrayList al) {
		System.out.println("Array List size: " + al.size());
		int counter=0;
		for (Object o : al) {
			System.out.printf("[%d] ", counter++);
			System.out.println(o);
		}
	}
}
