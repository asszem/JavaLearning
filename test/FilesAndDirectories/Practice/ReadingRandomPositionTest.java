package FilesAndDirectories.Practice;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
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
public class ReadingRandomPositionTest {

	static Path inputFile = Paths.get("E:\\javaFileOpTest\\ReadingFiles\\primesTest.bin");

	public ReadingRandomPositionTest() {
	}

	@BeforeClass
	public static void setUpClass() {
		System.out.println("@Before Class");
	}

	@AfterClass
	public static void tearDownClass() {
		System.out.println("@AfterClass");
	}

	@Before
	public void setUp() {
		System.out.println("Before Method");
	}

	@After
	public void tearDown() {
		System.out.println("After method");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testWhenFileDoesntExist() {
		Path badInputFile = Paths.get("E:\\alma.txt");
		ReadingRandomPosition.readLongFromRandomPosition(badInputFile);
	}

}
