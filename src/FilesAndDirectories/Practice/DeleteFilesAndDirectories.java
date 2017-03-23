package FilesAndDirectories.Practice;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;

/**
 *
 * @author Andras Olah (olahandras78@gmail.com)
 */
public class DeleteFilesAndDirectories {

	public static void createAFile(Path inputPath) {
//Create a file 
		System.out.println("Input path: " + inputPath);
		try {
			Files.createDirectories(inputPath.getParent()); //Create dir if don't exists
			Files.createFile(inputPath);
			System.out.println("File created.");
		} catch (java.nio.file.FileAlreadyExistsException e) {
			System.out.println("A file már létezik! " + e);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
//Create a Directory
	public static void createADirectory(Path inputPath) {
		System.out.println("Input path: " + inputPath);
		try {
			Files.createDirectories(inputPath.getParent()); //Create dir if don't exists
			System.out.println("Folder(s) created.");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

//Delete a File or Directory
	/*The Files class defines a delete() method that deletes a file or a directory. 
		A directory has to be empty before it can be deleted. 
		Attempting to delete a directory that is not empty 
		throws exception java.nio.file.DirectoryNotEmptyException. 
	 */
	public static void deleteAFileOrDir(Path inputPath) {
		try {
			System.out.println("Input path to delete: " + inputPath);
			Files.delete(inputPath);
			//BasicFileAttributes works only if the file/dir exists
			if (Files.exists(inputPath)){
			BasicFileAttributes fileAttributes = Files.readAttributes(inputPath, BasicFileAttributes.class); //To check whether the path is a file or a directory
			boolean isDirectory = fileAttributes.isDirectory();
			System.out.println(isDirectory ? "Folder deleted" : "File deleted.");
			}
		} catch (DirectoryNotEmptyException e) {
			System.out.println("Directory is not empty!");
//			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		String utvonal = "E:\\javaTest";
		Path utv = Paths.get(utvonal);
		Path fileToCreated = utv.resolve("toBeDeleted.txt");
		String dirToDeleted = "dir\\dir\\dir";
		Path dirToDeletedPath = utv.resolve(dirToDeleted);
		createAFile(dirToDeletedPath);
		deleteAFileOrDir(dirToDeletedPath);
	}
}
