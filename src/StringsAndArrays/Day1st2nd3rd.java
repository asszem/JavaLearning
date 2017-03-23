package StringsAndArrays;

/**
 *
 * @author Andras Olah (olahandras78@gmail.com)
 */
public class Day1st2nd3rd {

	static String dayEnding(int i) {
		String ending;
//		int rnd = (int) (1 + Math.random() * 331);
//		System.out.println("random int: " + rnd);
		int rnd = i;
		char rndS = Integer.toString(rnd).charAt(Integer.toString(rnd).length() - 1);
//		System.out.println("Char: " + rndS);
		switch (Character.toString(rndS)) {
			case "1":
				ending = "st";
				break;
			case "2":
				ending = "nd";
				break;
			case "3":
				ending = "rd";
				break;
			default:
				ending = "th";
				break;
		}
		return rnd + ending;
	}

	public static void main(String[] args) {
		for (int i = 0; i < 10; i++) {
			int rnd = (int) (Math.random() * 1000);
			System.out.printf("Number: %d -> %s%n", rnd, dayEnding(rnd));
		}
	}
}
