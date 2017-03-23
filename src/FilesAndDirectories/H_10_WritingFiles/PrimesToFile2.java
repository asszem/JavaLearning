package FilesAndDirectories.H_10_WritingFiles;

import static java.lang.Math.ceil;
import static java.lang.Math.sqrt;
import static java.nio.file.StandardOpenOption.*;
import java.nio.file.*;
import java.nio.channels.*;
import java.nio.*;
import java.util.*;
import java.io.IOException;

public class PrimesToFile2 {
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
      // Count of primes found � up to now, which is also the array index
      int count = 2;
      // Next integer to be tested
      long number = 5L;

      outer:
      for ( ; count < primesRequired ; number += 2) {

        // The maximum divisor we need to try is square root of number
        long limit = (long)ceil(sqrt((double)number));

        // Divide by all the primes we have up to limit
        for (int i = 1; i < count && primes[i] <= limit; ++i)
          if (number % primes[i] == 0L)                                // Is it an exact divisor?
            continue outer;                                            // yes, try the next number

        primes[count++] = number;                                      // We got one!
      }

    Path file = Paths.get("E:\\javaTest\\H10\\primes.txt");
    final int BUFFERSIZE = 100;                                        // Byte buffer size

    try (WritableByteChannel channel = Files.newByteChannel(file, EnumSet.of(WRITE, CREATE, TRUNCATE_EXISTING))){
      ByteBuffer buf = ByteBuffer.allocate(BUFFERSIZE);
      DoubleBuffer doubleBuf = buf.asDoubleBuffer();
      buf.position(8);
      CharBuffer charBuf = buf.asCharBuffer();
      LongBuffer longBuf = null;
      String primeStr = null;

      for (long prime : primes) {
        primeStr = "prime = " + prime;                                 // Create the string
        doubleBuf.put(0,(double)primeStr.length());                    // Store the string length
        charBuf.put(primeStr);                                         // Store the string
        buf.position(2*charBuf.position() + 8);                        // Position for 3rd buffer
        longBuf = buf.asLongBuffer();                                  // Create the buffer
        longBuf.put(prime);                                            // Store the binary long value
        buf.position(buf.position() + 8);                              // Set position after last value
        buf.flip();                                                    // and flip
        channel.write(buf);                                            // Write the buffer as before

        buf.clear();
        doubleBuf.clear();
        charBuf.clear();
      }
      System.out.println("File written is " + ((FileChannel)channel).size() + " bytes.");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
