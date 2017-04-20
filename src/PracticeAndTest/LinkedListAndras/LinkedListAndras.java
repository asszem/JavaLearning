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

//import FilesAndDirectories.H12_Serialization.GadgetOwner;
import java.io.Serializable;

/**
 *
 * @author Andras Olah (olahandras78@gmail.com)
 */
public class LinkedListAndras implements Serializable{

	private int listItemNumberPointer; 	//First item number=1
	private int listItemTotal;			//The total number of list items
	private LinkedListAndrasItem firstItem; //the starting point for the linked list objects. the next one will be referenced from this
	//needs to be defined here so that all method can reference it

	//Constructor
	public LinkedListAndras() {
		listItemNumberPointer = 0;
		listItemTotal = 0;
	}

	public int getListItemNumberPointer() {
		return listItemNumberPointer;
	}

	public boolean setPointerPosition(int newPointerPosition) {
		//If listItemTotal==0 then position=0 is a valid position, otherwise it must be itemcount-1
		if (newPointerPosition >= 0 && newPointerPosition < (getListItemTotal() == 0 ? 1 : getListItemTotal())) { //valid position to set
			this.listItemNumberPointer = newPointerPosition;
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Add an Item to the end of the current position of the list
	 *
	 * @param objectToAdd
	 */
	public void addItem(Object objectToAdd) {
		if (listItemTotal == 0) { //adding the very first object to the list
			firstItem = new LinkedListAndrasItem();
			firstItem.listItemNumber = 1; //Starting from 1
			firstItem.previousListItemObject = null;
			firstItem.objectReference = objectToAdd;
			listItemTotal=1; 
			listItemNumberPointer=1; //point to the first item on the list
		} else { //adding a new object and establishing link with previous object
			LinkedListAndrasItem nextItem = new LinkedListAndrasItem(); //create a new object
			nextItem.listItemNumber = ++listItemTotal; //increment the listItemTotal and use it as the listItemNumber of the new object
			nextItem.objectReference = objectToAdd;

			//Establish reference to the PREVIOUS LIST ITEM OBJECT
			nextItem.previousListItemObject = getLinkedListAndrasItem(listItemTotal-1);  
				//2 items, 1st item pos=1, listitemtotal=2
				//3 items, 2nd item pos=2, listitemtotal=3

			//Establish reference IN THE PREVIOUS LIST ITEM OBJECT to the NEW LIST ITEM OBJECT
			getLinkedListAndrasItem(listItemTotal-1).nextListItemObject=nextItem;
			listItemNumberPointer=listItemTotal; //set the pointer to the newly created item
		}
	}

	//TODO to be continued from here
	public void insertItem(Object objectToInsert, int positionToInsert) {

	}

	/**
	 * Remove item from the position of the list
	 *
	 * @param objectToRemove
	 */
	public void removeItem(Object objectToRemove, int positionToRemove) {

	}

	/**
	 * Returns the object of the given position
	 *
	 * @param listItemNumberToGet the listItemNumber of the item to get
	 * @return the object at the Index
	 */
	public Object getItem(int listItemNumberToGet) {
		if (listItemTotal == 0) {
			return null;
		}
		LinkedListAndrasItem checkedListItem = firstItem;
		for (int itemToCheck = 0; itemToCheck < listItemTotal; itemToCheck++) {
			if (listItemNumberToGet == checkedListItem.listItemNumber) { //this is the object what we are looking for
				return checkedListItem.objectReference; //return the object reference to the current item
			}
			//set the checkedListItem object to the next List item object
			checkedListItem=checkedListItem.nextListItemObject;
		}
		//if this point was reached, no item found
		return null;
	}

	//return the linked list object of position THIS IS NOT THE LIST ITEM, it is the object that holds the list item
	private LinkedListAndrasItem getLinkedListAndrasItem(int positionIndex) {
		if (listItemTotal == 0) {
			return null;
		}
		LinkedListAndrasItem checkedListItem = firstItem;
		for (int itemToCheck = 0; itemToCheck < listItemTotal; itemToCheck++) {
			if (positionIndex == checkedListItem.listItemNumber) { //this is the object what we are looking for
				return checkedListItem; //return the LinkedListItem object ITSELF!
			}
			//set the checkedListItem object to the object that was referenced as next list item object
			checkedListItem=checkedListItem.nextListItemObject; //loop starts from 0 so there will always be a next item
		}
		//if this point was reached, no item found
		return null;
	}

	/**
	 * @return the listItemTotal
	 */
	public int getListItemTotal() {
		return listItemTotal;
	}

	/**
	 * @param listItemTotal the listItemTotal to set
	 */
	public void setListItemTotal(int listItemTotal) {
		this.listItemTotal = listItemTotal;
	}

	//Nested class to store the items
	private class LinkedListAndrasItem {

		int listItemNumber;
		LinkedListAndrasItem previousListItemObject;  	//must be another LinkedListAndrasItem object
		Object objectReference; 						//can be any type of objects
		LinkedListAndrasItem nextListItemObject;  		//must be another LinkedListAndrasItem object
	}

	public static void main(String[] args) {
		LinkedListAndras instance = new LinkedListAndras();
	}
}
