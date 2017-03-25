package FilesAndDirectories;

import java.nio.file.*;
import java.nio.file.attribute.*;
import java.io.IOException;

/**
 *
 * @author Andras Olah (olahandras78@gmail.com)
 */
//								This extension to SimpleFileVisitor is required to inherit methods
public class WalkFileTree extends SimpleFileVisitor<Path> {

	private StringBuilder indent = new StringBuilder("__"); //used to increase indentation for subdirs
	public static boolean debug = false;

//Displays the directory path and sets the indentation level
//Method will be called BEFORE visiting a directory
	@Override //must have the same parameters as the overridden method in FileVisitResults
	public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attr) {
		if (debug) {
			System.out.println("\n\t\tpreVisitDirectory method started ...");
		}
		System.out.printf("%n%s%s [Folder] contains:", indent, dir.toString().toUpperCase());
		indent.append("__");
		return FileVisitResult.CONTINUE;
	}

//Method to run AFTER a directory visited to display possible errors AND to decrease indentation
	@Override
	public FileVisitResult postVisitDirectory(Path dir, IOException e) {
		//If there was an IO error, display the error
		if (e != null) {
			System.out.printf("%n%sIO error in directory:%s%n%s", indent, dir, e);
		}
		//Delete the latest part of the indentation
		//			 	FROM				UNTIL
		if (debug) {
			System.out.println("\n\t\tpostVisitDirectory method called...");
		}
		indent.delete(indent.length() - 2, indent.length());
		return FileVisitResult.CONTINUE;
	}

//Record file or symbolic link details
	@Override
	public FileVisitResult visitFile(Path file, BasicFileAttributes attr) {
		if (attr.isRegularFile()) {
			System.out.printf("%n%s%s [File]", indent, file);
		} else if (attr.isSymbolicLink()) {
			System.out.printf("%n%sSymLink:[%s]", indent, file);
		}
		return FileVisitResult.CONTINUE;
	}

//Record file visit failure
	@Override
	public FileVisitResult visitFileFailed(Path file, IOException e) {
		System.err.printf("%n%sError! File can not be read: [%s]%nError:%s", indent, file, e);
		return FileVisitResult.CONTINUE;
	}
}
