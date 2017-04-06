package FilesAndDirectories;

import java.io.BufferedInputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 *
 * @author Andras Olah (olahandras78@gmail.com)
 */
public class AllReadingFilesTD {

	public static void main(String[] args) {
		Path path = Paths.get("E:\\javaFileOpTest\\ReadingFiles");
		int testCase = 1;
		switch (testCase) {
			case 1:
				//<editor-fold desc="Writing and Reading BYTE to a binary file with Output/InputStream">
				Path pathReadByteFromBin = path.resolve("readByteFromBinaryFile.bin");
				byte bytesToWrite;
				//Delete the output file every time to make sure only the created amount of data exists
				AllFileOperationsSample.deleteSingleFileOrDir(pathReadByteFromBin);
				bytesToWrite = -10;
				AllWritingFiles.writingByteToBinaryWithOutputStream(path.resolve(pathReadByteFromBin), bytesToWrite);
				bytesToWrite = -1;
				AllWritingFiles.writingByteToBinaryWithOutputStream(path.resolve(pathReadByteFromBin), bytesToWrite);
				bytesToWrite = 127;
				AllWritingFiles.writingByteToBinaryWithOutputStream(path.resolve(pathReadByteFromBin), bytesToWrite);
				System.out.println("Bytes read: "+AllReadingFiles.readByteWithInputStream(pathReadByteFromBin));
				//</editor-fold>
				break;
			case 2:
				//<editor-fold desc="Reading LONG data from binary file using InputStream">
				//Note: the ReadLongFromBinaryFile must have LONG values stored in it in order to work correctly
				//See Hortons StreamOutputToFile class, that writes LONG values as binary to the file
				Path pathReadLongFromBinary = path.resolve("readLongFromBinaryFile.bin");
				AllReadingFiles.readLongWithInputStream(pathReadLongFromBinary);
				//</editor-fold>
				break;
			case 3:
				//<editor-fold desc="Writing INT to a binary file (OutputStream) and reading it back (InputStream)">
				Path pathReadIntFromBin = path.resolve("readIntFromBin.bin");
				AllFileOperationsSample.deleteSingleFileOrDir(pathReadIntFromBin);
				int intToWrite;
				intToWrite=100;
				//AllWritingFiles.writingIntegersToBinaryWithOutputStream(pathReadIntFromBin, intToWrite);
				//intToWrite=-1;
				//AllWritingFiles.writingIntegersToBinaryWithOutputStream(pathReadIntFromBin, intToWrite);
				//intToWrite=1024;
				//AllWritingFiles.writingIntegersToBinaryWithOutputStream(pathReadIntFromBin, intToWrite);
				//This generates a wrong input as only one byte is read, the integer gets written as one byte
				//AllWritingFiles.writingWithOutputStream(pathReadIntFromBin, intToWrite);
				System.out.println("Int read: "+AllReadingFiles.readIntWithInputStream(pathReadIntFromBin));
				//</editor-fold>
				break;
		}
	}
}
