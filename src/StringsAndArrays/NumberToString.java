package StringsAndArrays;

/**
 *
 * @author Andras Olah (olahandras78@gmail.com)
 */
public class NumberToString {

	static String[] numbers = {"one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten"};
	static String[] tens = {"teen", "twenty", "thirty", "fourty", "fifty", "sixty", "seventy", "eighty", "ninety"};

	static String numberToString(int i) {
		String s = Integer.toString(i);
//		System.out.printf("%d hossza: %d", i, s.length());
		String number, tens, hundreds, thousands;
		if (i < 14) {
			return getTens(i);
		} else if (i < 100) {

		}

		return "Error";
	}

	/*
	1234
	 */
	static String getTens(int i) {
		if (i == 0) {
			return "zero";
		}
		if (i == 11) {
			return "eleven";
		}
		if (i == 12) {
			return "twelve";
		}
		if (i == 13) {
			return "thirteen";
		}
		if (i <= 10) {
			return numbers[i - 1];
		}
		return "getTens error";
	}

	static String getHundreds(int i) {
		return "getHundreds error";
	}

	static String getThousands(int i) {
		return "getThousands error";
	}

	public static void main(String[] args) {
		for (int i = 0; i < 10; i++) {
			int range = 1000;
			int rnd = (int) (Math.random() * range);
			String num = Integer.toString(rnd);
			int ones, tens, hundreds, thousands;
			ones = tens = hundreds = thousands = 0;
			System.out.println(num.length());
			switch (num.length()) {
				case 5:
				case 4:
					thousands = Integer.valueOf(num.substring(num.length()-4, num.length()-3));
				case 3:
					hundreds = Integer.valueOf(num.substring(num.length()-3, num.length()-2));
				case 2:
					tens =Integer.valueOf(num.substring(num.length()-2, num.length()-1));
				case 1:
					ones = Integer.valueOf(num.substring(num.length()-1, num.length()));
					break;
				default:
					num = "hiba";
					break;
			}
//			System.out.printf("Number: %d -> %s%n", rnd, numberToString(rnd));
			System.out.printf("Number:%s -- 1000:%5$d 100:%4$d 10:%3$d, 1:%2$d%n", num, ones, tens, hundreds, thousands);
		}
	}
}
