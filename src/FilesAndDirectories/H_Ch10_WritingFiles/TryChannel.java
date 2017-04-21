package FilesAndDirectories.H_Ch10_WritingFiles;

import static java.nio.file.StandardOpenOption.*;
import java.nio.channels.WritableByteChannel;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.EnumSet;
import java.nio.file.*;

public class TryChannel {

  public static void main(String[] args) {
    String[] sayings = {"The more you plan the luckier you get.",
                        "The time to complete a project is the time one person would need to complete it " +
                        "multiplied by the number of people on the project.",
                        "If at first you don't succeed, remove any evidence that you tried.",
                        "A clever person solves problems, a wise person avoids them.",
                        "Death is nature's way of telling you to slow down.",
                        "A hen is an egg's way of making other eggs.",
                        "The earlier you begin coding the later you finish.",
                        "Anything you can't understand is intuitively obvious."};

    String separator = System.lineSeparator();

    Path file = Paths.get("E:\\javaTest\\hortonWrite.txt");              // The file path
    try {
      // Create parent directory if it doesn't exist
      Files.createDirectories(file.getParent());
    } catch(IOException e) {
      System.err.println("Error creating directory: " + file.getParent());
      e.printStackTrace();
    }

    ByteBuffer buf = null;                                           // Buffer to hold a saying
    try(WritableByteChannel channel = Files.newByteChannel(file, EnumSet.of(CREATE, WRITE))){

      // Write sayings to the file
      for(int i = 0 ; i < sayings.length ; ++i) {
        buf = ByteBuffer.wrap((sayings[i] + separator).getBytes());  // Saying & separator in buffer
        channel.write(buf);
      }
      System.out.println("File written.");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
