package ClassesAndObjects.CollectionS;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

/**
 *
 * @author Andras Olah (olahandras78@gmail.com)
 */
public class ArrayListAndVectorOperations {

	//Generic method that accepts any type of collection that implements Iterable<C>
	public static <C extends Iterable<C>> void printCollection(Iterable<?> collection) {
		for (Object item : collection) {
			System.out.print("["+item + "] ");
		}
		System.out.println("");
	}

	public static void main(String[] args) {
		String[] firstNames = {"András", "Böbe", "Jack", "Jill", "John"};
		String[] moreNames = {"András", "Böbe"};

		//<editor-fold desc="Creating Vector, ArrayList, List">
		Vector<String> namesVector = new Vector<>();
		ArrayList<String> namesArrayList = new ArrayList<>();
		List<String> firstNamesCollection = Arrays.asList(firstNames);
		List<String> moreNamesCollection = Arrays.asList(moreNames);
		//</editor-fold>

		//<editor-fold desc="Add and print the unsorted namesVector">
		for (String firstname : firstNames) {
			namesVector.add(firstname);
		}
		System.out.println("Unsorted Vector");
		printCollection(namesVector); //ERROR! this is not (yet) working
		// List the contents of the vector
		//</editor-fold>

		//<editor-fold desc="Add Collection to an Arraylist">
		namesArrayList.addAll(firstNamesCollection);
		System.out.println("\nFirst names added to ArrayList");
		printCollection(namesArrayList);
		//</editor-fold>

		//<editor-fold desc="Add object/collection to specific index">
		System.out.println("\nUnsorted Array List");
		namesArrayList.add(1, "Barnabás");
		namesArrayList.addAll(0, moreNamesCollection);
		printCollection(namesArrayList);
		//</editor-fold>

		//<editor-fold desc="Replacing existing item in ArrayList">
		System.out.println("\nReplacing 0. element to Zero Index");
		namesArrayList.set(0, "Zero Index");
		printCollection(namesArrayList);
		//</editor-fold>

		//<editor-fold desc="Sort Collection">
		Collections.sort(namesArrayList);
		System.out.println("\nSorted Array List");
		printCollection(namesArrayList);
		System.out.println("\nReverse Sorted Array List");
		Collections.reverse(namesArrayList);
		printCollection(namesArrayList);
		//</editor-fold>

		//<editor-fold desc="Removing object/collection/all from a collection">
		System.out.println("\nRemoving \"John\" object from Array List");
		namesArrayList.remove("John");
		printCollection(namesArrayList);
		System.out.println("\nRemoving moreNamesCollection from Array List");
		namesArrayList.removeAll(moreNamesCollection);
		printCollection(namesArrayList);
		System.out.println("\nRemove everything from ArrayList with .clear()");
		namesArrayList.clear();
		printCollection(namesArrayList);
		//</editor-fold>

		//<editor-fold desc="Creating Iterator and ListIterator for ArrayList">
		System.out.println("\nIterate through ArrayList with Iterator");
		Iterator<String> iterator = namesArrayList.iterator();
		while (iterator.hasNext()) {
			System.out.print(iterator.next().concat(" fi "));
		}
		ListIterator<String> listIterator = namesArrayList.listIterator(namesArrayList.size());
		System.out.println("\nIterate backwards using ListIterator");
		while (listIterator.hasPrevious()) {
			System.out.print(listIterator.previous().concat(" bi "));
		}
		//</editor-fold>

		//<editor-fold desc="Create empty Collections">
		List newlist = Collections.EMPTY_LIST;
		Set newset = Collections.EMPTY_SET;
		Map newmap = Collections.EMPTY_MAP;

		List<String> listString = Collections.emptyList();
		Set<Long> setLong = Collections.emptySet();
		Map<Date, String> map = Collections.emptyMap();
		HashMap<Date, String> hmap = new HashMap<>(map);
		Date date = new Date(2017, 05, 07);
		Date date2 = new Date(2017, 05, 10);
		hmap.put(date, "Teszt");
//		System.out.println(hmap.containsKey(date2));
//		System.out.println(hmap.get(date));
		//</editor-fold>
		
		//<editor-fold desc="Create Synchronized Collections">
		Collection syncronizedCollection = Collections.synchronizedCollection(new ArrayList());
		List synchronizedList = Collections.synchronizedList(new ArrayList());
		Set synchronizedSet = Collections.synchronizedSet(new HashSet());
		Map synchronizedMap = Collections.synchronizedMap(new HashMap());
		//</editor-fold>
	}

}
