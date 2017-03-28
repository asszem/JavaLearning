package FilesAndDirectories;

//import static FilesAndDirectories.BuffersTD.bufTrace;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.file.Path;
import java.nio.channels.WritableByteChannel;
import java.nio.file.Files;
import java.util.EnumSet;
import static java.nio.file.StandardOpenOption.*;

/**
 *
 * @author Andras Olah (olahandras78@gmail.com)
 */
public class Channels {

	public static void writeToWritableByteChannel(ByteBuffer buf, Path path) {
		//Make sure the parent directory of the file exists. If it doesn't this method will create it
		try {
			Files.createDirectories(path.getParent());
		} catch (IOException e) {
			e.printStackTrace();
		}
		//Write the content of buffer to the file
		try (WritableByteChannel channel = Files.newByteChannel(path, EnumSet.of(CREATE, WRITE))) {
			buf.flip();
			channel.write(buf);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
