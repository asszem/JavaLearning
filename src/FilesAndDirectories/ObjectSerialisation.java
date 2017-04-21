package FilesAndDirectories;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 *
 * @author Andras Olah (olahandras78@gmail.com)
 */
public class ObjectSerialisation {

	/**
	 * Serializes multiple objects passed in the ArrayList parameter
	 *
	 * This is a static method so it can write multiple objects in one output stream without APPEND Writing multiple times from multiple streams with append to the same file will
	 * cause AC exception The objects must be in a SERIALIZABLE class
	 *
	 * @param targetFile the file to write the objects
	 * @param objectsToWrite An ArrayList that holds all the objects to write
	 */
	public static void serializeObjects(Path targetFile, ArrayList objectsToWrite) {
		try {
			Files.createDirectories(targetFile.getParent());
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		try (
				ObjectOutputStream objectOutputStream = new ObjectOutputStream(new BufferedOutputStream(Files.newOutputStream(targetFile)))) {
			//write all objects from arraylist
			for (int i = 0; i < objectsToWrite.size(); i++) {
				objectOutputStream.writeObject(objectsToWrite.get(i));
			}
			System.out.printf("%d object%swritten to file %s%n", objectsToWrite.size(), (objectsToWrite.size() > 1) ? "s are " : " is ", targetFile);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * Serializes single object passed in as parameter
	 *
	 * @param targetFile the file to write the objects
	 * @param objectsToWrite the Object to write
	 */
	public static void serializeObject(Path targetFile, Object objectsToWrite) {
		try {
			Files.createDirectories(targetFile.getParent());
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		try (
				ObjectOutputStream objectOutputStream = new ObjectOutputStream(new BufferedOutputStream(Files.newOutputStream(targetFile)))) {
			objectOutputStream.writeObject(objectsToWrite);
			System.out.printf("Object is written to file %s%n", targetFile);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * Deserializes (reads) multiple objects from a source file.
	 *
	 * @param sourceFile
	 * @return an ArrayList of all objects readed from the file
	 */
	public static ArrayList readObjects(Path sourceFile) {
		if (Files.notExists(sourceFile)) {
			System.out.println("File does not exists");
			return null;
		}
		ArrayList readedObjects = new ArrayList();
		try (ObjectInputStream objectInputStream = new ObjectInputStream(new BufferedInputStream(Files.newInputStream(sourceFile)))) {
			Object readedObject = null;  //this is a reference to be used 
			while (true) {  //repeat until exception - to read all objects
				readedObject = objectInputStream.readObject();
				readedObjects.add(readedObject);
			}
		} catch (EOFException eof) {
			//this is expected, this will break the reading cycle
			int readedObjectsCount = readedObjects.size();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return readedObjects;
	}

	/**
	 * Deserializes (reads) a single object from a source file
	 *
	 * @param sourceFile the file to read from
	 * @return an Object reference to the readed object
	 */
	public static Object readObject(Path sourceFile) {
		if (Files.notExists(sourceFile)) {
			System.out.println("File does not exists");
			return null;
		}
		Object readedObject = null;  //this is a reference to be used 
		try (ObjectInputStream objectInputStream = new ObjectInputStream(new BufferedInputStream(Files.newInputStream(sourceFile)))) {
			readedObject = objectInputStream.readObject();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return readedObject;
	}

	public static void main(String[] args) {
		Path objectFile = Paths.get("J:\\Serialising Objects\\SerialiseMe.bin");
		FilesAndDirectories.H_Ch12_Serialization.ObjectSerializationPractice obj1 = new FilesAndDirectories.H_Ch12_Serialization.ObjectSerializationPractice("SerialiseMe!");
		ArrayList writeArray = new ArrayList();
		writeArray.add(obj1);
		serializeObjects(objectFile, writeArray);
		ArrayList readArray = new ArrayList();
		readArray = readObjects(objectFile);
		System.out.println("Input object:");
		System.out.println(writeArray.get(0));
		System.out.println("\n");
		System.out.println("Output object:");
		System.out.println(readArray.get(0));
	}
}
