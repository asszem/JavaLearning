package Variables;

/**
 *
 * @author Andras Olah (olahandras78@gmail.com)
 */
public class VariableSizes {
//https://www.tutorialspoint.com/java/java_basic_datatypes.htm
//https://docs.oracle.com/javase/tutorial/java/nutsandbolts/datatypes.html

	static String variableTypes[][] = {
		{"byte", "1", "8", "-128", "-2^7", "127", "2^7", "0"},
		{"short", "2", "16", "-32768", "-2^15", "32767", "2^15-1", "0"},
		{"int", "4", Integer.toString(4 * 8), "-2,147,483,648", "-2^31", "-2,147,483,647", "2^31", "0"},
		{"long", "8", Integer.toString(8 * 8), "-9,223,372,036,854,775,808()","-2^63", "9,223,372,036,854,775,807","-2^63", "0"},
		{"float", "4", Integer.toString(4 * 8), "-128", "127", "0"},
		{"double", "8", Integer.toString(8 * 8), "-128", "127", "0"},
		{"char", "2", Integer.toString(2 * 8), "-128", "127", "0"},
		{"boolean", "n/a","n/a", "n/a", "n/a", "false"},};

//		System.out.println("Type\tByte\tBit\tMin\tMax\tDef");
	static void printSizes() {
		System.out.println("Type\tByte\tBit");
		for (String[] s1 : variableTypes) {
			for (int i = 0; i < 3; i++) {
				System.out.print(s1[i] + "\t");
			}
			System.out.println("");
		}
	}

	public static void main(String[] args) {
		printSizes();
	}
}
