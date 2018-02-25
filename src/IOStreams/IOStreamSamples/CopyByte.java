//Byte input-output using InputStream/OutputStream classes
package IOStreams.IOStreamSamples;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;  //import so that the exception class could be directly referenced

/**
 *
 * @author Andras Olah (olahandras78@gmail.com)
 */
public class CopyByte {

	public static void main(String[] args) throws IOException {
		String userDir = System.getProperty("user.dir");
		String classPath = CopyByte.class.getProtectionDomain().getCodeSource().getLocation().getPath();
		String fileNameWithPath = "C:\\Users\\olaha\\OneDrive\\W10-OneDrive\\Java programozás OneDrive\\NetBeans_Projects\\JavaLearning\\src\\JavaDocTutorial\\IOStreams\\olvassbe.txt";
		FileInputStream bevitel = null;
		FileOutputStream kiiras = null;
		System.out.println("user directory:\n" + userDir);
		try {
			bevitel = new FileInputStream(fileNameWithPath);
			kiiras = new FileOutputStream(userDir+"\\Assets\\irjalki.txt"); //target path is user.dir
			kiiras.write(65); //Writes a character "A" to the beginning of the file
			int egyByte;
			int counter = 0;
			while ((egyByte = bevitel.read()) != -1) {
				System.out.print("Beolvasások száma: " + counter++);
				System.out.println(" Beolvasás értéke: " + egyByte);
				kiiras.write(egyByte); //Appends characters to file
			}
		} finally {
			if (bevitel != null) {
				bevitel.close();
			}
			if (kiiras != null) {
				kiiras.close();
			}
		}
		System.out.println("Class Path: " + classPath);
	}
}
