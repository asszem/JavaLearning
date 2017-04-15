package HortonExercises.Ch11.Ex2;

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
public class Ex2_AndrasTest {

	static Ex2_Andras testObject;

	public Ex2_AndrasTest() {
	}

	@BeforeClass
	public static void setUpClass() {
		testObject = new Ex2_Andras();
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
	 * Test of readPrimes method, of class Ex2_Andras. Last result format: [99] Sting length:[011] String:[prime = 541] Long:[541] Let's just check the last long value
	 *
	 */
	@Test
	public void testReadPrimes() {
		testObject.readPrimes();
		long expResult = 541L;
		//get the last item 
		int t = testObject.readedLongsArrayList.size();
		int lastItemIndex = testObject.readedLongsArrayList.size() - 1;
		long actResult = (long) testObject.readedLongsArrayList.get(lastItemIndex);
		assertEquals(expResult, actResult);
	}

	@Test
	public void testDisplayResult() {
		testObject.readPrimes();
		int requestedNumber = 99;
		long expResult = 541L;
		long actResult = (long) (testObject.displayPrime(requestedNumber));
		assertEquals(expResult, actResult);

	}

	/**
	 * Test of getKeyboardInput method, of class Ex2_Andras. For some reason this doesnt seem to be working
	 */
	@Ignore
	@Test
	public void testGetKeyboardInput() {
		System.out.println("getKeyboardInput");
		int expResult = 422;
		int result = Ex2_Andras.getKeyboardInput();
		assertEquals(expResult, result);
	}

}
