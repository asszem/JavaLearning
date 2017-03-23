package Exceptions.H_Ch7_Exceptions;
/*Note:
The ArrayIndexOutOfBoundsException class is a subclass of RuntimeException and is therefore exempted from the obligation to deal with it.
*/

import java.io.IOException; //The IOException class is in the package java.io, so you need the import statement for this class because you refer to it in the catch block using its unqualified name

public class TryBlockTest {
  public static void main(String[] args) {
    int[] x = {10, 5, 0};                                              // Array of three integers

    // This block only throws an exception if the divide() method does
    try {
      System.out.println("First try block in main() entered");
      System.out.println("result in main= " + divide(x,0));                   // No error
      x[1] = 0;                                                        // Will cause a divide by zero
      System.out.println("result in main= " + divide(x,0));                   // Arithmetic error
      x[1] = 1;                                                        // Reset to prevent divide by zero
      System.out.println("result in main= " + divide(x,1));                   // Index error
    } catch(ArithmeticException e) {
      System.out.println("Arithmetic exception caught in main()");
    } catch(ArrayIndexOutOfBoundsException e) {
      System.out.println("Index-out-of-bounds exception caught in main()");
    }

    System.out.println("Outside first try block in main()");
    System.out.println("\nPress Enter to exit");

    // This try block is just to pause the program before returning
    try {
      System.out.println("In second try block in main()");
      System.in.read();                                                // Pauses waiting for input...
      return;

    } catch(IOException e) {                                           // The read() method can throw exceptions
      System.out.println("I/O exception caught in main()");
    } finally {                                                        // This will always be executed
      System.out.println("finally block for second try block in main()");
    }

    System.out.println("Code after second try block in main()");
  }

  // Divide method
  public static int divide(int[] array, int index) {
    try {
      System.out.println("\nFirst try block in divide() entered");
      array[index + 2] = array[index]/array[index + 1];
      System.out.println("Code at end of first try block in divide()");
      return array[index + 2];
    } catch(ArithmeticException e) {
      System.out.println("Arithmetic exception caught in divide()");
      System.out.println("index = " + index +
       "  Expression: " + "array[" + index + "]/array["+ (index+1) +"] is " +
                                        array[index] + "/" + array[index+1]);
    } catch(ArrayIndexOutOfBoundsException e) {
      System.out.println("Index-out-of-bounds exception caught in divide()");
      System.out.println("array length = " + array.length +
                                                       "  index = " + index);
    } finally {
      System.out.println("finally block in divide()");
    }

    System.out.println("Executing code after try block in divide()");
    return array[index + 2];
  }
}
