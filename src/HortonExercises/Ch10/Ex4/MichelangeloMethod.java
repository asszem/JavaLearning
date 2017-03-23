package HortonExercises.Ch10.Ex4;

/**
 *
 * @author Andras Olah (olahandras78@gmail.com)
 */
public class MichelangeloMethod {

	static void buildAll(String[] input) {
		System.out.println("Input Array Length:" + input.length);
		int totalSize = 1;
		for (int i = input.length; i > 0; i--) {
			totalSize *= i;
		}
		System.out.println("Total size: " + totalSize);
		/*
		Create an Array, every character are in every Index.
		
		 */
	}

	static void recursiveTest(String[] s) {
		System.out.println("s.length:" + s.length);
		for (String str:s){
			System.out.printf("[%s]",str);
		}
		System.out.println("");
		int currentLength=s.length;
		if (currentLength== 1) {
			System.out.println(s[0]);
		} else {
			String[] newString = new String[s.length-1];
			//Rebuild the new String from the second index
			for (int i=0;i<newString.length;i++){
				newString[i]=s[i+1];
			}
			currentLength--;
			recursiveTest(newString);
		}
	}

	public static void main(String[] args) {
		String s = "A B C D";
		String[] strings = {"A", "B", "C", "D"};
//		buildAll(strings);
		recursiveTest(strings);
		//yim tyimt	
		//yim test	
	}
}
