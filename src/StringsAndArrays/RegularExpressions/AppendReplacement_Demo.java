package StringsAndArrays.RegularExpressions;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AppendReplacement_Demo {

	public static void main(String[] args) {

		String joke = "My dog hasn't got any nose.\nHow does your dog smell then?\nMy dog smells horrible.\n";
		String regEx = "dog";

		Pattern doggone = Pattern.compile(regEx);
		Matcher m = doggone.matcher(joke);

		StringBuffer newJoke = new StringBuffer();
		System.out.println("Replacement progres...");
		int counter=0;
		while (m.find()) {
			m.appendReplacement(newJoke, "goat");
			System.out.println("Appending step #"+(++counter)+"\n["+newJoke+"]\n");
		}
		m.appendTail(newJoke);
		System.out.println("\nReplacement result:");
		System.out.println(newJoke);
		
		
		
		System.out.println(Regex_Collection.replaceEveryMatchingPattern(m, "bulldog"));
	}
}
