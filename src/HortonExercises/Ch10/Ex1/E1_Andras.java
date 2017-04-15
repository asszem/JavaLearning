/*
Implement a new version of the example that writes proverbs to a file 
that writes the proverbs to one file using a Writer 
and the length of each proverb to a separate file using an OutputStream object.
 */
package HortonExercises.Ch10.Ex1;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.nio.file.Files;
import static java.nio.file.StandardOpenOption.*;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 *
 * @author Andras Olah (olahandras78@gmail.com)
 */
public class E1_Andras {

	public static void main(String[] args) throws IOException {
		String[] sayings = {
			"Indecision maximizes flexibility.",
			"Only the mediocre are always at their best.",
			"A little knowledge is a dangerous thing.",
			"Many a mickle makes a muckle.",
			"Who begins too much achieves little.",
			"Who knows most says least.",
			"A wise man sits on the hole in his carpet.",
			"És ezt még én írtam hozzá",
			"1",
			"22",
			"333"};
		Charset charset = Charset.forName("UTF-16");
//		Charset charset = Charset.defaultCharset();
		Path p = Paths.get("E:\\javaTest", "Exercises", "H10", "Ex1");
		System.out.println("path: " + p);

		//Create a buffer to hold the line lenghts (4 bytes for each line in array)
		ByteBuffer bb = ByteBuffer.allocate(sayings.length * 4);
		IntBuffer intB = bb.asIntBuffer();

		//Add the length of each line to the intB buffer
		for (String saying : sayings) {
			intB.put(saying.length());
		}

		Files.createDirectories(p.getParent());
		try (
				BufferedWriter fileOut = Files.newBufferedWriter(p.resolve("proverbsAndras.txt"), charset, CREATE, WRITE, TRUNCATE_EXISTING);
				OutputStream outputStream = Files.newOutputStream(p.resolve("proverbsAndrasLengths.txt"), CREATE, WRITE, TRUNCATE_EXISTING);) {
			for (String string : sayings) {
				fileOut.write(string + System.lineSeparator());
				outputStream.write(bb.array(), 0, bb.capacity());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
