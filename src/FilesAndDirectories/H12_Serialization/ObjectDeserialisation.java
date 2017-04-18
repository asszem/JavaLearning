package FilesAndDirectories.H12_Serialization;

import java.io.BufferedInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Andras Olah (olahandras78@gmail.com)
 */
public class ObjectDeserialisation {
//When reading back objects from file, I have to know the class it should belong to...	

	public static ArrayList readObject(Path sourceFile) {
		if (Files.notExists(sourceFile)) {
			System.out.println("File does not exists");
			return null;
		}
		ArrayList readedObjects = new ArrayList();
		try (ObjectInputStream objectInputStream = new ObjectInputStream(new BufferedInputStream(Files.newInputStream(sourceFile)))) {
			ObjectSerialization readedObject = null;  //this is a reference to be used 
			while (true) {  //repeat until exception - to read all objects
				readedObject = (ObjectSerialization) objectInputStream.readObject();
				readedObjects.add(readedObject);
			}
		} catch (EOFException eof) {
			//this is expected, this will break the reading cycle
			int readedObjectsCount=readedObjects.size();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return readedObjects;
	}

	public static void main(String[] args) {
		Path sourcePath = Paths.get("J:\\Serialising Objects\\");
		ArrayList readedObjects;
//		readedObjects = readObject(sourcePath.resolve("obj1.bin"));
//		System.out.println(readedObjects.get(0));
		readedObjects = readObject(sourcePath.resolve("objALL.bin"));
		for (Object object:readedObjects){
			System.out.println( object);
		}
	}
}
