package FilesAndDirectories.H_Ch10_WritingFiles;

import static java.lang.Math.ceil;
import static java.lang.Math.sqrt;
import static java.nio.file.StandardOpenOption.*;
import java.nio.file.*;
import java.nio.channels.*;
import java.nio.*;
import java.util.*;
import java.io.IOException;

public class PrimesToFile3 {

	public static void main(String[] args) {
		int primesRequired = 100;                                          // Default count
		if (args.length > 0) {
			try {
				primesRequired = Integer.valueOf(args[0]).intValue();
			} catch (NumberFormatException e) {
				System.out.println("Prime count value invalid. Using default of "
						+ primesRequired);
			}
		}

		long[] primes = new long[primesRequired];                        // Array to store primes
		primes[0] = 2L;                                                  // Seed the first prime
		primes[1] = 3L;                                                  // and the second
		// Count of primes found � up to now, which is also the array index
		int count = 2;
		// Next integer to be tested
		long number = 5L;

		outer:
		for (; count < primesRequired; number += 2) {

			// The maximum divisor we need to try is square root of number
			long limit = (long) ceil(sqrt((double) number));

			// Divide by all the primes we have up to limit
			for (int i = 1; i < count && primes[i] <= limit; ++i) {
				if (number % primes[i] == 0L) // Is it an exact divisor?
				{
					continue outer;                                            // yes, try the next number
				}
			}
			primes[count++] = number;                                      // We got one!
		}

		Path file = Paths.get("J:\\Writing Files\\primesMixed.bin");
		try {
			Files.createDirectories(file.getParent());                       // Make sure we have the directory
		} catch (IOException e) {
			e.printStackTrace();
		}
		final int BUFFERSIZE = 1024;                                       // Buffer size in bytes � bigger!

		try (WritableByteChannel channel = Files.newByteChannel(file, EnumSet.of(TRUNCATE_EXISTING, WRITE, CREATE))) {
			ByteBuffer buf = ByteBuffer.allocate(BUFFERSIZE);
			String primeStr = null;
			int primesWritten = 0;
			while (primesWritten < primes.length) {
				while (primesWritten < primes.length) {
					primeStr = "prime = " + primes[primesWritten];
					if ((buf.position() + 2 * primeStr.length() + 16) > buf.limit()) {
						break;
					}
					buf.asDoubleBuffer().put(0, (double) primeStr.length());
					buf.position(buf.position() + 8);
					//creates a charbuffer, puts the string to the charbuffer and gets the position of it
					//The value is the position of the CHARbuffer and not the bytebuffer!
					buf.position(buf.position() + 2 * buf.asCharBuffer().put(primeStr).position());
					buf.asLongBuffer().put(primes[primesWritten++]);
					buf.position(buf.position() + 8);
				}
				buf.flip();
				channel.write(buf);
				buf.clear();
			}
			System.out.println("File written is " + ((FileChannel) channel).size() + " bytes.");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
