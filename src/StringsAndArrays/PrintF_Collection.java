/*
%[argument_position$][flag(s)][width][.precision]conversion-type-character

The optional argument_position specifies the position of the argument in the argument list. The first argument is "1$", second argument is "2$", and so on.

The optional width indicates the minimum number of characters to be output.

The optional precision restricts the number of characters (or number of decimal places for float-point numbers).

The mandatory conversion-type-character indicates how the argument should be formatted. 
	'b', 'B' (boolean), 
	'h', 'H' (hex string), 
	's', 'S' (string), 
	'c', 'C' (character), 
	'd' (decimal integer), 
	'o' (octal integer), 
	'x', 'X' (hexadecimal integer), 
	'e', 'E' (float-point number in scientific notation), 
	'f' (floating-point number), ALSO USE FOR DOUBLE
	'%' (percent sign). 

The uppercase conversion code (e.g., 'S') formats the texts in uppercase.

Flag: 
	'-' (left-justified),
	'+' (include sign), 
	' ' (include leading space), 
	'0' (zero-padded), 
	',' (include grouping separator). 
	'(' (negative value in parentheses), 
	'#' (alternative form).

Additional sources: 
http://www3.ntu.edu.sg/home/ehchua/programming/java/j5b_io.html
https://docs.oracle.com/javase/8/docs/api/java/util/Formatter.html#syntax
*/
package StringsAndArrays;

import java.util.Formatter;

/**
 * 
 * @author Andras Olah (olahandras78@gmail.com)
 */
public class PrintF_Collection {
	public static void main(String[] args) {
		String stringToFormat="%d, %d, %d, %s";
		Formatter formatter = new Formatter();
		System.out.println(formatter.format(stringToFormat, 1,2,3,"Hello"));
		
		
		
		String str="teszt";
		System.out.printf("String %1$S String %1$s",str);
		System.out.println("");
		Double doubleNum=-123456.789;
		int intNum=54321;
		System.out.printf("Int %2$+,10d%nDouble %1$f%nDouble %1$,(.2f%nDouble %1$20f%%%n",doubleNum, intNum);
	}
}
