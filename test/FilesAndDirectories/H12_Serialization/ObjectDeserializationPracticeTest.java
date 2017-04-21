package FilesAndDirectories.H12_Serialization;

import FilesAndDirectories.H_Ch12_Serialization.ObjectSerializationPractice;
import FilesAndDirectories.H_Ch12_Serialization.ObjectDeserializationPractice;
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
public class ObjectDeserializationPracticeTest {

	//The same path can be used as this test only READS data
	Path testPath = Paths.get("J:\\Serialising Objects\\");

	public ObjectDeserializationPracticeTest() {
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
	 * Test of toString method, of class ObjectSerializationPractice.
	 */
	@Test
	public void testDeserializeResults() {
		//given
		String[] expResult = {"Árvíztűrő tükörfúrógép writeObject()", "Árvíztűrő tükörfúrógép byte[] array", "Árvíztűrő tükörfúrógép char array", "obj 2", null};
		int numberToWrite = 42;
		//when
		String[] actResult = new String[5];
		ArrayList readedBackObjects=ObjectDeserializationPractice.readObject(testPath.resolve("objALL.bin"));
		//Use the second object for the test case
		ObjectSerializationPractice readedBackInstance=(ObjectSerializationPractice) readedBackObjects.get(1); 
		actResult[0] = readedBackInstance.writeObjectTestString;
		actResult[1] = readedBackInstance.writeByteArrayTestString;
		actResult[2] = readedBackInstance.writeCharsTestString;
		actResult[3] = readedBackInstance.objectName;
		actResult[4] = readedBackInstance.transientString;
		//then
		assertArrayEquals(expResult, actResult);
		//the transient field should be null
		assertEquals(null, readedBackInstance.transientString);
	}

}
