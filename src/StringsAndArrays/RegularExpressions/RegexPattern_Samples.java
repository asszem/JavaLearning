package StringsAndArrays.RegularExpressions;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexPattern_Samples {

	public static void main(String[] args) {
		Pattern pattern;
		Matcher matcher;
		String source;
		String regex;
		// Match all lowercase consonants
		regex = "[a-z&&[^aeiou]]"; // works only in Java, does not work on regex101.com
		source = "Only the Lowercase Consonants Should Be Matched.";
		Regex_Collection.displayHighlightedResults(regex, source);
		regex = "(?![aeiou])[a-z]"; // negative lookahead, any match that is NOT preceded by vowel
		Regex_Collection.displayHighlightedResults(regex, source);
		regex = "(?=[aeiou])[a-z]"; // positive lookahead, any match that is preceded by vowel
		Regex_Collection.displayHighlightedResults(regex, source);

		// Match numbers with optional leading +- sign and numbers
		regex = "[+|-]?(\\d+(\\.\\d*)?)|[+|-](\\.\\d+)\\b";
		source = "256 is the square of 16 and -2.5 squared is 6.25 and -.243 is less than 0.1234. 0.1.2.3 0,1,2,3";
//		Regex_Collection.displayHighlightedResults(regex, source);
//		Regex_Collection.displayMatchedGroups(regex, source);

		// Match two arguments in a method parameter
		String regEx = "(Math.pow)" // Math.pow
				+ "\\s*\\(\\s*" // Opening (
				+ "(([a-zA-Z_]\\w*)|([+|-]?(\\d+(\\.\\d*)?)|(\\.\\d+)))" // First argument
				+ "\\s*,\\s*" // Comma
				+ "(([a-zA-Z_]\\w*)|([+|-]?(\\d+(\\.\\d*)?)|(\\.\\d+)))" // Second argument
				+ "\\s*\\)"; // Closing )

		String oldCode = "double result = Math.pow( 3.0, 16.0);\n" + "double resultSquared = Math.pow(2 ,result );\n"
				+ "double hypotenuse = Math.sqrt(Math.pow(2.0, 30.0)+Math.pow(2 , 40.0));\n";
	}
}
