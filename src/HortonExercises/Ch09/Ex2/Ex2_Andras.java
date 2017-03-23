/*
Write a program to list all the files with a given extension in a directory. The absolute path for the directory and the extension should be read from the command line. If the extension is absent from the command-line input, the program should list all the files in the specified directory.

 */
package HortonExercises.Ch09.Ex2;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;

/**
 *
 * @author Andras Olah (olahandras78@gmail.com)
 */
public class Ex2_Andras {

	public static void main(String[] arguments) {
		String[] args;
		int testCase = 2;
		switch (testCase) {
			case 1:
				args = new String[2];
				args[0] = "E:\\javaTest";
				args[1] = "*.txt";
				break;
			case 2:
				args = new String[1];
				args[0] = "E:\\javaTest";
				break;
			default:
				args = new String[1];
		}

		try {
			//check the provided arguments
			Path dirPath;
			String fileFilter;
			switch (args.length) {
				case 2:
					dirPath = Paths.get(args[0]);
					fileFilter = args[1];
					break;
				case 1:
					dirPath = Paths.get(args[0]);
					fileFilter = "*.*";
					break;
				default:
					dirPath = Paths.get(args[0]);
					fileFilter = "*.*";
					System.out.println("Hiba");
					System.exit(0);
			}
			dirPath = dirPath.toAbsolutePath();
			DirectoryStream<Path> mappaKiiras = Files.newDirectoryStream(dirPath, fileFilter);
			int counter = 0;
			String isDir = "[DIR]";
			for (Path p : mappaKiiras) {
				//Check if the element in p path is a file or a directory
				BasicFileAttributes attr = Files.readAttributes(p, BasicFileAttributes.class);
				//Print out each element with a counter and append [DIR] if it is a directory
				System.out.println(" #" + (++counter) + " " + (attr.isDirectory() ? "" : p));
			}
		} catch (IOException e) {
			System.out.println("IOException in listDir method." + e);
			e.printStackTrace();
		}
	}

}
