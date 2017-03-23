package FilesAndDirectories.Practice;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.NoSuchFileException;
import java.nio.file.FileAlreadyExistsException;
import java.io.IOException;

/**
 *
 * @author Andras Olah (olahandras78@gmail.com)
 */
public class CreateFiles {

	static void createNewFile(Path inputPath) {
		CreateDirectories.vonal(40);
		System.out.println("Új fájl létrehozása.");
		System.out.printf("%nCélfile:%n%s%n", inputPath);
		try {
			//verify if path exists and if not, create it
			/* 
			if (Files.isDirectory(inputPath.getParent())){
				System.out.println("Útvonal létező mappa.");
			} else {
				System.out.println("Útvonal nem létező mappa.");
				System.out.println("Ezért most létrehozom.");
				Files.createDirectories(inputPath.getParent());
				System.out.printf("%n%s mappa létrehozva.%n", inputPath.getParent());
			}
			*/
			Files.createDirectories(inputPath.getParent()); //If already exists, this does nothing
			Files.createFile(inputPath); //target path must exists
			System.out.println("Új fájl létrehozva.");
		} catch (NoSuchFileException e) {
			System.out.println("Nem sikerült, mert " + e);
		} catch (FileAlreadyExistsException e){
			System.out.println("Nem sikerült, mert " + e);
		} catch (IOException e){
			System.err.println(e);
		}
		CreateDirectories.vonal(40);
	}

	public static void main(String[] args) {
		boolean mehet = false;
		String testPath = "E:\\javaTest";
		Path utv = Paths.get(testPath);
		Path ujmappa = Paths.get("NewTestFolder2");
		Path celmappa = utv.resolve(ujmappa); //Az utv path-hoz hozzáadja a relatív ujmappát
		Path celfile = celmappa.resolve("createFileTest.txt");
		mehet = true;
		if (mehet) {
			createNewFile(celfile);
			createNewFile(celfile);
		}
	}
}
