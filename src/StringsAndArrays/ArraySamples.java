package StringsAndArrays;
//<editor-fold desc="Tömbök elméleti tudnivalók">
/*
Egydimenziós tömbök
	double[] tombNev = new double[10];

Többdimenziós tömb létrehozása, hosszának megadásával:
	double[][] tombNev = new double[10][10];
  
Többdimenziós tömb létrehozása úgy, hogy a második dimenziónak külön adjuk meg a hosszát minden esetben
	double[][] tombNev = new double[10][];
	tombNev[0] = new double[5]
	tombNev[1] = new double[10]
	...
	tombNev[9] = new double[10]

fill() metódus használata értékadáshoz
  import static java.util.Arrays.fill;
  Egydimenziós tömbnél:
    fill(tömbnév, érték);
  Többdimenziós tömbnél:
    fill(tömbnév[2], kezdőIndex, záróIndexUtáni, Érték); 

Objects as arrays
	As variables referencing object, if a variable is an array, it can reference multiple objects.
 */
//</editor-fold>

import static java.lang.Math.ceil;
import static java.lang.Math.random;
import java.util.Arrays;              //A teljes Arrays osztály importálása
import static java.util.Arrays.fill;  //az Arrays osztályból a fill() metódus statikusan importálva

public class ArraySamples {

	public static void main(String[] args) {

		ClassesAndObjects.Horcsog[] tombHorcsog = new ClassesAndObjects.Horcsog[3]; //Létrehoz egy 3 elemű tömbváltozót, ami a Hörcsög osztályra hivatkozik! 
		tombHorcsog[0] = new ClassesAndObjects.Horcsog("Tömbi1", "Tömbi1 értéke hivatkozva", "fekete", "tömbös", 200); //értéket ad az első tömbnek
		tombHorcsog[1] = new ClassesAndObjects.Horcsog("Tömbi2", tombHorcsog[0].getGender(), "lila", "tömbörű", 21); //értéket ad az első tömbnek
		ClassesAndObjects.Horcsog[][] ketDHorcsog = new ClassesAndObjects.Horcsog[3][2];
		ketDHorcsog[0][0] = new ClassesAndObjects.Horcsog();
		tombHorcsog[2] = tombHorcsog[1];
		tombHorcsog[2].setName("Tömb Hörcsög új neve");
		System.out.println(tombHorcsog[0].toString());
		System.out.println(tombHorcsog[1].toString());
		System.out.println(tombHorcsog[2].toString());
		System.out.println("A kétdimenziós hörcsög változó[0][0] indexe erre az ojjektumra hivatkozik:\n" + ketDHorcsog[0][0].toString());

//    printArray();
//    avgArray(array);
//    fillArray(array);
//    printArray(array);
//    double[][] test = new double[15][13];
//    printTwoDimArrayLength(test);
//    double[] randomLenghtArray = new double[(int) (Math.random() * 100)];
//    System.out.println("véletlen generált tömb mérete: "+randomLenghtArray.length);
		double[][] test = new double[3][];
		test[0] = new double[1]; //a test[0] sorhoz [5] oszlop tartozik, 0-4 index-el. 
		test[1] = new double[2];
		test[2] = new double[3];
		printTwoDimArrayLength(test);
		setTwoDimArrayValue(test, 110);
		printTwoDimArrayValues(test);

//  double[][] test = new double[5][5];
//    fill(test[2], 0, 2, 42);
//    printTwoDimArrayValues(test);
	}

	public static double[] array = new double[15];//array nevű, double tipusú tömb 15 értékkel létrehozva

//statikus inicializátor, azaz mindig lefut, mindig ad értéket az array tömbnek
	static {
		fill(array, 0, 15, 3.0); //paraméterek: tömb neve, kezdő index, utolsó utáni index, érték
//    System.out.println("Kezdeti inicializálás értékei:");
//    printArray();
	}

	static void printTwoDimArrayLength(double[][] inputArray) {
		for (int i = 0; i < inputArray.length; i++) {
			System.out.println("A tömb " + i + ". sorához tartozó oszlop hossza:" + inputArray[i].length);
		}
	}

	static void printTwoDimArrayValues(double[][] inputArray) {
		for (int i = 0; i < inputArray.length; i++) {
			for (int j = 0; j < inputArray[i].length; j++) {
				System.out.println("A tömb [" + i + "][" + j + "] elemének értéke: " + inputArray[i][j]);
			}
		}
	}

	static void setTwoDimArrayValue(double[][] inputArray, double arrayValue) {
		for (int i = 0; i < inputArray.length; ++i) {

			//Egyik metódus az értékek feltöltésére
			for (int j = 0; j < inputArray[i].length; ++j) {
				inputArray[i][j] = arrayValue;
			}

			//Másik metódus, tök ugyanazzal az eredménnyel, a fill() használatával
			fill(inputArray[i], arrayValue);
		}
	}

	static void fillArray() {
//    System.out.println("fillArray() called");
		for (int i = 0; i < array.length; ++i) {
			array[i] = ceil(100 * random());
		}
	}

	static void fillArray(double[] inputArray) {
//    System.out.println("fillArray(double[] inputArray) called");
		for (int i = 0; i < inputArray.length; ++i) {
			inputArray[i] = ceil(100 * random());
		}
	}

	static void printArray() {
//    System.out.print("array.length=" + array.length + " értékek: ");
		for (double i : array) {
			System.out.print(i + ", ");
		}
		System.out.println("");
	}

	static void printArray(double[] inputArray) {
//    System.out.print("array.length=" + inputArray.length + " értékek: ");
		for (double i : inputArray) {
			System.out.print(i + ", ");
		}
		System.out.println("");
	}

	static void printArray(int[] inputArray) {
//    System.out.print("array.length=" + inputArray.length + " értékek: ");
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
		double average = summary / inputArray.length;
		System.out.println("Tömb értékeinek összege: " + summary);
		System.out.println("Tömb értékeinek száma:\t " + inputArray.length);
		System.out.println("Tömb értékeinek átlaga:\t " + average);
	}

}
