package practiceAndTry;

import java.util.ArrayList;
import java.util.Map;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import StringsAndArrays.RegularExpressions.Regex_Collection;

public class Regex_Practice {

	public static void main(String[] args) {
		String source = "Joe:3 Bill:4 Ted:4 Tegan:5 Judith:4";
		String regEx = "(\\w+):(\\d)"; // Group followed by a number
		Pattern pattern = Pattern.compile(regEx);
		Matcher matcher = pattern.matcher(source);
		Regex_Collection.displayHighlightedResults(regEx, source);
		Regex_Collection.displayMatchedGroups(regEx, source);
		Map<Integer, ArrayList<String>> matchedGroups = Regex_Collection.getMatchedGroupsMap(matcher);
		Regex_Collection.displayMatchedGroupsMap(matchedGroups);
		
		System.out.println("\nAppend in progress");
		StringBuffer replacementBuffer = new StringBuffer();
		while (matcher.find()) {
			matcher.appendReplacement(replacementBuffer, "$2-$1");
			System.out.println(replacementBuffer);
		}
		matcher.appendTail(replacementBuffer);
		System.out.println("\nFinal result:");
		System.out.println(replacementBuffer);
	}
}
