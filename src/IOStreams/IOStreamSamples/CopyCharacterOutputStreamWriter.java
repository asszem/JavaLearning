//Copies a content of a file using OutPutStreamWriter method to set localized charset
package IOStreams.IOStreamSamples;

import java.io.OutputStreamWriter;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;


/**
 *
 * @author Andras Olah (olahandras78@gmail.com)
 */
public class CopyCharacterOutputStreamWriter {

	public static void main(String[] args) throws IOException {
		String userDir = System.getProperty("user.dir");
		String inputFile = userDir + "\\Assets\\olvassbe.txt";
		String outputFile = userDir + "\\Assets\\irjalki.txt";

		InputStreamReader beolvasas = null;
		CharsetDecoder decoder = Charset.forName("UTF-8").newDecoder();
		OutputStreamWriter kiiras = null;
		CharsetEncoder encoder = Charset.forName("UTF-8").newEncoder();
		try {
			beolvasas = new InputStreamReader(new FileInputStream(inputFile), decoder);
			kiiras = new OutputStreamWriter(new FileOutputStream(outputFile), encoder);
			System.out.println("Encoding: " + beolvasas.getEncoding());
			int beolvasva;
			while ((beolvasva = beolvasas.read()) != -1) {
//				System.out.print("[" + beolvasva + "] ");
				kiiras.write(beolvasva);
			}
		} catch (IOException e) {
		} finally {
			if (beolvasas != null) {
				beolvasas.close();
			}
			if (kiiras != null) {
				kiiras.close();
			}
		}
	}
}
