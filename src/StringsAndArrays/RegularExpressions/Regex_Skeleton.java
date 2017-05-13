package StringsAndArrays.RegularExpressions;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Regex_Skeleton {

	// Matcher created inside method without any specific flags.
	public static void displayHighlightedResults(String inputPattern, String source) {

		// The matches in the output will be marked (fixed-width font required)
		char[] marker = new char[source.length()];
		Arrays.fill(marker, ' ');

		// Obtain the required matcher
		Pattern pattern = Pattern.compile(inputPattern);
		Matcher m = pattern.matcher(source);

		// Find every match and mark it
		while (m.find()) {
			Arrays.fill(marker, m.start(), m.end(), '^');
		}

		// Show the object string with matches marked under it
		System.out.println(inputPattern);
		System.out.println(source);
		System.out.println(marker);
	}

	// Regex pattern and source string is provided by matcher
	public static void displayHighlightedResults(Matcher matcher, String source) {
		String inputPattern = matcher.pattern().pattern();
		// The matches in the output will be marked (fixed-width font required)
		char[] marker = new char[source.length()];
		Arrays.fill(marker, ' ');

		// Find every match and mark it
		matcher.reset(); //In case other methods already run
		while (matcher.find()) {
			Arrays.fill(marker, matcher.start(), matcher.end(), '^');
		}

		// Show the object string with matches marked under it
		System.out.println("Pattern: " + inputPattern);
		System.out.println("Flags: " + matcher.pattern().flags());
		System.out.println("Source: \n"+source);
		System.out.println(marker);
	}

public static void displayMatchedGroups(String inputPattern, String source){
	
		// The matches in the output will be marked (fixed-width font required)
		char[] marker = new char[source.length()];
		Arrays.fill(marker, ' ');

		// Obtain the required matcher
		Pattern pattern = Pattern.compile(inputPattern);
		Matcher m = pattern.matcher(source);

		// Show the object string with matches marked under it
		System.out.println(inputPattern);
		System.out.println(source);

		// Find every groups and display
		int groupCounter=1;
		while (m.find()) {
			System.out.printf("Group #%02d [%s]%n",(groupCounter++),m.group());
		}

}
	
	
	public static Pattern createPattern(String inputPattern) {
		Pattern pattern = Pattern.compile(inputPattern);
		Pattern patternWithFlags = Pattern.compile(inputPattern, Pattern.CASE_INSENSITIVE);
		return pattern;
	}

	public static Matcher createMatcher(String source, Pattern pattern) {
		Matcher matcher = pattern.matcher(source);
		return matcher;
	}

	public static void updateExistingMatcher(Matcher matcher, String newSource) {
		matcher.reset(newSource);
	}

	public static boolean findPatternInMatcher(Matcher matcher) {
		boolean isFound = matcher.find();
		return isFound;
	}

	public static void resetFind(Matcher matcher) {
		matcher.reset();
	}

	public static void main(String[] args) {
	}
}
