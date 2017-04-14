package FilesAndDirectories.Practice;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class MemoryMappedFileTest {

	static MemoryMappedFile testObject;
	static Path testFile = Paths.get("E:\\javaFileOpTest\\ReadingFiles\\MemoryMapped\\replacePrimesOriginalTest.bin");

	public MemoryMappedFileTest() {
	}

	@BeforeClass
	public static void setUpClass() {
		System.out.println("Inicializing Junit tests...");
		testObject = new MemoryMappedFile();
		testObject.originalFile = testFile;
	}

	@AfterClass
	public static void tearDownClass() {
	}

	@Before
	public void setUp() {
	}

	@After
	public void tearDown() {
		testObject.originalFile = testFile; //in case a test case changes the test file, change it back to the original
		//Delete the test files that are being created after each test
		//TODO Find out why access denied exception is thrown
		if (testObject.newFile != null) {
			try {
				testObject.mappedByteBuffer=null;
//				System.gc();
				Files.delete(testObject.newFile);
				testObject.newFile = null;
			} catch (IOException ex) {
				System.out.println(ex);
//				Logger.getLogger(MemoryMappedFileTest.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
	}

	@Test(expected = IllegalArgumentException.class)
	public void testCreateMappedByteBuffer_InputFileDoesntExists() {
		testObject.originalFile = testFile.resolve("xxxNonExistingFilexxx");
		testObject.createMappedByteBuffer();
	}

	/**
	 * Test whether the size of the file (in bytes) can be divided by 8 (LONG=8 BYTES)
	 */
	@Test
	public void testCreateMappedByteBuffer_LongCountIsValid() {
		testObject.createMappedByteBuffer(); //run the method to get value for mappedByteBuffer
		assertEquals(0, testObject.fileSize % 8);
	}

	@Test
	public void testCreateMappedByteBuffer_FileOpenedForReadAndWrite() {
		testObject.createMappedByteBuffer(); //run the method to get value for mappedByteBuffer
		assertFalse(testObject.mappedByteBuffer.isReadOnly());
	}

	@Test
	public void testReplaceRandomLongs() {
		//given
		testObject.createMappedByteBuffer();
		assertEquals(testObject.numOfLongsToBeReplaced, testObject.replaceRandomLongs().length);
	}
}
