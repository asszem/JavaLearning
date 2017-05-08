package HortonExercises.Ch14.Ex3.Ex3_Andras;

import java.nio.file.*;
import java.io.*;
import java.util.*;

class PhoneBook implements Serializable {

	private HashMap<Person, BookEntry> phoneBook = new HashMap<>();
	private HashMap<PhoneNumber, BookEntry> phoneBookByNumberKey = new HashMap<>();  //Just for practice...
	private static final long serialVersionUID = 1001L;
	private Path file = Paths.get("J:/Phonebook/Phonebook.bin");
	// List all entries in the book

	public void listEntries() {
		// Get the entries as a linked list
		LinkedList<BookEntry> entries = new LinkedList<>(phoneBook.values());
		Collections.sort(entries);                                         // Sort the entries

		for (BookEntry entry : entries) {
			System.out.println("Name = " + entry.getPerson());
			System.out.printf("Number =(%s) %s%n", entry.getNumber().getAreaCode(), entry.getNumber().getPhoneNumberNotAreaCode());
			System.out.println("");
		}
	}

	//To create a PhoneBook with PhoneNumbers keys based on existing PhoneBook
	public HashMap<PhoneNumber, BookEntry> cloneBook() {
		HashMap<PhoneNumber, BookEntry> clonedBook = new HashMap<>();  //Just for practice...
		//Create a Collection 
		Collection<BookEntry> sourcePhoneBookValues = this.phoneBook.values();  //Based on the object instance's phoneBook field
		//Iterate through the collection and copy each entry to the cloneBook map
		for (BookEntry currentEntry:sourcePhoneBookValues){
			clonedBook.put(currentEntry.getNumber(), currentEntry);
		}
		return clonedBook;
	}

	@SuppressWarnings("unchecked")
	public PhoneBook() {
		if (Files.exists(file)) {                                           // If there's a phone book in a file...
			try (ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(Files.newInputStream(file)))) {
				phoneBook = (HashMap<Person, BookEntry>) in.readObject();       //...read it in.
			} catch (ClassNotFoundException | IOException e) {
				e.printStackTrace();
				System.exit(1);
			}
		}
	}

	public void save() {
		try {
			Files.createDirectories(file.getParent());                       // Make sure we have the directory
		} catch (IOException e) {
			System.err.println("I/O error creating directory. " + e.getMessage());
			e.printStackTrace();
			System.exit(1);
		}
		try (ObjectOutputStream out = new ObjectOutputStream(
				new BufferedOutputStream(Files.newOutputStream(file)))) {
			System.out.println("Saving phone book");
			out.writeObject(phoneBook);
			System.out.println("Done");
		} catch (IOException e) {
			System.out.println("I/O error saving phone book. " + e.getMessage());
			e.printStackTrace();
			System.exit(1);
		}
	}

	public void addEntry(BookEntry entry) {
		phoneBook.put(entry.getPerson(), entry);
	}

	public BookEntry getEntry(Person key) {
		return phoneBook.get(key);
	}

	public BookEntry getEntry(PhoneNumber phoneNumber) {
		//Create a Set for Map.Entry<K,V> type entries
		Set<Map.Entry<Person, BookEntry>> phoneBookEntries = phoneBook.entrySet();
		//Iterate through the entry set
		for (Map.Entry<Person, BookEntry> currentEntry : phoneBookEntries) {
			//Compare the PhoneNumber object referenced in current Bookentry with the target phoneNumber
			if (currentEntry.getValue().getNumber().equals(phoneNumber)) {  //equals method to be overriden in PhoneNumber class!
				//If a match is found, return the full BookEntry by the key
				return phoneBook.get(currentEntry.getKey());
			}
		}
		//If this is reached, no BookEntry with phoneNumber was found, return null
		return null;
	}

	//Creates a cloned book with PhoneNumber keys and returns the searched key
	public BookEntry getEntryByPhoneKey(PhoneNumber key){
		HashMap<PhoneNumber, BookEntry> clonedBook=this.cloneBook();
		BookEntry foundEntry = clonedBook.get(key);
		return this.cloneBook().get(key);
	}
	//Returns a PhoneNumber object from a BookEntry based on the key
	public PhoneNumber getNumber(Person key) {
		BookEntry entry = getEntry(key);
		if (entry != null) {
			return entry.getNumber(); //Returns the phoneNumber from the entry
		} else {
			return null;
		}
	}

}
