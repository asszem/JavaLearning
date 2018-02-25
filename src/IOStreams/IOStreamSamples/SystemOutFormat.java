
package IOStreams.IOStreamSamples;

/**
 * 
 * @author Andras Olah (olahandras78@gmail.com)
 */
public class SystemOutFormat {
	public static void main(String[] args) {
		int i = 23;
		double r = Math.sqrt(i);
		System.out.println(r);
		System.out.format("The square of %d equals %.3f %n",i,r);
		//%[argument_index$][flags][width][.precision]conversion
		//%d - formats an integer value as a decimal value
		//%f - formats a floating value as a decimal value
		//%n - new line
		//additional details: https://docs.oracle.com/javase/8/docs/api/java/util/Formatter.html#syntax
	}

}
