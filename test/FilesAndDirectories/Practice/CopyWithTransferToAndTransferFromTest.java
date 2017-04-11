package FilesAndDirectories.Practice;

import java.io.IOException;
import java.nio.file.Files;
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
public class CopyWithTransferToAndTransferFromTest {

	Path sourceFile = Paths.get("E:\\javaFileOpTest\\CreateBackupFile\\test.txt");
	Path targetFile = CreateBackupPath.getBackupPath(sourceFile);

	public CopyWithTransferToAndTransferFromTest() {
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

	@Test(expected = IllegalArgumentException.class)
	public void testCopyFileWhenSourceNotExists() {
		//given
		Path invalidSourceFile = Paths.get("E:\\doesnotexists");
		Path invalidTargetFile = Paths.get("E:\\doesnotexists");
		//when
		//then
		CopyWithTransferToAndTransferFrom.copyFile(invalidSourceFile, invalidTargetFile);
	}

	@Test
	public void testCopyFilesResultFileSizeEqualsSourceFileSize() {
		try {
			//given
			long sourceFileLength = Files.size(sourceFile);
			//when
			CopyWithTransferToAndTransferFrom.copyFile(sourceFile, targetFile);
			long targetFileLength=Files.size(targetFile);
			//then
			assertEquals(sourceFileLength, targetFileLength);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
