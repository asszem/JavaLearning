/*Put both the previous classes in a package called Measures. 

Import this package into a program that 
calculates and displays the total weight of the following: 
	200 carpets — size: 4 meters by 2 meters 9 centimeters, that weigh 1.25 kilograms per square meter; 
	60 carpets — size: 3 meters 57 centimeters by 5 meters, that weigh 1.05 kilograms per square meter

 */
package HortonExercises.Ch05.Measures;

/**
 *
 * @author Andras Olah (olahandras78@gmail.com)
 */
public class CarpetsAndras {

	final static double SQRMMtoSQRM = 1000000;  //1sqmm= 0.000001 sqm
	McmLengthAndras carpetWidth;
	McmLengthAndras carpetHeight;
	double carpetWeightKgPerSQM;
	int numberOfCarpets;
	int carpetAreaInMillimeters;
	double carpetAreaInMeters;
	tkgWeightAndras singleCarpetWeight;
	tkgWeightAndras totalWeight;

	public void calculateArea() {
//		carpetAreaInMillimeters = carpetWidth.calcAreaInMillimeters(carpetHeight);
//		carpetAreaInMeters=(double) (carpetAreaInMillimeters/SQRMMtoSQRM);
		carpetAreaInMeters = carpetWidth.calcAreaInMeters(carpetHeight);
	}

	public void calculateSingleCarpetWeight() {
		singleCarpetWeight = new tkgWeightAndras(carpetAreaInMeters * carpetWeightKgPerSQM);
	}

	public void calculateTotalWeight() {
		//There are two working methods, they basically do the same

//Method 1
		//convert to gramm
		final int TON_TO_GRAM = 1000000;
		final int KG_TO_GRAM = 1000;
		int oneCarpetInGrams = singleCarpetWeight.getTons() * TON_TO_GRAM + singleCarpetWeight.getKilograms() * KG_TO_GRAM + singleCarpetWeight.getGrams();
		//multiply with total number of carpets
		int totalGramms = oneCarpetInGrams * numberOfCarpets;
		//create new weight based on total gramms
		//totalWeight=new tkgWeightAndras(totalGramms);

//Method2
		totalWeight = new tkgWeightAndras(singleCarpetWeight.toGrams() * numberOfCarpets);

//This was the root cause because it calculated only the kilos, not the grams
		//totalWeight=new tkgWeightAndras(numberOfCarpets * (carpetAreaInMeters*carpetWeightKgPerSQM));
	}

	public static void main(String[] args) {
		//<editor-fold desc="Original main procedure">
/*
		McmLengthAndras carpet1 = new McmLengthAndras(4, 0, 0);
		McmLengthAndras carpet1B = new McmLengthAndras(2, 9, 0);
		McmLengthAndras carpet2 = new McmLengthAndras(3, 57, 0);
		McmLengthAndras carpet2B = new McmLengthAndras(5, 0, 0);
		double carpet1WeightPerSQM = 1.25; //KG
		double carpet2WeightPerSQM = 1.05;
		int numCarpet1 = 200;
		int numCarpet2 = 60;

//1. calculate the square meters of carpet
		double carpet1AreaSqMeter = (double) (carpet1.calcAreaInMillimeters(carpet1B) / SQRMMtoSQRM);
		double carpet2AreaSqMeter = (double) (carpet2.calcAreaInMillimeters(carpet2B) / SQRMMtoSQRM);
		System.out.println("Carpet 1: " + carpet1AreaSqMeter + "sq meter \nCarpet 2: " + carpet2AreaSqMeter + "sq meter");
//2. Calculate the actual weight of 1 carpet in Kg
		double carpet1WeightOneCarpet = carpet1AreaSqMeter * carpet1WeightPerSQM;
		double carpet2WeightOneCarpet = carpet2AreaSqMeter * carpet2WeightPerSQM;
		System.out.println("Carpet 1 weight:" + carpet1WeightOneCarpet + "kg\nCarpet 2 weight:" + carpet2WeightOneCarpet + "kg");
//3. Calculate the total weight of all 200 and 60 carpets in KGs
		double totalCarpet1Weight = carpet1WeightOneCarpet * numCarpet1;
		double totalCarpet2Weight = carpet2WeightOneCarpet * numCarpet2;
		System.out.println("Carpet 1 total weight:" + totalCarpet1Weight + "kg\nCarpet2 total weight:" + totalCarpet2Weight + " kg");
//4. Display the total weight results in T KG G format
		tkgWeightAndras carpet1TotalDisplay = new tkgWeightAndras(0, 0, (int) totalCarpet1Weight * 1000);
		tkgWeightAndras carpet2TotalDisplay = new tkgWeightAndras(totalCarpet2Weight); // mert megírtam a double értéket fogadó konstruktort is
		System.out.println(carpet1TotalDisplay);
		System.out.println(carpet2TotalDisplay);
		/*
		Carpet 1: Size = 4m 0cm 0mm by 2m 9cm 0mm
          Weight per sq. m. = 1.25
          Area = 8.36 sq. m.
          Weight = 0t 10kg 450g
          Weight of 200 carpets = 2t 90kg 0g

		Carpet 2: Size = 3m 57cm 0mm by 5m 0cm 0mm
          Weight per sq. m. = 1.05
          Area = 17.849999999999998 sq. m.
          Weight = 0t 18kg 743g
          Weight of 60 carpets = 1t 124kg 580g
		 */
		//</editor-fold>
	}
}
