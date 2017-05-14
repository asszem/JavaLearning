package StringsAndArrays.RegularExpressions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Regex_Collection {

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
		matcher.reset(); // In case other methods already run
		while (matcher.find()) {
			Arrays.fill(marker, matcher.start(), matcher.end(), '^');
		}

		// Show the object string with matches marked under it
		System.out.println("Pattern: " + inputPattern);
		System.out.println("Flags: " + matcher.pattern().flags());
		System.out.println("Source: \n" + source);
		System.out.println(marker);
		matcher.reset();
	}

	public static void displayMatchedGroups(String inputPattern, String source) {

		// // The matches in the output will be marked (fixed-width font required)
		// char[] marker = new char[source.length()];
		// Arrays.fill(marker, ' ');

		// Obtain the required matcher
		Pattern pattern = Pattern.compile(inputPattern);
		Matcher m = pattern.matcher(source);

		// Show the object string with matches marked under it
		System.out.println("\nDisplaying matched groups");
		System.out.println("\nSource\n" + source);
		System.out.println("\nRegEx pattern\n" + inputPattern);
		System.out.println();
		// Find every groups and display
		int matchCounter = 1;
		while (m.find()) {
			System.out.println("Match #" + matchCounter++);
			for (int i = 0; i <= m.groupCount(); i++) {
				System.out.println("\tGroup #" + i + " " + m.group(i));
			}
			System.out.println("-----------------");
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

	/**
	 * Returns a Map. Integer = key to the match. ArrayList = captured group content
	 * */
	public static Map<Integer, ArrayList<String>> getMatchedGroupsMap(Matcher matcher) {
		matcher.reset();
		Map<Integer, ArrayList<String>> results = new HashMap<>(); // Integer might be ok for hash?
		int matchCount = 0;
		while (matcher.find()) {
			matchCount++;
			ArrayList<String> groups = new ArrayList<>();
			for (int i = 0; i <= matcher.groupCount(); i++) {
				groups.add(matcher.group(i));
			}
			results.put(matchCount, groups);
		}
		matcher.reset();
		return results;
		// For reference: to print out content of a hashmap:
		// for (int currentKey:matchedGroups.keySet()){
		// System.out.println(matchedGroups.get(currentKey));
		// }
	}

	public static void displayMatchedGroupsMap(Map<Integer, ArrayList<String>> inputMap) {
		for (int currentKey : inputMap.keySet()) {
			System.out.println(inputMap.get(currentKey));
		}
	}

	public static String replaceEveryMatchingPattern(Matcher matcher, String newString) {
		StringBuffer resultString = new StringBuffer();
		matcher.reset();
		while (matcher.find()) {
			matcher.appendReplacement(resultString, newString);
		}
		matcher.appendTail(resultString);
		matcher.reset();
		return resultString.toString();
	}
}
