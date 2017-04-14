package FilesAndDirectories.Practice;

import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import static java.nio.channels.FileChannel.MapMode.READ_ONLY;
import static java.nio.channels.FileChannel.MapMode.READ_WRITE;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import static java.nio.file.StandardOpenOption.*;

/**
 *
 * @author Andras Olah (olahandras78@gmail.com) Horton, p439, Chapter 11 - Reading files
 */
//TODO add this method to Reading Files class when completed
public class MemoryMappedFile {

	protected MappedByteBuffer mappedByteBuffer;
	public final int LONG_BYTES = 8;
	protected int numOfLongsToBeReplaced = 10;
	protected long replaceLongTo = 888888L;
	protected long[][] replacedLongs = new long[numOfLongsToBeReplaced][2]; //[0][0] -> (int) position, [0][1] longOldValue
	public final int POSITION = 0;			// replacedLongsCount[...][POSITION]
	public final int OLD_LONG_VALUE = 1; 	// replacedLongsCount[...][OLD_LONG_VALUE]
	protected FileChannel fileChannel;
	Path originalFile = Paths.get("E:\\javaFileOpTest\\ReadingFiles\\MemoryMapped\\replacePrimesOriginal.bin");
	Path newFile;
	protected long fileSize;
	protected int numOfLongsInFile;

	public void createMappedByteBuffer() {
		if (!Files.exists(originalFile)) {
			throw new IllegalArgumentException("Input file does not exists!");
		}
		newFile = AppendStringToFileName.appendStringToFileName(originalFile, "_modified");
		CopyWithTransferToAndTransferFrom.copyFile(originalFile, newFile);
		try {
			fileChannel = (FileChannel) Files.newByteChannel(newFile, READ, WRITE);
			fileSize = fileChannel.size();
			numOfLongsInFile = (int) fileSize / LONG_BYTES;
			//Open the buffer for ReadWrite, from position0, up until last position, and immedieately load it
			mappedByteBuffer = fileChannel.map(READ_WRITE, 0L, fileSize).load();
			fileChannel.close(); //the mappedByteBuffer should have all the data
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public long[][] replaceRandomLongs() {
		if (mappedByteBuffer == null) {
			this.createMappedByteBuffer();
		}
		if (!mappedByteBuffer.isLoaded()) {
			mappedByteBuffer.load();
		}
		long[][] returnLong = new long[numOfLongsToBeReplaced][2];
		int randomPosition = 0;
		for (int replacedLongsCount = 0; replacedLongsCount < numOfLongsToBeReplaced; replacedLongsCount++) {
			randomPosition = LONG_BYTES * ((int) (Math.random() * numOfLongsInFile));
			returnLong[replacedLongsCount][POSITION] = randomPosition;
			//get FROM THE position!
			returnLong[replacedLongsCount][OLD_LONG_VALUE] = mappedByteBuffer.getLong(randomPosition);
			mappedByteBuffer.putLong(randomPosition, replaceLongTo);
		}
		return returnLong;
	}

	public void displayMappedByteBuffer() {
		if (mappedByteBuffer == null) {
			createMappedByteBuffer();
		}
		System.out.println("Original file: " +originalFile.getFileName());
		System.out.println("New file: " + newFile.getFileName());
		int counter = 0;
		mappedByteBuffer.clear();
		while (mappedByteBuffer.hasRemaining()) {
			System.out.printf("%02d=%d%n", counter++, mappedByteBuffer.getLong());
			if (counter % 5 == 0) {
				System.out.println("");
			}
		}
	}

	public static void main(String[] args) {
		MemoryMappedFile mappedFileObject = new MemoryMappedFile();
		mappedFileObject.displayMappedByteBuffer();
		mappedFileObject.replaceRandomLongs();
		mappedFileObject.displayMappedByteBuffer();
	}
}
