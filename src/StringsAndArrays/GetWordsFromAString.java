package StringsAndArrays;

/**
 *
 * @author Andras Olah (olahandras78@gmail.com)
 */
public class GetWordsFromAString {

	public static void main(String[] args) {
		String s = "Ez egy teszt! ";
		String[] wordsSplitted= s.split("\\s");
		System.out.println("Split with regex");
		for (String str:wordsSplitted){
			System.out.printf("[%s]",str);
		}
		System.out.println("");
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
		System.out.println("Separator indices at char:");
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
		System.out.println("Words separated in array:");
		int counter = 0;
		for (String word : words) {
			System.out.printf("Index:%d word: [%s]%n", counter++, word);
		}
	}
}
