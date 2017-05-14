package StringsAndArrays.RegularExpressions;

import java.util.ArrayList;
import java.util.Map;
import java.util.regex.*;

public class RegexMatcher_Samples {

	public static boolean getPatternPosition(Matcher matcherObject) {
		if (matcherObject.find()) {
			int startIndex = matcherObject.start();
			int endIndex = matcherObject.end();
			return true;
		}
		return false;
	}

	public static void main(String[] args) {
		// Setup pattern and source strings
		String patternString = "\\d{2,}REGEX";
		String sourceString = "One Two 1 2 Ten Eleven 10REGEX 11regex regex REGEX";
		String sourceStringDifferent = "ÁRVÍZTŰRŐ TÜKÖRFÚRÓGÉP árvíztűrő tükörfúrógép ..vesszőcske 42";

		// Create Pattern objects - .compile(patternString)
		Pattern patternObject = Pattern.compile(patternString);
		Pattern patternObjectWithFlags = Pattern.compile(patternString, Pattern.MULTILINE + Pattern.CASE_INSENSITIVE);

		// Create Matcher objects - matcher(sourceString) and - reset(sourceString)
		Matcher matcherObjectWithFlags = patternObjectWithFlags.matcher(sourceString);
		matcherObjectWithFlags.reset(sourceStringDifferent);
		Matcher matcherObjectNotFound = patternObject.matcher(sourceStringDifferent); 	//Assume 0 match
		Matcher matcherObject = patternObject.matcher(sourceString);  					//Assume 1 match
		matcherObjectWithFlags.reset(sourceString); 									//2 matches

		// Find if there is a match in the Matcher object
		boolean isFound = matcherObject.find();		//true
		isFound = matcherObjectWithFlags.find();  	//true
		isFound = matcherObjectNotFound.find();		//false
		
		// Reset the matcher to set find() index to zero
		matcherObject.reset();
		matcherObjectWithFlags.reset();
		matcherObjectWithFlags.reset();
		
		//Check the position of match (only if find() returns true!
		getPatternPosition(matcherObject);
		getPatternPosition(matcherObjectWithFlags);
		getPatternPosition(matcherObjectNotFound);
		
		// Reset the matcher to set find() index to zero
		matcherObject.reset();
		matcherObjectWithFlags.reset();
		matcherObjectWithFlags.reset();

		//Check all occurrences
		int occurenceCount=0;
		while (getPatternPosition(matcherObject)){						//Expected: 1
			occurenceCount++;
		}
		occurenceCount=0;
		while (getPatternPosition(matcherObjectWithFlags)){				//Expected: 2
			occurenceCount++;
		}
		occurenceCount=0;
		while (getPatternPosition(matcherObjectNotFound)){				//Expected: 0
			occurenceCount++;
		}
		occurenceCount=0;

		// Displaying the marked matches (using Horton's method)
		String regEx = "had";
		String str = "Smith, where Jones had had 'had', had had 'had had'.";
		Regex_Collection.displayHighlightedResults(regEx, str);
		Regex_Collection.displayHighlightedResults(patternString, sourceString);
		

		//Displaying the marked matches with different matchers
		Regex_Collection.displayHighlightedResults(matcherObject, sourceString);
		Regex_Collection.displayHighlightedResults(matcherObjectWithFlags, sourceString);
		Regex_Collection.displayHighlightedResults(matcherObjectNotFound, sourceStringDifferent);
		
		//Match the entire pattern
		System.out.println("Matching entire patterns");
		Regex_Collection.displayHighlightedResults(matcherObject, sourceString);
		System.out.println("Entire pattern matches: " + matcherObject.matches());
		Matcher matchEntirePattern = Pattern.compile(sourceString).matcher(sourceString);
		Regex_Collection.displayHighlightedResults(matchEntirePattern, sourceString);
		System.out.println("Entire pattern matches: " + matchEntirePattern.matches());
		
		//Get matching groups - make sure pattern defines capturing groups
		String regex = "[+|-]?(\\d+(\\.\\d*)?)|[+|-](\\.\\d+)\\b";
		String source= "256 is the square of 16 and -2.5 squared is 6.25 and -.243 is less than 0.1234. 0.1.2.3 0,1,2,3";
		System.out.println(regex);
		System.out.println(source);
		System.out.println("Group 0: ");
		System.out.println("Group 1: ( \\d+(\\.\\d*)? )");
		System.out.println("Group 2: ( \\.\\d* )");
		System.out.println("Group 3: ( \\.\\d+ )");
		Matcher matcherWithGroups = Pattern.compile(regex).matcher(source);
		System.out.println("\n\nDisplay map\n\n");
		Regex_Collection.displayMatchedGroupsMap(Regex_Collection.getMatchedGroupsMap(matcherWithGroups));
		System.out.println("\n\nDisplay Groups\n\n");
		Regex_Collection.displayMatchedGroups(regex, source);

	}

}
