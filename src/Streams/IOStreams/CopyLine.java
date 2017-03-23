//Copies characters by line
package Streams.IOStreams;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.IOException;

/**
 *
 * @author Andras Olah (olahandras78@gmail.com)
 */
public class CopyLine {

	public static void main(String[] args) throws IOException {
		String userDir = System.getProperty("user.dir");
		String inputFile = userDir + "\\Assets\\olvassbe.txt";
		String outputFile = userDir + "\\Assets\\irjalki.txt";
		BufferedReader beolvasas = null;
		PrintWriter kiiras = null;
		try {
		beolvasas = new BufferedReader(new FileReader(inputFile));
		kiiras = new PrintWriter(outputFile);
		String egySor;
		int sorSzam=0;
		while ((egySor=beolvasas.readLine()) !=null){
			System.out.println("Sor "+(++sorSzam)+":" + egySor);
			kiiras.println(egySor);
		}
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
