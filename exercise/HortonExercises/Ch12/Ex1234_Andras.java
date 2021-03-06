/*
Requirement

Define a Person class to encapsulate a person's name and address, with the name and address being fields
of type Name and Address. Write a program to allow names and addresses to be entered from the keyboard
and stored as Person objects in a personFile. 
After the personFile exists new entries should be appended to the personFile

Ex2
Extend the previous example to optionally list all the names and addresses contained within the fi le on the
command line.

Ex3

	Extend the previous example to add an index based on the person's name for each person entered at the
	keyboard to locate the corresponding Person object in the object personFile. 

	The index personFile contains entries of type IndexEntry, each of which encapsulates
	a name and a personFile position in the object personFile. 

	The index personFile should be a separate personFile from the original personFile containing Person objects.

	Note: You might find it easiest to delete the previous personFile before you run this example so that the object
	personFile can be reconstructed along with the index personFile.
	You can't get the personFile position in an object stream in the
	same way as you can with a channel. 
	However, you can use the sequence number for an object as the index
	the first object being 1, the second being 2, and so on.

Ex4
Use the index file to provide random direct access to the object file for querying random names entered
from the keyboard. Entering a name from the keyboard should result in the address for the individual, or
a message indicating the entry is not present in the fi le. The process is to fi rst search the index fi le for
an object with a name fi eld matching the keyboard entry. When an IndexEntry is found, you use the
sequence number it contains to retrieve the appropriate Person object.
 */
package HortonExercises.Ch12;

import FilesAndDirectories.AppendableObjectOutputStream;
import java.io.BufferedInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import static java.nio.file.StandardOpenOption.READ;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Andras Olah (olahandras78@gmail.com)
 */
public class Ex1234_Andras implements Filepaths {

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

	public static void writePersonFile(Person personToWrite, Path fileToWrite) {
		boolean append = false;
		if (Files.exists(fileToWrite)) {
			append = true;
		} else {
			try {
				//Create the folder
				Files.createDirectories(fileToWrite.getParent());
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		try (ObjectOutputStream objectOut = AppendableObjectOutputStream.newObjectOutputStream(fileToWrite, append)) {
			objectOut.writeObject(personToWrite);
			objectOut.reset();
		} catch (IOException ex) {
			ex.printStackTrace();

		}
	}

	public static void writeIndexEntryFile(Person personToWrite, Path fileToWrite) {
		boolean append = false;
		if (Files.exists(fileToWrite)) {
			append = true;
		} else {
			try {
				//Create the folder
				Files.createDirectories(fileToWrite.getParent());
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		try (ObjectOutputStream objectOut = AppendableObjectOutputStream.newObjectOutputStream(fileToWrite, append)) {
			objectOut.writeObject(new IndexEntry(personToWrite.getPersonNameObject()));
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
			IndexEntry readedIndexEntryObject = null;
			Person readedPersonObject = null;  //this is a reference to be used 
			while (true) {  //repeat until exception - to read all objects
				//determine the class of the readed object
				Object readedObject = objectsIn.readObject();
				if (readedObject.getClass().getSimpleName().equals("Person")) {
					readedPersonObject = (Person) readedObject;
					readedObjects.add(readedPersonObject);
				}
				if (readedObject.getClass().getSimpleName().equals("IndexEntry")) {
					readedIndexEntryObject = (IndexEntry) readedObject;
					readedObjects.add(readedIndexEntryObject);
				}
			}
		} catch (EOFException eof) {
			//this is expected, this will break the reading cycle
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return readedObjects;
	}

	public static Person searchPersonObjectByName(String searchForName) {
		Person returnPersonObject = null;
		ArrayList readIndexFileObjects = new ArrayList();
		readIndexFileObjects = readAllObjectsFromFile(indexFile);
		int positionInPersonFile = -1; //this value must be changed if valid position found
		for (int i = 0; i < readIndexFileObjects.size(); i++) {
			IndexEntry currentEntryObject = (IndexEntry) readIndexFileObjects.get(i);
			if (currentEntryObject.nameObject.name.equals(searchForName)) {
				positionInPersonFile = currentEntryObject.getPosition();
				break; //no need to search further, found the position
			}
		}
		if (positionInPersonFile>-1){ //A match was found, positionInPersonFile points to the object
			ArrayList readPersonFileObjects=readAllObjectsFromFile(personFile);
			returnPersonObject=(Person) readPersonFileObjects.get(positionInPersonFile-1); //-1 because array index is 0
		}
		return returnPersonObject;
	}

	public static void run() {
		while (true) {
			System.out.println("(A)dd new entry, (L)ist all entries, (S)earch, (Q)uit");
			System.out.print("Choose:");
			String userChoice = getKeyboardInput();
			switch (userChoice.toLowerCase()) {
				case "a":
					Person newPerson = createPersonObject();
					writePersonFile(newPerson, personFile);
					writeIndexEntryFile(newPerson, indexFile);
					System.out.println("New entry added");
					break;
				case "l":
					ArrayList allObjects = readAllObjectsFromFile(personFile);
					for (int i = 0; i < allObjects.size(); i++) {
						System.out.println(allObjects.get(i));
					}
					allObjects = readAllObjectsFromFile(indexFile);
					for (int i = 0; i < allObjects.size(); i++) {
						System.out.println(allObjects.get(i));
					}
					break;
				case "s":
					System.out.print("Enter the name that you are looking for: ");
					String userNameToLookFor = getKeyboardInput();
					Person foundPerson = searchPersonObjectByName(userNameToLookFor);
					if (foundPerson == null) {
						System.out.println("Person not found");
					} else {
						System.out.println(foundPerson);
					}
					break;
				case "q":
					System.exit(0);
					break;
			}
		}

	}

	public static void main(String[] args) {
		run();
	}
}
