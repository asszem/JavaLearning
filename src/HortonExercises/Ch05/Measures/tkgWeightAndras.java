/*
Define a class, tkgWeight, to represent a weight in tons, kilograms, and grams, and include a similar range
of methods and constructors as the previous example. Demonstrate this class by creating and combining
some class objects

 */
package HortonExercises.Ch05.Measures;

/**
 *
 * @author Andras Olah (olahandras78@gmail.com)
 */
public class tkgWeightAndras {
//Variables

	private int tons;
	private int kilograms;
	private int grams;
	private int objID;
	public final static int GRperTON = 1000000;
	public final static int GRperKG = 1000;
	public static int objCount;

//Constructors
	public tkgWeightAndras(int inputT, int inputK, int inputG) {
		this(inputT * GRperTON + inputK * GRperKG + inputG);
//		objID = objCount++;  //Azért nem kell, mert a this által meghívott konstruktor elvégzi ezt.
	}

	public tkgWeightAndras(int inputGrams) {
		tons = inputGrams / GRperTON; //a törtrészeket úgyis levágja
		kilograms = (inputGrams - GRperTON * tons) / GRperKG; //kivonja a tonnákat. Ha negatív szám lesz, akkor az osztás eredménye nulla
		grams = (inputGrams - GRperTON * tons) - kilograms * GRperKG; //kivonja a tonnákat, majd kivonja a kilókat is. 
		objID = objCount++;

		/*
	YEEES, le tudtam utánozni anélkül, hogy másoltam volna.	
    meters = mm/MM_PER_M;
    centimeters = (mm - meters*MM_PER_M)/MM_PER_CM;
    millimeters = mm - meters*MM_PER_M - centimeters*MM_PER_CM;
		
		 */
	}

	//Constructor that accepts double as Kilograms
	public tkgWeightAndras(double inputKg) {
		this ((int)Math.round(inputKg*1000));
		System.out.println("double");
	}

	public tkgWeightAndras() {
		tons = kilograms = grams = 0;
		objID = objCount++;
	}

//Methods
	public tkgWeightAndras add(tkgWeightAndras o1) {
		return new tkgWeightAndras(o1.toGrams() + this.toGrams());
	}

	public tkgWeightAndras substract(tkgWeightAndras o1) {
		return new tkgWeightAndras(this.toGrams() - o1.toGrams());
	}

	public tkgWeightAndras multiply(int multiplier) {
		return new tkgWeightAndras(this.toGrams() * multiplier);
	}

	public tkgWeightAndras divide(int divider) {
		return new tkgWeightAndras(this.toGrams() / divider);
	}

	public int toGrams() {
		return tons * GRperTON + kilograms * GRperKG + grams;
	}

	@Override
	public String toString() {
		return "Obj #" + this.objID + " is " + this.tons + "t " + this.kilograms + "kg " + this.grams + "g";
	}
}
