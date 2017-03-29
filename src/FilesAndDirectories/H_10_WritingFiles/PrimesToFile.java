package FilesAndDirectories.H_10_WritingFiles;

import static java.lang.Math.ceil;
import static java.lang.Math.sqrt;
import static java.lang.Math.min;
import static java.nio.file.StandardOpenOption.*;
import java.nio.file.*;
import java.nio.channels.*;
import java.nio.*;
import java.util.*;
import java.io.IOException;

public class PrimesToFile {

	public static void main(String[] args) {
		int primesRequired = 100;                                          // Default count
		if (args.length > 0) {
			try {
				primesRequired = Integer.valueOf(args[0]).intValue();
			} catch (NumberFormatException e) {
				System.out.println("Prime count value invalid. Using default of " + primesRequired);
			}
		}

		long[] primes = new long[primesRequired];                        // Array to store primes
		primes[0] = 2L;                                                  // Seed the first prime
		primes[1] = 3L;                                                  // and the second
		// Count of primes found up to now, which is also the array index
		int count = 2;
		// Next integer to be tested
		long number = 5L;

		outer:
		for (; count < primesRequired; number += 2) {
			// The maximum divisor we need to try is square root of number
			// Testing for 25. Sqrt:5. Need to test only up until 5. 
			// Because every odd number that is not a prime can be divided by its square root
			long limit = (long) ceil(sqrt((double) number));
			// Divide by all the primes we have up to limit
			for (int i = 1; i < count && primes[i] <= limit; ++i) {
				if (number % primes[i] == 0L) // Is it an exact divisor (of an existing prime)?
				{
					continue outer;           // yes, so it's not a prime, try the next number
				}
			}//end for - only get here if all primes tested but none of them was exact divisor!
			primes[count++] = number;         // We got one!
		}
		int displayCounter = 0;
		for (long prime : primes) {
			System.out.printf("%02d. [%03d] ", ++displayCounter, prime);
			if (displayCounter % 5 == 0) {
				System.out.println("");
			}
		}
		Path file = Paths.get("E:\\javaTest\\H10\\primes.bin");
		try {
			Files.createDirectories(file.getParent());      // Make sure we have the directory
		} catch (IOException e) {
			e.printStackTrace();
		}

		final int BUFFERSIZE = 100;                                        // Byte buffer size
		try (WritableByteChannel channel = Files.newByteChannel(file, EnumSet.of(WRITE, CREATE))) {
			ByteBuffer buf = ByteBuffer.allocate(BUFFERSIZE);
			LongBuffer longBuf = buf.asLongBuffer();             // View buffer for type long
			int primesWritten = 0;                               // Count of primes written to file
			while (primesWritten < primes.length) {
				longBuf.put(primes, // Array to be written
						primesWritten, // Index of 1st element to write
						min(longBuf.capacity(), primes.length - primesWritten));
				buf.limit(8 * longBuf.position());                      // Update byte buffer position
				channel.write(buf);
				primesWritten += longBuf.position();
				longBuf.clear();
				buf.clear();
			}
			System.out.println("File written is " + ((FileChannel) channel).size() + " bytes.");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
