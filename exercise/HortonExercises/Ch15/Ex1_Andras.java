package HortonExercises.Ch15;
/*
 * Requirement: 
 * Define a static method to fill an array of type char[] with 
 * a given value passed as an argument to the method.
 *
 * Note: by value it meant a number, not a char.
 * */

public class Ex1_Andras {

	public static void fillArray(char[] arrayToFill, int filler) {
		java.util.Arrays.fill(arrayToFill,(char) filler);
	}
	public static void main(String[] args) {
		char[] test = new char[12];
		fillArray(test, 97);
	}
}
