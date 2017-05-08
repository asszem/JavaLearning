/*
Exercise 3 requirements:

Extend the program from this chapter that used a map to store names and telephone numbers such that
you can enter a number to retrieve the name.

Exercise 4 requirements

Implement a phone book so that just a surname can be used to search and have all the entries
corresponding to the name display

 */
package HortonExercises.Ch14.Ex3.Ex34_Andras;

import java.util.ArrayList;
import java.util.HashMap;

public class Ex34_PhoneBook_Andras {

	public static void main(String[] args) {
		PhoneBook book = new PhoneBook();                                  // The phone book
		FormattedInput in = new FormattedInput();                          // Keyboard input
		Person someone;
		while (true) {
			System.out.println("Enter 1 to enter a new phone book entry\n"
					+ "Enter 2 to find the number for a name\n"
					+ "Enter 3 to list all the entries\n"
					+ "Enter 4 to find a name for a number\n"
					+ "Enter 5 to find the number for a SURNAME\n"
					+ "Enter 9 to quit.");
			int what = 0;                                                    // Stores input selection
			try {
				what = in.readInt();

			} catch (InvalidUserInputException e) {
				System.out.println(e.getMessage() + "\nTry again.");
				continue;
			}

			switch (what) {
				case 1:
					book.addEntry(BookEntry.readEntry());
					break;
				case 2:
					//<editor-fold desc="Find entry by Person">
					someone = Person.readPerson();
					BookEntry entry = book.getEntry(someone);
					if (entry == null) {
						System.out.println("The number for " + someone + " was not found.");
					} else {
						System.out.println("The number for " + someone + " is " + entry.getNumber());
					}
					break;
					//</editor-fold>
				case 3:
					book.listEntries();
					break;
				case 4:
					//<editor-fold desc="Find entry by PhoneNumber">
					//Read the number
					PhoneNumber somenumber = PhoneNumber.readNumber();

					//Method 1 - use the overloaded getEntry method with a PhoneNumber argument
					//Get the BookEntry for the number
					BookEntry phoneNumberEntry = book.getEntry(somenumber);
					//Display results
					System.out.println("Method 1");
					if (phoneNumberEntry == null) {
						System.out.println("The name for " + somenumber + " was not found.");
					} else {
						System.out.println("The name for " + somenumber + " is " + phoneNumberEntry.getPerson());
					}
					//Method 2: using the phonebook with PhoneNumber keys
					//Generate a PhoneBook with PhoneNumber keys
					System.out.println("Method 2");
					phoneNumberEntry = book.getEntryByPhoneKey(somenumber);
					if (phoneNumberEntry == null) {
						System.out.println("The name for " + somenumber + " was not found.");
					} else {
						System.out.println("The name for " + somenumber + " is " + phoneNumberEntry.getPerson());
					}
					break;
				//</editor-fold>
				case 5:
					//<editor-fold desc="Find entry by Surname only">
					String surnameToFind = Person.readPersonSurname();
					ArrayList<BookEntry> foundEntries = book.getEntry(surnameToFind);

					if (foundEntries.size() == 0) {
						System.out.println("No entry for " + surnameToFind + " was found.");
					} else {
						for (BookEntry currEntry : foundEntries) {
							System.out.println("The book entry for " + surnameToFind + " is:\n" + currEntry);
						}
					}
					break;
				//</editor-fold>
				case 9:
					book.save();
					System.out.println("Ending program.");
					return;
				default:
					System.out.println("Invalid selection, try again.");
					break;
			}
		}
	}
}
