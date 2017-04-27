package ClassesAndObjects.H_Ch13_GenericTypes.IterableLinkedList;

public class TryIterableLinkedList {
  public static void main(String[] args) {
    IterableLinkedList<Double> temperatures = new IterableLinkedList<>();

    // Insert 6 temperature values 0 to 25 degrees Centigrade
    for(int i = 0 ; i < 6 ; ++i) {
      temperatures.addItem(25.0*Math.random());
    }

    // Collection-based for loop used with IterableLinkedList<Double>
    for(Double value : temperatures) {
      System.out.printf("%.2f degrees Fahrenheit%n", toFahrenheit(value));
    }
  }

  // Convert Centigrade to Fahrenheit
  public static double toFahrenheit(double temperature) {
    return 1.8*temperature + 32.0;
  }
}
