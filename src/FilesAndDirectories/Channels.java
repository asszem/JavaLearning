package FilesAndDirectories;

//import static FilesAndDirectories.BuffersTD.bufTrace;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.GatheringByteChannel;
import java.nio.channels.SeekableByteChannel;
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
		boolean debug=false;
		//Make sure the parent directory of the file exists. If it doesn't this method will create it
		try {
			Files.createDirectories(path.getParent());
		} catch (IOException e) {
			e.printStackTrace();
		}
		//Write the content of buffer to the file
		try (WritableByteChannel channel = Files.newByteChannel(path, EnumSet.of(CREATE, WRITE))) {
			buf.flip();
			if (debug) {
				Buffers.bufferStatus(buf, "Buffer before writing");
			}
			channel.write(buf);
			if (debug) {
				System.out.println("File written is " + ((FileChannel) channel).size() + " bytes.");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void appendToWritableByteChannel(ByteBuffer buf, Path path) {
		boolean debug = false;
		//Make sure the parent directory of the file exists. If it doesn't this method will create it
		try {
			Files.createDirectories(path.getParent());
		} catch (IOException e) {
			e.printStackTrace();
		}
		//Write the content of buffer to the file
		try (WritableByteChannel channel = Files.newByteChannel(path, EnumSet.of(CREATE, APPEND))) {
			buf.flip();
			if (debug) {
				Buffers.bufferStatus(buf, "Buffer before writing");
			}
			channel.write(buf);
			if (debug) {
				System.out.println("File written is " + ((FileChannel) channel).size() + " bytes.");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void writeToSeekableByteChannel(ByteBuffer buf, long position, Path path) {
		//NOTE: channel must be open  to WRITE to be able to write to specific position
		//If APPEND is open, the file will be written to the end
		//Setting the channel's position is not recommended when connected to an entity, typically a file, that is opened with the APPEND option. When opened for append, the position is first advanced to the end before writing.
		boolean debug = false;
		//Make sure the parent directory of the file exists. If it doesn't this method will create it
		try {
			Files.createDirectories(path.getParent());
		} catch (IOException e) {
			e.printStackTrace();
		}
		//Write the content of buffer to the file
		try (SeekableByteChannel channel = Files.newByteChannel(path, EnumSet.of(CREATE, WRITE))) {
			buf.flip();
			if (debug) {
				Buffers.bufferStatus(buf, "Buffer before writing");
			}
			if (debug) {
				System.out.println("Position parameter: " + position);
				System.out.println("Before channel position set: " + channel.position());
			}
			channel.position(position);
			if (debug) {
				System.out.println("After channel position set: " + channel.position());
			}
			channel.write(buf);
			if (debug) {
				System.out.println("After write file position: " + channel.position());
				Buffers.bufferStatus(buf, "After write buffer position:");
				System.out.println("File written is " + ((FileChannel) channel).size() + " bytes.");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Write to a file from a ByteBuffer[] array
	 *
	 * @param buf the ByteBuffer array
	 * @param path the path to the file to be written
	 * @param needFlip true if buffer needs to be flip before writing, false if not
	 */
	public static void writeToGatheringByteChannel(ByteBuffer[] buf, Path path, boolean needFlip) {
		boolean debug = false;
		//Make sure the parent directory of the file exists. If it doesn't this method will create it
		try {
			Files.createDirectories(path.getParent());
		} catch (IOException e) {
			e.printStackTrace();
		}
		//Must be casted to FileChannel
		try (GatheringByteChannel channel = (FileChannel) Files.newByteChannel(path, EnumSet.of(CREATE, WRITE))) {
			for (ByteBuffer b : buf) {
				if (debug) {
					Buffers.bufferStatus(b, "Buffer before flip");
				}
				//If the buffer was created with .wrap it doesn't need to be flipped
				if (needFlip) {
					b.flip();
				}
				if (debug) {
					Buffers.bufferStatus(b, "Buffer afer flip before write");
				}
				channel.write(b);
				if (debug) {
					Buffers.bufferStatus(b, "Buffer after write");
				}
			}
			if (debug) {
				System.out.println("File written is " + ((FileChannel) channel).size() + " bytes.");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
