package PracticeAndTest.LinkedListAndras;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

/**
 *
 * @author Andras Olah (olahandras78@gmail.com)
 */
public class LinkedListAndrasTest {

	static LinkedListAndras testInstance = new LinkedListAndras();

	public LinkedListAndrasTest() {
	}

	@BeforeClass
	public static void setUpClass() {
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
	 * Test of setPointerPosition method, of class LinkedListAndras.
	 * The method should return -1 when a not existing position was added to the parameter to set
	 */
	@Test
	public void testSetPointerPosition() {
		System.out.println("setPointerPosition");
		boolean result;
		result = testInstance.setPointerPosition(5000);
		assertFalse(result);
		System.out.println("setPointerPosition over tested OK, result: " + testInstance.getPointerPosition());
		result = testInstance.setPointerPosition(-100000);
		assertFalse(result);
		System.out.println("setPointerPosition under tested OK, result: " + testInstance.getPointerPosition());
		result = testInstance.setPointerPosition(0);
		assertTrue(result);
		System.out.println("setPointerPosition 0 tested OK, result: " + testInstance.getPointerPosition());
//Uncomment this when the add method completed
//		result = testInstance.setPointerPosition(1);
//		assertTrue(result);
	}


	/**
	 * Test of addItem method, of class LinkedListAndras.
	 */
	@Ignore
	@Test
	public void testAddItem() {
		System.out.println("addItem");
		Object objectToAdd = null;
		LinkedListAndras instance = new LinkedListAndras();
		instance.addItem(objectToAdd);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of removeItem method, of class LinkedListAndras.
	 */
	@Ignore
	@Test
	public void testRemoveItem() {
		System.out.println("removeItem");
		Object objectToRemove = null;
		LinkedListAndras instance = new LinkedListAndras();
		instance.removeItem(objectToRemove);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of getItem method, of class LinkedListAndras.
	 */
	@Ignore
	@Test
	public void testGetItem() {
		System.out.println("getItem");
		int indexToGet = 0;
		LinkedListAndras instance = new LinkedListAndras();
		Object expResult = null;
		Object result = instance.getItem(indexToGet);
		assertEquals(expResult, result);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of main method, of class LinkedListAndras.
	 */
	@Ignore
	@Test
	public void testMain() {
		System.out.println("main");
		String[] args = null;
		LinkedListAndras.main(args);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

}
