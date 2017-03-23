//This is to test AllFileOperationsSample
package FilesAndDirectories;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 *
 * @author Andras Olah (olahandras78@gmail.com)
 */
public class AllFileOperationsSampleTD {

	public static void main(String[] args) {
//		AllFileOperationsSample.printFileSystemProperties();
		Path p = Paths.get("E:\\javaTest");
//		AllFileOperationsSample.listDir(p, null);
//		AllFileOperationsSample.fileAttributes(p.resolve("test.txt"));
//		AllFileOperationsSample.createSingleDirectory(p.resolve("newDir"));
//		AllFileOperationsSample.createMultipleDirectories(p.resolve("ddir\\dir2\\dir3"));
//		AllFileOperationsSample.createSingleFile(p.resolve("testfil2.txt"));
//		AllFileOperationsSample.copySingleFile(p.resolve("test.txt"), p.resolve("newDir"));
//		AllFileOperationsSample.listDir(p, null);
//		AllFileOperationsSample.createSingleDirectory(p.resolve("ddir"));
//		AllFileOperationsSample.copyMultipleFiles(p, p.resolve("ddir"), "dfadf");
//		AllFileOperationsSample.listDir(p.resolve("dir"), null);
//		AllFileOperationsSample.moveSingleFile(p.resolve("moveTest.txt"), p.resolve("dir"), "replace");
//		AllFileOperationsSample.listDir(p.resolve("dir"), null);
//		AllFileOperationsSample.listDir(p, null);
//		AllFileOperationsSample.deleteSingleFileOrDir(p.resolve("testfile.txt"));
//		AllFileOperationsSample.listDir(p, null);
//		AllFileOperationsSample.renameSingleFile(p.resolve("testfil2.txt"), p.resolve("testfile.txt"));
//		AllFileOperationsSample.moveMultipleFiles(p, p.resolve("dir"), "*.txt", "replace");
//		AllFileOperationsSample.deleteMultipleFiles(p.resolve("deldir"), "*.*");
//		AllFileOperationsSample.deleteSingleFileOrDir(p.resolve("deldir"));
//		AllFileOperationsSample.listDir(p.resolve("deldir"), null);
		AllFileOperationsSample.listDir(p, null);
	}
}
