package HortonExercises.Ch11.Ex3;

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
public class Ex3_AndrasTest {

	static Ex3_Andras testInstance = new Ex3_Andras();

	public Ex3_AndrasTest() {
	}

	@BeforeClass
	public static void setUpClass() {
		testInstance.readPrimes();
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

	@Test(expected = IllegalArgumentException.class)
	public void testDisplayPrimeRange_IllegalStart() {
		testInstance.displayPrimeRange(101, 10);
	}
	@Test(expected = IllegalArgumentException.class)
	public void testDisplayPrimeRange_IllegalRange() {
		testInstance.displayPrimeRange(90, 100);
	}
}
