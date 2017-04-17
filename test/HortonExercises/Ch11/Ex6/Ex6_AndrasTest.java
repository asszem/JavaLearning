package HortonExercises.Ch11.Ex6;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
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
	static final Ex6_Andras reference = new Ex6_Andras();
	static Path mainTestFile = Paths.get("J:\\Exercises\\Ch11\\Ex6\\Junit\\Junit-Name And Address - Main.txt");
	static Path indexTestFile = Paths.get("J:\\Exercises\\Ch11\\Ex6\\Junit\\Junit-Name And Address - Index.txt");

	public Ex6_AndrasTest() {
	}

	//This is not a test! this is a helper method, if a specific test method needs the files to be deleted
	public static void deleteTestFiles() {
		//Delete test files to make sure all test case start from 0 data
		try {
			Files.deleteIfExists(mainTestFile);
			Files.deleteIfExists(indexTestFile);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	//Not a test, but a helper method to create test files
	public static void createTestFiles() {
		try {
			//Check if test files exists, if not, create them
			if (!Files.exists(mainTestFile) || !Files.exists(indexTestFile)) {
				Files.createDirectories(mainTestFile.getParent());
				Files.createFile(mainTestFile);
				Files.createFile(indexTestFile);
			}
			//Copy latest production file to test file
			Files.copy(instance.mainFile, mainTestFile, StandardCopyOption.REPLACE_EXISTING);
			Files.copy(instance.indexFile, indexTestFile, StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	@BeforeClass
	public static void setUpClass() {
		createTestFiles();
		//not to touch production data, the path replaced in test object to the test files
		instance.mainFile = mainTestFile;
		instance.indexFile = indexTestFile;
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


//	@Ignore //This test only fails because of encoding. If UTF-8 is used in loadUserList method, it works
	@Test
	public void testLoadUserList() {
		//given
		String[][] expUserList = new String[2][4];
		expUserList[0][instance.USER_ID_INDEX] = "ID1";
		expUserList[0][instance.FIRST_NAME_INDEX] = "András";
		expUserList[0][instance.SECOND_NAME_INDEX] = "Oláh";
		expUserList[0][instance.ADDRESS_INDEX] = "Mordor road";
		expUserList[1][0] = "ID2";
		expUserList[1][1] = "Árvíztűrő FirstName";
		expUserList[1][2] = "Tükörfúrógép SecondName";
		expUserList[1][3] = "Second User Address";

		Path loadUserList = Paths.get("J:\\Exercises\\Ch11\\Ex6\\Junit\\Load_Userlist_Testcase.txt");
		instance.mainFile = loadUserList;
		//when
		Ex6_Andras.CharsetToUse="UTF-8";
		instance.loadUserList(); //call the method to get the userlist loaded to the userListARR inst. variable
		String[][] actUserList = instance.userListARR;

		//then
		instance.mainFile = mainTestFile; //reste back to the original test file
		assertArrayEquals(expUserList, actUserList);
	}

	@Test
	public void testAddToUserListArray() {
// public static String[][] addToUserListArray(String[][] inputUserList, String[] arrayToAdd) {
		String[][] startString = {{"ID01", "András", "Oláh", "Mordor road"}};
		String[] stringToAdd = {"ID02", "Árvíztűrő FirstName", "Tükörfúrógép SecondName", "Second User Address"};
		String[][] expString = {{"ID01", "András", "Oláh", "Mordor road"}, {"ID02", "Árvíztűrő FirstName", "Tükörfúrógép SecondName", "Second User Address"}};
		String[][] actString=Ex6_Andras.addToUserListArray(startString, stringToAdd);
		assertArrayEquals(expString, actString);
	}

	@Test
	public void testSplitReadedString() {
		String testString1 = "[ID01][First Name][András][Second Name][Oláh][Address][Mordor road]";
		String testString2 = "[ID02][First Name][Árvíztűrő FirstName][Second Name][Tükörfúrógép SecondName][Address][Second User Address]";
//		ArrayList[] expArrays=new ArrayList[4];
//		expArrays[instance.USER_ID_INDEX].add("ID01");
//		expArrays[instance.FIRST_NAME_INDEX].add("András");
//		expArrays[instance.SECOND_NAME_INDEX].add("Oláh");
//		expArrays[instance.ADDRESS_INDEX].add("Mordor road");
		String[] expString = {"ID01", "András", "Oláh", "Mordor road"};
		String[] actString = Ex6_Andras.splitReadedString(testString1);
		assertArrayEquals(expString, actString);
		String[] expString2 = {"ID02", "Árvíztűrő FirstName", "Tükörfúrógép SecondName", "Second User Address"};
		String[] actString2 = Ex6_Andras.splitReadedString(testString2);
		assertArrayEquals(expString2, actString2);
	}

	@Ignore
	@Test
	public void testCreateNewObject() {
		System.out.println("creating new object");
		int newUserID = instance.userListARR.length + 1;
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
