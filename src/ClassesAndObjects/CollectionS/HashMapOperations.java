package ClassesAndObjects.CollectionS;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Andras Olah (olahandras78@gmail.com)
 */
public class HashMapOperations {

	/**
	 * Put a new key:value pair to the given HashMap<K,V>, and displays the result
	 *
	 * @param map HashMap
	 * @param key Key of the value
	 * @param objectToPut Value to put to the key
	 * @return returns null if there was no value at current key, or the old value that was replaced
	 */
	public static <K, V> V putToMap(HashMap<K, V> map, K key, V objectToPut) {
		V oldObjectInMap = null;
		if ((oldObjectInMap = map.put(key, objectToPut)) != null) {   	//key was used before for another value
			//optional code here in case of replacement
		} else {														//key was not used before
			//optional code here in case of non-replacement
		}
		System.out.println("Put " + key + ":" + (oldObjectInMap == null ? objectToPut : objectToPut + " (replaced old value: " + oldObjectInMap + ")"));
		return oldObjectInMap;
	}

	//Returns a Set<K> object that stores all keys in a HashMap
	public static <K, V> Set<K> getKeySet(HashMap<K, V> map) {
		Set<K> keySet = map.keySet();
		return keySet;
	}

	//Returns an array that holds all the keys. INCOMPLETE!
	public static <K> K[] getKeySetArray(Set<K> keySet) {
		K[] returnArray = null;		//a generic array can not be initialized...
		keySet.toArray(returnArray);
		return returnArray;
	}

	//Displays and Returns a String[] array of Keys - parameter is bounded to String class
	public static <K extends String, V> String[] getStringKeyArray(HashMap<K, V> map) {
		Set<K> keys = map.keySet();
		String[] keysArray = new String[keys.size()];
		keys.toArray(keysArray);
		System.out.println("\nList String keys in the Map");
		for (int i = 0; i < map.size(); i++) {
			System.out.printf("Key #%d=%s value=%d%n", i, keysArray[i], map.get(keysArray[i]));
		}
		return keysArray;
	}

	//Generic method to display all keys in a HashMap
	public static <K, V> void listAllKeys(HashMap<K, V> map) {
		for (K key : map.keySet()) {
			System.out.println("Key: " + key);
		}
	}

	//Generic method to return a Collection of Values stored in a map
	public static <K, V> Collection<V> getACollectionOfValuesInMap(HashMap<K, V> map) {
		Collection<V> collection = map.values();
		return collection;
	}

	//Generic method to list the content of a Collection
	public static <V> void listACollection(Collection<V> collection) {
		for (V currentValue : collection) {
			System.out.println(currentValue);
		}
	}

	//Generic method to list Key:Value pairs in a HashMap
	public static <K, V> void listKeyValuePairsInAMap(HashMap<K, V> map) {
		Set<K> keys = map.keySet();
		for (K key : keys) {
			System.out.println(key + ":" + map.get(key));
		}
	}

	//Generic method to get Key:Value pairs to a Set<Map.Entry<K,V>> object
	public static <K, V> Set<Map.Entry<K, V>> getKeyValuePairsInAMapWithEntrySet(HashMap<K, V> map) {
		Set<Map.Entry<K, V>> returnEntrySet = map.entrySet();
//		for (Map.Entry<K, V> currentEntry : returnEntrySet) {
//			System.out.println(currentEntry.getKey() + ":" + currentEntry.getValue());
//		}
		return returnEntrySet;
	}

	//Generating hashCode if a data member is another class reference - it has to implement hashcode()
	public int createHashCode() {
		String string1 = "test";
		String string2 = "test2";
		int integer = 42;
		// Wherever a data member is an object of another class rather than a variable of one of the basic types
		// you need to implement the hashCode() method for that class.
		MobilePhone mobilePhone = new MobilePhone("OnePlus3", 123456); //MobilePhone MUST implement hashCode() method
		return 7 * string1.hashCode() + 13 * string2.hashCode() + 17 * integer + mobilePhone.hashCode();
	}

	//Class to demonstrate @Override for hashCode method implementation
	private class MobilePhone {

		String phoneType;
		int phoneNumber;

		//Constructor
		public MobilePhone(String type, int number) {
			this.phoneType = type;
			this.phoneNumber = number;
		}

		@Override														//Method to generate HashCode for this class
		public int hashCode() {
			return 7 * phoneType.hashCode() + 13 * phoneNumber;
		}

		@Override
		public String toString() {
			return "Phone Type= "+ this.phoneType+"\n Number= "+this.phoneNumber;
		}

	}

	//Using a class as Key - must implement Comparable and Override equals, hashcode
	private class MobileOwner implements Comparable<MobileOwner> {
		//<editor-fold desc="Requirements for a Key class">
		/*
			Override equals() method
			- You MUST override the equals() method of the Object class.
			- The equals() method is used by methods in the HashMap<> class to determine when two KEYS are equal.
			- The overridden equals() method should return true when two different objects contain identical data values.

			Override hashCode method
			- Use the fields of the object to generate a hashcode. 
			- Otherwise Object's default hashcode method will use memory mapping for the key
		
		*/
		//</editor-fold>

		public String ownerFirstName;
		String ownerLastName;

		public MobileOwner(String firstName, String LastName) {
			this.ownerFirstName = firstName;
			this.ownerLastName = LastName;
		}

		@Override
		public boolean equals(Object comparedToMobileOwner) {
			return this.compareTo((MobileOwner) comparedToMobileOwner) == 0;  //True if compareTo is 0
		}

		@Override
		public int compareTo(MobileOwner comparedToMobileOwner) {
			//Compare the Last name first. If they are the same, 0 will be the result
			int comparingLastName = this.ownerLastName.compareToIgnoreCase(comparedToMobileOwner.ownerLastName);
			//If last names are the same, compare first names. 
			return comparingLastName == 0 ? this.ownerFirstName.compareToIgnoreCase(comparedToMobileOwner.ownerFirstName) : comparingLastName;

		}

		//To make sure hashCodes are generated by fields and not Object's mapping in memory!!
		@Override														
		public int hashCode() {
			return 7 * ownerFirstName.hashCode() + 13* ownerLastName.hashCode();
		}

		@Override
		public String toString() {
			return "Phone owner= "+ this.ownerFirstName+" "+this.ownerLastName;
		}
	}

	public static void main(String[] args) {
		//<editor-fold desc="Instantiate and operate on a HashMap<MobileOwner, MobilePhone> type map">
		HashMapOperations instance = new HashMapOperations();
		HashMap<MobileOwner, MobilePhone> phoneOwnersMap = new HashMap<>();
		//Create some object references to be used
		MobileOwner steveJobs=instance.new MobileOwner("Steve", "Jobs");
		MobilePhone onePlusThree = instance.new MobilePhone("OnePlus3", 0);
		MobilePhone iPhone = instance.new MobilePhone("iPhone", 0);
		//Create new map entries based on existing object references
		putToMap(phoneOwnersMap, steveJobs, onePlusThree);
		putToMap(phoneOwnersMap, steveJobs, iPhone);
		//Create new instances while adding them to the map
		putToMap(phoneOwnersMap, instance.new MobileOwner("András", "Oláh"), instance.new MobilePhone("OnePlus3", 123456));
		putToMap(phoneOwnersMap, instance.new MobileOwner("András", "Oláh"), instance.new MobilePhone("OnePlus3", 123456));
		putToMap(phoneOwnersMap, instance.new MobileOwner("András", "Oláh"), instance.new MobilePhone("Iphone X", 999999));
		//Remove Steve Job's OP3
		System.out.println("\nListing owners");
//		phoneOwnersMap.remove(steveJobs, iPhone);
		listKeyValuePairsInAMap(phoneOwnersMap);
		//</editor-fold>
		/*
		//<editor-fold desc="Instantiate and operate on a HashMap<String, Integer> type map">
		HashMap<String, Integer> aMap = new HashMap<>();

		//Add key:value pairs to the map
		System.out.println("Put values to the HashMap");
		putToMap(aMap, "This is a Key", 12345);
		putToMap(aMap, "Key2", 12345);
		putToMap(aMap, "Key2", 99999);
		putToMap(aMap, "Key3", 12345);
		putToMap(aMap, "Árvíztűrő", 100);
		putToMap(aMap, "Tükörfúrógép", 100);

		//List keys in the map
		System.out.println("\nThe keys in the HashMap:");
		listAllKeys(aMap);

		//The all values stored in the map
		System.out.println("\nList the values stored in the map");
		listACollection(getACollectionOfValuesInMap(aMap));

		//List all Key:Values pair in the map
		System.out.println("\nList all Key:Values pairs");
		listKeyValuePairsInAMap(aMap);

		//List all Key:Values with EntrySet
		System.out.println("\nList all Key:Value pairs with EntrySet");
		//This set has to be specific for the map it is expecting to retrieve
		Set<Map.Entry<String, Integer>> fullSet = getKeyValuePairsInAMapWithEntrySet(aMap);
		for (Map.Entry<String, Integer> currentEntry : fullSet) {
			System.out.println(currentEntry.getKey() + ":" + currentEntry.getValue());
		}

		//Get the String Array from Keys
		String[] keys = getStringKeyArray(aMap);

		//TODO List hashmap values sorted
		System.out.println("\nList all Key:Value pairs with EntrySet sorted alphabetically");
		//Get an Entry Set
		//Sort the collection
		//Display the result
		listKeyValuePairsInAMap(aMap);
		//</editor-fold>
		 */
	}
}
