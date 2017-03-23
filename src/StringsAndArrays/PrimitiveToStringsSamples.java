/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package StringsAndArrays;

import java.text.NumberFormat;
import java.util.Locale;

/**
 *
 * @author olaha
 */
public class PrimitiveToStringsSamples {

  public static void main(String[] args) {
    int inValue = 42;
    //helper class Integer used
    String fromInt = Integer.toString(inValue);
    System.out.println("fromInt: " + fromInt);

    boolean boolValue = true;
    String fromBool = Boolean.toString(boolValue);
    System.out.println("fromBool: " + fromBool);

    long longValue = 10_000_000;
    String fromLong = Long.toString(longValue);
    System.out.println("fromLong: " + fromLong);

    NumberFormat longFormatted = NumberFormat.getNumberInstance(Locale.ITALY);
    System.out.println("longFormatted IT: " + longFormatted.format(longValue));

    longFormatted = NumberFormat.getNumberInstance(Locale.ENGLISH);
    System.out.println("longFormatted ENG: " + longFormatted.format(longValue));

    Locale lokalis = new Locale("hu", "HU");
    longFormatted = NumberFormat.getNumberInstance(lokalis);
    System.out.println("longFormatted HU: " + longFormatted.format(longValue));

    char[] chars = {'H', 'e', 'l', 'l', 'o'};
    String salutation = new String(chars);
    System.out.println("Salutation: " + salutation);

    char[] chars2 = salutation.toCharArray();
    System.out.print("chars2: ");
    for (char i : chars2) {
      System.out.print(i);
    }

  }

}
