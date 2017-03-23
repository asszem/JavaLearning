package FilesAndDirectories.Practice;

import FilesAndDirectories.Buffers;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import static java.nio.file.StandardOpenOption.*;

/**
 *
 * @author Andras Olah (olahandras78@gmail.com)
 */
public class WritingFixedPositionInAFile {

	public static void main(String[] args) {
		Path p = Paths.get("E:\\javaTest", "writeToFixedPosition.txt");
		ByteBuffer buf = ByteBuffer.wrap("This is a test message".getBytes());
		try (FileOutputStream fos = new FileOutputStream(p.toFile(), true);
				SeekableByteChannel sbc = Files.newByteChannel(p, CREATE, WRITE, TRUNCATE_EXISTING);) {
			long oldPosition;
			for (int i = 0; i < 4; i++) {
				System.out.println("Iteration: " + i);
				System.out.println("\tBefore write");
				oldPosition = sbc.position();
				System.out.println("Old Position saved: " + oldPosition);
				sbc.position(0);
				ByteBuffer buf2 = ByteBuffer.wrap(Integer.toString(i).getBytes("UTF-8"));
				sbc.write(buf2);
				if (i == 0) {
					sbc.position(1); //At the first iteration we have to move file pos to 1
				} else {
					sbc.position(oldPosition);
				}
				System.out.println("\tFile position: " + sbc.position());
				sbc.write(buf);
				System.out.println("\tAfter write");
				System.out.println("\tFile position: " + sbc.position());
				buf.rewind();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
