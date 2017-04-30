
package HortonExercises.Ch13.Stack_Andras;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import static java.nio.file.StandardOpenOption.CREATE;

/**
 * 
 * @author Andras Olah (olahandras78@gmail.com)
 */
public class Try_WriteReadSerializableStack {
	public static void main(String[] args) {
		
		//Instantinate objects of type string and double
		Stack_Andras_Serializable<String> stringStack = new Stack_Andras_Serializable<>();
		Stack_Andras_Serializable<Double> doubleStack = new Stack_Andras_Serializable<>();

		//Create test data for 10 strings and 10 random number
		String[] strings = {"One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten"};
		Double[] numbers = new Double[10];
		for (int i = 0; i < numbers.length; i++) {
			numbers[i] = Math.random() * 100;
		}
		//Display the content of String array for verification
		System.out.println("Original String Array:");
		for (int i = 0; i < numbers.length; i++) {
			System.out.println(strings[i]);
		}
		//Display the content of String array for verification
		System.out.println("Original Double Array:");
		for (int i = 0; i < numbers.length; i++) {
			System.out.println(numbers[i]);
		}

		//push all data to stack
		for (int i = 0; i < numbers.length; i++) {
			stringStack.push(strings[i]);
			doubleStack.push(numbers[i]);
		}

//Demonstrate that this is the case by creating a Stack<String> object, adding 10 strings to it, serializing and deserializing the Stack<String> object, and listing the contents of the deserialized stack.

		//Write data to file
		Path file = Paths.get("J:\\Exercises\\Ch13\\stack.bin");
		try (ObjectOutputStream oos = new ObjectOutputStream(Files.newOutputStream(file, CREATE))) {
			oos.writeObject(stringStack);
			oos.writeObject(doubleStack);
		} catch (IOException ex) {
			ex.printStackTrace();
		}

		//Read data from file
		try (ObjectInputStream ois = new ObjectInputStream(Files.newInputStream(file))) {
			Stack_Andras_Serializable<String> readObjectString =(Stack_Andras_Serializable<String>) ois.readObject();
			Stack_Andras_Serializable<Double> readObjectDouble =(Stack_Andras_Serializable<Double>) ois.readObject();
			readObjectString.listAll();
			readObjectDouble.listAll();
		} catch (IOException ex) {
			ex.printStackTrace();
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}
	}
}
