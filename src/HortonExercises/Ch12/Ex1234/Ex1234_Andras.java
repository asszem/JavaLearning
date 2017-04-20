/*
Requirement

Define a Person class to encapsulate a person's name and address, with the name and address being fields
of type Name and Address. Write a program to allow names and addresses to be entered from the keyboard
and stored as Person objects in a file. 
After the file exists new entries should be appended to the file

Ex2
Extend the previous example to optionally list all the names and addresses contained within the fi le on the
command line.

Ex3

Extend the previous example to add an index based on the person's name for each person entered at the
keyboard to locate the corresponding Person object in the object fi le. The index fi le contains entries of
type IndexEntry, each of which encapsulates a name and a fi le position in the object fi le. The index fi le
should be a separate fi le from the original fi le containing Person objects.
Note: You might fi nd it easiest to delete the previous fi le before you run this example so that the object
fi le can be reconstructed along with the index fi le. You can't get the fi le position in an object stream in the
same way as you can with a channel. However, you can use the sequence number for an object as the
index — the fi rst object being 1, the second being 2, and so on.
 */
package HortonExercises.Ch12.Ex1234;

import FilesAndDirectories.AppendableObjectOutputStream;
import java.io.BufferedInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import static java.nio.file.StandardOpenOption.READ;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Andras Olah (olahandras78@gmail.com)
 */
public class Ex1234_Andras {

	public static String getKeyboardInput() {
		//Charset encodings
		//ISO8869_1
		//Windows-1250
		//UTF-8
		//UTF-16
		Scanner scanner = new Scanner(System.in, "ISO8859_1");
		return scanner.nextLine();
	}

	public static Person createPersonObject() {
		System.out.println("Enter name:");
		String name = getKeyboardInput();
		System.out.println("Enter address:");
		String address = getKeyboardInput();
		Person newPerson = new Person(name, address);
		return newPerson;
	}

	public static void writeObject(Person personToWrite, Path fileToWrite) {
		boolean append = false;
		if (Files.exists(fileToWrite)) {
			append = true;
		}
		try (ObjectOutputStream objectOut = AppendableObjectOutputStream.newObjectOutputStream(fileToWrite, append)){
			objectOut.writeObject(personToWrite);
			objectOut.reset();
		} catch (IOException ex) {
			ex.printStackTrace();

		}
	}

	public static ArrayList readAllObjectsFromFile(Path sourceFile) {
		if (Files.notExists(sourceFile)) {
			System.out.println("File does not exists");
			return null;
		}
		ArrayList readedObjects = new ArrayList();
		try (ObjectInputStream objectsIn = new ObjectInputStream(new BufferedInputStream(Files.newInputStream(sourceFile, READ)))) {
			Person readedObject = null;  //this is a reference to be used 
			while (true) {  //repeat until exception - to read all objects
				readedObject = (Person) objectsIn.readObject();
				readedObjects.add(readedObject);
			}
		} catch (EOFException eof) {
			//this is expected, this will break the reading cycle
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return readedObjects;
	}

	public static void main(String[] args) {
		Path file = Paths.get("J:\\Serialising Objects\\Exercises\\Ex1\\person.bin");
//		Path file=Paths.get("J:/Serialising Objects/Exercises/Ex1");
		Person newPerson = createPersonObject();
		writeObject(newPerson, file);
		ArrayList allObjects = readAllObjectsFromFile(file);
		for (int i = 0; i < allObjects.size(); i++) {
			System.out.println(allObjects.get(i));
		}
	}
}
