package HortonExercises.Ch10.Ex4;

/**
 *
 * @author Andras Olah (olahandras78@gmail.com)
 */
public class MichelangeloMethod {

	static void buildAll(String[] input) {
		System.out.println("Input Array Length:" + input.length);
		int maxPermutations = 1;
		for (int i = input.length; i > 0; i--) {
			maxPermutations *= i;
		}
		double maxElements = Math.pow(input.length, input.length);
		System.out.println("Max permutations size: " + maxPermutations);
		System.out.printf("Max elements:%1$d^%1$d=%2$f%n", input.length, maxElements);
		String[] allVersions = new String[(int) maxElements];
		String[][] possibleOptions = new String[input.length][input.length];
		for (int i = 0; i < input.length; i++) {
			for (int j = 0; j < input.length; j++) {
				possibleOptions[i][j] = input[j];
				System.out.printf("[%d][%d]=%s%n", i, j, input[j]);
			}
		}
		int counter = 0;
		/*
		Create an Array, every character are in every Index.
		
		 */
	}

	static void recursiveTest(String[] s) {
		System.out.println("s.length:" + s.length);
		for (String str : s) {
			System.out.printf("[%s]", str);
		}
		System.out.println("");
		int currentLength = s.length;
		if (currentLength == 1) {
			System.out.println(s[0]);
		} else {
			String[] newString = new String[s.length - 1];
			//Rebuild the new String from the second index
			for (int i = 0; i < newString.length; i++) {
				newString[i] = s[i + 1];
			}
			currentLength--;
			recursiveTest(newString);
		}
	}

	public static void main(String[] args) {
		String s = "A B C D";
		String[] strings = {"A", "B", "C", "D"};
//		buildAll(strings);
		recursiveTest(strings);
		//yim tyimt	
		//yim test	
	}
}
