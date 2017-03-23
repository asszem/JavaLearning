
package FilesAndDirectories.Practice;

/**
 * 
 * @author Andras Olah (olahandras78@gmail.com)
 */
public class SetUserDirTest {
	public static void main(String[] args) {
		System.out.println("User dir: " + System.getProperty("user.dir"));
		String ujPath = "E:\\javatest";
		System.setProperty("user.dir", ujPath);
		System.out.println("User dir: " + System.getProperty("user.dir"));
	}
}
