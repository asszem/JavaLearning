package FilesAndDirectories.H_Ch9_FilesAndDirectories;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;
import java.io.IOException;

public class PathObjects {

	public static void main(String[] args) {
		FileSystem fileSystem = FileSystems.getDefault();

		Path path = fileSystem.getPath("garbage.java");
		checkPath(path);

		path = Paths.get(System.getProperty("user.dir"));
		checkPath(path);

		// Amend the following path to your environment
		path = fileSystem.getPath("E:", "javaTest", "subdirectory", "test.txt");
		checkPath(path);

		String p1 = "E:\\javaFileOpTest";
		String p2 = "firstSubdirectory";
		String p3 = "SecondSubdirectory";
		System.out.println("Creating Path objects");
		System.out.println(Paths.get(p1 + p2 + p3));
		System.out.println(Paths.get(p1, p2, p3));
		System.out.println("Resolve Path");
		Path toResolve = Paths.get(p1);
		System.out.println("toResolve before: " + toResolve);
		System.out.println("toResolve after: " + toResolve.resolve(p2).resolve(p3));
		System.out.println("Relativize Path");
		Path toRelativize = Paths.get(p1, p2, p3);
		System.out.println("toRelativize before: " + toRelativize);
		System.out.println("toRelativize: " + toRelativize.relativize(Paths.get(p1, p3)));
		Path path1 = Paths.get("E:\\resolve\\joe");
		Path path2 = Paths.get("E:\\resolve\\sally");
		System.out.printf("path1: %s%npath2: %s%n", path1, path2);
// Result is ../sally
		Path p1_to_p2 = path1.relativize(path2);
		System.out.println(p1_to_p2);
// Result is ../joe
		Path p2_to_p1 = path2.relativize(path1);
		System.out.println(p2_to_p1);
	}

	// Check the attributes of a path
	static void checkPath(Path path) {
		System.out.println("\n" + path + " has " + path.getNameCount() + " elements.");
		if (path.isAbsolute()) {
			System.out.println(path + " is an absolute path.");
			System.out.println("The parent path is " + path.getParent());
			System.out.println("The root is " + path.getRoot());
		} else {
			System.out.println(path + " is a relative path.");
			path = path.toAbsolutePath();
		}

		if (Files.notExists(path)) {
			System.out.println(path + " does not exist.");
			return;
		}

		try {
			BasicFileAttributes attr = Files.readAttributes(path, BasicFileAttributes.class);
			if (attr.isDirectory()) {
				System.out.println(path.getFileName() + " is a directory.");
			} else if (attr.isRegularFile()) {
				System.out.println(path.getFileName() + " is a file containing "
						+ attr.size() + " bytes.");
			}
			System.out.println(path + " was created " + attr.creationTime());
			System.out.println(path + " was last accessed " + attr.lastAccessTime());
			System.out.println(path + " was last modified " + attr.lastModifiedTime());
			System.out.println(path + " is " + attr.size() + " bytes.");
		} catch (IOException e) {
			System.err.println(e);
		}
	}
}
