package StringsAndArrays;

import java.text.NumberFormat;
import java.util.Locale;

public class NumberFormat_Samples {
//Setting the locale
  static Locale localeVariable = new Locale("de", "DK");
          
  public static void main(String[] args) {
    double doubleValue = 1_234_567.89;
    System.out.println("Locale "+localeVariable);
    
    NumberFormat numF;
    numF = NumberFormat.getNumberInstance(); 
    System.out.println("Number: \t\t" + numF.format(doubleValue));
    numF = NumberFormat.getNumberInstance(localeVariable); 
    System.out.println("Locale Number: \t\t" + numF.format(doubleValue));
    
    NumberFormat curF;
    curF = NumberFormat.getCurrencyInstance();
    System.out.println("Currency: \t\t"+curF.format(doubleValue));
    curF = NumberFormat.getCurrencyInstance(localeVariable);
    System.out.println("Locale Currency: \t"+curF.format(doubleValue));
    
    NumberFormat integerF;
    integerF = NumberFormat.getIntegerInstance();
    System.out.println("Integer: \t\t"+integerF.format(doubleValue));
    integerF = NumberFormat.getIntegerInstance(localeVariable);
    System.out.println("Locale Integer: \t"+integerF.format(doubleValue));
    System.out.println("Locale Integer: \t"+integerF.format(doubleValue-567.89));
  }
}
