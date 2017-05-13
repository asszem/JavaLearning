package StringsAndArrays;

/**
 *
 * @author Andras Olah (olahandras78@gmail.com)
 */
public class GetWordsFromAString_Collection {

	// Split using a Regex pattern as separator token
	public static String[] splitWithRegex(String input, String pattern) {
		return input.split(pattern);
	}

	// Split using matching a separator token string
	public static String[] splitWithSeparatorChar(String input, char separator) {
		String[] words;
		int numSeparators = 0;
		// Get the number of separators
		for (int i = 0; i < input.length(); i++) {
			if (input.charAt(i) == separator) {
				numSeparators++;
			}
		}
		// Store the index locations of separators
		int[] separatorIndex = new int[numSeparators];
		int separatorArrayIndex = 0;
		for (int i = 0; i < input.length(); i++) {
			if (input.charAt(i) == separator) {
				separatorIndex[separatorArrayIndex++] = i;
			}
		}
		words = new String[numSeparators + 1]; // to include the last word without separator
		int start, end;
		start = 0;
		end = separatorIndex[0]; // the index of the end of the first word
		for (int i = 0; i < separatorIndex.length + 1; i++) { // +1 to include last word
			words[i] = input.substring(start, end);
			start = end + 1;
			if (i + 1 < separatorIndex.length) {
				end = separatorIndex[i + 1];
			} else {
				end = input.length(); // the last word
			}
		}
		return words;
	}

	// Using the Character.isLetter(str.CharAt(index)) method as separator
	public static String[] splitWithIsLetterOfCharAt(String str) {
		int wordCount = 0;
		boolean isNewWord = true;

		// Loop through the input to get the number of words
		// It will be needed to determine the size of the result array
		// Note: this method was written before learning about ArrayList
		for (int currentCharIndex = 0; currentCharIndex < str.length(); currentCharIndex++) {
			if (!Character.isLetter(str.charAt(currentCharIndex))) { // If not a Letter
				isNewWord = true; // Set it to start a new word
				continue; // Continue the cycle with the next character
			} else {
				// The character is a letter
				if (isNewWord) { // Is this the start of a new word?
					wordCount++;
					isNewWord = false;// Next character will not be a new word
				}
			}
		}

		// Store words in an array
		String[] words = new String[wordCount];
		isNewWord = true;
		int currentWordIndex = -1; // Will be increased when finding the first word
		StringBuffer temp = new StringBuffer();
		for (int i = 0; i < str.length(); i++) {
			if (!Character.isLetter(str.charAt(i))) {
				isNewWord = true;
				continue;
			} else {
				if (isNewWord) {
					isNewWord = false;
					currentWordIndex++;
					// Because it is a new word, create a new stringbuffer object
					temp = new StringBuffer(Character.toString(str.charAt(i)));
				} else {
					// Because it is not a new word, add the current char to the buffer
					temp.append(Character.toString(str.charAt(i)));
				}
			}
			//The continue jumps here??
			//Add the buffer to the words array
			words[currentWordIndex] = temp.toString();
		}
		return words;
	}

	public static void displayResults(String[] input) {
		for (String string : input) {
			System.out.print("[" + string + "] ");
		}
		System.out.println();
	}

	public static void main(String[] args) {
		String test = "To be? ?? or  not to be, that is the question. 23 !";
		String[] result;
		String pattern;
		System.out.println(test);
		pattern = "[^\\w]+";
		result = splitWithRegex(test, pattern);
		System.out.printf("\nSplit with regex pattern [%s] - words:%d%n", pattern, result.length);
		displayResults(result);
		result = splitWithSeparatorChar(test, ' ');
		System.out.printf("\nSplit with separator ' ' - words:%d%n", result.length);
		displayResults(result);
		result = splitWithIsLetterOfCharAt(test);
		System.out.printf("\nSplit with isLetter - words:%d%n", result.length);
		displayResults(result);
	}
}
