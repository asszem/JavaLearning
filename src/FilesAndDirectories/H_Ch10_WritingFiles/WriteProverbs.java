package FilesAndDirectories.H_Ch10_WritingFiles;

import static java.nio.file.StandardOpenOption.*;
import java.nio.file.*;
import java.nio.channels.*;

import java.util.EnumSet;
import java.io.IOException;
import java.nio.ByteBuffer;

public class WriteProverbs {
  public static void main(String[] args) {
    String[] sayings = {
      "Indecision maximizes flexibility.",
      "Only the mediocre are always at their best.",
      "A little knowledge is a dangerous thing.",
      "Many a mickle makes a muckle.",
      "Who begins too much achieves little.",
      "Who knows most says least.",
      "A wise man sits on the hole in his carpet."
    };

    Path file = Paths.get("E:\\javaTest","WriteProverbs.txt");                     // Path object for the file
    try {
      Files.createDirectories(file.getParent());                       // Make sure we have the directory
    } catch (IOException e) {
      e.printStackTrace();
    }

    // The buffer must accommodate the longest string
    // so we find the length of the longest string
    int maxLength = 0;
    for (String saying : sayings) {
      if(maxLength < saying.length())
        maxLength = saying.length();
    }
    // The buffer length must hold the longest string
    // plus its length as a binary integer
    // Each character needs 2 bytes and an integer 4 bytes
    ByteBuffer buf = ByteBuffer.allocate(2*maxLength + 4);

    try (WritableByteChannel channel = Files.newByteChannel(file, EnumSet.of(WRITE, CREATE, APPEND))) {

     for (String saying : sayings) {
        buf.putInt(saying.length()).asCharBuffer().put(saying);
        buf.position(buf.position() + 2*saying.length()).flip();
        channel.write(buf);                                            // Write the buffer to the file channel
        buf.clear();
      }
      System.out.println("Proverbs written to file.");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
