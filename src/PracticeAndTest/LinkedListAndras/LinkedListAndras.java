/*
Requirements:

1. The linked list object will have a list of objects
2. Methods that can add/remove/query the list items
3. The list items will be objects
4. The objects and the linked list should be serializable so it can be read and read back
5. Unit test will be written for each method in a TDD approach

Preparation
0. The GadgetOwner class and their subclasses will be used to be included in the linked list

Implementation plan
1. The LinkedListAndras class will have a nested class that holds the items of a list object
2. The LinkedListAndras class will have the methods to add, remove and query list items

 */
package PracticeAndTest.LinkedListAndras;

import FilesAndDirectories.H12_Serialization.GadgetOwner;

/**
 *
 * @author Andras Olah (olahandras78@gmail.com)
 */
public class LinkedListAndras {

	private int pointerPosition; 	//0 --> (itemCount-1)
	private int itemCount;			//4 items = position index: 0-1-2-3

	//Constructor
	public LinkedListAndras() {
		pointerPosition = 0;
		itemCount = 0;
	}

	public int getPointerPosition() {
		return pointerPosition;
	}

	public boolean setPointerPosition(int newPointerPosition) {
		//If itemCount==0 then position=0 is a valid position, otherwise it must be itemcount-1
		if (newPointerPosition >= 0 && newPointerPosition < (itemCount==0?1:itemCount)){ //valid position to set
			this.pointerPosition = newPointerPosition;
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Add an Item to the current position of the list
	 *
	 * @param objectToAdd
	 */
	public void addItem(Object objectToAdd) {

	}

	/**
	 * Remove item from the current position of the list
	 *
	 * @param objectToRemove
	 */
	public void removeItem(Object objectToRemove) {

	}

	/**
	 * Returns the object of the given position
	 *
	 * @param indexToGet the index of the item to get
	 * @return the object at the Index
	 */
	public Object getItem(int indexToGet) {
		return null;
	}

	//Nested class to store the items
	private class LinkedListAndrasItem {

		int index;
		Object previousItem;
		Object currentItem;
		Object nextItem;
	}

	public static void main(String[] args) {
		LinkedListAndras instance = new LinkedListAndras();
	}
}
