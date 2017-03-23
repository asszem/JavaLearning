package FilesAndDirectories.Practice;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 *
 * @author Andras Olah (olahandras78@gmail.com)
 */
public class DirectoryStreams {

	//Method to list content of directory with optional Filter parameter
	//use "null" if filter is not needed
	public static void listDir(Path dirPath, String filter) {
		System.out.println("Mappa kilistázása");
		System.out.printf("Mappa neve:%s%nFilter:%s%n", dirPath, filter);
		try {
			DirectoryStream<Path> mappaStream = filter==null?Files.newDirectoryStream(dirPath):Files.newDirectoryStream(dirPath, filter);
			int counter=0;
			for (Path p : mappaStream) {
				System.out.println("#"+(++counter)+" "+p);
			}
			System.out.println("Lista vége.");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

		//List only .txt files in the dir
		/*
		TABLE 9-5: Character Sequences for Filtering Directory Streams
		CHARACTER SEQUENCE EFFECT
		* Matches zero or more characters of a name without crossing a directory boundary,
		that is without passing over a separator character.
		** Matches zero or more characters crossing directory boundaries.
		? Matches a single character in a name.
		\ Specifi es escape characters that would otherwise be interpreted as special
		characters in a glob.
		[] Specifi es a bracket expression that encloses a set of characters where a single
		character can match any character in the set. The characters *, ?, and \ match
		themselves in a bracket expression.
		- Used to specify a range, so [a-e] specifi es all the lowercase letters a though e.
		! Specifi es the negation of a range so [!a-e] specifi es that any character other
		than a through e is a match. The - character matches itself when it is the fi rst
		character in a bracket expression.
		{} Specifi es a set of subpatterns that can be matched where the subpatterns are
		separated by commas. For example, {exe, txt} matches either exe or txt.
		 */
	public static void main(String[] args) {
		Path utv = Paths.get("E:\\javaTest");
		listDir(utv, null);
		listDir(utv, "*.txt");
	}
}
