package ClassesAndObjects;

import java.util.Comparator;

public class ComparatorSkeleton implements Comparator<ComparableClass> {

	@Override
	// Comparable class should have its compareTo method defined
	public int compare(ComparableClass o1, ComparableClass o2) {
		return o1.numberFieldInClass.compareTo(o2.numberFieldInClass) * -1; // Reverse
																			// order!
		// or any other comparison logic
	}

	@Override
	/*
	 * This compares the current Comparator<> object with another object of a
	 * type that also implements the Comparator<> interface that you pass as the
	 * argument.
	 */
	public boolean equals(Object comparatorObject) {
		if (this == comparatorObject) // argument is the same object
			return true;
		if (comparatorObject == null) { // argument is null
			return false;
		}
		return getClass() == comparatorObject.getClass();
	}

	public static void main(String[] args) {
		// Instantiating a Comparator object
		ComparatorSkeleton comparatorObject = new ComparatorSkeleton();
	}
}

// Comparable class
class ComparableClass implements Comparable<ComparableClass> {
	Integer numberFieldInClass;

	@Override
	public int compareTo(ComparableClass o) {
		// TODO Auto-generated method stub
		return 0;
	}
}
