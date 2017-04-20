/*
Requirement

Define a Person class to encapsulate a person's name and address, with the name and address being fields
of type Name and Address. Write a program to allow names and addresses to be entered from the keyboard
and stored as Person objects in a file. 
After the file exists new entries should be appended to the file

Ex2
Extend the previous example to optionally list all the names and addresses contained within the fi le on the
command line.
 */
package HortonExercises.Ch12.Ex1;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import static java.nio.file.StandardOpenOption.APPEND;
import static java.nio.file.StandardOpenOption.CREATE;
import static java.nio.file.StandardOpenOption.READ;
import static java.nio.file.StandardOpenOption.WRITE;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Andras Olah (olahandras78@gmail.com)
 */
public class Ex1_Andras {

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
