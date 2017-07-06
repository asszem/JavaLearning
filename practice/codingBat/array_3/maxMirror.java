/*
 * 
We'll say that a "mirror" section in an array is a group of contiguous elements such that somewhere in the array,
 the same group appears in reverse order. For example, the largest mirror section in 
 {1, 2, 3, 8, 9, 3, 2, 1} is length 3 (the {1, 2, 3} part).

 Return the size of the largest mirror section found in the given array.

maxMirror([1, 2, 3, 8, 9, 3, 2, 1]) → 3
maxMirror([1, 2, 1, 4]) → 3
maxMirror([7, 1, 2, 9, 7, 2, 1]) → 2
 * */
package codingBat.array_3;

public class maxMirror {

	public static int mirrorr(int[] array) {
		System.out.println("Initial array:");
		System.out.println(java.util.Arrays.toString(array));
		int result = 0;
		int maxRightIndex = array.length - 1;
		// Loop 1 - increase mirrorSize up until length of array, starting from 2 - mirrorSize
		for (int mirrorSize = 1; mirrorSize <= array.length; mirrorSize++) {

			// Loop 2 - walk array from left to right, with mirrorSize to get the leftIndex (from 0 to array.lenght-mirrorSize)
			for (int leftIndex = 0; leftIndex <= array.length - (mirrorSize); leftIndex++) {

				// Loop 3 - create and set leftMirror values -> leftMirror[mirrorSize]
				int[] leftMirror = new int[mirrorSize];
				for (int i = 0; i < leftMirror.length; i++) {
					leftMirror[i] = array[leftIndex + i];
				}// End loop 3

				// Loop 4 - walk backward from right to left and check if there is a mirror match - rightIndex (from array.length until 0+mirrorSize
				for (int rightIndex = maxRightIndex; rightIndex >= mirrorSize-1; rightIndex--) {
					int[] rightMirror = new int[mirrorSize];

					// Loop 5 - create and set rightMirror values
					for (int i = 0; i < rightMirror.length; i++) {
						rightMirror[(rightMirror.length-1)-i] = array[rightIndex - i];
					}// End loop 5
						// Compare left & right mirrors
					boolean mirrorsEqual = false;
//					System.out.println();
//					System.out.printf("ms=%d, li=%d, ri=%d%n", mirrorSize, leftIndex, rightIndex);
//					System.out.println("Left mirror: " + java.util.Arrays.toString(leftMirror));
//					System.out.println("Right mirror:" + java.util.Arrays.toString(rightMirror));
					mirrorsEqual = compareMirror(leftMirror, rightMirror);
//					System.out.println("Equal: " + mirrorsEqual);
					if (mirrorsEqual) {
						// System.out.println(java.util.Arrays.toString(leftMirror) + " -- "
						// + java.util.Arrays.toString(rightMirror));
						result = mirrorSize;
					}
				} // end Loop 4
			} // end loop 2

		} // end loop 1
		return result;
	}

	public static boolean compareMirror(int[] a, int[] b) {
		int alength = a.length;
		int blength = b.length;
		for (int i = 0; i < a.length; i++) {
			int curA = a[i];
			int curB = b[(blength - 1) - i];
			// int curB=b[i];
			if (curA != curB)
				return false;
		}
		return true;
	}

	public static void main(String[] args) {
		int[] test1 = { 1, 2, 3, 4, 5 }; // 0
		int[] test2 = { 1, 2, 3, 8, 9, 3, 2, 1 }; // 3
		int[] test3 = { 7, 1, 2, 9, 7, 2, 1 }; // 2
		int[] test4 = { 1, 2, 1, 4 }; // 3
		 System.out.println(mirrorr(test1));
		 System.out.println(mirrorr(test2));
		 System.out.println(mirrorr(test3));
		System.out.println(mirrorr(test4));
		/*
		 * maxMirror([1, 2, 3, 8, 9, 3, 2, 1]) → 3 maxMirror([1, 2, 1, 4]) → 3 maxMirror([7, 1, 2, 9, 7, 2, 1]) → 2
		 */

	}
}
