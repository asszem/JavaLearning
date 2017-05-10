/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.lang.Math;
import static java.lang.Math.ceil;
import static java.lang.Math.random;

public class RandomTesting {

  private static int randomRange = 100; //A véletlen számot ennyivel fogja megszorozni.

  public static double rnd() {
    double random = random() * randomRange;
    return random;
  }

  public static int rnd(int min, int max) {
    
    int randomNumber;
    do {
      randomNumber = rndInt();
    } while (randomNumber < min || randomNumber > max);
//    System.out.println("A "+min+" és "+max+" közötti véletlen szám:"+randomNumber);
    return randomNumber;
  }


public static int rndInt() {
    int random = (int) (random() * randomRange);
    return random;
  }

  public static void main(String[] args) {
//    System.out.println("rnd(): " + rnd());
//    System.out.println("rndInt(): " + rndInt());
//    rnd(115,110);
//    
    
    /*The Math.random() method generates a value of type double from 0.0 up to, but excluding, 1.0. This value
is multiplied by 45.0 in the expression for the temperature, which results in values between 0.0 and 45.0.
Subtracting 10.0 from this value gives you the range you require, 10.0 to 35.0.*/
    double a = Math.random();
    System.out.println("random: "+a+" megszorozva: "+(161*a));
    
  }
}
