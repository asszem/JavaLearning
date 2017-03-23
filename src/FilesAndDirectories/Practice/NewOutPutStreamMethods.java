/*Test FileOutPutSteam vs Files.newOutPutSteam methods to open files for writing

Creating a BufferedWriter object wrapping a FileOutputStream
BufferedWriter obj = new BufferedWriter (new OutputStreamWriter (new FileOutputStream(file, encoder)))

Using the Files mehtod
BufferedWriter obj = Files.newBufferedWriter(file, charset, options)

 */
package FilesAndDirectories.Practice;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.nio.file.*;
import static java.nio.file.StandardOpenOption.CREATE;
import static java.nio.file.StandardOpenOption.TRUNCATE_EXISTING;

/**
 *
 * @author Andras Olah (olahandras78@gmail.com)
 */
public class NewOutPutStreamMethods {

	public static void main(String[] args) {
		CharsetEncoder encoder = Charset.forName("UTF-8").newEncoder();
//	CharsetDecoder decoder = Charset.forName("UTF-8").newDecoder();
		FileSystem fileSystem = FileSystems.getDefault();
		Path p = fileSystem.getPath("E:\\javaTest");
		Path fileOSW = p.resolve("OutputSteamWriter.txt");
		Path fileFNOPS = p.resolve("files.newOutputStream.txt");
		System.out.println(p.toAbsolutePath());
		try (OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(fileOSW.toString()), encoder); BufferedWriter bw = Files.newBufferedWriter(fileFNOPS, Charset.forName("UTF-16"), CREATE, java.nio.file.StandardOpenOption.WRITE, TRUNCATE_EXISTING);) {

			BufferedWriter osw2 = new BufferedWriter(osw);
			BufferedWriter osw3 = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileOSW.toString()), encoder));
			for (int i = 1; i < (int) (Math.random() * 20); i++) {
				String str = String.format("%d. iteráció%n", i);
				System.out.println(str);
				osw.write("osw1" + str);
				osw2.write("osw2 " + str);
				osw3.write("osw3 " + str);
			}
//			osw.flush();
//			osw2.flush();
//			osw3.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
