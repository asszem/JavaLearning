/*
 * http://oraclejavacentral.blogspot.hu/2018/03/mergesort-in-java-algorithm-example.html?m=1
 * 
 * 
 * Time Complexity of mergesort algorithm is 
 * same in best, average and worst case and it's equal to 
 * O(n*log(n))
*/

package StringsAndArrays.Sorting;

import java.net.SocketTimeoutException;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicLong;

public class MergeSort {

	public static void main(String[] args) {

		System.out.println("Mergesort sample");
		int[] input = { 87, 57, 370, 110, 90, 610, 02, 710, 140, 203, 150 };

		System.out.println("Array before sorting");
		System.out.println(Arrays.toString(input));

		// sorting array using MergeSort algorithm
		mergesort(input);

		System.out.println("Array after sorting using mergesort algorithm");
		System.out.println(Arrays.toString(input));

	}

	/**
	 * Java function to sort given array using merge sort algorithm
	 *
	 * @param input
	 */
	public static void mergesort(int[] input) {
		mergesort(input, 0, input.length - 1);
	}

	private static final AtomicLong totalMergesortCalls = new AtomicLong();
	private static final AtomicLong totalRecursions = new AtomicLong();
	private static final AtomicLong recursionDepth = new AtomicLong();

	/**
	 * A Java method to implement MergeSort algorithm using recursion
	 *
	 * @param inputArray
	 *            integer array to be sorted
	 * @param startIndex
	 *            index of first element in array
	 * @param endIndex
	 *            index of last element in array
	 */
	private static void mergesort(int[] inputArray, int startIndex, int endIndex) {
		Long currentMergesortCall = totalMergesortCalls.getAndIncrement();
		System.out.println(currentMergesortCall+".");
		String indent = "  ";
		for (int i = 0; i < recursionDepth.get(); i++) {
			indent += "~";
		}
		indent+="["+currentMergesortCall+"."+recursionDepth.get()+"] ";
		System.out.println(indent + "Mergesort call #" + currentMergesortCall + "."+recursionDepth.get());

		// break problem into smaller structurally identical problems
		int midIndex = (startIndex + endIndex) / 2;
		if (startIndex < endIndex) {
			totalRecursions.incrementAndGet();
			System.out.println(indent + "->Start-Mid Recursive call in #" + currentMergesortCall + "." + recursionDepth.get());
			recursionDepth.getAndIncrement();
			mergesort(inputArray, startIndex, midIndex);
			
			totalRecursions.incrementAndGet();
			System.out.println(indent + "->Mid-End Recursive call in #" + currentMergesortCall + "." + recursionDepth.get());
			recursionDepth.getAndIncrement();
			mergesort(inputArray, midIndex + 1, endIndex);
		}
		System.out.println(indent + "Performing sort");
		// merge solved pieces to get solution to original problem
		int i = 0, firstIndex = startIndex, lastIndex = midIndex + 1;
		System.out.printf("%s\tstart[index]=%d[%d]%n", indent, inputArray[startIndex],startIndex);
		System.out.printf("%s\tlast[index]=%d[%d]%n", indent, lastIndex<endIndex?inputArray[lastIndex]:inputArray[endIndex],lastIndex);
		int[] tmp = new int[endIndex - startIndex + 1];

		while (firstIndex <= midIndex && lastIndex <= endIndex) {
			tmp[i++] = inputArray[firstIndex] < inputArray[lastIndex] ? inputArray[firstIndex++]
					: inputArray[lastIndex++];
		}
		while (firstIndex <= midIndex) {
			tmp[i++] = inputArray[firstIndex++];
		}
		while (lastIndex <= endIndex) {
			tmp[i++] = inputArray[lastIndex++];
		}
		i = 0;
		while (startIndex <= endIndex) {
			inputArray[startIndex++] = tmp[i++];
		}
		System.out.println(indent + "<-Exiting recursion: " + recursionDepth.getAndDecrement());
	}

}
