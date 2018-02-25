/*
 * Source: https://www.programcreek.com/2014/01/5-different-syntax-of-lambda-expression/
 * */
package Java8Stuff.LambdaExpressions;

import java.util.Arrays;

public class SortStringArray {
	public static void main(String[] args) {

		String[] strings = { "program", "creek", "is", "a", "java", "site" };

		// Sort the strings based on their length
		Arrays.sort(strings, (String m, String n) -> Integer.compare(m.length(), n.length()));
		System.out.println("Size Ascending order: " + Arrays.toString(strings));

		// Sort reversed by overriding the comparator
		Arrays.sort(strings, (String m, String n) ->
		{
			if (m.length() > n.length() ) {
				return -1;
			} else {
				return 0;
			}
		} 
		);// End of Arrays.sort

		System.out.println("Size Descending order: " + Arrays.toString(strings));
	}
}
