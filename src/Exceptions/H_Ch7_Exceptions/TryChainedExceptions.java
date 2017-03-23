package Exceptions.H_Ch7_Exceptions;


public class TryChainedExceptions {

  public static void main(String[] args) {
    int[] x = {10, 5, 0, 3, 12, 0, 6};                                 // Array of  integers
    System.out.print("Values: ");
    for(int xValue : x) {
      System.out.print("  " + xValue);
    }
    System.out.println();

    for(int i = 0 ; i < x.length ; ++i) {
      // This block only throws an exception if method divide() does
      try {
        System.out.println("First try block in main()entered");
        System.out.println("result = " + divide(x,i));
      } catch(ZeroDivideException e) {
        System.out.println("\nZeroDivideException caught in main()");
        int index = e.getIndex();                                      // Get the index for the error
        if(index > 0) {                                                // Verify it is valid and now fix the array
          x[index] = 1;                                                // ...set the divisor to 1...
          x[index + 1] = x[index - 1];                                 // ...and set the result
          e.printStackTrace();
          System.err.println("Zero divisor at x[" + index + "] corrected to " + x[index]);
        }
      } catch(ArithmeticException e) {
        System.out.println("Arithmetic exception caught in main()");
      } catch(ArrayIndexOutOfBoundsException e) {
        System.out.println("Index-out-of-bounds exception caught in main()");
      }
      System.out.println("Outside first try block in main()");
    }
    System.out.print("Values: ");
    for(int xValue : x) {
      System.out.print("  " + xValue);
	  //Prediction
	  //10,5,1,5,12,1,12 
	  //Result:
	  //0,5,2,0,12,1,12
	  //Explanation: the divide method substituts x[index] value if the division is valid
	  //If not valid, the exception handling method changes x[error] and x[error+1] values.
    }
    System.out.println();
  }

  public static int divide(int[] array, int index) throws ZeroDivideException {
    try {
      System.out.println("\nFirst try block in divide() entered");
      array[index] = array[index+2]/array[index + 1];
      System.out.println("Code at end of first try block in divide()");
      return array[index + 2];

    } catch(ArithmeticException e) {
      System.out.println("Arithmetic exception caught in divide()");
      ZeroDivideException zde = new ZeroDivideException(index + 1, e);
      System.out.println("Throwing ZeroDivideException");
      throw zde;                                                       // Throw the new exception
    } catch(ArrayIndexOutOfBoundsException e) {
      System.out.println("Index-out-of-bounds index exception caught in divide()");
    }
    System.out.println("Executing code after try block in divide()");
    return array[index];
  }
}
