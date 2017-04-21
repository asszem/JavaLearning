/*This class has sample methods for all relevant file operations

- Get and set Path objects - setPath()
- Get FileSystem properties -  printFileSystemProperties()
- Get a list of directory items - listdir(Path dirPath, String filter)
- Get a file attribute - fileAttributes - fileAttributes (Path)
- Create a new file or new directory - createSingleDirectory(Path), createSingleFile(Path)
- Create multiple new directories  - createMultipleDirectories(Path)
- Copy, move and rename a file or a directory - copySingleFile(), moveSingleFile(), renameSingleFile()
- Copy, move all files from a directory to another directory - copyMultipleFiles(), MoveMultipleFiles()
- Delete a single file or a single directory - deleteSingleFileOrDir()
- Delete all files from a directory - deleteMultipleFiles()
- Delete all directories from a path 

Code samples:
BasicFileAttributes fileTulajdonsagok = Files.readAttributes(p, BasicFileAttributes.class);
fileTulajdonsagok.isDirectory()

DirectoryStream<Path> mappaStream = (filter == null ? Files.newDirectoryStream(dirPath) : Files.newDirectoryStream(dirPath, filter));

Files.copy(source, target.resolve(source.getFileName()));
Files.move()
Files.delete
Files.create
Files.exists

Inserting to a filename before the extension
	int indexOfExtension = input.lastIndexOf(".");
		//Return the file name to the array index 0
		removedExtension[0]=input.substring(0, indexOfExtension);
		//Return the extension to the array index 1
		removedExtension[1]=input.substring(indexOfExtension,input.length());
	Another method with StringBuilder
			StringBuilder newFileName = new StringBuilder(input);
			newFileName.insert(input.lastIndexOf("."), toBeInserted);
			return newFileName.toString();
 */
package FilesAndDirectories;

import java.nio.file.FileAlreadyExistsException;
import java.nio.file.NoSuchFileException;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.file.DirectoryStream; //A mappa tartalmának kilistázásához kell

import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes; //Interface - cannot be instantized to an object

//FileSystem objektum létrehozásához kell
import java.nio.file.FileStore;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;

//Path objektum létrehozásához kell
import java.nio.file.Path; //Interface
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import static java.nio.file.StandardOpenOption.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Andras Olah (olahandras78@gmail.com)
 */
public class AllFileOperations {

	Path objectPath; //Sets an instance variable called objectPath

//Prints out the filesystem properties
	public static void printFileSystemProperties() {
		FileSystem fajlRendszer = FileSystems.getDefault();	//Nincs NEW.
		Iterable<FileStore> fajlStoreok = fajlRendszer.getFileStores();
		final long GIGABYTE = 1_073_741_824L;
		for (FileStore fajlStoreIteracio : fajlStoreok) {
			try {
				System.out.printf("Meghajtónév: %s%nSzabad hely: %dGB%n", fajlStoreIteracio.name(), fajlStoreIteracio.getUsableSpace() / GIGABYTE);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

//Return true if the file exists and it's size is not zero
	public boolean checkIfFileExistsAndNonZeroSize(Path fileToCheck) {
		try {
			// If the file exists and its size is non-zero, it contains data so we are appending
			if (Files.exists(fileToCheck) && Files.readAttributes(fileToCheck, BasicFileAttributes.class).size() > 0) {
				return true;
			}
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		return false;
	}

//Prints out the content of the directory
	public static void listDir(Path dirPath, String filter) {
		System.out.println("\n*****ListDir method started*****");
		System.out.printf("%s (%s)%n", dirPath, filter == null ? "*.*" : filter);
		try {
			DirectoryStream<Path> mappaStream = filter == null ? Files.newDirectoryStream(dirPath) : Files.newDirectoryStream(dirPath, filter);
			int counter = 0;
			String isDir = "[DIR]";
			for (Path p : mappaStream) {
				//Check if the element in p path is a file or a directory
				BasicFileAttributes attr = Files.readAttributes(p, BasicFileAttributes.class);
				//Print out each element with a counter and append [DIR] if it is a directory
				System.out.println(" #" + (++counter) + " " + p + (attr.isDirectory() ? isDir : ""));
			}
			System.out.println("\n*****End of ListDir*****");
		} catch (IOException e) {
			System.out.println("IOException in listDir method." + e);
			e.printStackTrace();
		}
	}

//Prints out the basic file attributes of a file or directory
	public static void fileAttributes(Path filePath) {
		try {
			BasicFileAttributes a = Files.readAttributes(filePath, BasicFileAttributes.class);
			System.out.println("The " + filePath + (a.isDirectory() ? " is a Directory" : " is a File"));
			System.out.printf("Size:%d%nLast modified:%s%nLast accessed:%s%nCreation time:%s%n", a.size(), a.lastModifiedTime(), a.lastAccessTime(), a.creationTime());
		} catch (IOException e) {
			System.out.println("IOException in fileAttributes method" + e);
			e.printStackTrace();
		}
	}

//Returns true if p is a Directory
	public static boolean isDirectory(Path p) {
		try {
			BasicFileAttributes fileTulajdonsagok = Files.readAttributes(p, BasicFileAttributes.class);
			return fileTulajdonsagok.isDirectory();
		} catch (IOException e) {
			System.out.println("IO exception in isDirectory method. " + e);
		}
		return false;
	}

//Returns true if creating a single directory completed successfully
	public static boolean createSingleDirectory(Path path) {
		try {
			Files.createDirectory(path);
			path = path.toAbsolutePath(); //to make sure path is absolute
			System.out.println("\n" + path + " directory created.");
			return true;
		} catch (NoSuchFileException e) {
			System.err.println("\nDirectory creation failed:\n" + e);
		} catch (FileAlreadyExistsException e) {
			System.err.println("\nDirectory creation failed:\n" + e);
		} catch (IOException e) { //must be last, because other exceptions extend to this
			System.err.println("\nDirectory creation failed:\n" + e);
		}
		return false; //If this is reached the directory was not created
	}

//Returns true if multiple directories created successfully
	public static boolean createMultipleDirectories(Path path) {
		try {
			Files.createDirectories(path);
			path = path.toAbsolutePath();
			System.out.println("\n" + path + " directory created.");
			return true;
		} catch (IOException e) {
			System.err.println("\nDirectory creation failed:\n" + e);
		}
		return false;
	}

//Returns true if a single file creation was successfull
	public static boolean createSingleFile(Path path) {
		try {
			Files.createFile(path);
			path = path.toAbsolutePath(); //to make sure path is absolute
			System.out.println("\n" + path + " file created.");
			return true;
		} catch (NoSuchFileException e) {
			System.err.println("\nfile creation failed:\n" + e);
		} catch (FileAlreadyExistsException e) {
			System.err.println("\nfile creation failed:\n" + e);
		} catch (IOException e) { //must be last, because other exceptions extend to this
			System.err.println("\nfile creation failed:\n" + e);
		}
		return false; //If this is reached the file was not created
	}

//Create single file and if required, all directories in the provided path
	public static void createSingleFileWithParentDirectories(Path filename) {
		try {
			Files.createDirectories(filename.getParent()); //if parent already exists, this does nothing
			Files.createFile(filename);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

//Sets the path based on a String parameter
	public void setPath(String pathToSet) {
		this.objectPath = Paths.get(pathToSet);
	}

//Returns true if file successfully copied
	public static boolean copySingleFile(Path source, Path target) {
		System.out.printf("Copy Single File%nSource:%s%nTarget:%s%n", source, target.resolve(source.getFileName()));
		if (isDirectory(source)) {
			System.out.println("Can not copy file. Source is not a file.");
			return false;
		} else if (!isDirectory(target)) {
			System.out.println("Can not copy file. Target is not a directory.");
			return false;
		}
		try {
			Files.copy(source, target.resolve(source.getFileName()));
			System.out.println("File copied.");
			return true;
		} catch (IOException e) {
			System.out.println("IOException in copySingleFile method" + e);
		}
		return false;
	}

//Uses transferTo method between direct channels which is more effective
	public static void copyFile(Path sourceFile, Path targetFile) {
		if (!Files.exists(sourceFile)) {
			throw new IllegalArgumentException("Source file does not exists.");
		}
		try (FileChannel readFromChannel = (FileChannel) Files.newByteChannel(sourceFile, READ);
				FileChannel writeToChannel = (FileChannel) Files.newByteChannel(targetFile, WRITE, CREATE_NEW);) {
			long bytesWritten = 0;
			long sourceFileSize = readFromChannel.size();
			//This works also:
			//readFromChannel.transferTo(bytesWritten, sourceFileSize, writeToChannel);
			//This makes sure all bytes are transferred and keeps count of bytes sent
			while (bytesWritten < sourceFileSize) {
				bytesWritten += readFromChannel.transferTo(bytesWritten, sourceFileSize - bytesWritten, writeToChannel);
				System.out.println("");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

//Returns true if all files successfully copied
	public static boolean copyMultipleFiles(Path source, Path target, String filter) {
		if (!isDirectory(source)) {
			System.out.println("Can not copy file. Source is not a directory.");
			return false;
		} else if (!isDirectory(target)) {
			System.out.println("Can not copy file. Target is not a directory.");
			return false;
		}
		if (filter == null) {
			System.out.println("Filter must be provided. *.* for all");
			return false;
		}
		try {
			System.out.printf("Copy %s%nfrom%n%s%nto%n%s", filter, source, target);
			DirectoryStream<Path> ds = Files.newDirectoryStream(source, filter);
			for (Path p : ds) {
				//Adding a second try block to keep the loop going if FileAlreadyExists occurs during copy
				try {
					Files.copy(p, target.resolve(p.getFileName())); //to append the current filename}
					System.out.println("File copied: " + p.getFileName());
				} catch (FileAlreadyExistsException e) {
					System.out.println("File already exists: " + p.getFileName());
				} catch (IOException e) {
					System.out.println("IOException during copy." + e);
				}
			}
		} catch (IOException e) {
			System.out.println("IOException in copyMultipleFiles method" + e);
		}
		return false;
	}

//Returns true if file move completed. 3rd argument can be "replace" to make move replace
	public static boolean moveSingleFile(Path source, Path target, String options) {
		System.out.println("Single file move method started...");
		System.out.printf("Source: %s%nTarget: %s%nOptions: %s%n", source, target, options);
		if (isDirectory(source)) {
			System.out.println("Error! Source is a directory!");
			return false;
		}
		if (!isDirectory(target)) {
			System.out.println("Error! Target is not a directory!");
			return false;
		}
		try {
			Files.move(source, target.resolve(source.getFileName()), options.toLowerCase() == "replace" ? StandardCopyOption.REPLACE_EXISTING : StandardCopyOption.ATOMIC_MOVE);
			System.out.println("File moved successfully");
			return true;
		} catch (NoSuchFileException e) {
			System.err.println("No such file exception!" + e.getMessage());
		} catch (FileAlreadyExistsException e) {
			System.err.println("File already exists." + e);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

//Returns true if file move completed. 4th argument can be "replace" to make move replace
	public static boolean moveMultipleFiles(Path source, Path target, String filter, String options) {
		System.out.println("Moving multiple files method started...");
		if (!isDirectory(source)) {
			System.out.println("Can not move files. Source is not a directory.");
			return false;
		} else if (!isDirectory(target)) {
			System.out.println("Can not move files. Target is not a directory.");
			return false;
		}
		if (filter == null) {
			System.out.println("Filter must be provided. *.* for all");
			return false;
		}
		try {
			System.out.printf("Move %s%nfrom%n%s%nto%n%s%n", filter, source, target);
			DirectoryStream<Path> ds = Files.newDirectoryStream(source, filter);
			for (Path p : ds) {
				//Adding a second try block to keep the loop going if FileAlreadyExists occurs during copy
				try {
					Files.move(p, target.resolve(p.getFileName()), options.toLowerCase() == "replace" ? StandardCopyOption.REPLACE_EXISTING : StandardCopyOption.ATOMIC_MOVE); //to append the current filename}
					System.out.println("File moved: " + p.getFileName());
				} catch (FileAlreadyExistsException e) {
					System.out.println("File already exists: " + p.getFileName());
				} catch (IOException e) {
					System.out.println("IOException during copy." + e);
				}
			}
			System.out.println("All files moved successfully.");
			return true;
		} catch (IOException e) {
			System.out.println("IOException in moveMultipleFiles method" + e);
		}
		return false;
	}

//Returns true if deleting a single file completed successfully
	public static boolean deleteSingleFileOrDir(Path source) {
		System.out.printf("File: %s%n", source);
		try {
			Files.delete(source);
			System.out.println("Delete completed.");
			return true;
		} catch (NoSuchFileException e) {
			System.err.println("Delete failed." + e);
		} catch (IOException e) {
			System.err.println("I/O error." + e);
		}
		return false; //Ha ide jut, akkor false legyen. 
	}

//Returns true if ALL files deleted successfully. If filter is null, *.* included
	public static boolean deleteMultipleFiles(Path source, String filter) {
		if (!isDirectory(source)) {
			System.out.println("Can not delete files. Source is not a directory.");
			return false;
		}
		if (filter == null) {
			filter = "*.*";
		}
		try {
			System.out.printf("Delete %s%nfrom%n%s%n%n", filter, source);
			DirectoryStream<Path> ds = Files.newDirectoryStream(source, filter);
			for (Path p : ds) {
				try {
					Files.delete(p); //to append the current filename}
					System.out.println("File deleted: " + p.getFileName());
				} catch (IOException e) {
					System.out.println("IOException during delete." + e);
				}
			}
			System.out.println("Files deleted successfully");
			return true;
		} catch (IOException e) {
			System.out.println("IOException in copyMultipleFiles method" + e);
		}
		return false;
	}

//Returns true if rename completed successfully
	public static boolean renameSingleFile(Path source, Path target) {
		System.out.println("Rename Single File Method Started");
		System.out.printf("Old name: %s%nNew name:%s%n", source, target);
		Path parentSource = source.getParent();
		Path parentTarget = target.getParent();
		System.out.printf("Old parent: %s%nNew parent: %s%n", parentSource, parentTarget);
		if (parentSource.compareTo(parentTarget) != 0) {
			System.out.println("Error! The target parent is not the same as the source parent");
			return false;
		}
		try {
			Files.move(source, target);
			System.out.println("Rename completed.");
			return true;
		} catch (NoSuchFileException e) {
			System.out.println("No such file exception in Rename method" + e);
		} catch (FileAlreadyExistsException e) {
			System.err.println("File already exists." + e);
		} catch (IOException e) {
			System.out.println("IOException in renameFile method");
			e.printStackTrace();
		}
		return false;
	}

//Gets an exclusive lock over entire file - breaks until lock is aquired
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

//Tries to get an exclusive lock over entire file, but only maxAttempt times
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

//Tries to get a shared lock on the entire file maxAttmepts time
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
}
