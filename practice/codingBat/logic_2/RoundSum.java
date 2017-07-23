package codingBat.logic_2;

public class RoundSum {

	public int roundSum(int a, int b, int c) {
		System.out.println(round10Solution2(a)+round10Solution2(b)+round10Solution2(c));
		return round10(a) + round10(b) + round10(c);
	}

	public int round10(int num) {
		String numString = String.valueOf(num);
		String rightMost = numString.substring(numString.length() - 1);
		System.out.println(rightMost);
		int rightMostNum = Integer.parseInt(rightMost);
		if (rightMostNum >= 5) {
			return num + (10 - rightMostNum);
		}
		return num - rightMostNum;
	}

	public int round10Solution2(int num) {
		int remainder = num % 10;
		num -= remainder;
		if (remainder >= 5) {
			num += 10;
		}
		return num;
	}

	public static void main(String[] args) {
		RoundSum instance = new RoundSum();
		// instance.round10(15);
		System.out.println(instance.roundSum(16, 17, 18));
		System.out.println(instance.roundSum(16, 17, 18));
	}

}
