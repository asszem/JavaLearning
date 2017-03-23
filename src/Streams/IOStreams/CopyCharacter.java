//Using character streams to copy characters from one file to another
//TODO find out why character encoding is not working with this method
package Streams.IOStreams;

import java.io.FileReader; //Read characters from a file
import java.io.FileWriter; //Write characters to a file
import java.io.IOException; //read() and write() method throws this exception

/**
 *
 * @author Andras Olah (olahandras78@gmail.com)
 */
public class CopyCharacter {

	public static void main(String[] args) throws IOException {
		String userDir = System.getProperty("user.dir");
		String inputFile = userDir + "\\Assets\\olvassbe.txt";
		String outputFile = userDir + "\\Assets\\irjalki.txt";
		FileReader beolvas = null;
		FileWriter kiiras = null;
		try {
			beolvas = new FileReader(inputFile);
			System.out.println("Encoding: " + beolvas.getEncoding());
			kiiras = new FileWriter(outputFile);
			int beolvasottErtek;
			do {
				beolvasottErtek = beolvas.read();
				System.out.println("Beolvasva: " + beolvasottErtek);
				kiiras.write(beolvasottErtek);
			} while (beolvasottErtek != -1);
		} finally {
			if (beolvas != null) {
				beolvas.close();
			}
			if (kiiras != null) {
				kiiras.close();
			}
		}
	}
}
