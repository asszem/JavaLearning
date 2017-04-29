package ClassesAndObjects.Practice.H_Ch06_Inheritance.TryLinkedList;

public class LinkedList {
	// Default constructor - creates an empty list

	public LinkedList() {
	}

	// Constructor to create a list containing one object
	public LinkedList(Object item) {
		if (item != null) {
			current = end = start = new ListItem(item);                      // item is the start and end
		}
	}

	// Construct a linked list from an array of objects
	public LinkedList(Object[] items) {
		if (items != null) {
			// Add the items to the list
			for (int i = 0; i < items.length; ++i) {
				addItem(items[i]);
			}
			current = start;
		}
	}

	// Add an item object to the list
	public void addItem(Object item) {
		ListItem newEnd = new ListItem(item);   // Create a new ListItem
		//Megjegyzés: a ListItem-nek átadott objektum hivatkozása az .item változóban lesz tárolva
		if (start == null) {        // Is the list empty? - ha igen, akkor a start=null
			start = end = newEnd;        // Yes, so new element is start and end
		} else {                         // No, so append new element
			end.next = newEnd;
			//MIT csinál?
			// Set next variable for old end on the called ListItem object.
			//end is a variable in LinkedList object, referencing a ListItem object
			//end.next is a field in the LinkedList object, referencing another LI object
			//Miért csinálja?
			//Ebbe az ágba csak a második LinkedList objektum létrehozásakor jut
			//Értéket ad, mégpedig az AKTUÁLIS (amin meg lett hívva) LISTITEM objektum
			//end.next változóját beállítja, hogy az új objektumra hivatkozzon

			end = newEnd;       // Store new item as end in the LinkedList object
			//end is variable, referencing a ListItem object
		}
	}

	// Get the first object in the list
	public Object getFirst() {
		current = start;
		return start == null ? null : start.item;
	}

	// Get the next object in the list
	//Egy LinkedList objektumon használható metódus, ami egy Objektum referenciát ad vissza
	public Object getNext() {
		if (current != null) {
			current = current.next;             // Get the reference to the next item
			//MIT csinál?
			//Ha van current változó a LinkedList objektumban, akkor a current által hivatkozott
			//ListItem objectum next változója által hivatkozott objektum legyen
			//a current változó által is hivatkozva. 
			//MIÉRT csinálja?
			//Azért, hogy a következő objektum legyen currentként hivatkozva és visszaadva
		}
		return current == null ? null : current.item;
		//MIT csinál?
		//A current változó által hivatkozott objektum
		//item változója által hivatkozott objektumot adja vissza
		//MIÉRT csinálja? 
		//Mert ez a getNext() metódus célja.
		//Megjegyzés
		//ez itten NEMa current változót buzerálja, hanem a metódus visszatérési értékének azt az 
		//OBJEKTUMOT adja meg, amire a current változó 
		//(amit az if cikluson belül állítottunk be) hivatkozik. 
	}

// Variables - Members that reference objects (ListItem type objects)
	// First ListItem in the list
	private ListItem start = null;

	// Last ListItem in the list
	private ListItem end = null;

	// The current item for iterating - ListItem objektumra hivatkozó változó
	private ListItem current = null;

	public String toString() {
		current = start;
		StringBuffer returnStr = new StringBuffer("\nAz objektumban tárolt lista: \n");
		do {
			returnStr.append("Objektum #" + current.hashCode() + " " + current.item + "\n");
			current = current.next;
		} while (current.next != null);
		//Miért kell még egyszer? Azért, hogy az utolsó listaelemet, ahol a next=null is kiírja
		returnStr.append("Objektum #" + current.hashCode() + " " + current.item + "\n");
		return returnStr.toString();
	}
// Nested Class - ListItem as a nested class

	private class ListItem {

// Constructor
		public ListItem(Object item) {
			this.item = item;                             // Store the item
			next = null;               		              // Set next as end point
		}

		// Return class name & object
		@Override
		public String toString() {
			return "ListItem " + item;
		}

		// Refers to next item in the list - egy List Item objektumra hivatkozó változó
		ListItem next;

		// The item for this ListItem - egy BÁRMILYEN objektumra hivatkozó változó!
		Object item;
	}

//for testing purposes
	public static void main(String[] args) {
		Point[] pontok = {new Point(10, 10), new Point(20, 20), new Point(30, 30), new Point(40, 40)};
		LinkedList teszt = new LinkedList(pontok);
		System.out.println("start: \t\t" + teszt.start);
		System.out.println("start.item: \t" + teszt.start.item);
		System.out.println("start.next: \t" + teszt.start.next);
		System.out.println("\nend: \t\t" + teszt.end);
		System.out.println("end.item: \t" + teszt.end.item);
		System.out.println("end.next: \t" + teszt.end.next);
		System.out.println("\ncurrent:\t" + teszt.current);
		System.out.println("current.item:\t" + teszt.current.item);
		System.out.println("current.next:\t" + teszt.current.next);
		//Testing iteration with current on the teszt object
		int counter = 0;
		boolean uccso = false;
		do {
			counter++;
			System.out.println("Objektum #" + counter + " " + teszt.current.item + " hashcode:" + teszt.current.hashCode());
			teszt.current = teszt.current.next;
			if (teszt.current.next == null) {
				System.out.println("Objektum #" + counter + " " + teszt.current.item + " hashcode:" + teszt.current.hashCode());
				uccso = true;
			}
		} while (!uccso);

		teszt.addItem(new Point(100,100));
		//Ez a példa mutatja, hogy BÁRMILYEN objektumot hozzá tudunk adni a listához, a toString lekezeli! :-)
		teszt.addItem(new ClassesAndObjects.Practice.Horcsog("Hörrencs", "fiú", "sárga", "András", 100) );
		//megjezgyzés: a Hörcsög osztályból az Object osztályba automatikus az upcast
		//mert az Object osztály minden osztály felett áll. 
		System.out.println("Teszt objektum toString()-el kiírva:");
		System.out.println(teszt);

		//Ez nem működik, mert az utolsó elem Hörcsög osztályú, nem lehet Pointba downcastolni!
		//Point castteszt = (Point) teszt.end;

		//Ez működik, mert az első eleme a teszt objektumnak Point osztályú
		Point castteszt = (Point) teszt.getFirst();
		System.out.println(castteszt);
	}

}
