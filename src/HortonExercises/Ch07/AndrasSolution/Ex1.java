/*Write a program that generates exceptions of type NullPointerException,
NegativeArraySizeException, and IndexOutOfBoundsException. Record the catching of each
exception by displaying the message stored in the exception object and the stack trace record
 */
package HortonExercises.Ch07.AndrasSolution;

/**
 *
 * @author Andras Olah (olahandras78@gmail.com)
 */
public class Ex1 {

	public static void main(String[] args) {
		int[] array = new int[(1 + (int) ((Math.random() * 20)))]; //1 és 21 közötti random tömb méret
//1. Null Pointer Exception test
		try {
			//* <li>Calling the instance method of a {@code null} object.
			Ex1 object = null;
			object.toString();
		} catch (NullPointerException e) {
			System.out.println("Null Pointer Exception generated");
			System.out.println("Message: "+e);
			System.out.println("Stack Trace:");
			e.printStackTrace(System.out);
			System.out.println("End of Stack Trace \n");
		}
//2. Negative Array Size test
		try {
			//* Thrown if an application tries to create an array with negative size.
			int[] test = new int[-12];
		} catch (NegativeArraySizeException e) {
			System.out.println("Negative Array Size Exception generated");
			System.out.println("Message: "+e.getMessage());
			System.out.println("Stack Trace:");
			e.printStackTrace(System.out);
			System.out.println("End of Stack Trace \n");
		}
//3. Index out of bounds Exception generated
		try {
			int test = array[array.length+1];
		} catch (IndexOutOfBoundsException e) {
			System.out.println("Index Out of Bounds Exception generated");
			System.out.println("Message: "+e.getMessage());
			System.out.println("Stack Trace:");
			e.printStackTrace(System.out);
			System.out.println("End of Stack Trace \n");
		} finally {

		}
	}

}
