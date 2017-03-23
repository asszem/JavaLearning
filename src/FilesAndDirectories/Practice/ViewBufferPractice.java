package FilesAndDirectories.Practice;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.IntBuffer;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import static java.nio.file.StandardOpenOption.*;
import java.util.EnumSet;
import java.util.Formatter;
import java.util.Locale;

/**
 *
 * @author Andras Olah (olahandras78@gmail.com)
 */
public class ViewBufferPractice {

	public static void main(String[] args) throws IOException {
//Setting initial values
		String[] vers = {"Íme, itt a költeményem.", "Ez a második sora.", "K betűkkel szól keményen", "címe: \"Költőnk és Kora\".", "Ugy szállong a semmi benne,", "mintha valaminek lenne a pora ..."};
		Locale magyar = new Locale("hu", "HU");
		String separator = System.lineSeparator();
		Path p = Paths.get("E:\\javaTest", "versFormatter.txt");
		//The lenght ot the byte buffer must equal to the length of the longest line
		int maxLength = 0;
		for (String sor : vers) {
			if (sor.length() >= maxLength) {
				maxLength = sor.length();
			}
		}
//Creating the buffers
		//2 bytes for each character, 4 bytes for {} and 2 for separator
//		ByteBuffer buf = ByteBuffer.allocate(maxLength * 2 + 8);
		ByteBuffer buf = ByteBuffer.allocate(1024);
		bufStat(buf);
		CharBuffer cbuf = buf.asCharBuffer(); //Capacity = ByteBuffer cap / 2, because each char is 2 bytes
		bufStat(cbuf);

//Setting up formatter
		Formatter formatter = new Formatter(cbuf);
//Filling up the charbuffer, keeping position of bytebuffer on track
		System.out.println("\nGo through line by line");
		int iteration = 1;
		try (SeekableByteChannel sbc = Files.newByteChannel(p, EnumSet.of(WRITE, CREATE, TRUNCATE_EXISTING))) {
			for (String sor : vers) {
				if (iteration == 3) {
					break;
				}
				System.out.println("\tIteration: " + iteration++);
				System.out.printf("Vers sor: [%s]%n", sor);
				System.out.println("Sor hossza: " + sor.length());
				formatter.format(magyar, "[%s]%s", sor, separator);
				System.out.println("pre flip:");
				bufStat(buf);
				bufStat(cbuf);
				cbuf.flip();
				buf.position(0);
				buf.limit(cbuf.limit()*2); //each character takes 2 bytes!
				System.out.println("post flip, bytebuf updated");
				bufStat(buf);
				bufStat(cbuf);
//Writing the charbuffer to a file
				sbc.write(buf);
				System.out.println("Line written to file.");
				System.out.println("cbuf clear");
				cbuf.clear();
				bufStat(cbuf);
			} //end for loop
		} catch (IOException e) {
			e.printStackTrace();
		} //end catch
		System.out.println("File written, program completed.");
	}//end main

	static void bufStat(CharBuffer buf) {
		System.out.printf("CharBuffer pos:%d, limit:%d, remaining:%d, cap:%d%n", buf.position(), buf.limit(), buf.remaining(), buf.capacity());
	}

	static void bufStat(ByteBuffer buf) {
		System.out.printf("ByteBuffer pos:%d, limit:%d, remaining:%d, cap:%d%n", buf.position(), buf.limit(), buf.remaining(), buf.capacity());
	}

	static void bufStat(IntBuffer buf) {
		System.out.printf("\tIntBuffer pos:%d, limit:%d, remaining:%d, cap:%d%n", buf.position(), buf.limit(), buf.remaining(), buf.capacity());
	}

	static void bufContent(IntBuffer intBuf) {
		System.out.println("--- Start of Content of IntBuffer " + intBuf.getClass().getSimpleName());
		bufStat(intBuf);
		for (int i = 0; i < intBuf.capacity(); i++) {
			System.out.printf("%d. [%d]%n", i, intBuf.get(i));
		}
		System.out.println("---End of Int Buffer Content method");
	}

}
