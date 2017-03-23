/*
Enumerations to import for Open Options (multiple or no options can be used)
	import static java.nio.file.StandardOpenOption.*; //to import all available options
	Default Options:
	-WRITE (open the file for writing)
	-CREATE (create the file if doesnt exists)
	-TRUNCATE_EXISTING (new data is overwriting existing data)

Writing Bytes by creating OutputStream object - Files.newOutputStream
	OutputStream fileOut = Files.newOutputStream(path, CREATE, APPEND);
	BufferedOutputStream fileOutBuffered = new BufferedOutputStream(fileOut);

Writing Characters by creating BufferedWriter object - Files.newBufferedWriter
	BufferedWriter fileOut = Files.newBufferedWriter(filePath, Charset.forName("UTF-16"), CREATE, APPEND);

Files.newByteChannel()
	Writing files through a Channel. See Channel & Buffers
 */
package FilesAndDirectories;

import java.io.IOException;
import java.nio.*;
import java.nio.file.*;
import static java.nio.file.StandardOpenOption.APPEND; //Enumerations
import static java.nio.file.StandardOpenOption.CREATE;

/**
 *
 * @author Andras Olah (olahandras78@gmail.com)
 */
public class AllWritingFiles {

}
