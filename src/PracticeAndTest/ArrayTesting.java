/*
http://www.learnjavaonline.org/en/Arrays

 */
package PracticeAndTest;

import static java.lang.Math.ceil;
import static java.lang.Math.random;
import java.util.Arrays;              //maga a teljes Array osztály importálva
import static java.util.Arrays.fill;  //az Arrays osztályból a fill metódus statikusan importálva


public class ArrayTesting {

  public static double[] array = new double[15];

  static {
    fill(array, 0, 15, 3.0);
  } //statikus inicializátor, azaz mindig lefut.

  static void fillArray() {
    System.out.println("fillArray() called");
    for (int i = 0; i < array.length; ++i) {
      array[i] = ceil(100 * random());
    }
  }

  static void printArray() {
    System.out.print("array.length=" + array.length + " értékek: ");
    for (double i : array) {
      System.out.print(i + ", ");
    }
    System.out.println("");
  }

  static void printArray(double[] inputArray) {
//TOLEARN learn how to print out the name of parameter variable passed to a method
    System.out.print("array.length=" + inputArray.length + " értékek: ");
    for (double i : inputArray) {
      System.out.print(i + ", ");
    }
    System.out.println("");
  }

  static void printArray(int[] inputArray) {
    System.out.print("array.length=" + inputArray.length + " értékek: ");
    for (int i : inputArray) {
      System.out.print(i + ", ");
    }
    System.out.println("");
  }

  static void avgArray(double[] inputArray) { //Calculates the average of the elements of array
    double summary = 0;
    for (double value : inputArray) {
      summary += value;
    }
    System.out.println("Tömb értékeinek összege: " + summary);
    System.out.println("Tömb értékeinek száma:\t " + inputArray.length);
    System.out.println("Tömb értékeinek átlaga:\t " + summary/inputArray.length);
  }

  public static void main(String[] args) {
    double[][] samples;        //Declare array of arrays
    samples = new double[5][]; //Define 5 elements, each is an array. 
    /*The samples variable now references an array with six elements, each of which can hold a reference to a
one-dimensional array. You can define these arrays individually if you want:*/
    samples[2] = new double[6];    // The 3rd array has 6 elements
    samples[4] = new double[101];  // The 6th array has 101 elements    

    //Többdimenziós tömb meghatározása esetén minden sornak (első dimenzió) megadható külön- is, hogy mekkora oszlop tartozon hozzá.
    
    double[][] tobbDimTomb;
    tobbDimTomb = new double[5][];    //Legyen 5 sor, de a sorokhoz tartozó oszlop mérete ismeretlen
    tobbDimTomb[0] = new double[1];   //A 0. soron egy 1 elemes tömb legyen
    tobbDimTomb[1] = new double[2];   //Az első soron egy 2 elemes tömb legyen
    tobbDimTomb[2] = new double[3];
    tobbDimTomb[3] = new double[4];
    tobbDimTomb[4] = new double[5];   //Az utolsó, 4, soron pedig 5 elemes tömb legyen.
    
    
    //Adjunk értéket kézzel az első tömbnek
    tobbDimTomb[0][0]=100; //ez itt konkrét értékadás, a tömb adott elemére hivatkozva
    tobbDimTomb[0][0]=100;
    System.out.println("Az első dimenzió mérete"+tobbDimTomb.length);
    System.out.println("A második dimenzió mérete az első tömb adott indexén"+tobbDimTomb[1].length);
    
    //Írjuk ki az összes tömb hosszát
    for (int i=0;i<tobbDimTomb.length;++i){
      System.out.println("A tömb "+i+" sorához tartozó tömb mérete:" + tobbDimTomb[i].length);
    }
    tobbDimTomb = new double[6][6];    //Legyen 5 sor, de a sorokhoz tartozó oszlop mérete ismeretlen
    for (int i=0;i<tobbDimTomb.length;++i){
      System.out.println("A tömb "+i+" sorához tartozó tömb mérete:" + tobbDimTomb[i].length);
    }
    
    //adjunk értéket ciklussal, a tömbök változó hosszát figyelembe véve
    for (int i=0;i<tobbDimTomb.length; ++i){
      for (int j=0;j<tobbDimTomb[i].length; ++j){
        tobbDimTomb[i][j] = 42;
      }
    }
    
    
    
    
    
//    
//    for (double[] i:tobbDimTomb){
//      System.out.println(" "+tobbDimTomb[i][1]);
//    }
    
    
    
    
    
    
    
//    double[] randomLenghtArray = new double[(int)(Math.random()*100)];
//    System.out.println("véletlen generált tömb mérete"+randomLenghtArray.length);
//    printArray();
//    double[] tombreHivatkozas = array;
//    double[] tombreHivatkozas2 = array;
//    tombreHivatkozas[0] = 100;
//    printArray();
//    printArray(tombreHivatkozas);
//    printArray(tombreHivatkozas2);
    
//    int[] ujtomb = new int[5]; //létrehoz egy 25 elemes tömböt
//    fill(ujtomb, 0, 5, 10);

//    int[] tomb;
//    tomb = new int[3];
//    System.out.println("A tömbben lévő elemek száma: " + tomb.length);
//    tomb[0] = 10;
//    tomb[1] = tomb[0] + 10;
//    tomb[2] = tomb[0] + tomb[1];
//
//    int sum = 0;
//    for (int i : tomb) {
//      sum += i;
//      System.out.println(i);
//    }
////    System.out.println("A tombben található értékek összege:" + sum);
//
//    String[] tombCsalad = {"András", "Anikó", "Barni", "Benő"};
//    System.out.println("A tombCsalad 0. indexén lévő elem: " + tombCsalad[0]);
//    for (int i = 0; i < tombCsalad.length; i++) {
//      System.out.println("A tombcsald i[" + i + "] indexén lévő érték:" + tombCsalad[i]);
//    }
//
//    int[][] multiDimenziosTomb = {{1, 2, 3}, {4, 5, 6}, {7}, {8, 9, 10, 11}};
//    //123  -> első tömb (első sor)
//    //456  -> második tömb (második sor)
//    //[1][2] -> a második tömb harmadik eleme 
//    System.out.println("A 1-es sor indexen és az egyes oszlop indexen lévő érték " + multiDimenziosTomb[2][0]);
  }

}
