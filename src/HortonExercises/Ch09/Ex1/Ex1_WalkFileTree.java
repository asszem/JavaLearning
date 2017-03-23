/*
List all directories - including subdirectories with WalkFileTree




 */
package HortonExercises.Ch09.Ex1;

import java.nio.file.*;
import java.nio.file.attribute.*;
import java.io.IOException;
import static java.nio.file.FileVisitOption.FOLLOW_LINKS;   //A WalkFileTree paraméterhez kell
import java.util.EnumSet;									//A WalkFileTree paraméterhez kell

/**
 *
 * @author Andras Olah (olahandras78@gmail.com)
 */
public class Ex1_WalkFileTree extends SimpleFileVisitor<Path> {

	public static boolean debug = false;
	public StringBuilder behuzas= new StringBuilder("__");

	@Override
	public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attr) {
		if (debug) {
			System.out.println("PreVisit");
		}
		System.out.printf("%n%s%s",behuzas,dir.toAbsolutePath());
		behuzas.append("__");
		return FileVisitResult.CONTINUE;
	}

	@Override
	public FileVisitResult postVisitDirectory(Path dir, IOException e) {
		if (debug) {
			System.out.println("PostVisit");
		}
		behuzas.delete(behuzas.length()-2, behuzas.length());
		return FileVisitResult.CONTINUE;
	}

	@Override
	public FileVisitResult visitFile(Path file, BasicFileAttributes attr) {
		if (debug) {
			System.out.println("visitFile");
		}
//		System.out.printf("%n%s%n", file.getFileName());
		return FileVisitResult.CONTINUE;
	}

	@Override
	public FileVisitResult visitFileFailed(Path file, IOException e) {
		if (debug) {
			System.out.println("VisitFileFailed");
		}
		return FileVisitResult.CONTINUE;
	}

	public static void main(String[] args) {
		//Create new object for the overridden methods
		FileVisitor<Path> fileBejaro = new Ex1_WalkFileTree();
		Path utvonal = Paths.get("E:\\javaTest");
		int bejarasiMelyseg = 4;
		try {
			Files.walkFileTree(utvonal, EnumSet.of(FOLLOW_LINKS), bejarasiMelyseg, fileBejaro);
		} catch (IOException e) {
			System.out.println("IOException\n" + e);
		}
	}
}
