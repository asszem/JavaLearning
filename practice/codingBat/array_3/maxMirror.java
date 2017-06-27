/*
 * 
We'll say that a "mirror" section in an array is a group of contiguous elements such that somewhere in the array, the same group appears in reverse order. For example, the largest mirror section in {1, 2, 3, 8, 9, 3, 2, 1} is length 3 (the {1, 2, 3} part). Return the size of the largest mirror section found in the given array.

maxMirror([1, 2, 3, 8, 9, 3, 2, 1]) → 3
maxMirror([1, 2, 1, 4]) → 3
maxMirror([7, 1, 2, 9, 7, 2, 1]) → 2
 * */
package codingBat.array_3;

public class maxMirror {

	public static int mirrorr(int[] array) {
		int result = 0;
		// Loop 1 - increase mirrorSize up until length of array, starting from 2 - mirrorSize
		for (int mirrorSize = 2; mirrorSize < array.length; mirrorSize++) {

			// Loop 2 - walk array from left to right, with mirrorSize to get the leftMirror values - leftIndex (from 0 to array.lenght-mirrorSize)
			for (int leftIndex = 0; leftIndex < array.length - (mirrorSize); leftIndex++) {

				// Loop 3 - create and set leftMirror values -> leftMirror[mirrorSize]
				int[] leftMirror = new int[mirrorSize];
				for (int i = 0; i < leftMirror.length; i++) {
					leftMirror[i] = array[leftIndex + i];
				}// End loop 3
					// Loop 4 - walk backward from right to left and check if there is a mirror match - rightIndex (from array.length until 0+mirrorSize
				for (int rightIndex = array.length - 1; rightIndex > mirrorSize; rightIndex--) {
					int[] rightMirror = new int[mirrorSize];
					// Loop 5 - create and set rightMirror values
					for (int i = 0; i < rightMirror.length; i++) {
						rightMirror[i] = array[rightIndex - i];
					}// End loop 5
						// Compare left & right mirrors
					boolean mirrorsEqual = true;
					for (int i = 0; i < leftMirror.length; i++) {
						if (leftMirror[i] != rightMirror[(rightMirror.length - 1) - i]) {
							mirrorsEqual = false;
						}
					}
					if (mirrorsEqual) {
						System.out.println(java.util.Arrays.toString(leftMirror) + " -- "
								+ java.util.Arrays.toString(rightMirror));
					}
				} // end Loop 4
			} // end loop 2

		} // end loop 1
		return result;
	}

	public static void main(String[] args) {
		int[] test = { 1, 2, 3, 1, 2 }; // 4
		// int[] test = { 1, 2, 3, 4, 5, 4, 3, 2, 1 }; // 4
		mirrorr(test);

	}
}
