/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package StringsAndArrays;

/**
 *
 * @author olaha
 */
public class StringCompareTo_Demo {

	public static void main(String[] args) {
		//<editor-fold desc="This provides the result for the comparison call in the add() method in BinaryTree class">
		/*
	this.person 		compared to person		returns   
	Andras			=	Andras					0
	Andras			<	Bob						-1 
	Bob				>	Andras					+1

compareTo() method description: 
	returns
	<0 if this string is lexicographically less than the string argument; 
	>0 if this string is lexicographically greater than the string argument

		 */
		//</editor-fold>
		System.out.println("CompareTo test");
		String[] names = {"Andras", "Bob"};
		System.out.printf("%3$02d = %s.compareTo(%s)%n", names[0], names[0], names[0].compareTo(names[0]));
		System.out.printf("%3$02d = %s.compareTo(%s)%n", names[0], names[1], names[0].compareTo(names[1]));
		System.out.printf("%3$02d = %s.compareTo(%s)%n", names[1], names[0], names[1].compareTo(names[0]));
		System.out.println("");

		String str1 = "Hello";
		String str2 = "Hello"; //here the second reference variable were pointed to the first one since they are identical
		System.out.print("str 1 \"" + str1 + "\" == str2 \"" + str2 + "\":\t");
		if (str1 == str2) {
			System.out.println("They match!");
		} else {
			System.out.println("They don't match!");
		}

		String str3 = "hello"; //don't match because case mismatch, the new object was created in lower case
		System.out.print("str 1 \"" + str1 + "\" == str3 \"" + str3 + "\":\t");
		if (str1 == str3) {
			System.out.println("They match!");
		} else {
			System.out.println("They don't match!");
		}

		String part1 = "Hello ";
		String part2 = "World";
		String str4 = part1 + part2;    //str 4 and str 5 are different objects. 
		String str5 = "Hello World";
		System.out.print("str 4 \"" + str4 + "\" == str5 \"" + str5 + "\":\t\t\t");
		if (str4 == str5) {
			System.out.println("They match!");
		} else {
			System.out.println("They don't match!");
		}

		System.out.print("str 4 \"" + str4 + "\" equals() str5 \"" + str5 + "\":\t\t");
		if (str4.equals(str5)) {
			System.out.println("They match!");
		} else {
			System.out.println("They don't match!");
		}

		str5 = "HELLO WORLD";
		System.out.print("str 4 \"" + str4 + "\" equals() str5 \"" + str5 + "\":\t\t");
		if (str4.equals(str5)) {
			System.out.println("They match!");
		} else {
			System.out.println("They don't match!");
		}
		System.out.print("str 4 \"" + str4 + "\" equalsIgnoreCase() str5 \"" + str5 + "\":\t");
		if (str4.equalsIgnoreCase(str5)) {
			System.out.println("They match!");
		} else {
			System.out.println("They don't match!");
		}

	}

}
