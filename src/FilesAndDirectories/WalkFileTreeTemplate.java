package FilesAndDirectories;

/**
 *
 * @author Andras Olah (olahandras78@gmail.com)
 */
import java.nio.file.*;
import java.nio.file.attribute.*;
import java.io.IOException;
import static java.nio.file.FileVisitOption.FOLLOW_LINKS;
import java.util.EnumSet;

/**
 *
 * @author Andras Olah (olahandras78@gmail.com)
 */
public class WalkFileTreeTemplate extends SimpleFileVisitor<Path> {

	@Override
	public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attr) {

		return FileVisitResult.CONTINUE;
	}

	@Override
	public FileVisitResult postVisitDirectory(Path dir, IOException e) {
		return FileVisitResult.CONTINUE;
	}

	@Override
	public FileVisitResult visitFile(Path file, BasicFileAttributes attr) {
		return FileVisitResult.CONTINUE;
	}

	@Override
	public FileVisitResult visitFileFailed(Path file, IOException e) {
		return FileVisitResult.CONTINUE;
	}

	public static void main(String[] args) {
		//Create new object for the overridden methods
		FileVisitor<Path> fileBejaro = new WalkFileTreeTemplate();
		Path utvonal = Paths.get("E:\\javaTest");
		int bejarasiMelyseg = 4;
		try {
			Files.walkFileTree(utvonal, EnumSet.of(FOLLOW_LINKS), bejarasiMelyseg, fileBejaro);
		} catch (IOException e) {
			System.out.println("IOException\n" + e);
		}

		//walkFileTree with two parameters only
		FileVisitor<Path> fileBejaroEgyszeru = new WalkFileTreeTemplate();
		try {
			Files.walkFileTree(utvonal, fileBejaroEgyszeru);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
