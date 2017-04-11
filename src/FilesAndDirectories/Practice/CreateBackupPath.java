package FilesAndDirectories.Practice;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 *
 * @author Andras Olah (olahandras78@gmail.com)
 */
public class CreateBackupPath {

	/**
	 * Create the name of the new file by appending “_backup” to the original filename Append it as many times as necessary to form a unique filename.
	 *
	 * Input: file_backup_backup.ext Result: file_backup_backup_backup.ext
	 *
	 * @param originalFilenameWithPath
	 * @return
	 */
	public static Path getBackupPath(Path originalFilenameWithPath) {
		/*Pseudo code
		Start 
		1. get the original path, filename.ext, filename, .ext 
		2. append _backup to the filename
		3. handle if there is no extension
		4. add extension (if exists)
		6. add original path to new filename


		No test coverage yet
		6. check if the newly created file exists so it won't get overwritten
			If yes, call the method recursively again and add a _backup
		End - return new filename with path		
		 */
		String originalPathOnly = originalFilenameWithPath.getParent().toString();
		String originalFilenameWithExtension = originalFilenameWithPath.getFileName().toString();
		int originalFileExtensionIndex = originalFilenameWithExtension.lastIndexOf('.');
		if (originalFileExtensionIndex == -1) { //if there is no extension
			originalFileExtensionIndex = originalFilenameWithExtension.length();
		}
		String originalFilenameWithoutExtension = originalFilenameWithExtension.substring(0, originalFileExtensionIndex);
		String originalFilenameExtension = originalFilenameWithExtension.substring(originalFileExtensionIndex);
		String stringToAppend = "_backup";
		String newFilenameWithExtension = originalFilenameWithoutExtension + stringToAppend + originalFilenameExtension;
		Path returnPath = Paths.get(originalPathOnly + "\\" + newFilenameWithExtension);
		boolean newFilenameAlreadyExits = true;
		while (newFilenameAlreadyExits) { //assume the generated file exists
			if (Files.exists(returnPath)) {
				returnPath = getBackupPath(returnPath); //recursive call to create a new path
			} else {
				newFilenameAlreadyExits = false; //exit the cycle if the returnPath does not exists
			}
		}

		return returnPath;
	}

	public static void main(String[] args) {
		System.out.println(getBackupPath(Paths.get("E:\\javaFileOpTest\\CreateBackupFile\\test")));
		System.out.println(getBackupPath(Paths.get("E:\\javaFileOpTest\\CreateBackupFile\\test.txt")));
		/*
		System.out.println("With extension");
		Path originalFilename = Paths.get("E:\\javaFileOpTest\\CreateBackupFile\\Original File for BackupTest.txt");
		System.out.println(originalFilename);
		System.out.println(getBackupPath(originalFilename));
		System.out.println("Without extension");
		originalFilename = Paths.get("E:\\javaFileOpTest\\CreateBackupFile\\Original File for BackupTest");
		System.out.println(originalFilename);
		System.out.println(getBackupPath(originalFilename));
		originalFilename = Paths.get("E:\\javaFileOpTest\\CreateBackupFile\\Original File for BackupTest.txt");
		System.out.println("With extension at first iteration");
		System.out.println(originalFilename);
		System.out.println(originalFilename = getBackupPath(originalFilename));
		System.out.println("");
		System.out.println("Multiple iterations");
		System.out.println("\nO:" + originalFilename);
		System.out.println("R:" + (originalFilename = getBackupPath(originalFilename)));
		System.out.println("\nO:" + originalFilename);
		System.out.println("R:" + (originalFilename = getBackupPath(originalFilename)));
		System.out.println("\nO:" + originalFilename);
		System.out.println("R:" + (originalFilename = getBackupPath(originalFilename)));
		System.out.println("\nO:" + originalFilename);
		System.out.println("R:" + (originalFilename = getBackupPath(originalFilename)));
*/
	}
}
