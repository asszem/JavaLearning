package FilesAndDirectories;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.IntBuffer;
import java.nio.MappedByteBuffer;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.nio.file.Paths;

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
		int testCase = 9;
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
//<editor-fold desc="Test buffer status after various put operations">
				ByteBuffer bbTc1 = ByteBuffer.allocate(1030); //1 kbyte
				System.out.println("Test case: 1.1");
				System.out.println("Adding a STRING with .put(input.getBytes)");
				System.out.printf("String:[%s]%nTest string length:%d%n", testString, testString.length());
				System.out.println("Pass condition:\nBuffer position equals length of test string");
				Buffers.putStringToByteBuffer(bbTc1, testString);
				Buffers.bufferStatus(bbTc1, "ByteBuffer after put");
				if (bbTc1.position() == testString.length()) {
					System.out.println("Success.");
				} else {
					System.out.println("Fail!");
				}
				System.out.println("\nTest case: 1.2");
				System.out.println("Create a SLICE buffer from current position up to limit 99");
				System.out.println("Pass condition:\nSlice capacity == remaining of byte buffer");
				bbTc1.limit(99);
				Buffers.bufferStatus(bbTc1, "ByteBuffer before slice");
				ByteBuffer bbSliceTc1 = bbTc1.slice();
				Buffers.bufferStatus(bbSliceTc1, "SliceBuffer");
				if (bbSliceTc1.capacity() == bbTc1.remaining()) {
					System.out.println("Success.");
				} else {
					System.out.println("Failure!");
				}
				System.out.println("\nTest case: 1.3");
				System.out.println("Create a DUPLICATE from original byte buffer");
				Buffers.bufferStatus(bbTc1, "original byte buffer ");
				ByteBuffer bbDuplicateTc1 = bbTc1.duplicate();
				Buffers.bufferStatus(bbDuplicateTc1, "Duplicate buffer status");
				System.out.println("\nTest case: 1.4");
				bbTc1.limit(bbTc1.capacity());
				System.out.println("Adding a STRING as CHAR ARRAY with putChar()");
				Buffers.bufferStatus(bbTc1, "byte buffer BEFORE chars added");
				System.out.print("Char array:\n[");
				int counter = 0;
				for (char c : testString.toCharArray()) {
					counter++;
					System.out.print(c);
					bbTc1.putChar(c);
				}
				System.out.printf("]%nNumber of chars:%d%n", counter);
				Buffers.bufferStatus(bbTc1, "byte buffer after chars added");

				System.out.println("\nTest case: 1.5");
				bbTc1.rewind().limit(bbTc1.capacity());
				System.out.println("Adding a STRING ARRAY with getBytes");
				counter = 0;
				Buffers.bufferStatus(bbTc1, "byte buffer BEFORE chars added");
				for (String s : testStringArray) {
					counter += s.length(); //Add to the counter the lenght of the current string 
//					System.out.printf("  [%s]%n", s);
					bbTc1.put(s.getBytes());
					bbTc1.put(s.getBytes());
				}
				Buffers.bufferStatus(bbTc1, "byte buffer AFTER chars added");

				System.out.println("\nTest case: 1.6");
				System.out.println("Adding INT bb.putInt (+4 bytes)");
				Buffers.bufferStatus(bbTc1, "byte buffer BEFORE int added");
				bbTc1.putInt(testInt);
				Buffers.bufferStatus(bbTc1, "byte buffer AFTER int added");
				System.out.println("\nTest case: 1.7");
				System.out.println("Adding DOUBLE to the buffer (+8 bytes)");
				Buffers.bufferStatus(bbTc1, "byte buffer BEFORE double added");
				bbTc1.putDouble(testDouble);
				Buffers.bufferStatus(bbTc1, "byte buffer AFTER double added");
				System.out.println("\nTest case: 1.8");
				System.out.println("Adding CHAR to the buffer (+2 bytes)");
				Buffers.bufferStatus(bbTc1, "byte buffer BEFORE double added");
				bbTc1.putChar(testChr);
				Buffers.bufferStatus(bbTc1, "byte buffer AFTER char added");

//</editor-fold>
				break;
			case 2:
//<editor-fold desc="Reading String from Byte buffers with getBytes, Decode, GetChar">
				System.out.println("Reading a wrapped string from Byte Buffers");
				System.out.println("Input string:");
				System.out.printf("[%s]%nlength=%d%n", testString, testString.length());
				ByteBuffer bbTc2 = ByteBuffer.wrap(testString.getBytes(charset));

				System.out.println("\nTest case 2.1");
				System.out.println("GET with getStringFromByteBufferWithGetBytes");
				Buffers.bufferStatus(bbTc2, "bbTc2 before get");
				System.out.printf("Get results:%n[%s]%n", Buffers.getStringFromByteBufferWithGetBytes(bbTc2));
				Buffers.bufferStatus(bbTc2, "bbTc2 after get");

				System.out.println("\nTest case 2.2");
				System.out.println("GET with getStringFromByteBufferWithDecode");
				bbTc2.flip();
				Buffers.bufferStatus(bbTc2, "bbTc2 before get");
				System.out.printf("Get results:%n[%s]%n", Buffers.getStringFromByteBufferWithDecode(bbTc2));
				Buffers.bufferStatus(bbTc2, "bbTc2 after get");

				System.out.println("\nTest case 2.3");
				System.out.println("GET with getStringFromByteBufferWithGetChar");
				bbTc2.flip();
				Buffers.bufferStatus(bbTc2, "bbTc2 before get");
				System.out.printf("Get results:%n[%s]%n", Buffers.getStringFromByteBufferWithGetChar(bbTc2));
				Buffers.bufferStatus(bbTc2, "bbTc2 after get");

//</editor-fold>
				break;
			case 3:
//<editor-fold desc="Reading INT from ByteBuffer and Int ViewBuffers">
				System.out.println("ReadingINT from ViewBuffers");
				System.out.println("\nTest case 3.1 - initialization");
				ByteBuffer bbTc3 = ByteBuffer.allocate(1024);
				IntBuffer bb3Int = bbTc3.asIntBuffer();
//				CharBuffer bb3Char = bbTc3.asCharBuffer();
				Buffers.bufferStatus(bbTc3, "1024 bytes allocated for byte buffer");
				Buffers.bufferStatus(bb3Int, "Int Buffer created, capacity should be 256");
//				Buffers.bufferStatus(bb3Char, "Char Buffer created, capacity should be 512");

				System.out.println("\nTest case 3.2");
				System.out.printf("Put test data [%d] to IntBuffer with put%n", testInt);
				System.out.println("With updating original ByteBuffer's position");
				Buffers.putIntToIntBuffer(bb3Int, bbTc3, testInt);
				Buffers.bufferStatus(bbTc3, "Byte buf after put");
				Buffers.bufferStatus(bb3Int, "Int buf after put");
				System.out.println("Without updating original ByteBuffer's position");
				bb3Int.put(testInt);
				Buffers.bufferStatus(bbTc3, "Byte buf after put");
				Buffers.bufferStatus(bb3Int, "Int buf after put");

				System.out.println("\nTest case 3.3");
				System.out.printf("Put data [%d] to ByteBuffer with .putIn%n", testInt);
				Buffers.bufferStatus(bbTc3, "Byte buf before put");
				bbTc3.putInt(testInt);
				Buffers.bufferStatus(bbTc3, "Byte buf after put");

				System.out.println("\nTest case 3.4.1");
				System.out.println("Read from Byte Buffer to a byte[] array with .get");
				bbTc3.position(0);
				Buffers.bufferStatus(bbTc3, "Byte buf before read");
				byte[] bytesRead3 = new byte[8]; //going to read only four bytes
				bbTc3.get(bytesRead3, 0, 8); //OFFSET not INDEX! Starts from current position!
				for (byte b : bytesRead3) {
					System.out.printf("[%s]", b);
				}
				System.out.println("");
				Buffers.bufferStatus(bbTc3, "Byte buf after read");

				System.out.println("\nTest case 3.4.2");
				System.out.println("Same operation with method in Buffers class");
				bbTc3.position(0).limit(8);
				byte[] tc3 = Buffers.getByteArrayFromByteBuffer(bbTc3);
				for (byte b : tc3) {
					System.out.printf("[%s]", b);
				}
				System.out.println("");
				Buffers.bufferStatus(bbTc3, "Byte buf after read");

				System.out.println("\nTest case 3.5");
				System.out.println("Read from IntBuffer with .get");
				bb3Int.position(0);
				System.out.printf("Read value: [%d]%n", bb3Int.get());
				Buffers.bufferStatus(bb3Int, "Int buf after read");
				System.out.printf("Read value: [%d]%n", bb3Int.get());
				Buffers.bufferStatus(bb3Int, "Int buf after read");
				System.out.printf("Read value: [%d]%n", bb3Int.get());
				Buffers.bufferStatus(bb3Int, "Int buf after read");
				//</editor-fold>
				break;
			case 4:
//<editor-fold desc="Chaining methods">
				ByteBuffer bbTc4 = ByteBuffer.allocate(1024);
				System.out.println("Test case 4.1");
				System.out.println("Chaining buffer creation and .position(), returns the .position()");
				System.out.println("Command:\n\bbTc4.asCharBuffer().put(\"ABCD\").position();");
				System.out.println(bbTc4.asCharBuffer().put("ABCD").position());
				System.out.println("Command:\n\bbTc4.asCharBuffer().put(\"AB\").position();");
				System.out.println(bbTc4.asCharBuffer().put("AB").position());

				System.out.println("\nTest case 4.2");
				System.out.println("Put a Double (8 bytes) to bytebuffer and update bytebuffer's position\n with one command chaining");
				Buffers.bufferStatus(bbTc4, "bbTc4 before");
				int Tc42Int = bbTc4.position(bbTc4.position() + 8 * bbTc4.asDoubleBuffer().put(testDouble).position()).position();
				System.out.println("Position chain result: " + Tc42Int);
				Buffers.bufferStatus(bbTc4, "bbTc4 after");
				System.exit(0);
				bbTc4.rewind();
				byte[] bc4test = new byte[8];
				bbTc4.get(bc4test);
				System.out.println("Reading from byte buffer as bytes");
				for (byte b : bc4test) {
					System.out.printf("[%d]", b);
				}
				System.out.println("");

				System.out.println("Reading from byte buffer as double");
				bbTc4.rewind();
				System.out.println("Read number: " + bbTc4.getDouble());
//</editor-fold>
				break;
			case 5:
//<editor-fold desc="Put char/int to byteuffer and get with various methods">
				ByteBuffer bbTc5 = ByteBuffer.allocate(1024);
				System.out.println("Test case 5.1");
				System.out.println("Put char 'A' to bb with putChar method");
				bbTc5.putChar('A').rewind();
				System.out.println("Char read: " + bbTc5.getChar());
				bbTc5.rewind();
				System.out.println("Byte read: " + bbTc5.get());
				System.out.println("Byte read: " + bbTc5.get());
				bbTc5.rewind();
				System.out.println("\nTest case 5.2");
				System.out.println("Put int 123456 to bb with putInt method");
				bbTc5.putInt(123456).rewind();
				System.out.println("Int read: " + bbTc5.getInt());
				bbTc5.rewind();
				System.out.println("Byte read: " + bbTc5.get());
				System.out.println("Byte read: " + bbTc5.get());
				System.out.println("Byte read: " + bbTc5.get());
				System.out.println("Byte read: " + bbTc5.get());
				System.out.println("Byte read: " + bbTc5.get());
				System.out.println("Byte read: " + bbTc5.get());
				System.out.println("Byte read: " + bbTc5.get());
				System.out.println("bb5 position: " + bbTc5.position());
				bbTc5.position(0).limit(20);
				Buffers.bufferStatus(bbTc5, "bbTc5");
				bbTc5.putInt(544321);
				Buffers.bufferStatus(bbTc5, "bbTc5 after putInt");
				bbTc5.position(0).limit(20);
				Buffers.bufferStatus(bbTc5, "bbTc5 after pos0 lim 20");
				System.out.println("Byte read: " + bbTc5.getInt());
				bbTc5.position(0).limit(20);
				Buffers.bufferStatus(bbTc5, "bbTc5 after pos0 lim 20");
				System.out.println("\nTest case 5.3");
				System.out.println("Get the Byte Array");
				byte[] backingArray = new byte[bbTc5.remaining()];
				bbTc5.get(backingArray);
				for (byte b : backingArray) {
					System.out.printf("[%d]", b);
				}
				System.out.println("");
				System.out.println("Get the same byte array with Method");
				bbTc5.position(0).limit(20);
				byte[] bbTc5Array = Buffers.getByteArrayFromByteBuffer(bbTc5);
				for (byte b : bbTc5Array) {
					System.out.printf("[%d]", b);
				}
//</editor-fold>
				break;
			case 6:
//<editor-fold desc="Put data to viewBuffers with chaining">
				ByteBuffer bbTc6 = ByteBuffer.allocate(1024);
				System.out.println("Test case 6.1");
				System.out.println("Adding Double with chaining DoubleViewBuffer");
				bbTc6.asDoubleBuffer().put(testDouble);
				System.out.println(bbTc6.asDoubleBuffer().get());
				Buffers.putDoubleToByteBufferThroughChainedIntViewBuffer(bbTc6, 54321);
				System.out.println("Validation: read from buffer");
				System.out.println(bbTc6.asDoubleBuffer().get());
				System.out.println(bbTc6.asDoubleBuffer().get());
				System.out.println(bbTc6.asDoubleBuffer().get());
				System.out.println(bbTc6.asDoubleBuffer().get());
				Buffers.bufferStatus(bbTc6, "ByteBuffer status after read");
				bbTc6.putDouble(testDouble);
				Buffers.bufferStatus(bbTc6, "ByteBuffer status after put");
				System.out.println(bbTc6.asDoubleBuffer().get());
				bbTc6.rewind();
				System.out.println(bbTc6.asDoubleBuffer().get());
				System.out.println(bbTc6.getDouble());
				//</editor-fold>
				break;
			case 7:
//<editor-fold desc="Formatter used to put data to a CharBuffer">
				ByteBuffer bbTc7 = ByteBuffer.allocate(1024);
				CharBuffer bbTc7cb = bbTc7.asCharBuffer();
				String Tc7TestString = "This is a test string";
				Buffers.putStringToCharBufferWithFormatter(bbTc7, bbTc7cb, Tc7TestString);
				Buffers.getStringFromByteBufferWithGetChar(bbTc7);
				//</editor-fold>
				break;
			case 8:
//<editor-fold desc="Display a string from a byte[] array usingn ByteBuffer wrap and CharBuffer">
				System.out.println(Charset.defaultCharset());
				String test = "Árvíztűrő tükörfúrógép";
				byte[] byteArray = test.getBytes(Charset.forName("UTF-16"));
				String output;
				output = ByteBuffer.wrap(byteArray).asCharBuffer().toString();
				System.out.println(output);
				//</editor-fold>
				break;
			case 9:
				//<editor-fold desc="Create MappedByteBuffer">
				Path sourceFile=Paths.get("E:\\javaFileOpTest\\ReadingFiles\\fileChannelRead.txt");
				MappedByteBuffer newMappedByteBuffer=Buffers.createMappedByteBuffer(sourceFile);
				//</editor-fold>
				break;
		}//end Switch
	} //End Main
} //end class BuffersTD

