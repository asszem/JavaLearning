package FilesAndDirectories.H12_Serialization;

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
		String[] actResult= new String[3];
		ObjectSerialization readedBackInstance = new ObjectSerialization("Rea");
		actResult[0]=readedBackInstance.writeObjectTestString;
		actResult[1]=readedBackInstance.writeByteArrayTestString;
		actResult[2]=readedBackInstance.writeCharsTestString;
		//then
		assertArrayEquals(expResult, actResult);
	}

}
