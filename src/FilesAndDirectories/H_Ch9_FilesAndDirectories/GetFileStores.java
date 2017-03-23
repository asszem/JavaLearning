package FilesAndDirectories.H_Ch9_FilesAndDirectories;

import java.nio.file.FileStore;
import java.nio.file.FileSystems;
import java.nio.file.FileSystem;
import java.io.IOException;

/**
 *
 * @author Andras Olah (olahandras78@gmail.com)
 */
public class GetFileStores {

	public static void main(String[] args) {
		FileSystem fileSystem = FileSystems.getDefault();
		Iterable<FileStore> stores = fileSystem.getFileStores();
		long gigabyte = 1_073_741_824L;
		for (FileStore store : stores) {
			try {
				System.out.format("\nStore Name: %s%nType: %s%ngetTotalSpace: %dGB%ngetUnallocatedSpace: %dGB%ngetUsableSpace:%dGB%nisReadOnly? %s%n",
						store.name(),
						store.type(),
						store.getTotalSpace() / gigabyte,
						store.getUnallocatedSpace() / gigabyte,
						store.getUsableSpace() / gigabyte,
						store.isReadOnly());

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
