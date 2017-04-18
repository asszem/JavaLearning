package FilesAndDirectories.H12_Serialization;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Andras Olah (olahandras78@gmail.com)
 */
//TODO add completed and cleaned method to Files and Directories main folder
public class ObjectSerialization implements Serializable {
//write an int, a double and  a String
//write an object of another class

	private static final long serialVersionUID = 1L;
	String objectName;
	String writeObjectTestString;
	String writeByteArrayTestString;
	String writeCharsTestString;
	int numberToWrite;
	java.util.Random rndGenerator = new java.util.Random();	//reference a serializable class
	double[] rndNumbers;

	//Constructor
	ObjectSerialization(String objectName) {
		this.objectName = objectName;
		String writeObjectTestString = "Árvíztűrő tükörfúrógép writeObject()";
		String writeByteArrayTestString = "Árvíztűrő tükörfúrógép byte[] array";
		String writeCharsTestString = "Árvíztűrő tükörfúrógép char array";
		numberToWrite = 42;
		//Generate a random length array with random numbers using Random class's nextInt method
		rndNumbers = new double[3 + rndGenerator.nextInt(4)]; // Array size 3 to 6 (3+rnd 0,1,2,3)
		for (int i = 0; i < rndNumbers.length; ++i) {
			rndNumbers[i] = rndGenerator.nextDouble();
		}
	}

	public void serializeObject(Path targetFile) {
		try {
			Files.createDirectories(targetFile.getParent());
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		try (
				ObjectOutputStream objectOutputStream = new ObjectOutputStream(new BufferedOutputStream(Files.newOutputStream(targetFile)))) {
			objectOutputStream.writeObject(this); //to write the calling object to the file
			System.out.println(this);
			System.out.println("Object written to file " + targetFile);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	public Object deserializeObject() {
		return this.getClass();
	}

	//Create a method to easily display the content of the object. Also use the debugger to verify
	@Override
	public String toString() {
		System.out.printf("[%s] Class name: %s%n", objectName, this.getClass().getName());
		System.out.printf("[%s] Obj UID: %d%n", objectName, serialVersionUID);
		System.out.printf("[%s] writeObject()%s%n", objectName, writeObjectTestString);
		System.out.printf("[%s] writeByteArray()=%s%n", objectName, writeByteArrayTestString);
		System.out.printf("[%s] writeChars()=%s%n", objectName, writeCharsTestString);
		System.out.printf("[%s] int()=%d%n", objectName, numberToWrite);
		System.out.printf("[%s] random double array size:%d%n", objectName, rndNumbers.length);
		int counter = 0;
		for (double number : rndNumbers) {
			System.out.printf("\t[%d]=%f%n", counter++, number);
		}
		return super.toString(); //return the same as superclass, we just change the display
	}

	public static void main(String[] args) {
		Path targetFolder = Paths.get("J:\\Serializing Objects\\");
		ObjectSerialization obj1 = new ObjectSerialization("obj 1");
		obj1.serializeObject(targetFolder.resolve("obj1.bin"));
	}
}
