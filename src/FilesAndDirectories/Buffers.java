/*
To create a new buffer, you must use typeBuffer.allocate(capacity)

	CAPACITY - the total number of types the buffer can hold. Fixed if not ViewBuffer
	POSITION - index position of the next buffer element that is available to be read or written
	LIMIT - index position in a buffer of the first value that should not be read or written

	0 ≤ position ≤ limit ≤ capacity
	Elements can be read or written starting with the element at POSITION
	and up to and including the element at LIMIT-1

	Use the POSITION and LIMIT for a ByteBuffer to determine 
	what bytes in the buffer are involved in a read or write operation executed by a channel 

Methods
	remaining() - returns the number of elements between POSITION and LIMIT
	position() - returns the position
	position(int newPosition) - sets the new position
	limit(int newLimit) - sets the new limit
	duplicate() - new buffer with initial capacity, position, limit, content shared, pos/limit independent
	slice() - new buffer from original buffers position. Content shared, pos/limit/mark independent
	wrap() -creates a buffer that already contains the data in the array 
		The buffer is backed by the array that you have used to define it, 
		so modifications to the values in the buffer alters the array, and vice versa
		hasArray() will only be true if a buffer was created with a wrap
	mark() / reset() / rewind () - marks a position in a buffer
	put() / get() - transfer data to / from  a buffer
	flip() - setup the buffer to be written. Does: buf.limit(buf.position()).rewind(0);
	clear() -  sets the limit to the capacity and the position to zero. Doesnt reset data!
	array() - returns the backing array of the buffer
	hasArray() - true if there is a backing array

ViewBuffers - TypeBuffer referenceVariable = byteBufferObject.asTypeBuffer();

	Data is always transferred to or from a file as a series of bytes, but it typically consists
	of data values of a mix of types other than type byte

	A view buffer always maps to bytes in the byte buffer starting at the current position
	You can create as many view buffers from a buffer of type ByteBuffer as you want
	they can overlap or not as you require. 

Duplicate Buffers
A duplicate buffer is not really a new buffer in memory. It is just a new object that provides an
alternative route to accessing the same block of memory that is being used to buffer the data. The
duplicate() method returns a reference of a new object of the same type as the original, but has no
independent data storage. It merely shares the memory that belongs to the original buffer object but
with independent position and limit values.

Writing Buffers to file
	1. Create Buffer
	2. Put values to Buffer
	3. Flip() buffer - makes the current position as limit and sets position to 0
	4. Write buffer - writing updates the buffer position
	5. Clear() buffer

Other sources on Buffers
https://books.google.hu/books?id=z7TQ8NSooS4C&pg=PT18&hl=hu&source=gbs_toc_r&cad=4#v=onepage&q&f=false
Java NIO by Ron Hitchens
 */
package FilesAndDirectories;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.IntBuffer;
import java.nio.charset.Charset;

/**
 *
 * @author Andras Olah (olahandras78@gmail.com)
 */
public class Buffers {

	public static void main(String[] args) {
//<editor-fold desc="Initializing Test data">
		Charset charset;
		charset = Charset.defaultCharset();
//		charset = Charset.forName("Windows-1250");
//		charset = Charset.forName("ISO8859_1");
		String str = "AAaa 11 Ez egy sor a bufferbe.";
		String[] strA = {"Ez az array 1 sora", "Ez az array második sora", "Ez az array 3. sora"};
		int numInt = 123;
		double numDouble = 456.789;
		char chr = 'A';
		//</editor-fold>
		int testCase = 5;
		System.out.println("Test case: " + testCase);
		switch (testCase) {
			case 1:
//<editor-fold desc="Create ByteBuffer with allocate method">
				//Add all input data to the bytebuffer
				ByteBuffer bb = ByteBuffer.allocate(1024); //1 kbyte
				System.out.println("Initializing byte buffer bb");
				System.out.println("bb has array?\t" + bb.hasArray());
				System.out.println("bb:\t\t" + bufTrace(bb));
				System.out.println("\nAdding a STRING with getBytes to bb: ");
				System.out.printf("  [%s] Str.length:%d%n", str, str.length());
				bb.put(str.getBytes());
				System.out.println("bb:\t\t" + bufTrace(bb));

				System.out.println("\nCreate a SLICE from bb");
				ByteBuffer bbSlice = bb.slice();
				System.out.println("bbSlice:\t" + bufTrace(bbSlice));
				System.out.println("bb:\t\t" + bufTrace(bb));

				System.out.println("\nCreate a DUPLICATE from bb");
				ByteBuffer bbDuplicate = bb.duplicate();
				System.out.println("bbDuplicate:\t" + bufTrace(bbDuplicate));
				System.out.println("bb:\t\t" + bufTrace(bb));

				System.out.println("\nAdding a STRING as CHAR ARRAY");
				System.out.print("  [");
				int counter = 0;
				for (char c : str.toCharArray()) {
					counter++;
					System.out.print(c);
					bb.putChar(c);
				}
				System.out.println("]\nChars added:\t" + counter);
				System.out.println("bb:\t\t" + bufTrace(bb));

				System.out.println("Adding a STRING ARRAY with getBytes");
				counter = 0;
				for (String s : strA) {
					counter += s.length(); //Add to the counter the lenght of the current string 
					System.out.printf("  [%s]%n", s);
					bb.put(s.getBytes());
				}
				System.out.println("Total lenght: " + counter);
				System.out.println("bb:\t\t" + bufTrace(bb));

				System.out.println("\nAdding INT bb.putInt (+4 bytes)");
				System.out.printf("  Int: %d%n", numInt);
				bb.putInt(numInt);
				System.out.println("bb:\t\t" + bufTrace(bb));
				System.out.println("\nAdding DOUBLE to the buffer (+8 bytes)");
				System.out.printf("  Double:%f%n", numDouble);
				bb.putDouble(numDouble);
				System.out.println("bb:\t\t" + bufTrace(bb));
				System.out.println("\nAdding CHAR to the buffer (+2 bytes)");
				System.out.printf("  Char:'%c'%n", chr);
				bb.putChar(chr);
				System.out.println("bb:\t\t" + bufTrace(bb));

				System.out.println("\nCreate a INT ViewBuffer (bb remaining/4)");
				System.out.printf("bb.remaining[%d]/4 =%d%n", bb.remaining(), bb.remaining() / 4);
				IntBuffer intB = bb.asIntBuffer();
				System.out.println("bb:\t\t" + bufTrace(bb));
				System.out.println("intB:\t\t" + bufTrace(intB));
				System.out.printf("Put one int value [%d] to IntBuffer:%n", numInt);
				intB.put(numInt);
				System.out.println("bb:\t\t" + bufTrace(bb));
				System.out.println("intB:\t\t" + bufTrace(intB));
				System.out.println("Update bb postition with 4 because of new int (4 bytes)");
				bb.position(bb.position() + 4);
				System.out.println("bb:\t\t" + bufTrace(bb));
//</editor-fold>
				break;
			case 2:
//<editor-fold desc="Reading from Byte buffers">
				System.out.println("Reading from Byte Buffers");
				System.out.println("Input string:");
				System.out.printf("\t[%s]%n\tlength=%d%n", str, str.length());
				System.out.println("\nInitialize bbw (byte buffer with WRAP)");
				ByteBuffer bbw = ByteBuffer.wrap(str.getBytes(charset));
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

				System.out.printf("\nPut data [%d] to Int buffer%n", numInt);
				bb3Int.put(numInt);
				System.out.println("bb3:\t\t" + bufTrace(bb3));
				System.out.println("bb3Int:\t\t" + bufTrace(bb3Int));

				System.out.printf("\nPut data [%d] to ByteBuffer with .putIn%n", numInt);
				bb3.position(4);
				bb3.putInt(numInt);
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
				System.out.println("Double to put: " + numDouble);
				bb4.position(bb4.position() + 8 * bb4.asDoubleBuffer().put(numDouble).position());
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
				byte[] backingArray=bb5.array();
				for (byte b:backingArray){
					System.out.printf("[%d]-", b);
				}
//</editor-fold>
				break;
		}

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
} //end class Buffers
