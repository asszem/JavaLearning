package FilesAndDirectories.H_Ch9_FilesAndDirectories;

import java.io.IOException;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 *
 * @author Andras Olah (olahandras78@gmail.com)
 */
public class DeleteFileorDirectory {

	static void waitForEnter() {
		try {
			System.in.read();
		} catch (IOException e) {
			System.err.println(e);
		}
	}

	public static void deleteFile(Path path) {
//Delete File and Directory		
		try {
			Files.delete(path); // Delete file
			Files.delete(path.getParent()); // Delete parent directory
			System.out.println("File and directory deleted");
		} catch (DirectoryNotEmptyException e) {
			System.err.println("Directory not deleted.\n" + e);
		} catch (IOException e) {
			System.err.println(e);
		}
	}

	public static void main(String[] args) {
		Path path = Paths.get("E:\\javaFileOpTest\\deleteDirectory\\deleteFile.txt");
//First create a file to delete later
		CreatingFiles.createFile(path);
		System.out.println("File created at " + path);
		waitForEnter();
		deleteFile(path);
	}
}
