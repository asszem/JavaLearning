/*
Write a program that lists all the directories in a directory defined by a path specified as a command-line argument.

http://stackoverflow.com/questions/9168759/netbeans-how-to-set-command-line-arguments-in-java

	
step(1): create the java code which can receive an argument as command-line argument .

class TestCode{
    public static void main(String args[]){
        System.out.println("first argument is: "+args[0]);
}
}
step(2): In NetBeans (im using 7.3) in the Output windown at bottom, click the yellow button (Re-run with different parameter). This will open a new window (Run Ant Target Window).

step(3): If the argument you need to pass is 'testArgument ', then here in this window pass the argument as application.args=testArgument
 */
package HortonExercises.Ch09.Ex1;

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
public class Ex1_Andras {

	public static void main(String[] args) {
		Path dirPath;
		if (args.length == 0) {
			System.out.println("No parameters provided. Directory set manually");
			dirPath = Paths.get("E:\\javaTest");
		} else dirPath= Paths.get(args[0]);
		boolean debug = true;
		if (debug) {
			System.out.println("Arguments provided: " + (args.length!=0?args[0]:" no args provided"));
		}
		try {
			DirectoryStream<Path> mappaStream = Files.newDirectoryStream(dirPath);
			int counter = 0;
			for (Path p : mappaStream) {
				BasicFileAttributes attr = Files.readAttributes(p, BasicFileAttributes.class);
				if (attr.isDirectory()){
				System.out.printf("#%d %s%n",++counter,p);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
