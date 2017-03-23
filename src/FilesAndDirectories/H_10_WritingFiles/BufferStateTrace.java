package FilesAndDirectories.H_10_WritingFiles;

import static java.nio.file.StandardOpenOption.*;
import java.nio.file.*;
import java.nio.channels.*;

import java.util.EnumSet;
import java.io.IOException;
import java.nio.ByteBuffer;

public class BufferStateTrace {
  public static void main(String[] args) {
    String phrase = "Garbage in, garbage out.\n";

    Path file = Paths.get("E:\\javaTest\\H10\\bufferStateTrace.txt");    // Path object for the file
    try {
      Files.createDirectories(file.getParent());                       // Make sure we have the directory
    } catch (IOException e) {
      e.printStackTrace();
    }

    try (WritableByteChannel channel = Files.newByteChannel(file, EnumSet.of(WRITE, CREATE, APPEND))) {
      ByteBuffer buf = ByteBuffer.allocate(1024);
      System.out.printf(
        "\nNew buffer:%nposition = %1$2d   Limit = %2$4d   remaining = %3$4d%n",
                        buf.position(), buf.limit(), buf.remaining());
      // Load the data into the buffer
      for(char ch : phrase.toCharArray())
        buf.putChar(ch);
      System.out.printf(
        "\nBuffer after loading:%nposition = %1$2d   Limit = %2$4d   remaining = %3$4d%n",
                        buf.position(), buf.limit(), buf.remaining());
      buf.flip();                                                      // Flip the buffer ready for file write
      System.out.printf(
        "\nBuffer after flip:%nposition = %1$2d   Limit = %2$4d   remaining = %3$4d%n",
                        buf.position(), buf.limit(), buf.remaining());
      channel.write(buf);                                              // Write the buffer to the file channel

// Uncomment the following to get the size of the file in the output
//      System.out.println("\nThe file contains " + ((FileChannel)channel).size() + " bytes.");

      System.out.printf(
        "\nBuffer after first write:%nposition = %1$2d   Limit = %2$4d   remaining = %3$4d%n",
                        buf.position(), buf.limit(), buf.remaining());
      buf.flip();
      System.out.printf(
        "\nBuffer after second flip:%nposition = %1$2d   Limit = %2$4d   remaining = %3$4d%n",
                        buf.position(), buf.limit(), buf.remaining());
      channel.write(buf);                // Write the buffer again to the file channel
      System.out.println("\nBuffer contents written to the file.");
      System.out.printf(
        "\nBuffer after second write:%nposition = %1$2d   Limit = %2$4d   remaining = %3$4d%n",
                        buf.position(), buf.limit(), buf.remaining());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
