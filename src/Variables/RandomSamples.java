/*
Math.random() 	- Selects a random number between 0.0 and 1.0, but never reaches 1.0
				- To select a range, multiply by the number, and you get a range from 0 to the number-1, because the number will never reached

java.util.Random class
	1. objektumot csinálni Random osztályban
	2. metódust meghívni objektumra.

	Random veletlen = new Random();
	veletlen.nextInt(6) - 0-5 között ad véletlen számot. Ha tömbből kell véletlen elemet választani, ez pont ideális rá, mert mindig a tömb indexen belül lesz.

 */
package Variables;

import static java.lang.Math.random;
import java.util.Random;	//Másik véletlen számot generáló osztály

public class RandomSamples {

	static Random veletlenszam = new Random();	//Egy Random típusú objektum

	private static int randomRange = 100; //A véletlen számot ennyivel fogja megszorozni.

	public static void setRandomRange(int newValue) {
		randomRange = newValue;
	}

	public static int getRandomRange() {
		return randomRange;
	}

	//Generate random double number within the randomRange
	public static double rnd() {
		double random = random() * randomRange;
		return random;
	}

	//Generate random int number within the parameter range
	public static int rnd(double min, double max) {
		boolean withinrange = (max < randomRange);
		if (withinrange) {
			int randomNumber;

			/*The Math.random() method generates a value of type double from 0.0 up to, but excluding, 1.0. This value
			is multiplied by 45.0 in the expression for the temperature, which results in values between 0.0 and 45.0.
			Subtracting 10.0 from this value gives you the range you require, 10.0 to 35.0/
			Mivel a minimumot úgyis mindig hozzáadja, hogy biztos nagyobb legyen az eredmény, így ennyivel kevesebb kell, hogy a maximum legyen
			Vegyünk egy számot 5 és 15 között, akkor elég, ha véletlen generál egy számot 1-10 között és 5-öt hozzáad. */
			randomNumber = (int) (min + ((max - min) * Math.random()));

			System.out.println("A " + min + " és " + max + " közötti véletlen szám:" + randomNumber);
			return randomNumber;
		} else {
			System.out.println("A megadott paraméter "+(max)+" nagyobb, mint a randomrange, ami " + randomRange);
			return 0;
		}
	}

	public static int rndInt() {
		int random = (int) (random() * randomRange);
		return random;
	}

	public static void main(String[] args) {
		System.out.println("Random class obj \"veletlenszam\" nextInt(5) metódus eredménye: " + veletlenszam.nextInt(5)); //0-4 közötti random számot ad
		String[] nevek = {"András", "Anikó", "Barni", "Benő", "Perec"};
		System.out.println("Véletlen név a tömbből: " + nevek[veletlenszam.nextInt(nevek.length)]);

		setRandomRange(1000);
		System.out.println("Random range:\t" + getRandomRange());
		System.out.println("rnd(): \t\t" + rnd());
		System.out.println("rndInt(): \t" + rndInt());
		System.out.println("rndInt(5,10):\t" + rnd(1, 3));
		rnd(6,1000);
	}
}
