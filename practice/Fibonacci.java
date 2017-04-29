package PracticeAndTry;

/**
 *
 * @author Andras Olah (olahandras78@gmail.com)
 */
public class Fibonacci {

	public static void main(String[] args) {
		int counter = 50;
		long[] fiboArray = new long[2]; //long used as numbers will go big
		int arrayIndex = 0;
		fiboArray[0]=0L;
		fiboArray[1]=1L;
		long[] fiboResults = new long[counter]; //to store the results
		for (int i = 0; i < counter; i++) {
			System.out.printf("%d. [%d]+[%d]",i, fiboArray[0],fiboArray[1]);
			fiboResults[i]=fiboArray[0]+fiboArray[1];
			fiboArray[0]=fiboArray[1];
			fiboArray[1]=fiboResults[i];
			System.out.printf("=%d%n",fiboResults[i]);
		}
	}//main	
}//args
