package HortonExercises.Ch07.AndrasSolution.Ex2;

/**
 *
 * @author Andras Olah (olahandras78@gmail.com)
 */
public class ioubException extends Exception {

	private int errIndex = 0;	//Instance variable to store the error index number

	public ioubException() {
		//a konstruktor hívása után a létrehozott objektumnak az initCause() metódussal tudjuk
		//átadni egy másik Throwable objektumot, okként.
	}

	//Constructor with 3 parameters to create own class
	public ioubException(int index, String errMsg, Throwable cause) {
		super(errMsg, cause);
		errIndex = index;
	}

	//Method to get the errIndex instance variable
	public int getErrIndex() {
		return errIndex;
	}
}
