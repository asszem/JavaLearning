package HortonExercises.Ch10.Ex4;

/**
 *
 * @author Andras Olah (olahandras78@gmail.com)
 */
public class Permutations {

	public static String[] shiftStrings(String[] words) {
		String[] results = new String[words.length];
		StringBuilder strBuilt = new StringBuilder();
		int currentWord = 0;
		for (int i = 0; i < words.length; i++) {
			currentWord = i;
			for (int j = 0; j < words.length; j++) {
				currentWord = i + j;
				if (currentWord >= words.length) {
					currentWord -= words.length;
				}
				strBuilt.append(words[currentWord]);
			}
			results[i] = strBuilt.toString();
			strBuilt.delete(0, strBuilt.length());
		}
		System.out.println("Input: ");
		for (String word : words) {
			System.out.printf("[%s]", word);
		}
		System.out.println("\nOutput");
		for (String word : results) {
			System.out.printf("[%s]%n", word);
		}
		return results;
	}

	public static String[] getPermutations(String[] words) {
		int maxPermutations = 1;
		for (int i = words.length; i > 1; i--) {
			maxPermutations *= i;
		}
//		System.out.println("max permutations:" + maxPermutations);
		String[] permutations = new String[maxPermutations];
		System.out.println("Input");
		for (String word : words) {
			System.out.printf("[%s] - ", word);
		}
		System.out.println("\nShifting words");
		int currentWordIndex = 0;
		StringBuilder strBuilt = new StringBuilder(maxPermutations);
		System.out.println("Max word index: " + words.length);
		for (int i = 0; i < words.length; i++) {
//			System.out.println("Iteration: " + i);
			for (int j = 0; j < words.length; j++) {
				currentWordIndex = i + j;
				if (currentWordIndex >= words.length) {
					currentWordIndex -= words.length;
				}
//				System.out.println("i+j: " + (i+j));
//				System.out.println("Current word index: " + currentWordIndex);
//				System.out.println("");
				strBuilt.append(words[currentWordIndex]);
			}
			permutations[i] = strBuilt.toString();
			strBuilt.delete(0, strBuilt.length()); //Erase the stringbuilder for the next iteration
		}
		for (String word : permutations) {
			System.out.printf("[%s]%n", word);
		}
		/*
		012
		123 3->0
		234 -> 201

		Input: ABCD
		Output: ABCD, BCDA, CDAB, DABC

		Input: BCD
		Output: BCD, CDB, DBC

		Input: CD
		Output: CD, DC
		
		




		Keep order, shift one to the left
		  0 1 2 
		0 A B C
		1 B C A
		2 C A B

		Keep order, shift one to the left for the right part only
		A   BC
		A	CB

		B 	CA
		B	AC

		C	AB
		C	BA
		
		 */
		return permutations;
	}

	public static String[][] createPool(String[] words) {
		String[][] pool = new String[words.length][words.length];
		for (int i = 0; i < words.length; i++) {
			for (int j = 0; j < words.length; j++) {
				pool[i][j] = words[j];
			}
		}
		return pool;
	}

	public static void printPool(String[][] pool) {
		System.out.printf("Pool size:[%1$02d][%1$02d]%n", pool.length);
		int totalCounter = 0;
		for (int i = 0; i < pool.length; i++) {
			int counter = 0;
			for (String word : pool[i]) {
				System.out.printf("%02d. Pool [%d][%d] [%s]%n", totalCounter++, i, counter++, word);
				if (totalCounter % pool.length == 0) {
					System.out.println("");
				}
			}
		}
	}

	public static int permutationCount(String[] words) {
		int maxPermutations = 1;
		for (int i = words.length; i > 1; i--) {
			maxPermutations *= i;
		}
		return maxPermutations;
	}

	public static void main(String[] args) {
		String[] test = {"A", "B", "C", "D"};
		getPermutations(test);
//		shiftStrings(test);
		String[][] testPool;
		testPool = createPool(test);
		printPool(testPool);

		String[] results = new String[permutationCount(test)];
		//go as many times as the total number of permutations
		//in each loop a new string is generated in the results[] array
		String[] inputPool = test;
		for (int permutationCounter = 0; permutationCounter < permutationCount(test); permutationCounter++) {
			//position = the array index of the input string pool
			for (int position = 0; position < inputPool.length; position++) {

				//positionVariations = all other variables for a specific position
				for (int positionVariations = 0; positionVariations < inputPool.length; positionVariations++) {
					if (positionVariations != position) {
						results[permutationCounter++] = test[positionVariations];
					}
				}
			}
		}
		int counter = 0;
		for (String result : results) {
			System.out.printf("%d [%s]%n", counter++, result);
		}
		/*
		String[][] pool = new String[test.length][test.length];
			System.out.println(pool.length);
		for (int i=0;i<pool.length;i++){
			for (int j=0;j<pool.length;j++){
			pool[i][j]=test[j];
			}
		}
		int totalCounter=0;
		for (int i=0;i<pool.length;i++){
			int counter=0;
			for (String word:pool[i]){
				System.out.printf("%02d. Pool [%d][%d] [%s]%n",totalCounter++,i, counter++,word);
				if (totalCounter%pool.length==0) System.out.println("");	
			}
		}
		 */
	}//main
}//class
