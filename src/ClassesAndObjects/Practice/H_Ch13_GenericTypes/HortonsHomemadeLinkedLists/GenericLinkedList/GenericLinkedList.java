/*
For better understanding renamed variables and moved fields to the top of class declarations
*/
package ClassesAndObjects.Practice.H_Ch13_GenericTypes.HortonsHomemadeLinkedLists.GenericLinkedList;

public class GenericLinkedList<T> {

	private ListItem startListItemObject = null;                                       // First ListItem in the list
	private ListItem endListItemObject = null;                                         // Last ListItem in the list
	private ListItem currentListItemObject = null;                                     // The currentListItemObject itemTypeT for iterating

	// Default constructor - creates an empty list
	public GenericLinkedList() {
	}

	// Constructor to create a list containing one object
	public GenericLinkedList(T item) {
		if (item != null) {
			currentListItemObject = endListItemObject = startListItemObject = new ListItem(item);                      // itemTypeT is the startListItemObject and endListItemObject
		}
	}

	// Construct a linked list from an array of objects
	public GenericLinkedList(T[] items) {
		if (items != null) {
			// Add the items to the list
			for (int i = 0; i < items.length; ++i) {
				addItem(items[i]);
			}
			currentListItemObject = startListItemObject;
		}
	}

	// Add an itemTypeT object to the list
	public void addItem(T item) {
		ListItem newEnd = new ListItem(item);                              // Create a new ListItem
		if (startListItemObject == null) {                                 // Is the list empty?
			 // Yes, so new element is startListItemObject and endListItemObject
			startListItemObject = endListItemObject = newEnd;   
		} else {        												   // No, so append new element
			// Set nextListItemObject variable for old endListItemObject
			endListItemObject.nextListItemObject = newEnd;                    
			endListItemObject = newEnd;                                    // Store new itemTypeT as endListItemObject
		}
	}

	// Get the first object in the list. The object type will be the argument given when instantiating class
	public T getFirst() {
		currentListItemObject = startListItemObject;
		return startListItemObject == null ? null : startListItemObject.itemTypeT;
	}

	// Get the nextListItemObject object in the list
	public T getNext() {
		if (currentListItemObject != null) {
			currentListItemObject = currentListItemObject.nextListItemObject;                                          // Get the reference to the nextListItemObject itemTypeT
		}
		return currentListItemObject == null ? null : currentListItemObject.itemTypeT;
	}

	private class ListItem {

		ListItem nextListItemObject;                            // Refers to nextListItemObject itemTypeT in the list
		T itemTypeT;                                            // The itemTypeT for this ListItem

		// Constructor
		public ListItem(T item) {
			this.itemTypeT = item;                              // Store the itemTypeT
			nextListItemObject = null;                          // Set nextListItemObject as endListItemObject point
		}

		// Return class name & object
		@Override
		public String toString() {
			return "ListItem " + itemTypeT;
		}

	}
}
