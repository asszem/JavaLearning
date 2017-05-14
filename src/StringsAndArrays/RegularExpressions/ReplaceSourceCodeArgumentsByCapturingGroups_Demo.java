package StringsAndArrays.RegularExpressions;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReplaceSourceCodeArgumentsByCapturingGroups_Demo {

	public static void main(String[] args) {
		String regEx = "(Math.pow)" 										// Math.pow
				+ "\\s*\\(\\s*" 											// Opening (
				+ "(([a-zA-Z_]\\w*)|([+|-]?(\\d+(\\.\\d*)?)|(\\.\\d+)))" 	// First argument
				+ "\\s*,\\s*" 												// Comma
				+ "(([a-zA-Z_]\\w*)|([+|-]?(\\d+(\\.\\d*)?)|(\\.\\d+)))" 	// Second argument
				+ "\\s*\\)"; // Closing )

		String oldCode = 
					"double result = Math.pow( 3.0, 16.0);\n" +
					"double resultSquared = Math.pow(2 ,result );\n" +
					"double hypotenuse = Math.sqrt(Math.pow(2.0, 30.0)+Math.pow(2 , 40.0));\n";

		Pattern pattern = Pattern.compile(regEx);
		Matcher m = pattern.matcher(oldCode);

		StringBuffer newCode = new StringBuffer();
		while (m.find()) {
			m.appendReplacement(newCode, "$1\\($8,$2\\)");
		}
		m.appendTail(newCode);

		System.out.println("Original Code:\n" + oldCode.toString());
		System.out.println("New Code:\n" + newCode.toString());
		
		Regex_Collection.displayMatchedGroups(regEx, oldCode);
	}
}
