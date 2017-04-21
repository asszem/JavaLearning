package FilesAndDirectories.H_Ch09_FilesAndDirectories;

import java.nio.file.Files;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;

/**
 *
 * @author Andras Olah (olahandras78@gmail.com)
 */
public class BasicFileAndDirectoryAttributes {

	public static void main(String[] args) {
		String testPath = "E:\\javaFileOpTest";
		Path path = Paths.get(testPath);
		System.out.println("Input path: " + path);
		System.out.println("Exists? " + Files.exists(path));
		try {
			BasicFileAttributes basicFileAttrs = Files.readAttributes(path, BasicFileAttributes.class);
//					BasicFileAndDirectoryAttributes attr = Files.readAttributes(path, BasicFileAndDirectoryAttributes.class);
			System.out.println("Basic File Attributes");
			System.out.println("is Directory? " + basicFileAttrs.isDirectory());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
