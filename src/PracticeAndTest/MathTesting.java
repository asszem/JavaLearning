/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PracticeAndTest;

import java.text.NumberFormat;

/**
 *
 * @author olaha
 */
public class MathTesting {
  public static void main(String[] args) {
    double a = 6.235322321453252;
    NumberFormat formatter = NumberFormat.getIntegerInstance();
    System.out.println("a= "+formatter.format(a));
    System.out.println("Math.round()= "+Math.round(a));
    System.out.println("Math.ceil()= "+Math.ceil(a));
    
    System.out.println("Random teszt" +(int)(1.0 + 6.0*Math.random()));
  }
}
