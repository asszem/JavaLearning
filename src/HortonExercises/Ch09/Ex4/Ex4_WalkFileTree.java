
/*
Write a program that outputs a count of the total number of files in each directory in a file tree

that is specified by a path entered on the command line 

and outputs the total number of directories and files in the tree. 

List the directories by name with each name followed by the file count in that directory


 */
package HortonExercises.Ch09.Ex4;

import java.nio.file.*;
import java.nio.file.attribute.*;
import java.io.IOException;
import static java.nio.file.FileVisitOption.FOLLOW_LINKS;
import java.util.EnumSet;

/**
 *
 * @author Andras Olah (olahandras78@gmail.com)
 */
public class Ex4_WalkFileTree extends SimpleFileVisitor<Path> {

	static int totalNumberOfFiles = 0; //Grand total of all files visited
	static int totalNumberOfFilesInDir = 0; //Total number of files in a directory
	static int totalNumberofDirs = 0; //Total number of directories
	static boolean debug = false;

	@Override
	public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attr) {
		if (debug) {
			System.out.printf("Entering directory: [%s]%n", dir.getFileName());
		}
//		totalNumberOfFilesInDir = 0;
		totalNumberofDirs++;
		return FileVisitResult.CONTINUE;
	}

	@Override
	public FileVisitResult postVisitDirectory(Path dir, IOException e) {
			System.out.printf("%s(files:%d)%n", dir.getParent().resolve("["+dir.getFileName()+"]"), totalNumberOfFilesInDir);
		totalNumberOfFilesInDir = 0;
		return FileVisitResult.CONTINUE;
	}

	@Override
	public FileVisitResult visitFile(Path file, BasicFileAttributes attr) {
		if (debug) System.out.printf("    File name: %s%n", file.getFileName());
		totalNumberOfFilesInDir++;
		totalNumberOfFiles++;
		return FileVisitResult.CONTINUE;
	}

//	@Override
//	public FileVisitResult visitFileFailed(Path file, IOException e) {
//		return FileVisitResult.CONTINUE;
//	}
}
