package HortonExercises.Ch06.ShapesAndras;

public class LinkedList {
//	for testing purpooses only

	public static void main(String[] args) {
		Point pont1 = new Point(10, 10);
		Point pont2 = new Point(20, 20);
		Point pont3 = new Point(21, 22);
		Point pont4 = new Point(22, 22);
		LinkedList lista = new LinkedList(pont1);
		lista.addItem(pont2);
		lista.addItem(pont3);
		lista.addItem(pont4);
		lista.addItem(pont2);
		System.out.println(lista);
		System.out.println(lista.getNext());
		System.out.println(lista.getPrev());
		System.out.println(lista.getFirst());
	}
	// Default constructor � creates an empty list

	public LinkedList() {
	}

	// Constructor to create a list containing one object
	public LinkedList(Object item) {
		if (item != null) {
			current = end = start = new ListItem(item);// item is the start and end
		}
	}

	// Construct a linked list from an array of objects
	public LinkedList(Object[] items) {
		if (items != null) {
			// Add the items to the list
			for (Object item : items) {
				addItem(item);
			}
			current = start;
		}
	}

	// Add an item object to the list
	public void addItem(Object item) {
		ListItem newEnd = new ListItem(item);    // Create a new ListItem
		if (start == null) {                     // Is the list empty?
			start = end = newEnd;       // Yes, so new element is start and end
			start.prev = start; 		// Prev start refers to itself
		} else {                        // No, so append new element
			end.next = newEnd;          // Set next variable for old end
			current = end;				// To reference previous end object 

			//Point end to the new ListItem object
			end = newEnd;               // Store new item as end
			end.prev = current;		// Set prev item in new end
		}
	}

	// Get the first object in the list
	public Object getFirst() {
		current = start;
		return start == null ? null : start.item;
	}

	public Object getLast() {
		current = end;
		return end == null ? null : end.item;
	}

	// Get the next object in the list
	public Object getNext() {
		if (current != null) {
			current = current.next;   // Get the reference to the next item
		}
		return current == null ? null : current.item;
	}

	//Get the previous object in the list (chapter 6 exercise 4)
	public Object getPrev() {
		if (current != null) {
			current = current.prev;
		} else {
			current = start;
		}
		return current == null ? null : current.item; //Ha nincs még start, akkor null adjon vissza
	}

	public Object getCurrent() {
		return current;
	}

	public void setCurrent(ListItem currentInput) {
		current = currentInput;
	}

	public void insertItem(Object inputObject) {
		//Insert a List Item at current position
		// Ha a current még nem létezik, vagy az utolsóra mutat, simán adjon hozzá egyet. 
		if (current == null || current == end) {
			System.out.println("Az insertItem a legvégére adja");
			addItem(inputObject);
		} else { //ha a current valahol a listában van
			ListItem insertedItem = new ListItem(inputObject);
			System.out.println("Be inzertálás történik");
			//A sorrend fontos
			// az újonnan inzertált következő eleme a current.next-je
			insertedItem.next = current.next;
			// az újonnan inzertált prev-je a current item lesz
			insertedItem.prev = current;
			// a current item next-je az újonnan inzertált lesz
			current.next = insertedItem;
			// átállítjuk a current-et az inserted item-re
			current = insertedItem;
			// a current item next-jének prev itemje az újonnan inzertált lesz
			current.next.prev = insertedItem;
		}
	}

	/*
	Implement a method in the LinkedList class to insert an object following an object passed as an
argument. Assume the objects stored in the list implement an equals() method that compares the this
object with an object passed as an argument and returns true if they are equal.
	 */
	public void insertItem(Object inputObj, Object testObj) {
		this.insertItem(inputObj);

	}

	public void deleteItem() {
		//Delete the current item
		//Ha a legutolsó elemet akarjuk törölni
		//Az utolsó előtti next-je legyen null és az utolsó előtti legyen end
		if (current == end) {
			System.out.println("A legutolsó elem törlése");
			getPrev(); //ez a currentet beállítja az előzőre
			current.next = null;
		} else if (current != start) { //A current NEM az end és nem is a start
			System.out.println("Köztes elem törlése");
			//az előző elem next-je a következőre mutasson
			current.prev.next = current.next;
			//a következő elem prev-je a törlendő prev-jére mutasson
			current.next.prev = current.prev;
			current = current.prev;
		} else { //Ebbe az ágba csak akkor kerülhet, ha a current a start
			System.out.println("A legelső elem törlése");
			//A következő elem prev. részét önmagára kell állítani, mert az lesz a start
			//De csak akkor, ha van egyáltalán következő elem
			if (start.next != null) {
				start.next.prev = start.next;
				start = start.next;
			} else { //ha csak start elem volt a listában, akkor simán nullázzuk
				start = null;
			}
		}
	}
	private ListItem start = null;      // First ListItem in the list
	private ListItem end = null;        // Last ListItem in the list
	private ListItem current = null;    // The current item for iterating

	public String toString() {
		StringBuffer rtn = new StringBuffer("\nLinked List Hash:" + hashCode());
		current = start;
		int counter = 0;
		while (current.next != null) {
			rtn.append(
					"\nList item #" + (++counter) + " Hash: " + current.hashCode()
					+ "\n\t " + current.item.getClass().getSimpleName()
					+ current.item.hashCode()
			//					+ "\n" + current.item + "\n"
			//	    + "Prev list item: " + current.prev.hashCode()
			//		+ "\nNext list item: " + current.next.hashCode()
			);
			current = current.next;
		}
		rtn.append(
				"\nList item #" + (++counter) + " Hash: " + current.hashCode()
				+ "\n\t" + current.item.getClass().getSimpleName()
				+ current.item.hashCode()
				//				+ "\n" + current.item + "\n"
				//				+ "Prev list item: " + current.prev.hashCode()
				+ "\n\tThis is the final item."
		);
		return rtn.toString();
	}

	// ListItem as a nested class
	class ListItem {

		// Constructor
		public ListItem(Object item) {
			this.item = item;            // Store the item
//			prev = end;				   // The last item in the list before new item
			next = null;                 // Set next as end point
		}


		/*
	Implement a method in the LinkedList class to insert an object following an object passed as an
argument. Assume the objects stored in the list implement an equals() method that compares the this
object with an object passed as an argument and returns true if they are equal.
		 */
		public boolean equals(Object inputObj) {
			boolean eredmeny = false;
			if (this.hashCode() == inputObj.hashCode()) {
				eredmeny = true;
			}
			return eredmeny;
		}

		// Return class name & object
		@Override
		public String toString() {
			return "ListItem " + item;
		}
		ListItem prev; 							//Refers to previous item in the list
		ListItem next;                          // Refers to next item in the list
		Object item;                           // The item for this ListItem
	}
}
