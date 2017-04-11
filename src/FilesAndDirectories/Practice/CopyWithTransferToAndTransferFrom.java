/*
Requirement based on Horton's chapter 11, reading files, pg:432
- You copy the file to a backup file that you create in the same directory as the original. 
- You create the name of the new file by appending “_backup” to the original filename as many times as necessary to form a unique filename. 


 */
package FilesAndDirectories.Practice;

import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import static java.nio.file.StandardOpenOption.*;

/**
 *
 * @author Andras Olah (olahandras78@gmail.com)
 */
public class CopyWithTransferToAndTransferFrom {

	public static void copyFile(Path sourceFile, Path targetFile) {
		if (!Files.exists(sourceFile)) {
			throw new IllegalArgumentException("Source file does not exists.");
		}
		try (FileChannel readFromChannel = (FileChannel) Files.newByteChannel(sourceFile, READ)) {
			long bytesWritten =0;
			long sourceFileSize=readFromChannel.size();
			FileChannel writeToChannel = (FileChannel) Files.newByteChannel(targetFile, WRITE, CREATE_NEW);
			//This works also:
			//readFromChannel.transferTo(bytesWritten, sourceFileSize, writeToChannel);
			//This makes sure all bytes are transferred and keeps count of bytes sent
			while(bytesWritten<sourceFileSize){
				bytesWritten+=readFromChannel.transferTo(bytesWritten, sourceFileSize-bytesWritten, writeToChannel);
				System.out.println("");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	//This is to test run of this class
	public static void main(String[] args) {
		Path sourceFile = Paths.get("E:\\javaFileOpTest\\CreateBackupFile\\test.txt");
		copyFile(sourceFile, CreateBackupPath.getBackupPath(sourceFile));
	}
}
