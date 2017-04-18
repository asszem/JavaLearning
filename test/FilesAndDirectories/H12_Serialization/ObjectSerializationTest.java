package FilesAndDirectories.H12_Serialization;

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
public class ObjectSerializationTest {

	Path testPath = Paths.get("J:\\Serialising Objects\\Junit tests\\");

	public ObjectSerializationTest() {
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
	 * Test of toString method, of class ObjectSerialization.
	 */
	@Test
	public void testDeserializeResults() {
		//given
		String[] expResult = {"Árvíztűrő tükörfúrógép writeObject()", "Árvíztűrő tükörfúrógép byte[] array", "Árvíztűrő tükörfúrógép char array"};
		int numberToWrite = 42;
		//when
		String[] actResult = new String[3];
		ObjectSerialization testObject = new ObjectSerialization("Junit Test Object");
		testObject.serializeObject(testPath.resolve("JunitTest1.bin"));
		ObjectSerialization readedBackInstance = new ObjectSerialization("Rea");
		actResult[0] = readedBackInstance.writeObjectTestString;
		actResult[1] = readedBackInstance.writeByteArrayTestString;
		actResult[2] = readedBackInstance.writeCharsTestString;
		//then
		assertArrayEquals(expResult, actResult);
		//the transient field should be null
		assertEquals(0, readedBackInstance.transientString);
	}

}
