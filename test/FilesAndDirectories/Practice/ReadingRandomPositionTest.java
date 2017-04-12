package FilesAndDirectories.Practice;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
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

	static Path inputFile = Paths.get("E:\\javaFileOpTest\\ReadingFiles\\replacePrimesTest.bin");
	static String toAppend = "_rplcLongJunit";
	static Path resultFile = AppendStringToFileName.appendStringToFileName(inputFile, toAppend);

	public ReadingRandomPositionTest() {
	}

	@BeforeClass
	public static void setUpClass() {
		System.out.println("@Before Class");
		System.out.println("Input file:" + inputFile);
		System.out.println("Result file:" + resultFile);
	}

//	@AfterClass
	public static void tearDownClass() {
		System.out.println("@AfterClass");
		System.out.println("Result file deleted.");
		try {
			Files.delete(resultFile);
		} catch (IOException e) {
			e.printStackTrace();
		}

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

	/**
	 * Test of replaceLongAtRandomPosition method, of class ReadingRandomPosition. The correct result file is created
	 */
	@Test
	public void testIfReadLongFromRandomPositionCreatesTheCorrectResultFile() {
		System.out.println("readLongFromRandomPosition");
		ReadingRandomPosition.replaceLongAtRandomPosition(inputFile, toAppend);
		boolean isNewFileCreated = Files.exists(resultFile);
		assertTrue(isNewFileCreated);
	}

}
