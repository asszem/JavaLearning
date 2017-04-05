package FilesAndDirectories;

import java.io.BufferedInputStream;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 *
 * @author Andras Olah (olahandras78@gmail.com)
 */
public class AllReadingFilesTD {

	public static void main(String[] args) {
		Path path = Paths.get("E:\\javaFileOpTest\\ReadingFiles");
		Path pathBin = path.resolve("readFromBinaryFile.bin");
//		AllWritingFiles.writingWithOutputStreamWithOptions(pathBin, 42);
//		AllWritingFiles.writingWithOutputStreamWithOptions(pathBin, 100);
//		AllWritingFiles.writingWithOutputStreamWithOptions(pathBin, 5);
		AllReadingFiles.readLongWithInputStream(pathBin);
	}
}
