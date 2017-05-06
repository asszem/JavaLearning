package PracticeAndTest.LinkedListAndras;

import ClassesAndObjects.Practice.H_Ch13_GenericTypes.HortonsHomemadeLinkedLists.AndrasLinkedLists.BasicLinkedListAndras;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;
import FilesAndDirectories.H_Ch12_Serialization.GadgetOwner;
import FilesAndDirectories.H_Ch12_Serialization.Mobile;
import FilesAndDirectories.H_Ch12_Serialization.Smartwatch;

/**
 *
 * @author Andras Olah (olahandras78@gmail.com)
 */
public class LinkedListAndrasTest {

	static BasicLinkedListAndras testInstance = new BasicLinkedListAndras();

	public LinkedListAndrasTest() {
	}
	static GadgetOwner[] gadgetOwners = new GadgetOwner[10];

	@BeforeClass
	public static void setUpClass() {
		//Create an array of 10 Gadget Owner objects, with unique identifier in each
		for (int i = 0; i < 10; i++) {
			gadgetOwners[i] = new GadgetOwner("TestOwner Number #" + i);
			gadgetOwners[i].mobile = new Mobile(gadgetOwners[i].gadgetOwnerName);
			gadgetOwners[i].smartwatch = new Smartwatch(gadgetOwners[i].gadgetOwnerName);
		}
		System.out.println("GadgetOwner objects created");
	}

	@AfterClass
	public static void tearDownClass() {
	}

	@Before
	public void setUp() {
	}

	@After
	public void tearDown() {
	}

	/**
	 * Test of setPointerPosition method, of class BasicLinkedListAndras. The method should return -1 when a not existing position was added to the parameter to set
	 */
	@Test
	public void testSetPointerPosition() {
		System.out.println("setPointerPosition test");
		boolean result;
		result = testInstance.setPointerPosition(5000);
		assertFalse(result);
		System.out.println("setPointerPosition over tested OK, result: " + testInstance.getListItemNumberPointer());
		result = testInstance.setPointerPosition(-100000);
		assertFalse(result);
		System.out.println("setPointerPosition under tested OK, result: " + testInstance.getListItemNumberPointer());
		result = testInstance.setPointerPosition(0);
		assertTrue(result);
		System.out.println("setPointerPosition 0 tested OK, result: " + testInstance.getListItemNumberPointer());
//Uncomment this when the add method completed
//		result = testInstance.setPointerPosition(1);
//		assertTrue(result);
	}

	/**
	 * Test of addItem method, of class BasicLinkedListAndras.
	 */
	@Test
	public void testAddItem() {
		System.out.println("addItem test");
		Object objectToAdd = gadgetOwners[0];
		BasicLinkedListAndras instance = new BasicLinkedListAndras();
		int positionBefore = instance.getListItemNumberPointer();
		int counterBefore = instance.getListItemTotal();
		instance.addItem(objectToAdd);
		int positionAfter = instance.getListItemNumberPointer();
		int counterAfter = instance.getListItemTotal();
		GadgetOwner resultObject = (GadgetOwner) instance.getItem(positionAfter); //the position is updated to the position of the new item
		assertEquals(positionBefore, positionAfter - 1); //position after value is greater with 1 
		System.out.println("addItem position test OK");
		assertEquals(counterBefore, counterAfter - 1); //counter after value is greater with 1 
		System.out.println("addItem counter test OK");
		assertEquals(objectToAdd, resultObject);  //The result object equals the input object
		System.out.println("addItem object test OK");
	}

	/**
	 * Test of removeItem method, of class BasicLinkedListAndras.
	 */
	@Ignore
	@Test
	public void testRemoveItem() {
		System.out.println("removeItem");
		Object objectToRemove = null;
		BasicLinkedListAndras instance = new BasicLinkedListAndras();
//		instance.removeItem(objectToRemove);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of getItem method, of class BasicLinkedListAndras.
	 */
	@Test
	public void testGetItem() {
		System.out.println("getItem test");
		BasicLinkedListAndras instance = new BasicLinkedListAndras();
		Object objectToAdd;
		//add all 10 objects to the list
		for (int i = 0; i < 10; i++) {
			instance.addItem(gadgetOwners[i]);
		}
		//test objcet 2 and 7
		int testIndex = 2; //test index= 2, item number = 3
		for (; testIndex < 10; testIndex++) {
			GadgetOwner resultObject = (GadgetOwner) instance.getItem(testIndex+1);
			assertEquals(gadgetOwners[testIndex], resultObject);  //The result object equals the input object
			System.out.printf("get item %d OK%n", testIndex);
		}
	}

	/**
	 * Test of main method, of class BasicLinkedListAndras.
	 */
	@Ignore
	@Test
	public void testMain() {
		System.out.println("main");
		String[] args = null;
		BasicLinkedListAndras.main(args);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

}
