package HortonExercises.Ch09.Ex3;

/**
 *
 * @author Andras Olah (olahandras78@gmail.com)
 */
public class RemoveExtension {

	public static boolean debug = false;

	//Method to return the filename and the extension in a separate array
	//Extension is returned with .ext format, including the dot.
	public static String[] removeExtension(String input) {
		//Verify if input has an extension
		String removedExtension[] = new String[2];
		if (debug) System.out.printf("%nMethod started%nInput:%s%n",input);
		if (input.contains(".")) {
			if (debug) {
				System.out.println(". present in input");
			}
			int indexOfExtension = input.lastIndexOf(".");
			//Return the file name to the array index 0
			removedExtension[0]=input.substring(0, indexOfExtension);
			//Return the extension to the array index 1
			removedExtension[1]=input.substring(indexOfExtension,input.length());
			if (debug) {
				System.out.println("Index of Extension:" + indexOfExtension);
				System.out.println("Return filename: " + removedExtension[0]);
				System.out.println("Return extension: " + removedExtension[1]);
			}
			return removedExtension;
		} else {
			if (debug) {
				System.out.println("input does not have an extension");
			}
			removedExtension[0]=input;
			removedExtension[1]=null;
			return removedExtension;
		}
	}
	public static String insertBeforeExtension(String input, String toBeInserted){
		StringBuilder newFileName = new StringBuilder(input);
		newFileName.insert(input.lastIndexOf("."), toBeInserted);
		return newFileName.toString();
	}

	public static void main(String[] args) {
//		removeExtension("alma.txt");
//		removeExtension("alma");
//		removeExtension("alma.teszt.txt");
		String teszt = insertBeforeExtension("alma.txt", "helló");
		System.out.println(teszt);
	}
}
