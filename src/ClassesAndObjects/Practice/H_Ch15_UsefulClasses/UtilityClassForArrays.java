package ClassesAndObjects.Practice.H_Ch15_UsefulClasses;

import java.util.Comparator;

public class UtilityClassForArrays {

	public static void main(String[] args) {
		// Fill array
		long[] longValues = new long[100];
		java.util.Arrays.fill(longValues, 100L);
		System.out.println(longValues[32]);
		System.out.println(longValues[62]);

		// Fill two dimensional array
		int[][] twoDimArray = new int[10][20];
		int filler = 0;
		for (int[] secondDim : twoDimArray) {
			java.util.Arrays.fill(secondDim, filler++);
		}
		System.out.println(twoDimArray[3][3]);
		System.out.println(twoDimArray[3][19]);
		System.out.println(twoDimArray[6][6]);

		// Fill array with objects from a given index up until
		// fill(type[] array, int fromIndex, int toIndex(exclusive), type value)
		Person[] people = new Person[10];
		java.util.Arrays.fill(people, 3, 6, new Person("Joe")); // 5 and 6 will
																// be new person

		// Copy array - same length
		Person[] clonePeople = java.util.Arrays.copyOf(people, people.length);
		Person[] halfPeople = java.util.Arrays.copyOf(people, people.length / 2);
		Person[] doublePeople = java.util.Arrays.copyOf(people, people.length * 2);
		// Copy array range - toIndex is exclusive
		Person[] newPeople = java.util.Arrays.copyOfRange(people, 4, 6);

		// Comparing arrays
		boolean areEquals = java.util.Arrays.equals(people, clonePeople);
		boolean areEquals2 = java.util.Arrays.equals(people, newPeople);

		// Sorting arrays
		Person[] fullPeople = new Person[12];
		for (int i = 0; i < fullPeople.length; i++) {
			fullPeople[i] = new Person("Name " + (int) (100 + Math.random() * 5), "surname");
		}
		java.util.Arrays.sort(fullPeople, new ReverseComparatorPerson());
		java.util.Arrays.sort(fullPeople);

		/*
		 * Searching arrays This works only if the elements of the array are in
		 * ascending sequence. so if they are not, you should call the sort()
		 * method before calling binarySearch(). Return value if not in array:
		 * Negative integer returned: a) taking the value of the index position
		 * of the first element that is greater than the value b) reversing its
		 * sign c) subtracting 1
		 */
		// Searching Object arrays
		Person lookingForThis = new Person("Name 100 surname");
		int resultIndex = java.util.Arrays.binarySearch(fullPeople, lookingForThis, new ReverseComparatorPerson());
		// searching int arrays
		int[] numbers = { 2, 4, 6, 8, 10 };
		int positionInArray = java.util.Arrays.binarySearch(numbers, 6); // 2
		int positionNotInArray = java.util.Arrays.binarySearch(numbers, 7); // -4

		// Converting to String
		String[][] folks = { { "Ann", "Arthur", "Arnie" }, { "Bill", "Barbara", "Ben", "Brenda", "Brian" },
				{ "Charles", "Catherine" } };
		System.out.println(java.util.Arrays.deepToString(folks));
		System.out.println("End.");
	}
}

class ReverseComparatorPerson implements Comparator<Person> {

	@Override
	public int compare(Person o1, Person o2) {
		return o1.name.compareTo(o2.name) * -1;
	}

}
