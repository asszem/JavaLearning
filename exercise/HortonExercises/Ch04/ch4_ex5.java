/*
Write a program that reverses the sequence of letters in each word of your chosen paragraph from Exercise
3. For instance, “To be or not to be.” becomes “oT eb ro ton ot eb.”


 */
package HortonExercises.Ch04;

public class ch4_ex5 {

	public static void main(String[] args) {
		String str = new String("To be or not to be.");
		str = "Into the face of the young man who sat on the terrace "
						+ "\nof the Hotel Magnifique at Cannes crept a look of furtive "
						+ "\nshame, the shifty, hangdog look which announces that "
						+ "\nan Englishman is about to talk French.";

		String[] words = str.split("[ ,-?!]");
		for (int i = 0; i < words.length; i++) {
			StringBuffer reversed = new StringBuffer(words[i]);
			words[i] = reversed.reverse().toString();
		}
		for (String s : words) {
			System.out.print(s + " ");
		}
	}
}
