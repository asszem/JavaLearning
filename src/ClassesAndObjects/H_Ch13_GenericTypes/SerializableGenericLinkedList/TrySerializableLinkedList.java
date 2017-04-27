package ClassesAndObjects.H_Ch13_GenericTypes.SerializableGenericLinkedList;

import static java.lang.Math.random;
import java.nio.file.*;
import java.io.*;

public class TrySerializableLinkedList {
  public static void main(String[] args) {
    Path file = Paths.get("J:/Serialising Objects/Serializable Linked List/Serialized Linked List.bin");
    try {
      // Create parent directory if it doesn't exist
      Files.createDirectories(file.getParent());
    } catch(IOException e) {
      System.err.println("Error creating directory: " + file.getParent());
      e.printStackTrace();
    }

    // Create a list containing random integers
    SerializableLinkedList<Integer> numbers = new SerializableLinkedList<>();
    for(int i = 0 ; i < 10 ; ++i) {
      numbers.addItem(1+(int)(100.0*random()));                        // Add ten random integers 1 to 100
    }

    System.out.println("\nnumbers list contains:");
    listAll(numbers);                                                  // List contents of numbers

    // Now serialize the list to a file
    try (ObjectOutputStream objOut = new ObjectOutputStream(new BufferedOutputStream(Files.newOutputStream(file)))){
      objOut.writeObject(numbers);
    } catch(IOException e) {
      e.printStackTrace(System.err);
      System.exit(1);
    }

    SerializableLinkedList<Integer> values = null;                                 // Variable to store list from the file

    // Deserialize the list from the file
    try (ObjectInputStream objIn = new ObjectInputStream(new BufferedInputStream(Files.newInputStream(file)))){
      values = (SerializableLinkedList<Integer>)(objIn.readObject()); //Compiler should give warnings here
    } catch(ClassNotFoundException|IOException e) {
      e.printStackTrace();
      System.exit(1);
    }

    System.out.println("\nvalues list contains:");
    listAll(values);                                                   // List contents of values
  }

  // Helper method to list the contents of a linked list
  static void listAll(SerializableLinkedList<Integer> list) {
    Integer number = list.getFirst();
    int count = 0;
    do {
      System.out.printf("%5d",number);
      if(++count%5 == 0 ) {
        System.out.println();
      }
    } while((number = list.getNext()) != null);
  }
}
