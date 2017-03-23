package HortonExercises.Ch07.AndrasSolution.Ex3;

/**
 *
 * @author Andras Olah (olahandras78@gmail.com)
 */
public class MyException extends Exception {

	private int iterationCount;

	public MyException() {
	}

	public MyException(int iterationCount, Throwable cause) {
		super(cause);
		this.iterationCount = iterationCount;
	}

	public int getIterationCount() {
		return iterationCount;
	}
}
