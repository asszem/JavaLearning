/*
Write a program that outputs a count of the total number of files in each directory in a file tree

that is specified by a path entered on the command line 

and outputs the total number of directories and files in the tree. 

List the directories by name with each name followed by the file count in that directory


 */
package HortonExercises.Ch09.Ex4;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.*;
import java.nio.file.attribute.*;
import java.io.IOException;
import static java.nio.file.FileVisitOption.FOLLOW_LINKS;
import java.util.EnumSet;

/**
 *
 * @author Andras Olah (olahandras78@gmail.com)
 */
public class Ex4_Andras {

	static void getFileCount(String p) {
		Path utvonal = Paths.get(p);
		FileVisitor<Path> fileBejaroEgyszeru = new Ex4_WalkFileTree();
		System.out.println("Root directory: " + utvonal);
		int depth = Integer.MAX_VALUE;
		try {
			Files.walkFileTree(utvonal, EnumSet.of(FOLLOW_LINKS), depth, fileBejaroEgyszeru);
			System.out.printf("%nTotal dirs:%d%nTotal files:%d%n", Ex4_WalkFileTree.totalNumberofDirs, Ex4_WalkFileTree.totalNumberOfFiles);
			Ex4_WalkFileTree.totalNumberOfFiles = 0;
			Ex4_WalkFileTree.totalNumberofDirs = 0;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		getFileCount("E:\\javaTest\\ex4test");
		getFileCount("E:\\javaTest");
	}
}
