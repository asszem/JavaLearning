package FilesAndDirectories.Practice;

import java.nio.file.DirectoryStream; //interface
import java.nio.file.Path; //Interface
import java.nio.file.Paths; //Class
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.NoSuchFileException;

/**
 *
 * @author Andras Olah (olahandras78@gmail.com)
 */
public class CreateDirectories {

	public static void vonal(int hossz) {
		System.out.println("");
		for (int i = 0; i < hossz; i++) {
			System.out.print("-");
		}
		System.out.println("");
	}

//Hortonból átvéve szép metódusok, amik lekezelik a hibákat jól
	static void createSingleDirectory(Path path) {
		try {
			Files.createDirectory(path);
			path = path.toAbsolutePath();
			System.out.println("\n" + path + " directory created.");
		} catch (NoSuchFileException e) {
			System.err.println("\nDirectory creation failed:\n" + e);
		} catch (FileAlreadyExistsException e) {
			System.err.println("\nDirectory creation failed:\n" + e);
		} catch (IOException e) { //must be last, because other exceptions extend to this
			System.err.println("\nDirectory creation failed:\n" + e);
		}
	}

	static void createMultipleDirectories(Path path) {
		try {
			Files.createDirectories(path);
			path = path.toAbsolutePath();
			System.out.println("\n" + path + " directory created.");
		} catch (IOException e) {
			System.err.println("\nDirectory creation failed:\n" + e);
		}
	}

	public static void main(String[] args) {
		boolean mehet = false;
		String testPath = "E:\\javaTest";
		Path utv = Paths.get(testPath);
		Path ujmappa = Paths.get("NewTestFolder");
		Path celmappa = utv.resolve(ujmappa); //Az utv path-hoz hozzáadja a relatív ujmappát
		System.out.printf("User dir path:%n%s", System.getProperty("user.dir"));
		vonal(40);
//Create One New Directory
		/*static createDirectory() method in the Files class 
	with a Path object that specifies the directory to be created as the argument. 
	This can be an absolute or a relative path.
	The new directory is appended to the current directory if the Path object is a relative path.
	Throws an exception of type java.nio.FileAlreadyExistsException
	Throws many other exceptions, see javadoc.
		 */
		if (mehet) {
			System.out.printf("Új mappa neve:%s%nLétrehozva itt:%s%n", ujmappa, celmappa);
			try {
				Files.createDirectory(celmappa);
				System.out.println("Új mappa létrehozva: " + ujmappa);
			} catch (FileAlreadyExistsException e) {
				System.out.println("Már létezik!" + e.getMessage());
				e.printStackTrace(System.out);
			} catch (IOException e) {
				System.err.println("Egyéb IO hiba történt! " + e);
				e.printStackTrace(System.err);
			}
		}
//Create Multiple CreateDirectories
		/*static createDirectories()
		method with the Path object as the argument. This creates all the required directories. 
		throw exception FileAlreadyExistsException if a file already exists with the same path
		 */
		mehet = true;
		if (mehet) {
			String testDirName = "testDir";
			celmappa = utv.resolve(testDirName);
			celmappa = celmappa.resolve(testDirName);
			celmappa = celmappa.resolve(testDirName);
			celmappa = celmappa.resolve(testDirName);
			try {
				Files.createDirectories(celmappa);
				System.out.println("Célmappa:\n" + celmappa);
				System.out.println("Sikeresen létrehozva.");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	} //end main
}
