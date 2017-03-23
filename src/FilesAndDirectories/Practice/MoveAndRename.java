package FilesAndDirectories.Practice;

import java.nio.file.FileAlreadyExistsException;
import java.nio.file.NoSuchFileException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes; //Interface - cannot be instantized to an object

/**
 *
 * @author Andras Olah (olahandras78@gmail.com)
 */
public class MoveAndRename {

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
//Returns true if moving completed successfully
	public static boolean moveSingleDirectory(Path source, Path target, String options) {
		System.out.printf("Source: %s%nTarget: %s%nOptions: %s%n", source, target, options);
		if (isDirectory(source) == false || isDirectory(target) == false) {
			System.out.println("Source or target is not a directory");
			System.out.println("Source is directory?: " + isDirectory(source));
			System.out.println("Target is directory?: " + isDirectory(target));
			return false;
		}
		return false;
	}

//Returns true if moving a single file completed successfully
	public static boolean moveSingleFile(Path source, Path target) {
		System.out.printf("Moving a file.%nSource: %s%nTarget: %s%n", source, target);
		if (isDirectory(source)){
			System.out.println("Source is not a file!");
			return false;
		} else if (isDirectory(target)){
			System.out.println("Target is not a file!");
			return false;
		}
		try {
			Files.move(source, target);
			System.out.println("Moving completed.");
		} catch (NoSuchFileException e) {
			System.err.println("Move failed." + e);
		} catch (FileAlreadyExistsException e) {
			System.err.println("File already exists." + e);
		} catch (IOException e) {
			System.err.println("I/O error." + e);
		}
		return false; //Ha ide jut, akkor false legyen. 
	}

	/* 
	move(source, target, options)
	Options:
	REPLACE_EXISTING  - the target file specified by the first argument should be replaced
	if it exists and is not a non-empty directory
	ATOMIC_MOVE - that the move is executed as an atomic operation.  
	
	An atomic operation is an operation that cannot be interrupted by another thread of execution. If you specify ATOMIC_MOVE, any other CopyOption values that you specify are ignored, so essentially you specify the optional third argument to the move() method as either REPLACE_EXISTING or ATOMIC_MOVE.  */
	public static void main(String[] args) {
		Path forras = Paths.get("E:\\javaTest\\moveTest.txt");
		Path cel = Paths.get("E:\\javaTest\\masolasCel\\moveTest.txt");
		Path rename = Paths.get("E:\\javaTest\\masolasCel\\moveTestRenamed.txt");
		System.out.println(rename.resolveSibling("alma"));
	}
}
