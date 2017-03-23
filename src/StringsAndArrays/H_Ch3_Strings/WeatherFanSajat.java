/*
 *To save you having to wander around 10 different locations armed with a thermometer, you'll generate the
temperatures as random values between –10 degrees and 35 degrees. This assumes you are recording temperatures
 */
package StringsAndArrays.H_Ch3_Strings;

/**
 *
 * @author olaha
 */
public class WeatherFanSajat {

  static float temperature[][] = new float[10][365];

  static double rnd() {
    double randomResult;
    do {
      randomResult = Math.ceil(Math.random() * 100) - 10;
    } while (randomResult > 35);
    return randomResult;
  }

  static void setTemperature() {
    for (int i = 0; i < temperature.length; i++) {
      for (int j = 0; j < 365; j++) {
        temperature[i][j] = (float) rnd();
      }
    }
  }

  static void getTemperature() {
    for (int i = 0; i < temperature.length; i++) {
      for (int j = 0; j < 365; j++) {
        System.out.println("[" + i + "] [" + j + "] = " + temperature[i][j]);
      }
    }
  }

  static void setAvg() {
    float summary;
    float[] average = new float[temperature.length];
    for (int i = 0; i < temperature.length; i++) {
      summary = 0;
      for (int j = 0; j < temperature[1].length; j++) {  //itt a többdimenziós tömb második dimenziójára, nem pedig a második elemére hivatkozok. 
        summary += temperature[i][j];
      }
      average[i] = summary / temperature[1].length;
      System.out.println("Average temperature at location " + i + " = " + average[i]);
    }
  }

  public static void main(String[] args) {
    System.out.println("temperature.length= " + temperature.length);
    System.out.println("temperature[0].length= " + temperature[1].length);
    System.out.println("temperature[1].length= " + temperature[1].length);
    setTemperature();
    setAvg();
    //getTemperature();
  }
}
