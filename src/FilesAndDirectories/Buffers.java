package FilesAndDirectories;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.IntBuffer;
import java.nio.LongBuffer;
import java.nio.charset.Charset;

/**
 *
 * @author Andras Olah (olahandras78@gmail.com)
 */
public class Buffers {

	static ByteBuffer createByteBuffer(int capacity) {
		ByteBuffer buffer = ByteBuffer.allocate(capacity);
		return buffer;
	}

	static boolean newPositionValidation(int newPosition, int newLimit) {
		if (newPosition >= 0 && newLimit > newPosition) {
			return true;
		} else {
			System.err.printf("Illegal position[%d]:limit[%d] settings.", newPosition, newLimit);
			return false;
		}
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

	static void putIntToIntBuffer(IntBuffer buff, int input) {
		buff.put(input);
	}

	static String getStringFromByteBuffer(ByteBuffer buff) {
		return Charset.forName("UTF-8").decode(buff).toString();
		//Source:
		//http://stackoverflow.com/questions/17354891/java-bytebuffer-to-string
	}

	/**
	 * This metod puts int to the viewbuffer and increments with 4 bytes the parent bytebuffer's position
	 *
	 */
	static void putIntToIntBuffer(IntBuffer ib, ByteBuffer bb, int input) {
		ib.put(input);
		bb.position(bb.position() + 4);
	}

	static int getIntFromIntBuffer(IntBuffer buff) {
		return buff.get();
	}

	static int getIntFromIntBuffer(IntBuffer buff, int position) {
		return buff.get(position);
	}

	static void setPosition(Buffer buff, int newPosition) {
		if (newPositionValidation(newPosition, buff.limit())) {
			buff.position(newPosition);
		}
	}

	static void bufferStatus(Buffer buff, String buffName) {
		int position = buff.position();
		int limit = buff.limit();
		int remaining = buff.remaining();
		int capacity = buff.capacity();
		boolean hasArray = buff.hasArray();
		System.out.printf("**[%s] trace:%n  pos:%d, lim:%d, rem:%d, cap:%d array:%b%n", buffName, position, limit, remaining, capacity, hasArray);
	}
}
