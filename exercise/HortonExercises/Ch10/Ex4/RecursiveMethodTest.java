package HortonExercises.Ch10.Ex4;

/**
 *
 * @author Andras Olah (olahandras78@gmail.com)
 */
public class RecursiveMethodTest {

	static void recursiveMethod(int i) {
		if (i != 0) {
			System.out.println("i:" + i);
			i--;
			recursiveMethod(i);
		}
	}

	public static void main(String[] args) {
		recursiveMethod(5);
	}
}
