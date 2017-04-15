package HortonExercises.Ch11.Ex1;

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
public class Ex1_AndrasTest {

	static Ex1_Andras testObject;
	static String[] expArray = {
		"Indecision maximizes flexibility.",
		"Only the mediocre are always at their best.",
		"A little knowledge is a dangerous thing.",
		"Many a mickle makes a muckle.",
		"Who begins too much achieves little.",
		"Who knows most says least.",
		"A wise man sits on the hole in his carpet.",
		"És ezt még én írtam hozzá",
		"1",
		"22",
		"333"};

	public Ex1_AndrasTest() {
	}

	@BeforeClass
	public static void setUpClass() {
		testObject = new Ex1_Andras();
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
	public void readLines() {
		testObject.readLines();
		assertArrayEquals(expArray, testObject.readLines);
	}

	@Test
	public void testAddToArray() {
		String[] initialArray = {"String1"};
		String[] expArray = {"String1", "NewString"};
		String[] resultArray = Ex1_Andras.addToArray(initialArray, "NewString");
		assertArrayEquals(expArray, resultArray);

	}
}
