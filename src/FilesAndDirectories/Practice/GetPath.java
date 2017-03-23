package FilesAndDirectories.Practice;

import java.nio.file.FileSystem; //Abstract osztály, ilyen típusúnak kell létrehozni az objektumot
import java.nio.file.FileSystems; //Ebben az osztályban van definiálva a getDefault() metódus
import java.nio.file.FileStore;	//Abstract class. A FileStore objektum létrehozásához kell, ez dobhat IOExceptiont
import java.io.IOException; //A FileStore dobja
import java.nio.file.Files;
import java.nio.file.Path; //Interface. A path objektum létrehozásához kell
import java.nio.file.Paths; //Osztály, ami a Path objektum létrehozásához kell
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Properties; //A System.getProperties ilyent ad vissza, ezt lehet lekérdezni, vagy kiírni

/**
 *
 * @author Andras Olah (olahandras78@gmail.com)
 */
public class GetPath {

	public static void main(String[] args) {
//Getting the default FileSystem object
		//You can obtain the FileSystem object that encapsulates the storage system on your machine by calling the getDefault() method that is defined in the FileSystems class
		FileSystem fajlRendszer = FileSystems.getDefault();	//Nincs NEW.
		Iterable<FileStore> fajlStoreok = fajlRendszer.getFileStores();
		final long GIGABYTE = 1_073_741_824L;
		for (FileStore fajlStoreIteracio : fajlStoreok) {
			try {
				System.out.printf("Meghajtónév: %s%nSzabad hely: %dGB%n", fajlStoreIteracio.name(), fajlStoreIteracio.getUsableSpace() / GIGABYTE);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

//PATHs
		String pathValtozo1 = "E:";
		String pathValtozo2 = "javaTest";
		String pathValtozo3 = "subdirectory";
		String pathValtozoFile = "test.txt";

		//note Path path = "C:\alma"; DOES NOT WORK!
		//Create path object by using getPath on a FileSystem object:
		Path absolutePath = fajlRendszer.getPath(pathValtozo1, pathValtozo2, pathValtozo3);
		//Create path object by using the Paths class 
		Path filePath = Paths.get(pathValtozo2, pathValtozo3, pathValtozoFile);
		//This does not do anything with the filesystem yet, these paths might or might not exists.

		System.out.println("Path műveletek \n");

		System.out.println("Path 1: abszolút path\n" + absolutePath);
		System.out.printf("A %s útvonal %s%n", absolutePath, absolutePath.isAbsolute() ? "abszolút" : "relatív");
		System.out.printf("getFileName: [%s]%n", absolutePath.getFileName());
		System.out.printf("getRoot [%s]%n", absolutePath.getRoot());
		System.out.printf("getNameCount: %d%n", absolutePath.getNameCount());
		System.out.printf("getName(1) [%s]%n", absolutePath.getName(1));

		System.out.println("\nPath 2: filename path\n" + filePath);
		//Relative path - relative to current directory
		System.out.printf("A %s útvonal %s%n", filePath, filePath.isAbsolute() ? "abszolút" : "relatív");
		System.out.printf("getFileName: [%s]%n", filePath.getFileName());
		System.out.printf("getRoot [%s]%n", filePath.getRoot());
		System.out.printf("getNameCount: %d%n", filePath.getNameCount());
		System.out.printf("getName(1) [%s]%n", filePath.getName(1));

//System Property elérés
//https://docs.oracle.com/javase/tutorial/essential/environment/sysprop.html
//		System.out.println("user.dir:" + System.getProperty("user.dir"));
//		System.out.println("file.separator:" + System.getProperty("file.separator"));
		Properties tulajdonsagok = System.getProperties();
//		System.out.println(tulajdonsagok.getProperty("user.dir"));
		Path userHomePath = Paths.get(System.getProperty("user.home"), "javatest.txt");

		System.out.println("\nPath 3: user.home path\n" + userHomePath);
		System.out.printf("A %s útvonal %s%n", userHomePath, userHomePath.isAbsolute() ? "abszolút" : "relatív");
		System.out.printf("getFileName: [%s]%n", userHomePath.getFileName());
		System.out.printf("getRoot [%s]%n", userHomePath.getRoot());
		System.out.printf("getNameCount: %d%n", userHomePath.getNameCount());
		System.out.printf("getName(1) [%s]%n", userHomePath.getName(1));
		System.out.printf("subPath(0,2) [%s]%n", userHomePath.subpath(0, 2));
		System.out.printf("subPath(1,2) [%s]%n", userHomePath.subpath(1, 2));
		System.out.printf("subPath(1,3) [%s]%n", userHomePath.subpath(1, 3));
		System.out.printf("subPath(2,3) [%s]%n", userHomePath.subpath(2, 3));

		System.out.printf("%nPath1 [%s] equals path2 [%s]: %b%n", filePath, userHomePath, filePath.equals(userHomePath));
		System.out.printf("Path1 [%s] equals path2 [%s]: %b%n", filePath, filePath, filePath.equals(filePath));
		System.out.println("Converting to absolute:");
		System.out.println("Before:");
		System.out.printf("A %s útvonal %s%n", filePath, filePath.isAbsolute() ? "abszolút" : "relatív");
		System.out.println("After:");
		System.out.printf("A %s útvonal %s%n", filePath.toAbsolutePath(), filePath.toAbsolutePath().isAbsolute() ? "abszolút" : "relatív");

//Querying Files and directories
		System.out.println("Querying files and directories");
		System.out.printf("Útvonal: %s%nFájl: %s%nLétezik: %b%n",
				absolutePath,
				absolutePath.getFileName(),
				java.nio.file.Files.exists(absolutePath)
		);
		Path testPath = Paths.get(pathValtozo1, pathValtozo2, pathValtozo3, pathValtozoFile);
		System.out.printf("Útvonal: %s%nFájl: %s%nLétezik: %b%n",
				testPath,
				testPath.getFileName(),
				java.nio.file.Files.exists(testPath)
		);

//Resolve and Relativize
	//RESOLVE teljes útvonal hozzáadása/lecserlélése a jelenlegi útvonalhoz
		Path utv1 = Paths.get("E:\\javaTest");
		Path utv2 = Paths.get("submappa");
		utv2 = utv1.resolve(utv2);
		System.out.println("utv1:" + utv1);
		System.out.println("utv2:" + utv2);

		utv1 = Paths.get("E:\\javaTest");
		utv2 = Paths.get("E:\\submappa");
		utv2 = utv1.resolve(utv2);
		System.out.println("utv1:" + utv1);
		System.out.println("utv2:" + utv2);

		//Resolve sibling - used to rename the final right part of a path
		Path utv4 = Paths.get("E:\\javaTest\\dir1\\dir2\\dir3");
		System.out.println(utv4.resolveSibling("DIR3_RENAMED"));
		System.out.println(utv4.resolveSibling(utv1.resolve("Dulimanó")));
		System.out.println(utv4.resolveSibling("Dulimanó"));
//Read file attributes
//BasicFileAttributes type
//Files class
//readAttributes() static method
//the attributes are returned as a reference to an object of type BasicFileAttributes that contains attributes common to most file systems
		try {
			BasicFileAttributes fajlTulajdonsagok = Files.readAttributes(testPath, BasicFileAttributes.class);
			System.out.printf("Creation time %s", fajlTulajdonsagok.creationTime());
			System.out.printf("Size: %d", fajlTulajdonsagok.size());
		} catch (IOException e) {
			System.err.println(e);
		}
	}
}
