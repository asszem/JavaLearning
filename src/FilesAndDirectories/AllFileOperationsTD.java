//This is to test AllFileOperations
package FilesAndDirectories;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 *
 * @author Andras Olah (olahandras78@gmail.com)
 */
public class AllFileOperationsTD {

	public static void main(String[] args) {
//		AllFileOperations.printFileSystemProperties();
		Path p = Paths.get("E:\\javaTest");
//		AllFileOperations.listDir(p, null);
//		AllFileOperations.fileAttributes(p.resolve("test.txt"));
//		AllFileOperations.createSingleDirectory(p.resolve("newDir"));
//		AllFileOperations.createMultipleDirectories(p.resolve("ddir\\dir2\\dir3"));
//		AllFileOperations.createSingleFile(p.resolve("testfil2.txt"));
//		AllFileOperations.copySingleFile(p.resolve("test.txt"), p.resolve("newDir"));
//		AllFileOperations.listDir(p, null);
//		AllFileOperations.createSingleDirectory(p.resolve("ddir"));
//		AllFileOperations.copyMultipleFiles(p, p.resolve("ddir"), "dfadf");
//		AllFileOperations.listDir(p.resolve("dir"), null);
//		AllFileOperations.moveSingleFile(p.resolve("moveTest.txt"), p.resolve("dir"), "replace");
//		AllFileOperations.listDir(p.resolve("dir"), null);
//		AllFileOperations.listDir(p, null);
//		AllFileOperations.deleteSingleFileOrDir(p.resolve("testfile.txt"));
//		AllFileOperations.listDir(p, null);
//		AllFileOperations.renameSingleFile(p.resolve("testfil2.txt"), p.resolve("testfile.txt"));
//		AllFileOperations.moveMultipleFiles(p, p.resolve("dir"), "*.txt", "replace");
//		AllFileOperations.deleteMultipleFiles(p.resolve("deldir"), "*.*");
//		AllFileOperations.deleteSingleFileOrDir(p.resolve("deldir"));
//		AllFileOperations.listDir(p.resolve("deldir"), null);
//		AllFileOperations.listDir(p, null);
		AllFileOperations.copyFile(p.resolve("TC2-results.txt"), p.resolve("TC2-results-Copied.txt"));
	}
}
