package ClassesAndObjects.H_Ch05_Classes;

class TryInitialization {
  static int[] values = new int[10];                                   // Static array  member

  // Initialization block
  static {
    System.out.println("Running static initialization block.");
    for(int i = 0; i < values.length; ++i) {
      values[i] = (int)(100.0*Math.random());
    }
  }

  // Non-static initialization block
  {
    System.out.println("Running instance initialization block.");
    for(int i = 0; i < values.length; ++i) {
      values[i] = (int)(100.0*Math.random());
    }
  }

  // List values in the array - static, can run without any object being created 
  static void listValuesStatic() {
    System.out.println("Static values initialized: ");                                              // Start a new line
    for(int value : values) {
      System.out.print(value+" ");                                   // Display values
    }
  }

  // List values in the array for an object
  void listValues() {
    for(int value : values) {
      System.out.print(value+" ");                                   // Display values
    }
  }

  public static void main(String[] args) {
	listValuesStatic();
    System.out.println("\nFirst object:");
    TryInitialization example = new TryInitialization();
    example.listValues();

    System.out.println("\nSecond object:");
    example = new TryInitialization();
    example.listValues();
  }
}
