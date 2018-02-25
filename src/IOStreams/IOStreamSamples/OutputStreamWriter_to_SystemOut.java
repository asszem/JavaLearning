//This is to practice how OutputStreamWriter works
//Difference between .write and .append in OutputStreamWriter
//http://stackoverflow.com/questions/5949926/what-is-the-difference-between-append-and-write-methods-of-java-io-writer
package IOStreams.IOStreamSamples;

import java.io.*;

/**
 *
 * @author Andras Olah (olahandras78@gmail.com)
 */
public class OutputStreamWriter_to_SystemOut {

	public static void main(String[] args) throws IOException {
		OutputStreamWriter kiiras = new OutputStreamWriter(System.out);
		kiiras.append((char) 233);
		kiiras.append("\n");
		kiiras.append("Ez egy string");
		kiiras.flush();
		kiiras.close();
	}
}
