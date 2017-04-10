package FilesAndDirectories.Practice;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import static java.nio.file.StandardOpenOption.READ;
import java.util.ArrayList;
import java.util.Formatter;

/**
 *
 * @author Andras Olah (olahandras78@gmail.com)
 */
public class ReadMixedFileWithCompact {

	public Path filename;
	public FileChannel readChannel;
	public int byteBufferCapacity = 100;

	public boolean checkFileExists() {
		return Files.exists(filename);
	}

	public boolean openFile() throws IOException {
		if (!this.checkFileExists()) {
			return false;
		}
		this.readChannel = (FileChannel) Files.newByteChannel(this.filename, READ);
		return true;
	}

	public ArrayList readFile() {
		ArrayList resultArray = new ArrayList();
		try {
			this.openFile();
			ByteBuffer bbuff = ByteBuffer.allocate(byteBufferCapacity);
			bbuff.flip();
			/*
		File structure
		[double][string][long]
		double	= the length of the following string (stored as Double to make things even more complicated :D)
		string	= the prime values as string, example prime = 23
		long 	= the prime value as long		
			 */
			double stringLength = 0;
			long longValue = 0;
			readNextBatch:
			while (true) {
				if (readChannel.read(bbuff.compact()) == -1) { //always use the maximuma available bbuff capacity
					break;
				}
				bbuff.flip();
				while (true) {
					//Check if there is enough data in the buffer to read Double for the string length
					if (bbuff.remaining() < 8) { //if there are not enough data for an int, continue to next READ
						continue readNextBatch;
					}
					stringLength = bbuff.getDouble();
					//Check if there is enough data in the buffer to build the string
					if (bbuff.remaining() < stringLength * 2) {
						//At this point there is not enough data to read the string. However, the Double was already read
						//The next read operation should re-read the previously read Double again, so
						//the buffer position needs to be rewinded with 8 bytes
						bbuff.position(bbuff.position() - 8); //rewind the buffer so stringLenght can be read again
						continue readNextBatch;
					}
					byte[] stringChars = new byte[(int) stringLength * 2];
					bbuff.get(stringChars);
					//Check if there is enough data in the buffer to get the long value
					if (bbuff.remaining() < 8) {
						//rewind DOUBLE and String lenght position so it can be read again
						bbuff.position(bbuff.position() - (8 + ((int) stringLength * 2)));
						continue readNextBatch;
					}
					longValue = bbuff.getLong();
					//At this point we have all 3 values
					Formatter formattedResult = new Formatter();
					resultArray.add(formattedResult.format("Sting length:[%03d] String:[%s] Long:[%d]", (int) stringLength, ByteBuffer.wrap(stringChars).asCharBuffer(), longValue));
				}//end while - continue to process the buffer
			}//end while- continue to read the file

		} catch (IOException e) {
			e.printStackTrace();
		}
		return resultArray;
	}
}
