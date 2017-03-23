
package FilesAndDirectories.Practice;
import java.nio.file.Path;
import java.nio.file.Paths;
/**
 * 
 * @author Andras Olah (olahandras78@gmail.com)
 */
/*
Resolve + relatív path = a forrás végéhez teszi hozzá
ResolveSibling + relatív path = a forrás szülőmappájával egy szintre teszi

Resolve + absolute = lecseréli az abszolút útvonalra
*/
public class ResolvePath {
	public static void main(String[] args) {
	Path p1 = Paths.get("E:\\javaTest\\dir1\\dir2\\dir3");
	Path p2 = Paths.get("E:\\javaTest\\dir1\\");
	//Relative path test
		System.out.println("Resolve + relative path test");
		System.out.println("p1:\n"+p1);
		System.out.println("");
		System.out.println("p1.resolve(\"Stringre rezolválva\")");
		System.out.println(p1.resolve("Strignre rezolválva"));
		System.out.println("");
		System.out.println("p1.resolveSibling(\"Stringre rezolválva\")");
		System.out.println(p1.resolveSibling("Strignre rezolválva"));
		//Absolute path test
		System.out.println("\nResolve + absolute path test");
		System.out.println("p1:\n"+p1);
		System.out.println("p2:\n"+p2);
		System.out.println("");
		System.out.println("p1.resolve(p2)");
		System.out.println(p1.resolve(p2));
		System.out.println("");
		System.out.println("p1.resolveSibling(p2)");
		System.out.println(p1.resolveSibling(p2));
	}
}
