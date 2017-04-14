package FilesAndDirectories.Practice;

import java.io.File;
import java.io.IOException;
import java.nio.channels.Channel;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import static java.nio.file.StandardOpenOption.READ;
import static java.nio.file.StandardOpenOption.WRITE;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Andras Olah (olahandras78@gmail.com)
 */
//TODO add this method to AllFileOperations when working copy completed
public class FileLocks {

	public static FileLock getExclusiveLock(Path fileToLock) {
		try {
			//try-with-resources would release the lock outside try block!
			FileChannel fileChannel = (FileChannel) Files.newByteChannel(fileToLock, READ, WRITE);
			FileLock isLocked = fileChannel.lock();
			return isLocked;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static FileLock tryExclusiveLock(Path fileToLock, int maxAttempts) {
		FileLock isLocked;
		try {
			//try-with-resources would release the lock outside try block!
			FileChannel fileChannel = (FileChannel) Files.newByteChannel(fileToLock, READ, WRITE);
			for (int attempts = 0; attempts < maxAttempts; attempts++) {
				if ((isLocked = fileChannel.tryLock()).isValid()) {
					//Lock successfully aquired
					return isLocked;
				}
			}
			//If this is reached, no lock was aquired
			return null;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static FileLock trySharedLock(Path fileToLock, int maxAttempts) {
		FileLock isLocked;
		try {
			//try-with-resources would release the lock outside try block!
			FileChannel fileChannel = (FileChannel) Files.newByteChannel(fileToLock, READ, WRITE);
			for (int attempts = 0; attempts < maxAttempts; attempts++) {
				// Wait 200 msec before the next try for a fle lock
				//nested try
				try {
					Thread.sleep(200); // Wait for 200 milliseconds
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				if ((isLocked = fileChannel.tryLock(0L, fileChannel.size(), true)).isValid()) {
					//Lock successfully aquired
					return isLocked;
				}
			}
			//If this is reached, no lock was aquired
			return null;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) {
		Path fileToLock = Paths.get("E:\\javaFileOpTest\\ReadingFiles\\LockFile\\primeToLock.bin");
		FileLock exclusiveLock = getExclusiveLock(fileToLock);
		System.out.println(exclusiveLock.isValid());
		try {
			exclusiveLock.release();
		} catch (IOException ex) {
			Logger.getLogger(FileLocks.class.getName()).log(Level.SEVERE, null, ex);
		}
		System.out.println(exclusiveLock.isValid());
		FileLock sharedLock=trySharedLock(fileToLock, 20);
		System.out.println(sharedLock.isValid());
		try {
			sharedLock.close();
		} catch (IOException ex) {
			Logger.getLogger(FileLocks.class.getName()).log(Level.SEVERE, null, ex);
		}
		System.out.println(sharedLock.isValid());
		sharedLock=trySharedLock(fileToLock, 20);
		System.out.println(sharedLock.isValid());
	}
}
