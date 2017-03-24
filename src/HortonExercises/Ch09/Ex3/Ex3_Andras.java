/*
Write a program that copies all the files in one directory to another with the paths being entered from the command line. 
The names of the file copies should have _old appended to them and no files in the destination folder should be overwritten.



 */
package HortonExercises.Ch09.Ex3;

import FilesAndDirectories.H_Ch9_FilesAndDirectories.DirectoryStreams;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;

/**
 *
 * @author Andras Olah (olahandras78@gmail.com)
 */
public class Ex3_Andras {

	public static boolean debug = true;

	public static void copyFiles(Path source, Path target) {
		//Path validity verification. Path must be a Directory
		try {
			BasicFileAttributes tulSource = Files.readAttributes(source, BasicFileAttributes.class);
			BasicFileAttributes tulTarget = Files.readAttributes(target, BasicFileAttributes.class);
			if (!tulSource.isDirectory()) {
				System.out.println("Error. Source path is not a Directory. Exiting");
				System.exit(0);
			}
			if (!tulTarget.isDirectory()) {
				System.out.println("Error. Target path is not a Directory. Exiting");
				System.exit(0);
			}
			DirectoryStream<Path> mappaStream = Files.newDirectoryStream(source, "*.*");
			for (Path p : mappaStream) {
//				String newFileName = p.getFileName().toString() + "_old";
				String removedExtension[] = RemoveExtension.removeExtension(p.getFileName().toString());
				String newFileName = removedExtension[0] + "_old" + removedExtension[1];
				if (debug) {
					System.out.println("Filename: " + p.getFileName());
//					System.out.println("GetNameCount: " + p.getNameCount());
//					System.out.println("Append: " + p.getFileName().resolve("_old"));
//					System.out.println("ToString: " + p.getFileName().toString() + "_old");
					System.out.println("newFileName: " + newFileName);
//					System.out.println("Target path: " + target.resolve(newFileName));
				}
				//Verify if file already exists in new location
				if (Files.exists(target.resolve(newFileName))) {
					if (debug) {
						System.out.println("File already exists in destination, skipping");
					}
				} else if (debug) {
					System.out.println("File does not exist, copying started...");
					Files.copy(p, target.resolve(newFileName));
					System.out.println("File copied successfully." + p.getFileName());
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		Path source = Paths.get("E:\\javaTest");
		Path target = Paths.get("E:\\javaTest\\ex3test");
		copyFiles(source, target);
	}
}
