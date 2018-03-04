package StringsAndArrays.Sorting;

/**
 *
 * @author Andras Olah (olahandras78@gmail.com)
 */
public class BubbleSort_Demo {

	public static void bubbleSortInt(int[] array) {
		int[] nums = array;
		int a, b, t;
		int size;

		size = 10; // number of elements to sort

		// display original array
		System.out.print("Original array is:");
		for (int i = 0; i < size; i++) {
			System.out.print(" " + nums[i]);
		}
		System.out.println();

		// This is the bubble sort.
		for (a = 1; a < size; a++) {
			for (b = size - 1; b >= a; b--) {
				if (nums[b - 1] > nums[b]) { // if out of order
					// exchange elements
					t = nums[b - 1];
					nums[b - 1] = nums[b];
					nums[b] = t;
				}
			}
		}

		// display sorted array
		System.out.print("Sorted array is:");
		for (int i = 0; i < size; i++) {
			System.out.print(" " + nums[i]);
		}
		System.out.println();
	}

	public static void bubbleSortChar(char[] array) {
		char[] chars = array;
		char t;
		int a, b;
		int size;

		size = chars.length; // number of elements to sort

		// display original array
		System.out.print("Original array is:");
		for (int i = 0; i < size; i++) {
			System.out.print(" " + chars[i]);
		}
		System.out.println();

		// This is the bubble sort.
		for (a = 1; a < size; a++) {
			for (b = size - 1; b >= a; b--) {
				if (chars[b - 1] > chars[b]) { // if out of order
					// exchange elements
					t = chars[b - 1];
					chars[b - 1] = chars[b];
					chars[b] = t;
				}
			}
		}

		// display sorted array
		System.out.print("Sorted array is:");
		for (int i = 0; i < size; i++) {
			System.out.print(" " + chars[i]);
		}
		System.out.println();
	}

	public static void bubbleSortString(String[] array) {
		String[] strings = array;
		String t;
		int a, b;
		int size;

		size = strings.length; // number of elements to sort

		// display original array
		System.out.print("Original array is:");
		for (int i = 0; i < size; i++) {
			System.out.print(" " + strings[i]);
		}
		System.out.println();

		// This is the bubble sort.
		for (a = 1; a < size; a++) {
			for (b = size - 1; b >= a; b--) {
				if (strings[b - 1].compareTo(strings[b]) > 0) { // if out of order
					// exchange elements
					t = strings[b - 1];
					strings[b - 1] = strings[b];
					strings[b] = t;
				}
			}
		}

		// display sorted array
		System.out.print("Sorted array is:");
		for (int i = 0; i < size; i++) {
			System.out.print(" " + strings[i]);
		}
		System.out.println();
	}

	public static void main(String args[]) {
		int nums[] = { 99, -10, 100123, 18, -978, 5623, 463, -9, 287, 49 };
		// bubbleSortInt(nums);
		char[] chars = { 'X', 'C', 'B', 'A' };
		bubbleSortChar(chars);
		String[] str = { "dia", "cecil", "béla", "alma" };
		bubbleSortString(str);
	}
}
