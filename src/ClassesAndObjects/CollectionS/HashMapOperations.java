package ClassesAndObjects.CollectionS;

import java.util.Collection;
import java.util.HashMap;
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

	public static <K, V> Set<Map.Entry<K, V>> listKeyValuePairsInAMapWithEntrySet(HashMap<K, V> map) {
		Set<Map.Entry<K, V>> returnEntrySet = map.entrySet();
		for (Map.Entry<K, V> currentEntry : returnEntrySet) {
			System.out.println(currentEntry.getKey() + ":" + currentEntry.getValue());
		}
		return returnEntrySet;
	}

	public static void main(String[] args) {
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
		System.out.println("The keys in the HashMap:");
		listAllKeys(aMap);

		//The all values stored in the map
		System.out.println("List the values stored in the map");
		listACollection(getACollectionOfValuesInMap(aMap));

		//List all Key:Values pair in the map
		System.out.println("List all Key:Values pairs");
		listKeyValuePairsInAMap(aMap);

		//List all Key:Values with EntrySet
		System.out.println("List all Key:Value pairs with EntrySet");

		//Get the String Array from Keys
		String[] keys=getStringKeyArray(aMap);

		//TODO sort the members in a HashMap by Key or by Value

	}
}
