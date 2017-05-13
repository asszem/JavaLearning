package StringsAndArrays.H_Ch3_Strings;

public class StringTokenizing {

	public static void main(String[] args) {
		String text = "To be or not to be, that is the question."; // String to segment
		String delimiters = "[, .]"; // Delimiters are comma, space, and period
		int[] limits = { 0, -1, 1, 5 }; // Limit values to try

		// Analyze the string
		for (int limit : limits) {
			System.out.println("\nAnalysis with limit = " + limit);
			String[] tokens = text.split(delimiters, limit);
			System.out.println("Number of tokens: " + tokens.length);
			for (String token : tokens) {
				System.out.print("[" + token + "] ");
			}
		}

		//Source: Horton, ch16, p602
		delimiters = "[^\\w]+"; // Anything that is not a word character
		System.out.println("\nTokenizing with delimiter: \n" + delimiters);
		String[] tokens = text.split(delimiters);
		System.out.println("Number of tokens: " + tokens.length);
		for (String token : tokens) {
			System.out.print("[" + token + "] ");
		}
	}
}
