package StringsAndArrays.RegularExpressions;

import java.util.regex.*;

public class Regex_Samples {

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
		Regex_Skeleton.displayHighlightedResults(regEx, str);
		Regex_Skeleton.displayHighlightedResults(patternString, sourceString);
		

		//Displaying the marked matches with different matchers
		Regex_Skeleton.displayHighlightedResults(matcherObject, sourceString);
		Regex_Skeleton.displayHighlightedResults(matcherObjectWithFlags, sourceString);
		Regex_Skeleton.displayHighlightedResults(matcherObjectNotFound, sourceStringDifferent);
		
	}

}
