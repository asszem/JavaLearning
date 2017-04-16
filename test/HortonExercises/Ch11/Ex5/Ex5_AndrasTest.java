package HortonExercises.Ch11.Ex5;

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
public class Ex5_AndrasTest {

	static Ex5_Andras testInstance = new Ex5_Andras();

	public Ex5_AndrasTest() {
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

	//TODO Learn how to write test case for keyboard inputs with System.setIn ...
	@Test
	public void testKeyboardInput() {
	int expResult=2;
	int actResult=2;
		assertEquals(expResult, actResult);
	}

}
