/*Write a program that calls a method that throws an exception of type ArithmeticException at a random
iteration in a for loop. Catch the exception in the method and pass the iteration count when the exception
occurred to the calling method by using an object of an exception class you define.*/
package HortonExercises.Ch07.AndrasSolution.Ex3;

/**
 *
 * @author Andras Olah (olahandras78@gmail.com)
 */
public class Ex3 {

	public static void main(String[] args) {
		try {
		tesztMethod();
		} catch (MyException hiba) {
			System.out.println("Hiba indexe:"+hiba.getIterationCount());
			System.out.println("Hiba oka: "+hiba.getCause());
		}
	}

	public static void tesztMethod() throws MyException {
		int iterationLength = 20;
		for (int i = 0; i < iterationLength; i++) {
			try {
				int teszt = (int) (Math.random() * 2) % 2 == 0 ? 1 : 0;
				System.out.print((teszt == 0 ? "Hibás iterációk:" + i + "\n" : ""));
				int test = 10 / teszt;
			} catch (ArithmeticException e) {
//				System.out.println("Nullával osztás indexe:"+i);
				throw new MyException(i, e);
			}finally{
				System.out.println("Finally iteration count:" + i);
			}
		}
	}
}
