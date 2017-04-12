package FilesAndDirectories.Practice;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Andras Olah (olahandras78@gmail.com)
 */
public class ReadingRandomPosition {

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
	public static void main(String[] args) {
		Path inputFile = Paths.get("E:\\javaFileOpTest\\ReadingFiles\\primes.bin");
		ArrayList resultArrayList=readLongFromRandomPosition(inputFile);
		int counter=1;
		for (Object current:resultArrayList){
			System.out.printf("%02d= %-4d",counter++,current);
			if (counter%6==0){
				System.out.println("");
			}
		}
	}
}
