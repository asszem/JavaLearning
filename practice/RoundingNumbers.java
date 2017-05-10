/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Arrays;

/**
 *
 * @author olaha
 */
public class RoundingNumbers {

  static void printClassName(Object obj) {
    System.out.println("The class of " + obj.hashCode()
            + " is " + obj.getClass().getName());
  }
  
  //DecimalFormat class
  static void roundup(double input){
    DecimalFormat df = new DecimalFormat("#.####"); //creates a new object with the format parameter
    df.setRoundingMode(RoundingMode.CEILING);       //sets the rounding mode to CEILING - setter method with a final instance variable
    String a = df.format(input);
    System.out.println("String: " +a);
  }

  public static void main(String[] args) {
    DecimalFormat df = new DecimalFormat("#.####");
    df.setRoundingMode(RoundingMode.CEILING);
    for (Number n : Arrays.asList(12, 123.12345, 0.23, 0.1, 2341234.212431324)) {
      Double d = n.doubleValue();
      System.out.println(df.format(d));
    }
    
    roundup(12);
    roundup(12.1);
    roundup(12.12112323);
    

  }
}
