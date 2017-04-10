package FilesAndDirectories.Practice;

import java.io.IOException;
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
public class ReadMixedFileWithCompactTest {

	//One object for all test methods
	ReadMixedFileWithCompact testExistingFile = new ReadMixedFileWithCompact();
	ReadMixedFileWithCompact testNonExistingFile = new ReadMixedFileWithCompact();

	public ReadMixedFileWithCompactTest() {
	}

	@BeforeClass
	public static void setUpClass() {
	}

	@AfterClass
	public static void tearDownClass() {
	}

	@Before
	public void setUp() {
		testExistingFile.filename = Paths.get("E:\\javaFileOpTest\\ReadingFiles\\readMixedData(primes).txt");
		testNonExistingFile.filename = Paths.get("E:\\nonexistingfile.txt");
	}

	@After
	public void tearDown() {
	}

	@Test
	public void testCheckFileExists() {
		//given file exitst
		//when calling check method
		//then result must be TRUE
		assertTrue(testExistingFile.checkFileExists());
		//given file does not exists
		//when calling check method
		//then it should return FALSE
		assertFalse(testNonExistingFile.checkFileExists());
	}

	@Test
	public void testOpeningNonExistingFileToReadThenReturnFalse() {
		//given file does not exists
		boolean doesFileExists = false;

		//when checkFileExists returns false  
		try {
			boolean openFileResult = testNonExistingFile.openFile();
			//then
			assertFalse(openFileResult);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Test
	public void testReadFileCorrectResultArrayLength() {
		try {
//given
//[99] Sting length:[011] String:[prime = 541] Long:[541]
			testExistingFile.openFile();
//when 
			int expectedResultArraySize = 100;
			long expectedFinalLongValue = 541;
			String expectedFinalString = "prime = 541";

			ArrayList resultArray = testExistingFile.readFile();
			String lastArray = resultArray.get(expectedResultArraySize - 1).toString();
			String lastLong = lastArray.substring(lastArray.lastIndexOf("[") + 1, lastArray.lastIndexOf("]"));
			long resultFinalLongValue = Long.parseLong(lastLong);

//then
			assertEquals(expectedResultArraySize, resultArray.size());
			assertEquals(expectedFinalLongValue, resultFinalLongValue);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testReadFileDifferentBufferAllocation() {
		try {
//given
			testExistingFile.openFile();
			int minByteBuffer=30;
			int maxByteBuffer=50;
//when 
			int expectedResultArraySize = 100;
			long expectedFinalLongValue = 541;
			for (int i = 0; i < 10; i++) {
				testExistingFile.byteBufferCapacity = (int)(minByteBuffer+Math.random()*maxByteBuffer);

				ArrayList resultArray = testExistingFile.readFile();
				String lastArray = resultArray.get(expectedResultArraySize - 1).toString();
				String lastLong = lastArray.substring(lastArray.lastIndexOf("[") + 1, lastArray.lastIndexOf("]"));
				long resultFinalLongValue = Long.parseLong(lastLong);

//then
				assertEquals(expectedResultArraySize, resultArray.size());
				assertEquals(expectedFinalLongValue, resultFinalLongValue);
				System.out.printf("Test run [%d], byteBuffer capacity [%d] successfull%n", i,testExistingFile.byteBufferCapacity);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
