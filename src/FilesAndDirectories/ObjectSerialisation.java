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

	//Changed to static method so it can write multiple objects in one stream without append
	/**
	 * Serializes all objects passed in the ArrayList
	 *
	 * The objects must be in a SERIALIZABLE class
	 *
	 * @param targetFile the path
	 * @param objectsToWrite An ArrayList that holds all the objects to write
	 */
	public static void serializeObject(Path targetFile, ArrayList objectsToWrite) {
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
	 * Deserializes (reads) objecst from the source file. Object class are not readed
	 *
	 * @param sourceFile
	 * @return an ArrayList of all objects readed from the file
	 */
	public static ArrayList readObject(Path sourceFile) {
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

	public static void main(String[] args) {
		Path objectFile = Paths.get("J:\\Serialising Objects\\SerialiseMe.bin");
		FilesAndDirectories.H12_Serialization.ObjectSerializationPractice obj1 = new FilesAndDirectories.H12_Serialization.ObjectSerializationPractice("SerialiseMe!");
		ArrayList writeArray = new ArrayList();
		writeArray.add(obj1);
		serializeObject(objectFile, writeArray);
		ArrayList readArray =new ArrayList();
		readArray=readObject(objectFile);
		System.out.println("Input object:");
		System.out.println(writeArray.get(0));
		System.out.println("\n");
		System.out.println("Output object:");
		System.out.println(readArray.get(0));
	}
}
