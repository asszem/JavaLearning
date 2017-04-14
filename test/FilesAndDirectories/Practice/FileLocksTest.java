package FilesAndDirectories.Practice;

import java.io.IOException;
import java.nio.channels.FileLock;
import java.nio.channels.OverlappingFileLockException;
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
public class FileLocksTest {

	static Path fileToLock = Paths.get("E:\\javaFileOpTest\\ReadingFiles\\LockFile\\primeToLock.bin");
	static FileLock getFirstLock;
	static FileLock getSecondLock;

	public FileLocksTest() {
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
		try {
			if (getFirstLock != null) {
				getFirstLock.release();
			}
			if (getSecondLock != null) {
				getSecondLock.release();
			}
		} catch (IOException ex) {
			Logger.getLogger(FileLocksTest.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	@Test(expected = OverlappingFileLockException.class)
	public void testTryExclusiveLock_unsuccessfull() {
		getFirstLock = FileLocks.getExclusiveLock(fileToLock);
		getSecondLock = FileLocks.tryExclusiveLock(fileToLock, 10);
	}

	@Test
	public void testTrySharedLock_unsuccessfull() {
		getFirstLock = FileLocks.trySharedLock(fileToLock, 10);
		getSecondLock = FileLocks.trySharedLock(fileToLock, 10);
		assertFalse(getSecondLock.isValid());
	}

}
