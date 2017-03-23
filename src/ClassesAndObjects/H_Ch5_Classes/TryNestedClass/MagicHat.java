package ClassesAndObjects.H_Ch5_Classes.TryNestedClass;

import java.util.Random;                                               // Import Random class name

public class MagicHat {

	static int maxRabbits = 5;                                           // Maximum rabbits in a hat
	static Random select = new Random();                                 // Random number generator 0 és a paraméter-1 között

	// Constructor for a hat
	public MagicHat(String hatName) {
		this.hatName = hatName;                                            // Store the hat name
		rabbits = new Rabbit[1 + select.nextInt(maxRabbits)];                // Random rabbits 1+ 0-4 között (paraméter=5)

		for (int i = 0; i < rabbits.length; ++i) {
			rabbits[i] = new Rabbit();                                       // Create the rabbits - a rabbit tömb annyi új objektumot kap, amekkora lett
		}
	}

	// String representation of a hat
	public String toString() {
		// Hat name first...
		String hatString = "\n" + hatName + " contains:\n";

		for (Rabbit rabbit : rabbits) {
			hatString += "   " + rabbit;                                     // Add the rabbits strings
		}
		return hatString;
	}

	private String hatName;                                              // Name of the hat
	private Rabbit rabbits[];                                            // Rabbits in the hat


	public String getHatName(){
		return hatName;
	}


	// Nested class to define a rabbit
	static class Rabbit {
		// A name is a rabbit name from rabbitNames followed by an integer

		static private String[] rabbitNames = {"Floppsy", "Moppsy", "Gnasher", "Thumper", "Nuszika"};
		static private int[] rabbitNamesCount = new int[rabbitNames.length];  //akkora tömböt hoz létre, ahány nyúlnevet megadtunk. 
		private String name;                                               // Name of the rabbit

		// Constructor for a rabbit
		public Rabbit() {
			int index = select.nextInt(rabbitNames.length);                   // Get random name index - 0 és nyúlnevek száma között De miért ninsc 0?
			name = rabbitNames[index] + (++rabbitNamesCount[index]);			// Azért, mert az index-et először a rabbitNames tömbben használja és ott az első a 0
			// Aztán a rabbitNamesCount tömbhöz hozzáad egyet, az index-en. 
			//Erre azért van szükség, hogy ha ugyanaz a nyúl neve (index) akkor a rabbitNamesCount tömböt növelve mindig más számot ad
			//Így végül sosem lesz két egyforma nevű nyúl
		}

		// String representation of a rabbit
		public String toString() {
			return name; 
		}
	}

	//non-static nested class
	class nonStaticTest {

		private String name;
		
		//Constructor
		public nonStaticTest() {
			name = "NonStaticTest" + select.nextInt(21);
		}

		public String toString(){
			return "Name objektumváltozó: "+name+" aminek a szülő objektuma: "+hatName;
		}
	}
}
