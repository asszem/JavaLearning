package FilesAndDirectories;

import java.io.IOException;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.IntBuffer;
import java.nio.LongBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import static java.nio.channels.FileChannel.MapMode.READ_WRITE;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import static java.nio.file.StandardOpenOption.*;
import java.util.Formatter;

/**
 *
 * @author Andras Olah (olahandras78@gmail.com)
 */
public class Buffers {

	static ByteBuffer createByteBuffer(int capacity) {
		ByteBuffer buffer = ByteBuffer.allocate(capacity);
		return buffer;
	}

	static public MappedByteBuffer createMappedByteBuffer(Path sourceFile) {
		if (!Files.exists(sourceFile)) {
			throw new IllegalArgumentException("Input file does not exists!");
		}
		try {
			FileChannel fileChannel = (FileChannel) Files.newByteChannel(sourceFile, READ, WRITE);
			long fileSize = fileChannel.size();
			//Open the buffer for ReadWrite, from position0, up until last position, and immedieately load it
			MappedByteBuffer mappedByteBuffer = fileChannel.map(READ_WRITE, 0L, fileSize).load();
			fileChannel.close(); //the mappedByteBuffer should have all the data
			return mappedByteBuffer;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	static boolean newPositionValidation(int newPosition, int newLimit) {
		if (newPosition >= 0 && newLimit > newPosition) {
			return true;
		} else {
			System.err.printf("Illegal position[%d]:limit[%d] settings.", newPosition, newLimit);
			return false;
		}
	}

	static void putDoubleToByteBufferThroughChainedIntViewBuffer(ByteBuffer buff, double doubleToPut) {
		buff.asDoubleBuffer().put(doubleToPut);
	}

	static IntBuffer createViewBufferForInt(ByteBuffer buff) {
		IntBuffer intView = buff.asIntBuffer();
		return intView;
	}

	static boolean wrapArrayToBuffer(String input) {
		byte[] byteArray = input.getBytes();
		ByteBuffer byteBuffer = ByteBuffer.wrap(byteArray);
		long[] numbers = {1L, 1L, 2L, 3L, 5L, 8L, 13L, 21L, 34L, 55L, 89L};
		LongBuffer numBuf = LongBuffer.wrap(numbers);
		return true;
	}

	static void putStringToByteBuffer(ByteBuffer buff, String input) {
		buff.put(input.getBytes());
	}

	static void putStringToCharBufferWithFormatter(ByteBuffer buf, CharBuffer cbuf, String input) {
		boolean debug = false;
		if (debug){
			bufferStatus(buf, "ByteBuffer before using formatter on CharBuff");
			bufferStatus(cbuf, "CharBuffer before formatting");
			System.out.println("CharBuf length: " +cbuf.length());
		}
		Formatter formatter = new Formatter(cbuf);
		formatter.format("String [%s] used.", input);
		//Flip charbuffer: sets limit=pos, pos=0; - narrow it to the content only
		cbuf.flip();
		//Increment bytebuffer's LIMIT with length of charbuffer
		buf.limit(cbuf.length()*2);
		if (debug) {
			bufferStatus(buf, "ByteBuffer after incrementing");
			bufferStatus(cbuf, "CharBuffer after formatting");
		}

	}

	static void putIntToIntBuffer(IntBuffer buff, int input) {
		buff.put(input);
	}

	static String getStringFromByteBufferWithDecode(ByteBuffer buff) {
		return Charset.forName("UTF-8").decode(buff).toString();
		//Source:
		//http://stackoverflow.com/questions/17354891/java-bytebuffer-to-string
	}

	static String getStringFromByteBufferWithGetBytes(ByteBuffer buff) {
		byte[] bytesRead = new byte[buff.capacity()];
		if (false) {
			System.out.println("Byte array length: " + bytesRead.length);
		}
		buff.get(bytesRead);
		//for debug
		if (false) {
			for (byte b : bytesRead) {
				System.out.printf("[%d]-", b);
			}
		}
//		Charset utf16 = Charset.forName("UTF-16");
		Charset utf8 = Charset.forName("UTF-8");
		String output = new String(bytesRead, Charset.defaultCharset());
		return output;
	}

	static String getStringFromByteBufferWithGetChar(ByteBuffer buff) {
		String output = null;
		char result;
		while (buff.hasRemaining()) {
			result = buff.getChar();
			if (true) {
				System.out.printf("%s", result);
			}
			output += result;
		}
		return output;
	}

	static void putIntToIntBuffer(IntBuffer ib, ByteBuffer bb, int input) {
		ib.put(input);
		bb.position(bb.position() + 4);
	}

	static int getIntFromIntBuffer(IntBuffer buff) {
		return buff.get();
	}

	static byte[] getByteArrayFromByteBuffer(ByteBuffer buff) {
		//the array must be the exact size of data to be read: limit-position
		byte[] resultArray = new byte[buff.limit() - buff.position()];
		buff.get(resultArray);
		return resultArray;
	}

	static int getIntFromIntBuffer(IntBuffer buff, int position) {
		return buff.get(position);
	}

	static void setPosition(Buffer buff, int newPosition) {
		if (newPositionValidation(newPosition, buff.limit())) {
			buff.position(newPosition);
		}
	}

	public static void bufferStatus(Buffer buff, String buffName) {
		int position = buff.position();
		int limit = buff.limit();
		int remaining = buff.remaining();
		int capacity = buff.capacity();
		boolean hasArray = buff.hasArray();
		System.out.printf("**[%s]%n  pos:%d, lim:%d, rem:%d, cap:%d array:%b%n", buffName, position, limit, remaining, capacity, hasArray);
	}
}
