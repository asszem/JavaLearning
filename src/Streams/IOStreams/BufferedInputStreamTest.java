package Streams.IOStreams;

import java.io.BufferedInputStream;
import java.io.IOException;

/**
 *
 * @author Andras Olah (olahandras78@gmail.com)
 */
public class BufferedInputStreamTest {

	public static void main(String[] args) {
		BufferedInputStream billentyuzet = new BufferedInputStream(System.in);
		try {
			byte[] tomb = new byte[30];
			int input = billentyuzet.read(tomb);
			System.out.println("Input: " + input);
			for (byte b : tomb) {
				System.out.println(b);
			}
			billentyuzet.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
