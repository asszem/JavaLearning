package FilesAndDirectories.H_Ch9_FilesAndDirectories;

import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 *
 * @author Andras Olah (olahandras78@gmail.com)
 */
public class CreatingFiles {

	public static void main(String[] args) {
		String pathStr = "E:\\javaFileOpTest";
		Path path = Paths.get(pathStr, "createFileinThisFolder", "createFile.txt");
		try {
			System.out.println("Input path: " + path);
			Files.createDirectories(path.getParent()); // Create parent
			Files.createFile(path);
		} catch (FileAlreadyExistsException e) {
			System.err.println("File not created.\n" + e);
		} catch (IOException e) {
			System.err.println(e);
		}
	}
}
