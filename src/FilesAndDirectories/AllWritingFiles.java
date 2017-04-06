package FilesAndDirectories;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.*;
import static java.nio.file.StandardOpenOption.*;

/**
 *
 * @author Andras Olah (olahandras78@gmail.com)
 */
public class AllWritingFiles {

	static boolean writingIntegersToBinaryWithOutputStream(Path file, int intToWrite) {
		ByteBuffer byteBuffer =ByteBuffer.allocate(4); //We are going to write a single integer (4 bytes) 
		IntBuffer intViewBuffer =byteBuffer.asIntBuffer();
		try (BufferedOutputStream bos = new BufferedOutputStream(Files.newOutputStream(file, APPEND, CREATE))) {
			intViewBuffer.put(intToWrite); //To put the actual value to the buffer
			bos.write(byteBuffer.array(), 0, byteBuffer.capacity()); //To write the full capacity of the buffer
			return true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	//This method is WRONG, it writes a SINGLE byte from the provided INT. It can not be retrieved later
	static boolean writingWithOutputStream(Path file, int content) {
		try (BufferedOutputStream bos = new BufferedOutputStream(Files.newOutputStream(file))) {
			bos.write(content);
			return true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	static boolean writingByteToBinaryWithOutputStream(Path file, byte content) {
		try (BufferedOutputStream bos = new BufferedOutputStream(Files.newOutputStream(file, APPEND, CREATE))) {
			bos.write(content);
			return true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	static boolean writingWithBufferedWriter(Path file, String outputContent, String openOption) {
		System.out.println("Writing character with UTF-16 encoding, standard opening options");
		System.out.println("Standard Open Option: " + openOption);
		System.out.println("Target file: " + file);
		switch (openOption.toLowerCase()) {
			case "append":
				try (BufferedWriter bufferedWriter = Files.newBufferedWriter(file, Charset.forName("UTF-16"),CREATE, APPEND)) {
					System.out.println("Appending...");
					bufferedWriter.write(outputContent);
					bufferedWriter.newLine();
					return true;
				} catch (IOException e) {
					e.printStackTrace();
				}
				break;
			default:
				try (BufferedWriter bufferedWriter = Files.newBufferedWriter(file, Charset.forName("UTF-16"))) {
					bufferedWriter.write(outputContent);
					return true;
				} catch (IOException e) {
					e.printStackTrace();
				}
		}//end switch
		return false;
	}

	public static void waitForEnter() {
		try {
			System.out.println("Press Enter");
			System.in.read();
		} catch (IOException e) {
			System.err.println(e);
		}
	}

}
