package FilesAndDirectories;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.IntBuffer;
import java.nio.charset.Charset;

/**
 * This class is to TEST buffers
 *
 * @author Andras Olah (olahandras78@gmail.com)
 */
public class BuffersTD {

	public static void main(String[] args) {
//<editor-fold desc="Initializing Test data">
		Charset charset;
		charset = Charset.defaultCharset();
//		charset = Charset.forName("Windows-1250");
//		charset = Charset.forName("ISO8859_1");
		String testString = "AAaa 11 Ez egy sor a bufferbe.";
		String[] testStringArray = {"Ez az array 1 sora", "Ez az array második sora", "Ez az array 3. sora"};
		int testInt = 123;
		double testDouble = 456.789;
		char testChr = 'A';
		//</editor-fold>
		int testCase = 1;
		System.out.println("Test case: " + testCase);
		switch (testCase) {
			case 0:
//<editor-fold desc="Put one int to an intViewBuffer and increment parent byteBuffer accordingly">
				System.out.println("Put one int to a IntBuffer viewBuffer, and increment the bytebuffer accordingly");
				System.out.println("Pass condition: the ByteBuffer position should be at 4");
				//
				ByteBuffer bbTc0 = Buffers.createByteBuffer(1024);
				IntBuffer bbIntTc0 = Buffers.createViewBufferForInt(bbTc0);
				int intTc0 = 100;
				bbTc0.rewind();
				Buffers.putIntToIntBuffer(bbIntTc0, bbTc0, intTc0);
				System.out.println("Int buffer's content: " + Buffers.getIntFromIntBuffer(bbIntTc0, 0));
				if (bbTc0.position() == 4) {
					Buffers.bufferStatus(bbTc0, "ByteBuffer");
					System.out.println("Success!");
				} else {
					Buffers.bufferStatus(bbTc0, "ByteBuffer");
					System.out.println("Failure!.");
				}

//</editor-fold>
				break;

			case 1:
//<editor-fold desc="Test buffer position after put">
				ByteBuffer bbTc1 = ByteBuffer.allocate(1030); //1 kbyte
				System.out.println("Test case: 1.1");
				System.out.println("Adding a STRING with getBytes");
				System.out.printf("String:[%s]%nStr.length:%d%n", testString, testString.length());
				System.out.println("Pass condition: buffer position equals length of test string");
				Buffers.putStringToByteBuffer(bbTc1, testString);
				Buffers.bufferStatus(bbTc1, "ByteBuffer");
				if (bbTc1.position() == testString.length()) {
					System.out.println("Success.");
				} else {
					System.out.println("Fail!");
				}
				System.out.println("\nTest case: 1.2");
				System.out.println("Create a SLICE buffer from current position");
				System.out.println("Pass condition:\n - Slice capacity == remaining of byte buffer\n - Slice content = byte content");
				bbTc1.position(0).limit(99);
				ByteBuffer bbSliceTc1 = bbTc1.slice();
				Buffers.bufferStatus(bbTc1, "ByteBuffer");
				Buffers.bufferStatus(bbSliceTc1, "SliceBuffer");
				if (bbSliceTc1.capacity() == bbTc1.remaining()) {
					bbTc1.position(0).limit(30);
					Buffers.bufferStatus(bbTc1, "ByteBuffer");
					System.out.println("ByteB: " + Buffers.getStringFromByteBuffer(bbTc1));
					bbTc1.position(0).limit(30);
//					System.out.println("Result lenght: " + Buffers.getStringFromByteBuffer(bbTc1).length());
					bbSliceTc1.limit(30);
					System.out.println("SliceB: " + Buffers.getStringFromByteBuffer(bbSliceTc1));
					System.out.println("Success!");
				} else {
					System.out.println("Failure!");
				}
				System.exit(0);
				System.out.println("\nCreate a DUPLICATE from bb");
				ByteBuffer bbDuplicate = bbTc1.duplicate();
				System.out.println("bbDuplicate:\t" + bufTrace(bbDuplicate));
				System.out.println("bb:\t\t" + bufTrace(bbTc1));

				System.out.println("\nAdding a STRING as CHAR ARRAY");
				System.out.print("  [");
				int counter = 0;
				for (char c : testString.toCharArray()) {
					counter++;
					System.out.print(c);
					bbTc1.putChar(c);
				}
				System.out.println("]\nChars added:\t" + counter);
				System.out.println("bb:\t\t" + bufTrace(bbTc1));

				System.out.println("Adding a STRING ARRAY with getBytes");
				counter = 0;
				for (String s : testStringArray) {
					counter += s.length(); //Add to the counter the lenght of the current string 
					System.out.printf("  [%s]%n", s);
					bbTc1.put(s.getBytes());
				}
				System.out.println("Total lenght: " + counter);
				System.out.println("bb:\t\t" + bufTrace(bbTc1));

				System.out.println("\nAdding INT bb.putInt (+4 bytes)");
				System.out.printf("  Int: %d%n", testInt);
				bbTc1.putInt(testInt);
				System.out.println("bb:\t\t" + bufTrace(bbTc1));
				System.out.println("\nAdding DOUBLE to the buffer (+8 bytes)");
				System.out.printf("  Double:%f%n", testDouble);
				bbTc1.putDouble(testDouble);
				System.out.println("bb:\t\t" + bufTrace(bbTc1));
				System.out.println("\nAdding CHAR to the buffer (+2 bytes)");
				System.out.printf("  Char:'%c'%n", testChr);
				bbTc1.putChar(testChr);
				System.out.println("bb:\t\t" + bufTrace(bbTc1));

				System.out.println("\nCreate a INT ViewBuffer (bb remaining/4)");
				System.out.printf("bb.remaining[%d]/4 =%d%n", bbTc1.remaining(), bbTc1.remaining() / 4);
				IntBuffer intB = bbTc1.asIntBuffer();
				System.out.println("bb:\t\t" + bufTrace(bbTc1));
				System.out.println("intB:\t\t" + bufTrace(intB));
				System.out.printf("Put one int value [%d] to IntBuffer:%n", testInt);
				intB.put(testInt);
				System.out.println("bb:\t\t" + bufTrace(bbTc1));
				System.out.println("intB:\t\t" + bufTrace(intB));
				System.out.println("Update bb postition with 4 because of new int (4 bytes)");
				bbTc1.position(bbTc1.position() + 4);
				System.out.println("bb:\t\t" + bufTrace(bbTc1));
//</editor-fold>
				break;
			case 2:
//<editor-fold desc="Reading from Byte buffers">
				System.out.println("Reading from Byte Buffers");
				System.out.println("Input string:");
				System.out.printf("\t[%s]%n\tlength=%d%n", testString, testString.length());
				System.out.println("\nInitialize bbw (byte buffer with WRAP)");
				ByteBuffer bbw = ByteBuffer.wrap(testString.getBytes(charset));
				System.out.println("bbw:\t\t" + bufTrace(bbw));
				System.out.println("\nRead the content with getChar");
				String output = null;
				char readAsChar;
				while (bbw.hasRemaining()) {
//					System.out.println("Position before read: " + bbw.position());
					readAsChar = bbw.getChar();
//					System.out.println("Read value: " + readAsChar);
					output += Character.toString(readAsChar);
				}
				System.out.printf("  [%s]%n", output);
				System.out.println("bbw:\t\t" + bufTrace(bbw));

				System.out.println("\nFlip the buffer and read again with getBytes");
				bbw.flip();
				byte[] bytesRead = new byte[bbw.capacity()];
				bbw.get(bytesRead);
				System.out.println("Reading as byte values:");
				for (byte b : bytesRead) {
					System.out.printf("[%d]-", b);
				}
				System.out.println("\nConverting to string");
				output = new String(bytesRead, charset);
				System.out.printf("  [%s]", output);
				System.out.println("\nbbw:\t\t" + bufTrace(bbw));

				System.out.println("\nCreate a CharBuffer for bbw");
				bbw.flip();
				CharBuffer cbuf = bbw.asCharBuffer();
				System.out.println("cbuf:\t\t" + bufTrace(cbuf));

				char[] charRead = new char[cbuf.capacity()];
				System.out.println("Reading from char buffer");
				cbuf.get(charRead);
				for (char c : charRead) {
					System.out.printf("[%c]", c);
				}
				System.out.println("");
				System.out.println("\nRead from underlying byte buffer with getChar");
				System.out.println("bbw:\t\t" + bufTrace(bbw));
				while (bbw.hasRemaining()) {
					System.out.printf("[%c]", bbw.getChar());
				}
				System.out.println("\nbbw:\t\t" + bufTrace(bbw));
//</editor-fold>
				break;
			case 3:
//<editor-fold desc="Put data from mixed type buffers">
				System.out.println("Put data from CHAR and INT ViewBuffer to a byte buffer");
				System.out.println("Allocate 1024 bytes to bytebuffer bb3");
				ByteBuffer bb3 = ByteBuffer.allocate(1024);
				System.out.println("bb3:\t\t" + bufTrace(bb3));
				IntBuffer bb3Int = bb3.asIntBuffer();
				CharBuffer bb3Char = bb3.asCharBuffer();
				System.out.println("Create Int buffer capacity: " + (bb3.capacity() / 4));
				System.out.println("bb3Int:\t\t" + bufTrace(bb3Int));
				System.out.println("Create Char buffer capacity: " + (bb3.capacity() / 2));
				System.out.println("bb3Char:\t" + bufTrace(bb3Char));

				System.out.printf("\nPut data [%d] to Int buffer%n", testInt);
				bb3Int.put(testInt);
				System.out.println("bb3:\t\t" + bufTrace(bb3));
				System.out.println("bb3Int:\t\t" + bufTrace(bb3Int));

				System.out.printf("\nPut data [%d] to ByteBuffer with .putIn%n", testInt);
				bb3.position(4);
				bb3.putInt(testInt);
				System.out.println("bb3:\t\t" + bufTrace(bb3));
				System.out.println("bb3Int:\t\t" + bufTrace(bb3Int));

				System.out.println("\nRead 8 bytes from ByteBuffer as bytes");
				byte[] bytesRead3 = new byte[8]; //going to read only four bytes
				bb3.position(0);
				System.out.println("bb3:\t\t" + bufTrace(bb3));
				bb3.get(bytesRead3, 0, 8); //OFFSET not INDEX! Starts from current position!
				System.out.print("Read bytes: ");
				for (byte b : bytesRead3) {
					System.out.printf("[%s]-", b);
				}
				System.out.println("");
				System.out.println("bb3:\t\t" + bufTrace(bb3));

				System.out.println("\nRead from IntBuffer as Int");
				System.out.println("bb3Int position needs to reset");
				bb3Int.position(0);
				System.out.println("bb3Int:\t\t" + bufTrace(bb3Int));
				System.out.printf("Read value: [%d]%n", bb3Int.get());
				System.out.println("bb3Int:\t\t" + bufTrace(bb3Int));
				//</editor-fold>
				break;
			case 4:
//<editor-fold desc="Chaining methods">
				ByteBuffer bb4 = ByteBuffer.allocate(1024);
				System.out.println("bb4:\t\t" + bufTrace(bb4));
				System.out.println("Value of command\n\tbb4.asCharBuffer().put(\"ABCD\").position();");
				System.out.println("\t" + bb4.asCharBuffer().put("ABCD").position());
				System.out.println("bb4:\t\t" + bufTrace(bb4));
				CharBuffer bb4C = bb4.asCharBuffer();
				bb4C.put("ABCD").position();
				System.out.println("bb4C:\t\t" + bufTrace(bb4C));
				System.out.println("\n Put Double (8 bytes) to bytebuffer\n and update bytebuffer's position\n with one command chaining");
				System.out.println("Double to put: " + testDouble);
				bb4.position(bb4.position() + 8 * bb4.asDoubleBuffer().put(testDouble).position());
				System.out.println("ByteBuffer after put");
				System.out.println("bb4:\t\t" + bufTrace(bb4));
				bb4.rewind();
				byte[] bc4test = new byte[8];
				bb4.get(bc4test);
				System.out.println("Reading from byte buffer as bytes");
				for (byte b : bc4test) {
					System.out.printf("[%d]", b);
				}
				System.out.println("");

				System.out.println("Reading from byte buffer as double");
				bb4.rewind();
				System.out.println("Read number: " + bb4.getDouble());
//</editor-fold>
				break;
			case 5:
//<editor-fold desc="Simple put / get">
				ByteBuffer bb5 = ByteBuffer.allocate(1024);
				System.out.println("bb5:\t\t" + bufTrace(bb5));
				System.out.println("Put char 'A' to bb with putChar method");
				bb5.putChar('A').rewind();
				System.out.println("Char read: " + bb5.getChar());
				bb5.rewind();
				System.out.println("Byte read: " + bb5.get());
				System.out.println("Byte read: " + bb5.get());
				bb5.rewind();
				System.out.println("Put int 123456 to bb with putInt method");
				bb5.putInt(123456).rewind();
				System.out.println("Int read: " + bb5.getInt());
				bb5.rewind();
				System.out.println("Byte read: " + bb5.get());
				System.out.println("Byte read: " + bb5.get());
				System.out.println("Byte read: " + bb5.get());
				System.out.println("Byte read: " + bb5.get());
				System.out.println("Byte read: " + bb5.get());
				System.out.println("Byte read: " + bb5.get());
				System.out.println("Byte read: " + bb5.get());
				System.out.println("bb5 position: " + bb5.position());
				bb5.position(0).limit(10);
				byte[] backingArray = bb5.array();
				for (byte b : backingArray) {
					System.out.printf("[%d]-", b);
				}
//</editor-fold>
				break;
		}//end Switch

	} //End Main

	public static String bufTrace(Buffer buf) { //Don't need to create method for all buffer types
		int p = buf.position();
		int c = buf.capacity();
		int l = buf.limit();
		int r = buf.remaining();
		String className = buf.getClass().getSimpleName();
		String returnStr = String.format("Pos:%02d Rem:%d Lim:%d Cap:%d", p, r, l, c);
		return returnStr;
	}
} //end class BuffersTD
