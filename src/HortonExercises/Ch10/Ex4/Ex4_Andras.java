/*
Write a program that, for a given String object defined in the code, 
writes strings to a file in the local
character encoding (as bytes) corresponding to all possible permutations of the words in the string. 

For example, for the string "the fat cat", you would write the strings 
the fat cat, the cat fat, cat the fat, cat fat the, fat the cat, and fat cat the 
to the file, although not necessarily in that sequence.
(Don’t use very long strings; with n words in the string, the number of permutations is n!, so a string with 10 words has 3,628,800 permutations!)
 */
package HortonExercises.Ch10.Ex4;

/**
 *
 * @author Andras Olah (olahandras78@gmail.com)
 */
public class Ex4_Andras {

	public static void main(String[] args) {
		String s = "A B C D";
//<editor-fold desc="Get the words">
		String[] words;
		char separator = ' ';
		int numSeparators = 0;
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == separator) {
				numSeparators++;
			}
		}
		int[] separatorIndex = new int[numSeparators];
		int separatorArrayIndex = 0;
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == separator) {
				separatorIndex[separatorArrayIndex++] = i;
			}
		}
		System.out.printf("Input string%n[%s]%n", s);
		System.out.println("numSeparators: " + numSeparators);
		System.out.print("Separator indices at char:");
		for (int i : separatorIndex) {
			System.out.printf("[%d]", i);
		}
		words = new String[numSeparators + 1];
		int start, end;
		start = 0;
		end = separatorIndex[0];
		for (int i = 0; i < separatorIndex.length + 1; i++) {
			words[i] = s.substring(start, end);
			start = end + 1;
			if (i + 1 < separatorIndex.length) {
				end = separatorIndex[i + 1];
			} else {
				end = s.length();
			}
		}
		System.out.println("\nWords separated in array:");
		int counter = 0;
		for (String word : words) {
			System.out.printf("Index:%d word: [%s]%n", counter++, word);
		}
//</editor-fold>
//<editor-fold desc="Get all available permutations of words[] array">
		System.out.println("Number of words: " + words.length);
		int totalNumPermutations = 1;
//manual calculation of factorial
		for (int i = words.length; i > 0; i--) {
			totalNumPermutations *= i;
		}
		System.out.println("Total number of permutations:" + totalNumPermutations);
		String[] permutations = new String[totalNumPermutations];
		int actualPermutationIndex = 0; //to use when a new permutation construction has completed
		StringBuffer actualPermutationString = new StringBuffer();
		for (int i = 0; i < words.length; i++) { //to go through each word
			actualPermutationString.delete(0, actualPermutationString.length());
			actualPermutationString.append(words[i]); //The first element is the actual word
			//A loop to go through all remaining elements
			for (int j = 0; j < words.length - 1; j++) {
			if (words[j]!=words[i]){  //only for characters that are not the current char

			}
			}
			actualPermutationString.append(words[i]); //The first element is the actual word
			actualPermutationString.append(words[i]); //The first element is the actual word
			permutations[i] = actualPermutationString.toString();
		}

		/*
		words=[A] [B] [C]
		getVariations(string[] words)


		Array Index: 0123
		indexMin=0
		indexMax=3
		Index start: 0

		if nextIndex=curIndex nextIndex=curIndex+1
		if nextIndex>indexMax nextIndex=0
	
		string building
		0 1
		0 1 2
		0 1 2 3
		0 1 3
		0 2
		0 1 
		0 3
		 */
		counter = 1;
		for (String permutation : permutations) {
			System.out.printf("%d [%s]%n", counter++, permutation);
		}
//</editor-fold>
	}
}
