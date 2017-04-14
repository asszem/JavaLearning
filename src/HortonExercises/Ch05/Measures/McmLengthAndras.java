/*
DONE - Define a class, mcmLength, to represent a length measured in meters, centimeters, and millimeters, each
stored as integers. 

Include methods 
DONE	to add and subtract objects, 
DONE	to multiply and divide an object by an integer value, 
DONE	to calculate an area resulting from the product of two objects, 
DONE	and to compare objects.

DONE - Include constructors 
	that accept three arguments — meters, centimeters, and millimeters; 
	one integer argument in millimeters; 
	one double argument in centimeters; 
	and no arguments, which creates an object with the length set to zero. 

Check the class by creating some objects and testing the class operations.
 */
package HortonExercises.Ch05.Measures;

/**
 *
 * @author Andras Olah (olahandras78@gmail.com)
 */
public class McmLengthAndras {

	public static void main(String[] args) {
		//<editor-fold desc="Some test data">
		/*
    lengths[0] = new mcmLength(274.65);
    lengths[1] = new mcmLength(274);
    lengths[2] = new mcmLength(274,2,3);
    lengths[3] = new mcmLength();


Length 0 is 2m 74cm 7mm
Length 1 is 0m 27cm 4mm
Length 2 is 274m 2cm 3mm
Length 3 is 0m 0cm 0mm
Addition: 2m 74cm 7mm plus 0m 27cm 4mm is 3m 2cm 1mm
Subtraction: 2m 74cm 7mm minus 0m 27cm 4mm is 2m 47cm 3mm
Multiplication: 2m 74cm 7mm times 10 is 27m 47cm 0mm
Division: 2m 74cm 7mm divided by 10 is 0m 27cm 4mm
Area: 2m 74cm 7mm by 0m 27cm 4mm is 752678 square mm
Length 2m 74cm 7mm is greater than length 0m 27cm 4mm
Length 2m 74cm 7mm is greater than length 0m 27cm 4mm
Length 0m 27cm 4mm is less than length 274m 2cm 3mm
Length 274m 2cm 3mm is greater than length 0m 0cm 0mm

		 */
		//</editor-fold>
		McmLengthAndras obj0 = new McmLengthAndras(274.65);
		McmLengthAndras obj1 = new McmLengthAndras(274);
		McmLengthAndras obj2 = new McmLengthAndras(274, 2, 3);
		McmLengthAndras obj3 = new McmLengthAndras();
		compare(obj0, obj2);
//		System.out.println(" Area: 2m 74cm 7mm by 0m 27cm 4mm is 752678 square mm");
//		System.out.println(obj0);
//		System.out.println(obj1);
//		System.out.println(obj2);
//		System.out.println(obj3);
	}

//Variables
	private int meters;
	private int centimeters;
	private int millimeters;
	private int objectIdentifier; //This will be use to store objCount for each object so that toString can reference it. 
	private static int objCount = 0;

//Constructors
	//No args constructor
	public McmLengthAndras() {
//		System.out.println("Object #" + (++objCount) + " created");
		meters = centimeters = millimeters = 0;
		objectIdentifier = objCount++;
	}

	//3 args constructor
	public McmLengthAndras(int inputMeters, int inputCentimeters, int inputMillimeters) {
//		System.out.println("Object #" + (++objCount) + " created");
		int[] conversed = conversion(inputMeters, inputCentimeters, inputMillimeters);
		meters = conversed[0];
		centimeters = conversed[1];
		millimeters = conversed[2];
		objectIdentifier = objCount++;
	}

	public McmLengthAndras(double inputCentimeters) {
//mini konverzió, ha centiméter törtben van megadva, akkor azt millivé alakítsa
//ha törtszám 5.43 cm, akkor az  54.3 mm. Mivel milliméter tört részét nem számolunk, ezért a cm*10 egészrészét adjuk csak paraméternek
//		System.out.println("Object #" + (++objCount) + " created");
		int[] conversed = conversion(0, 0, (int) (Math.round(inputCentimeters * 10)));
		millimeters = conversed[2];
		centimeters = conversed[1];
		meters = conversed[0];
		objectIdentifier = objCount++;
	}

	public McmLengthAndras(int inputMillimeters) {
//		System.out.println("Object #" + (++objCount) + " created");
//		System.out.println("a" + inputMillimeters);
		int[] conversed = conversion(0, 0, inputMillimeters);
		millimeters = conversed[2];
		centimeters = conversed[1];
		meters = conversed[0];
		objectIdentifier = objCount++;
	}

//Methods
	//to convert millimeters to centimeters and centimeters to meters
	//1M = 100 cm = 1000mm
	static int[] conversion(int iMeter, int iCMeter, int iMMeter) {
		int[] correct = new int[3];
		correct[0] = iMeter;
		correct[1] = iCMeter;
		correct[2] = iMMeter;
		//if mm>10, move everything above 10 to CMs.  x mm = x/10cm
		if (iMMeter >= 10) {
			correct[2] = iMMeter % 10; 				//Milliméterhez adja hozzá a 10-el osztás maradékát. 
			correct[1] += (int) (iMMeter / 10); 	//Centiméterhez adjuk hozzá a 10-el osztás egészrészét
		} //if cm>100, move everything above 100 to meters
		if (correct[1] >= 100) {
			correct[0] += (int) (correct[1] / 100); //A centiméter egészrésze hozzáadódik a méter változóhoz
			correct[1] %= 100;						//A centiméter maradéka marad a centi változón
		}
//		System.out.println("Running conversion() method.");
//		System.out.println("Input:\t\t Meters:" + iMeter + " Centimeters:" + iCMeter + " Millimeters:" + iMMeter + "");
//		System.out.println("Output:\t\t Meters:" + correct[0] + " Centimeters:" + correct[1] + " Millimeters:" + correct[2] + "");
//		System.out.println("");
		return correct;
	}

	//Method to display the same value as meter, centimeter and millimeter
	static double[] unitDisplay(double iMeter, double iCMeter, double iMMeter) {
		double[] unitDisplay = new double[3];
		unitDisplay[0] = iMeter + iCMeter / 100 + iMMeter / 1000;
		unitDisplay[1] = iMeter * 100 + iCMeter + iMMeter / 10;
		unitDisplay[2] = iMeter * 1000 + iCMeter * 10 + iMMeter;
		System.out.println("Input\t\t Meter:" + iMeter + "\t centimeter:" + iCMeter + "\t\t millimeter:" + iMMeter);
		System.out.println("Unit Display:\t Meter:" + unitDisplay[0] + "\t centimeter:" + unitDisplay[1] + "\t millimeter:" + unitDisplay[2] + "");
		return unitDisplay;
	}

	//to add and subtract objects, 
	void add(McmLengthAndras iObj1) {
		System.out.println("\nAdd() method called.");
		System.out.println("Obj A: " + this);
		System.out.println("Obj B: " + iObj1);
		System.out.println("Add #" + this.objectIdentifier + " + #" + iObj1.objectIdentifier + "");
		System.out.println("Result to be converted before creating new object.");
		int conversed[] = conversion(this.getMeters() + iObj1.getMeters(), this.getCentimeters() + iObj1.getCentimeters(), this.getMillimeters() + iObj1.getMillimeters());
		McmLengthAndras result = new McmLengthAndras(conversed[0], conversed[1], conversed[2]);
		System.out.println("Result of Add() method: " + result);
	}

	void substract(McmLengthAndras iObj1) {
		System.out.println("\nsubstract() method called.");
		System.out.println("Obj A: " + this);
		System.out.println("Obj B: " + iObj1);
		System.out.println("Add #" + this.objectIdentifier + " - #" + iObj1.objectIdentifier + "");
		System.out.println("Result to be converted before creating new object.");
		int conversed[] = conversion(this.getMeters() - iObj1.getMeters(), this.getCentimeters() - iObj1.getCentimeters(), this.getMillimeters() - iObj1.getMillimeters());
		McmLengthAndras result = new McmLengthAndras(conversed[0], conversed[1], conversed[2]);
		System.out.println("Result of Substract() method: " + result);
	}

	//to multiply and divide an object by an integer value, 
	void multiply(int multiplier) {
		System.out.println("\nmultiply() method called.");
		System.out.println("Multiply #" + this.objectIdentifier + "with " + multiplier);
		System.out.println(this);
		System.out.println("Result to be converted before creating new object.");
		//all parameters needs to be multiplied by the integer
		int conversed[] = conversion(this.getMeters() * multiplier, this.getCentimeters() * multiplier, this.getMillimeters() * multiplier);
		McmLengthAndras result = new McmLengthAndras(conversed[0], conversed[1], conversed[2]);
		System.out.println("Result object of multiply() method: " + result);
	}

	void divide(int divider) {
		System.out.println("\ndivider() method called.");
		System.out.println("Divide #" + this.objectIdentifier + "with " + divider);
		System.out.println(this);
		if (divider != 0) {
			System.out.println("Result to be converted before creating new object.");

			//	int conversed[] = conversion(this.meters / divider, this.centimeters / divider, this.millimeters / divider);
			//	McmLengthAndras result = new McmLengthAndras(conversed[0], conversed[1], conversed[2]);
			McmLengthAndras result = new McmLengthAndras((this.getMeters() * 1000 + this.getCentimeters() * 10 + this.getMillimeters()) / divider);
			System.out.println("Result of divide() method: " + result);
		} else {
			System.out.println("Nullával ne ossz!");
		}
	}

	//to calculate an area resulting from the product of two objects, 
	static void areacalc(McmLengthAndras obj1, McmLengthAndras obj2) {
		System.out.println("Obj A:" + obj1);
		System.out.println("Obj B:" + obj2);
		//mi van, ha null valamelyik érték, de a másik nem? a szorzat nulla lesz. Ezért akkor helyettesítsük egyre.
		int obj1Meter = obj1.getMeters() == 0 && obj2.getMeters() != 0 ? 1 : obj1.getMeters();
		int obj2Meter = obj2.getMeters() == 0 && obj1.getMeters() != 0 ? 1 : obj2.getMeters();
		int obj1Centi = obj1.getCentimeters() == 0 && obj2.getCentimeters() != 0 ? 1 : obj1.getCentimeters();
		int obj2Centi = obj2.getCentimeters() == 0 && obj1.getCentimeters() != 0 ? 1 : obj2.getCentimeters();
		int obj1Milli = obj1.getMillimeters() == 0 && obj2.getMillimeters() != 0 ? 1 : obj1.getMillimeters();
		int obj2Milli = obj2.getMillimeters() == 0 && obj1.getMillimeters() != 0 ? 1 : obj2.getMillimeters();
		int conversed[] = conversion(obj1Meter * obj2Meter, obj1Centi * obj2Centi, obj1Milli * obj2Milli);
//		System.out.println("Area is meters:" + conversed[0] + " centimeters:" + conversed[1] + " millimeters:" + conversed[2] + "");
		double[] unitDisplayed = unitDisplay(conversed[0], conversed[1], conversed[2]);
//		System.out.println("Result displayed for each unit:\n"
//				+ "Square Meters:" + unitDisplayed[0]
//				+ "\nSquare Centimeters:" + unitDisplayed[1]
//				+ "\nSquare Millimeteres:" + unitDisplayed[2]);
		McmLengthAndras area = new McmLengthAndras(
				(obj1.getMeters() * 1000 + obj1.getCentimeters() * 10 + obj1.getMillimeters())
				* (obj2.getMeters() * 1000 + obj2.getCentimeters() * 10 + obj2.getMillimeters())
		);
		System.out.println("Area object: " + area);
		double[] areaDisplayed = unitDisplay(area.getMeters(), area.getCentimeters(), area.getMillimeters());

		System.out.println("Result displayed for each unit:\n"
				+ "Square Meters:" + areaDisplayed[0]
				+ "\nSquare Centimeters:" + areaDisplayed[1]
				+ "\nSquare Millimeteres:" + areaDisplayed[2]);
	}

	//Ez nem egy objektumot ad vissza, csak simán a területet, mméterben
	public int areaObj(McmLengthAndras o1) {

		return (
				(getMeters() * 1000 + getCentimeters() * 10 + getMillimeters())
				* (o1.getMeters() * 1000 + o1.getCentimeters() * 10 + o1.getMillimeters())
		);
	}

	//Compare objects
	static void compare(McmLengthAndras iObj1, McmLengthAndras iObj2) {
		/*
		double[] objA = unitDisplay(iObj1.meters, iObj1.centimeters, iObj1.millimeters);
		double[] objB = unitDisplay(iObj2.meters, iObj2.centimeters, iObj2.millimeters);
		System.out.println("objA --> meter:" + objA[0] + "\t centi:" + objA[1] + "\tmilli:" + objA[2]);
		System.out.println("objB --> meter:" + objB[0] + "\t centi:" + objB[1] + "\tmilli:" + objB[2]);
		System.out.println("Diff --> meter:" + (objA[0] - objB[0]) + "\t centi:" + (objA[1] - objB[1]) + "\tmilli:" + (objA[2] - objB[2]));
		 */
		boolean compare = iObj1.toMM() > iObj2.toMM();
		boolean equals = iObj1.toMM() == iObj2.toMM();
		System.out.println(iObj1 + " toMM() = " + iObj1.toMM() + "\n" + iObj2 + " toMM() = " + iObj2.toMM() + "\n" + (equals ? "Egyforma" : (compare ? "Obj1 nagyobb" : "Obj2 nagyobb")));
	}

	//Idea stolen from Horton solution, this method to convert lenght to MM for calculations
	public int toMM() {
		return this.getMeters() * 1000 + this.getCentimeters() * 10 + this.getMillimeters();
	}

	@Override
	public String toString() {
//		return "Object #" + objectIdentifier + " Properties: \t Meters:" + meters + " \tCentimeters:" + centimeters + " \t\tMillimeters:" + millimeters + ".";
		return "Object #" + objectIdentifier + " Properties: \t " + getMeters() + "m " + getCentimeters() + "cm " + getMillimeters() + "mm.";
	}

	/**
	 * @return the meters
	 */
	public int getMeters() {
		return meters;
	}

	/**
	 * @return the centimeters
	 */
	public int getCentimeters() {
		return centimeters;
	}

	/**
	 * @return the millimeters
	 */
	public int getMillimeters() {
		return millimeters;
	}

}
