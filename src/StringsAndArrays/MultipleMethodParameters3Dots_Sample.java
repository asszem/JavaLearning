
package StringsAndArrays;

/**
 * 
 * @author Andras Olah (olahandras78@gmail.com)
 */
public class MultipleMethodParameters3Dots_Sample {

public static void printHeader(String... strings) {
		System.out.println("*************************");
		for (String string : strings) {
			System.out.println(string);
		}
		System.out.println("*************************");
	}

	public static void main(String[] args) {
		String string1 = "Hi!";
		printHeader("Hello", "Bello", string1);
	}
}
