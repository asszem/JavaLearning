package Streams;

import java.io.IOException;

/**
 * 
 * @author Andras Olah (olahandras78@gmail.com)
 */
public class WaitForEnter {

	public static void waitForEnter() {
		try {
			System.out.println("Press Enter");
			System.in.read();
		} catch (IOException e) {
			System.err.println(e);
		}
	}
}
