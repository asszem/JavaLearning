package ClassesAndObjects.H_Ch13_GenericTypes;

/**
 * Example of using multiple type variables
 *
 * @author Andras Olah (olahandras78@gmail.com) Based on Horton, ch13, Generics, p480
 *
 *
 * This would typically arise where one object is used as a key to access another object that represents a value in a collection. 
 * 
 * For example, you might store Person objects in a collection that encapsulates personal details
 * such as the name, the address, and the phone number. 
 * 
 * You could associate each Person object with a Name object that you use as a key to retrieve the Person object. 
 * 
 * One way of establishing the association between an object and its key is to encapsulate both in another object — of type Pair
 * 
 *
 */
public class KeyPairs<T, V> {

	private T key;
	private V value;

	// Constructor
	public KeyPairs(T aKey, V aValue) {
		key = aKey;
		value = aValue;
	}

	// Get the key for this pair
	public T getKey() {
		return key;
	}

	// Get the value for this pair
	public V getValue() {
		return value;
	}

	// Set the value for this pair
	public void setValue(V aValue) {
		value = aValue;
	}

	public static void main(String[] args) {
		KeyPairs<String, String> entry = new KeyPairs<>("Fred Thrump", "212 222 3333");
		KeyPairs<String, Integer> secondEntry = new KeyPairs<>("Fred Thrump", 42);
	}
}
