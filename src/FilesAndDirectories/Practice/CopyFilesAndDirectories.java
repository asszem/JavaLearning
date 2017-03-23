
package FilesAndDirectories.Practice;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;


/**
 * 
 * @author Andras Olah (olahandras78@gmail.com)
 */
public class CopyFilesAndDirectories {
	public static void main(String[] args) {
		Path p1 = Paths.get("E:\\javaTest\\dir1");
		Path file1 = Paths.get("copyTest.txt");
		p1=p1.resolve(file1);
		Path p2 = Paths.get("E:\\javaTest\\dir1\\dir2");
		System.out.println(p1);
				
	}
}
