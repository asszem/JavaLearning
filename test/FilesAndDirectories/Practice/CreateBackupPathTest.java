package FilesAndDirectories.Practice;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
public class CreateBackupPathTest {

	public CreateBackupPathTest() {
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
	 * Test of getBackupPath method, of class CreateBackupPath.
	 * This test runs only when there is no filename_backup.ext file created yet
	 */
	@Test
	public void testGetBackupPathAtFirstIterationWithExtension() {
		System.out.println("testGetBackupPath");
		//given
		Path originalFilename = Paths.get("E:\\javaFileOpTest\\CreateBackupFile\\Original File for BackupTest.txt");
		//when
		Path expResult = Paths.get("E:\\javaFileOpTest\\CreateBackupFile\\Original File for BackupTest_backup.txt");
		//then
		//Run the test only if the backup file does not exists yet
		if (!Files.exists(expResult)) {
			System.out.println("Actually testing");
			Path result = CreateBackupPath.getBackupPath(originalFilename);
			assertEquals(expResult, result);
		} else {
			System.out.println("Skipping this test, file already exists");
			System.out.println(expResult);
		}
	}
	@Test
	public void testGetBackupPathAtFirstIterationWithOutExtension() {
		System.out.println("testGetBackupPath for a file without extension");
		//given
		Path originalFilename = Paths.get("E:\\javaFileOpTest\\CreateBackupFile\\Original File for BackupTest");
		//when
		Path expResult = Paths.get("E:\\javaFileOpTest\\CreateBackupFile\\Original File for BackupTest_backup");
		//then
		//Run the test only if the backup file does not exists yet
		if (!Files.exists(expResult)) {
			System.out.println("Actually testing");
			Path result = CreateBackupPath.getBackupPath(originalFilename);
			assertEquals(expResult, result);
		} else {
			System.out.println("Skipping this test, file already exists");
			System.out.println(expResult);
		}
	}
	
	/**
	 * Test of getBackupPath method, of class CreateBackupPath.
	 * This test validates if _backup is appended to the latest _backup file if that exists
	 */
	@Test
	public void testGetBackupPathAfterMultipleIterations() {
		System.out.println("testGetBackupPath with multiple iterations");
		//given
		Path originalFilenameWithExtension = Paths.get("E:\\javaFileOpTest\\CreateBackupFile\\test.txt");
		Path originalFilenameWithOUTExtension = Paths.get("E:\\javaFileOpTest\\CreateBackupFile\\test");
		//when
		Path alreadyExistsWithExtension= Paths.get("E:\\javaFileOpTest\\CreateBackupFile\\test_backup.txt");
		Path alreadyExistsWithOUTExtension= Paths.get("E:\\javaFileOpTest\\CreateBackupFile\\test_backup");
		//then
		Path expResultWithExtension =Paths.get("E:\\javaFileOpTest\\CreateBackupFile\\test_backup_backup.txt"); 
		Path expResultWithOUTExtension = Paths.get("E:\\javaFileOpTest\\CreateBackupFile\\test_backup_backup");
		if (Files.exists(alreadyExistsWithExtension) && Files.exists(alreadyExistsWithOUTExtension)) {
			System.out.println("test_backup already exits");
			Path resultWithExtension = CreateBackupPath.getBackupPath(originalFilenameWithExtension);
			assertEquals(expResultWithExtension, resultWithExtension);
			Path resultWithOUTExtension = CreateBackupPath.getBackupPath(originalFilenameWithOUTExtension);
			assertEquals(expResultWithOUTExtension, resultWithOUTExtension);
		} else {
			System.out.println("Skipping this test, test_backup files do not exists");
		}
	}

}
