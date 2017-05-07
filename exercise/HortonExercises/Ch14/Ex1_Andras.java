/*
Requirement

Implement a version of the program to calculate prime numbers that you saw in Chapter 4 to use a
Vector<> object instead of an array to store the primes. (Hint: Remember the Integer class.)
 */
package HortonExercises.Ch14;

import static java.lang.Math.ceil;
import static java.lang.Math.sqrt;
import java.util.Vector;

/**
 *
 * @author Andras Olah (olahandras78@gmail.com)
 */
public class Ex1_Andras {

	public static void main(String[] args) {

		Vector<Long> primesVector = new Vector<>();
		int numberOfPrimesToLookFor = 40;

		primesVector.add(0, 2L);			//The first prime is 2, at index 0 in the Vector
		primesVector.add(1, 3L);			//The second prime is 3, at index 1 in the Vector

		int primesFound = 2;               // Count of primes found - up to now,
		// which is also the array index
		long numberToTest = 5L;            // Next integer to be tested

		outerToContinue:
		for (; primesFound < numberOfPrimesToLookFor; numberToTest += 2L) { //+2 to ensure odd number
			System.out.println("*****");
			System.out.println("Testing number " + numberToTest);
			// The maximum divisor we need to try is square root of numberToTest
			//Cast numberToTest to double, take the square root, round up(ceil) cast to long
			long limit = (long) ceil(sqrt((double) numberToTest));
			System.out.println("Maximum divisor limit: " + limit);
			// Divide by all the primes we have up to limit
			for (int i = 1; i < primesFound && primesVector.get(i) <= limit; ++i) {
//				System.out.println("\tDivided by prime: " + primesVector.elementAt(i));
				System.out.print("\tDivision: " +numberToTest+ "/" +primesVector.get(i) +"="+ numberToTest / primesVector.elementAt(i));
				System.out.println("\tRemainder: " + numberToTest % primesVector.elementAt(i));
				if (numberToTest % primesVector.get(i) == 0L) {   // Is it an exact divisor?
//<editor-fold desc="Description">
/*You test the value in numberToTest in the inner for loop
by dividing numberToTest by all of the prime numbers you have in the primes array 
that are less than, or equal to, the square root of the candidate [limit].

If you get an exact division, the value in numberToTest is not prime
so you go immediately to the next iteration of the outer loop via the continue statement.*/
//</editor-fold>
					System.out.println(numberToTest + " is not a prime!");
					continue outerToContinue;              // Yes, so try the next numberToTest
				}
			}
			// If this is reached, we found a new prime!
			System.out.println(numberToTest + " is a prime!");
			primesVector.add(primesFound++, numberToTest);
		}

		int counter = 1;
		for (long n : primesVector) {
			System.out.printf("%03d ", n);           // Output all the primes
			if (counter++ % 5 == 0) {
				System.out.println("");
			}
		}
		System.out.println("\nTotal number of primes found: " + primesFound);
	}
}
/*
   2    3    5    7   11   13
   17   19   23   29   31   37
   41   43   47   53   59   61
   67   71

 */
