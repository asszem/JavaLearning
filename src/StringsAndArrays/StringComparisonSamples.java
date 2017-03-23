/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package StringsAndArrays;

/**
 *
 * @author olaha
 */
public class StringComparisonSamples {

  public static void main(String[] args) {
    String str1 = "Hello";
    String str2 = "Hello"; //here the second reference variable were pointed to the first one since they are identical
    System.out.print("str 1 \"" + str1 + "\" == str2 \"" + str2 + "\":\t");
    if (str1 == str2) {
      System.out.println("They match!");
    } else {
      System.out.println("They don't match!");
    }

    String str3 = "hello"; //don't match because case mismatch, the new object was created in lower case
    System.out.print("str 1 \"" + str1 + "\" == str3 \"" + str3 + "\":\t");
    if (str1 == str3) {
      System.out.println("They match!");
    } else {
      System.out.println("They don't match!");
    }

    String part1 = "Hello ";
    String part2 = "World";
    String str4 = part1 + part2;    //str 4 and str 5 are different objects. 
    String str5 = "Hello World";
    System.out.print("str 4 \"" + str4 + "\" == str5 \"" + str5 + "\":\t\t\t");
    if (str4 == str5) {
      System.out.println("They match!");
    } else {
      System.out.println("They don't match!");
    }

    System.out.print("str 4 \"" + str4 + "\" equals() str5 \"" + str5 + "\":\t\t");
    if (str4.equals(str5)) {
      System.out.println("They match!");
    } else {
      System.out.println("They don't match!");
    }

    str5 = "HELLO WORLD";
    System.out.print("str 4 \"" + str4 + "\" equals() str5 \"" + str5 + "\":\t\t");
    if (str4.equals(str5)) {
      System.out.println("They match!");
    } else {
      System.out.println("They don't match!");
    }
    System.out.print("str 4 \"" + str4 + "\" equalsIgnoreCase() str5 \"" + str5 + "\":\t");
    if (str4.equalsIgnoreCase(str5)) {
      System.out.println("They match!");
    } else {
      System.out.println("They don't match!");
    }

  }

}
