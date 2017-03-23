
package FilesAndDirectories;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;

/**
 * 
 * @author Andras Olah (olahandras78@gmail.com)
 */
public class SystemProperties {
	public static void main(String[] args) throws FileNotFoundException, IOException {
//Create the Properties object
		Properties sys = System.getProperties();
//Print out specific information
		System.out.println("file.separator:\t" + sys.getProperty("file.separator"));
		System.out.println("java.class.path:" + sys.getProperty("java.class.path"));
		System.out.println("java.home:\t"+sys.getProperty("java.home"));
		System.out.println("java.vendor:\t"+sys.getProperty("java.vendor"));
		System.out.println("java.vendor.url: "+sys.getProperty("java.vendor.url"));
		System.out.println("java.version:\t"+sys.getProperty("java.version"));
		System.out.println("line.separator:\t"+sys.getProperty("line.separator"));
		System.out.println("os.arch:\t"+sys.getProperty("os.arch"));
		System.out.println("os.name:\t"+sys.getProperty("os.name"));
		System.out.println("os.version:\t"+sys.getProperty("os.version"));
		System.out.println("path.separator:\t"+sys.getProperty("path.separator"));
		System.out.println("user.dir:\t"+sys.getProperty("user.dir"));
		System.out.println("user.home:\t"+sys.getProperty("user.home"));
		System.out.println("user.name:\t"+sys.getProperty("user.name"));
//Print out everything to the screen
		System.out.println("");
//Print out everything to a file
		sys.list(System.out);
		System.out.println("user.dir:\t"+sys.getProperty("user.dir"));
		PrintWriter kiiro = new PrintWriter(new FileWriter("javaProperties2.txt"));
		sys.list(kiiro);
		kiiro.flush();
		kiiro.close();
	}
}
