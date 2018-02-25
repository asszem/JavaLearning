/*
A stream is an abstract representation of an input or output device that is a source of, or destination for,
data

Input stream - read data from (keyboard, files)
Output stream - write data to (file, monitor, network)

The main reason for using a stream as the basis for input and output operations is to make your program code for these operations independent of the device involved.

When you write strings to a stream as character data, by default the Unicode characters are automatically converted to the local representation of the characters in the host machine, and these are then written to the stream. 

StreamTokenizer
	import java.io.StreamTokenizer;
	StreamTokenizer token = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in, StandardCharsets.ISO_8859_1)));

Sample: how to set tokenizer to use space only as separator
    tokenizer.resetSyntax();

    // Characters other than comma and special characters are word characters
    tokenizer.wordChars('\u0000', (char)(separator - 1));                 // Everything is a word character
    tokenizer.wordChars((char)(separator + 1), '\u00ff');                 // except for the separator
    tokenizer.whitespaceChars('\n', '\n');                                // Make end-of-line whitespace(and therefore a word delimiter)
    tokenizer.whitespaceChars(separator, separator);                      // Delimiter separates words
    tokenizer.eolIsSignificant(true);                                     // End-of-line to be reported as TT_EOL


*/

/*Additonal resources
Good Structure of Streams:
	http://www.math.uni-hamburg.de/doc/java/tutorial/essential/io/overview.html
Good simple explanations and examples of reading/writing files
	https://docs.oracle.com/javase/tutorial/essential/io/streams.html



*/
package IOStreams.H_Ch8_Streams;

/**
 * 
 * @author Andras Olah (olahandras78@gmail.com)
 */
public class Chapter_8_Streams {

}
