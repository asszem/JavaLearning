package StringsAndArrays.H_Ch3_Strings;

/*
1. Take the number in question and determine its square root.
2. Set the limit for divisors to be the smallest integer that is greater than this square root value.
3. Test to see if the number can be divided exactly (without remainder) by any of the primes already in the
primes array that are less than the limit for divisors.
4. If any of the existing primes divide into the current number, discard the current number and start a new
iteration of the loop with the next candidate number.
5. If none of the divisors divide into number without a remainder, it is a prime, so enter the existing number in
the first available empty slot in the array and then move to the next iteration for a new candidate number.
6. When the array of primes is full, stop looking for new primes and output all the prime number values from
the array

*/

import static java.lang.Math.ceil;
import static java.lang.Math.sqrt;

public class MorePrimes {

  public static void main(String[] args) {
    long[] primes = new long[20];      // Array to store primes - LONG - integer típusú változó, több szám fér bele, mint az intbe
    primes[0] = 2L;                    // Seed the first prime
    primes[1] = 3L;                    // and the second
    int count = 2;                     // Count of primes found - up to now,
    // which is also the array index
    long number = 5L;                  // Next integer to be tested  -- a number változó lesz mindig letesztelve

    outerToContinue:
    //the first parameter in for can be ommitted as count already has a value of 2
    for (; count < primes.length; number += 2L) { //azért mindig kettőt ad hozzá, hogy biztos páratlan szám legyen, csak az lehet prím
      // The maximum divisor we need to try is square root of number
      long limit = (long) ceil(sqrt((double) number)); //a number változó gyökét felfelé kerekítve long típusba kasztolva

      // Divide by all the primes we have up to limit
      for (int i = 1; i < count && primes[i] <= limit; ++i) {
//        System.out.println("limit"+limit);
        if (number % primes[i] == 0L) {   // Is it an exact divisor?
          /*You test the value in number in the inner for loop by dividing number by all of the prime numbers you have in the primes array 
          that are less than, or equal to, the square root of the candidate [limit]. 
          If you get an exact division, the value in number is not prime, so you go immediately to the next iteration of the outer loop
          via the continue statement.*/
          continue outerToContinue;              // Yes, so try the next number
        }
      }
      primes[count++] = number;        // We got one! Hozzáadja az értéket a count indexen a primes tömbhöz MAJD megnöveli a countert.
    }

    for (long n : primes) {
      System.out.print(n+", ");           // Output all the primes
    }
    System.out.println("\nEnnyit találtunk " + count);
  }
}
