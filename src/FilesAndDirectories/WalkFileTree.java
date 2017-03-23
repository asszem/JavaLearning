/*
FileVisitor<t> - T is the type of file reference, usually type Path

The methods that are declared in the FileVisitor<Path> interface are called by another method that
you call to initiate the process of walking the file tree; this is the static walkFileTree() method that is defined in the Files class. This then calls the FileVisitor<Path> interface methods

This class must extend to SimpleFileVisitor, as it is implementing the FileVisitor interface 

FileVisitor<Path> - Interface
SimpleFileVisitor - Class, implementing FileVisitor methods
		public class SimpleFileVisitor<T> implements FileVisitor<T> {...
FileVisitResults - enum defined in java.nio.file package. 		
public enum FileVisitResult {...
WalkFileTree() - static method in Files class
		public static Path walkFileTree(Path start, Set<FileVisitOption> options, int maxDepth, FileVisitor<? super Path> visitor) throws IOException {...

Methods to override.
You should return one of the following constants defined by the FileVisitResult enum that is in the java.nio.file package.

FileVisitResult preVisitDirectory
FileVisitResult visitFile
FileVisitResult postVisitDirectory
FileVisitResult visitFileFailed

FileVisitResults Return Options: 
FileVisitResult.CONTINUE;
FileVisitResult.SKIP_SIBLINGS;
FileVisitResult.SKIP_SUBTREE;
FileVisitResult.TERMINATE

Steps.
1. Define and override the FileVisitResult methods
	SimpleFileVisitor is a class with default implementation to visit all files and on error it will re-throw errors. 
	Instead of implementing FileVisitor we can choose to extend SimpleFileVisitor 
	and override ONLY methods of our need.
2. Create a new FileVisitor<Path> type reference to the instance of the class that defined the overwriting methods. Example:
			FileVisitor<Path> fileBejaro = new WalkFileTree();
3. In a try-catch block call the Files.walkFileTree method, using the ref. variable created in step #2.
	Path: the starting Path
	EnumSet.of(FOLLOW_LINKS): parameter for symlinks
	Depth: int, define the depth of directories to be walked
	fileBejaro: the FileVisitor<Path> type variable referencing the class with FileResults methods
			try {
				Files.walkFileTree(Path, EnumSet.of(FOLLOW_LINKS), depth, fileBejaro);
			} catch (IOException e) {
				System.out.println("IOException\n"+e);
			}

Useful links
https://docs.oracle.com/javase/tutorial/essential/io/walk.html
http://javapapers.com/java/walk-file-tree-with-java-nio/



 */
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
		if (debug) System.out.println("\n\t\tpreVisitDirectory method started ...");
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
		if (debug) System.out.println("\n\t\tpostVisitDirectory method called...");
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
