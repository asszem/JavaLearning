package HortonExercises.Ch11.Ex6;

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
public class Ex6_AndrasTest {

	static Ex6_Andras instance = new Ex6_Andras();
	static Path mainFile = Paths.get("J:\\Exercises\\Ch11\\Ex5\\Junit\\Junit-Name And Address - Main.txt");
	static Path indexFile = Paths.get("J:\\Exercises\\Ch11\\Ex5\\Junit\\Junit-Name And Address - Index.txt");

	public Ex6_AndrasTest() {
	}

	@BeforeClass
	public static void setUpClass() {
		//not to touch production data
		instance.mainFile = mainFile;
		instance.indexFile = indexFile;
	}

	@AfterClass
	public static void tearDownClass() {
		/*
		//Delete test files to make sure all test case start from 0 data
		try {
			Files.deleteIfExists(mainFile);
			Files.deleteIfExists(indexFile);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		 */
	}

	@Before
	public void setUp() {
	}

	@After
	public void tearDown() {
	}

	//TODO To be continued from here - setup manually existing result files to test
	@Test
	public void testExistingEntriesCounter() {
	int expResult=4;
	int actResult=instance.getExistingEntryNumberFromFile();
		assertEquals(expResult, actResult);
	}

	@Ignore
	@Test
	public void testCreateNewObject() {
		System.out.println("creating new object");
		int newUserID = instance.userList.length + 1;
		String userInputFirstName = "FirstName:ÁÁÁ";
		String userInputSecondName = "SecondNameÉÉÉ";
		String userInputAddress = "AddressTest";
		boolean expResult = false;
		boolean result = instance.writeData(userInputFirstName, userInputSecondName, userInputAddress);
		assertEquals(expResult, result);
//	instance.writeData(userInputFirstName, userInputSecondName, userInputAddress)
	}

	/**
	 * Test of writeData method, of class Ex6_Andras.
	 */
	@Ignore
	@Test
	public void testWriteData() {
//[id]=1[name]=Andras Olah [address]=address
		System.out.println("writeData");
		String userInputFirstName = "FirstName:ÁÁÁ";
		String userInputSecondName = "SecondNameÉÉÉ";
		String userInputAddress = "AddressTest";
		boolean expResult = false;
		boolean result = instance.writeData(userInputFirstName, userInputSecondName, userInputAddress);
		assertEquals(expResult, result);
	}

	/**
	 * Test of displayFullMainFile method, of class Ex6_Andras.
	 */
	@Ignore
	@Test
	public void testReadData() {
		System.out.println("readData");
		Ex6_Andras instance = new Ex6_Andras();
		instance.displayFullMainFile();
	}

}
