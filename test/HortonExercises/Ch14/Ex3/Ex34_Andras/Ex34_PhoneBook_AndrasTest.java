package HortonExercises.Ch14.Ex3.Ex34_Andras;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Andras Olah (olahandras78@gmail.com)
 */
public class Ex34_PhoneBook_AndrasTest {
	
	PhoneBook book = new PhoneBook();            
	public Ex34_PhoneBook_AndrasTest() {
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
	 * Test of main method, of class Ex34_PhoneBook_Andras.
	 */
	@Test
	public void testByPerson() {
		Person testPerson = new Person("testFirstName", "testSurname");
		assertNotNull(book.getEntry(testPerson));
	}
	@Test
	public void testByPhoneNumber() {
		PhoneNumber testNumber= new PhoneNumber("0", "0 0");
		assertNotNull(book.getEntry(testNumber));
	}

	@Test
	public void testBySurname(){
		String surname="testSurname";
		assertNotNull(book.getEntry(surname));
	}

	@Test
	public void testByMultipleSurname(){
		String surname="testSurname";
		assertNotNull(book.getEntry(surname));
		assertEquals(2, book.getEntry(surname).size());
	}
	
}
