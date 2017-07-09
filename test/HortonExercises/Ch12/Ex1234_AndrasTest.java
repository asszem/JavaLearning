package HortonExercises.Ch12;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import HortonExercises.Ch12.Person;

import static org.junit.Assert.*;

/**
 *
 * @author Andras Olah (olahandras78@gmail.com)
 */
public class Ex1234_AndrasTest {
	
	public Ex1234_AndrasTest() {
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

	@Test
	public void testSearchPersonObjectByName(){
		String inputNameString="AndrasTeszt";
		Person foundPerson = HortonExercises.Ch12.Ex1234_Andras.searchPersonObjectByName(inputNameString);
		String actualNameString = foundPerson.getPersonNameObject().name;
		assertEquals(inputNameString, actualNameString);
	}
	/**
	 * Test of getKeyboardInput method, of class Ex1234_Andras.
	 */
	@Test
	public void testGetKeyboardInput() {
	}

	/**
	 * Test of createPersonObject method, of class Ex1234_Andras.
	 */
	@Test
	public void testCreatePersonObject() {
	}

	/**
	 * Test of writePersonFile method, of class Ex1234_Andras.
	 */
	@Test
	public void testWritePersonFile() {
	}

	/**
	 * Test of writeIndexEntryFile method, of class Ex1234_Andras.
	 */
	@Test
	public void testWriteIndexEntryFile() {
	}

	/**
	 * Test of readAllObjectsFromFile method, of class Ex1234_Andras.
	 */
	@Test
	public void testReadAllObjectsFromFile() {
	}

	/**
	 * Test of run method, of class Ex1234_Andras.
	 */
	@Test
	public void testRun() {
	}

	/**
	 * Test of main method, of class Ex1234_Andras.
	 */
	@Test
	public void testMain() {
	}
	
}
