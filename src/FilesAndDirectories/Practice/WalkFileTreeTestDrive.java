/*
Based on Horton Ch9 sample
 */
package FilesAndDirectories.Practice;

import FilesAndDirectories.WalkFileTree;
import static java.nio.file.FileVisitOption.*;
import java.nio.file.*;
import java.io.IOException;
import java.util.EnumSet;

/**
 *
 * @author Andras Olah (olahandras78@gmail.com)
 */
public class WalkFileTreeTestDrive {

	public static void main(String[] args) {
		Path test = Paths.get("E:\\javaTest");
		//Create a new FileWalkTreeTest() object referenced by a FileVisitor<Path> type. or sumting
		FileVisitor<Path> fileBejaro = new WalkFileTree();
		int depth = 4;
		try {
			Files.walkFileTree(test, EnumSet.of(FOLLOW_LINKS), depth, fileBejaro);
		} catch (IOException e) {
			System.out.println("IOException\n"+e);
		}
	}
}
