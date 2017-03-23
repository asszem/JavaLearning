package Streams;

import java.text.NumberFormat;
import java.util.Locale;

public class NumberFormatSamples {
//Setting the locale
  static Locale localeVariable = new Locale("de", "DK");
          
  public static void main(String[] args) {
    double doubleValue = 1_234_567.89;
    System.out.println("Locale "+localeVariable);
    
    NumberFormat numF;
    numF = NumberFormat.getNumberInstance(); //A NumberFormat osztályt használva a getNumberInstance methoddal.
    System.out.println("Number: \t\t" + numF.format(doubleValue));
    numF = NumberFormat.getNumberInstance(localeVariable); //A NumberFormat osztályt használva a getNumberInstance methoddal.
    System.out.println("Locale Number: \t\t" + numF.format(doubleValue));
    
    NumberFormat curF;
    curF = NumberFormat.getCurrencyInstance();
    System.out.println("Currency: \t\t"+curF.format(doubleValue));
    curF = NumberFormat.getCurrencyInstance(localeVariable);
    System.out.println("Locale Currency: \t"+curF.format(doubleValue));
    
    NumberFormat datF;
    datF = NumberFormat.getIntegerInstance();
    System.out.println("Integer: \t\t"+datF.format(doubleValue));
    datF = NumberFormat.getIntegerInstance(localeVariable);
    System.out.println("Locale Integer: \t"+datF.format(doubleValue));
  }
}
