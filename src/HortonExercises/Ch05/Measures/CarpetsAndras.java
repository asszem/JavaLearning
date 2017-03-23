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

	public static void main(String[] args) {
//		McmLengthAndras carpet1 = new McmLengthAndras(4, 0, 0);
		McmLengthAndras carpet1 = new McmLengthAndras(4, 0, 0);
		McmLengthAndras carpet1B = new McmLengthAndras(2, 9, 0);
		McmLengthAndras carpet2 = new McmLengthAndras(3, 57, 0);
		McmLengthAndras carpet2B = new McmLengthAndras(5, 0, 0);
		double carpet1WeightPerSQM = 1.25; //KG
		double carpet2WeightPerSQM = 1.05;
		int numCarpet1 = 200;
		int numCarpet2 = 60;
		final double SQRMMtoSQRM = 1000000;  //1sqmm= 0.000001 sqm

//1. calculate the square meters of carpet
		double carpet1AreaSqMeter = (double) (carpet1.areaObj(carpet1B)/SQRMMtoSQRM);
		double carpet2AreaSqMeter = (double) (carpet2.areaObj(carpet2B)/SQRMMtoSQRM);
		System.out.println("Carpet 1: "+carpet1AreaSqMeter+"sq meter \nCarpet 2: "+carpet2AreaSqMeter+"sq meter");
//2. Calculate the actual weight of 1 carpet in Kg
		double carpet1WeightOneCarpet = carpet1AreaSqMeter * carpet1WeightPerSQM;
		double carpet2WeightOneCarpet = carpet2AreaSqMeter * carpet2WeightPerSQM;
		System.out.println("Carpet 1 weight:"+carpet1WeightOneCarpet+"kg\nCarpet 2 weight:"+carpet2WeightOneCarpet+"kg");
//3. Calculate the total weight of all 200 and 60 carpets in KGs
		double totalCarpet1Weight = carpet1WeightOneCarpet * numCarpet1;
		double totalCarpet2Weight = carpet2WeightOneCarpet * numCarpet2;
		System.out.println("Carpet 1 total weight:"+totalCarpet1Weight+"kg\nCarpet2 total weight:"+totalCarpet2Weight+" kg");
//4. Display the total weight results in T KG G format
		tkgWeightAndras carpet1TotalDisplay = new tkgWeightAndras(0,0, (int) totalCarpet1Weight*1000);
		tkgWeightAndras carpet2TotalDisplay= new tkgWeightAndras(totalCarpet2Weight); // mert megírtam a double értéket fogadó konstruktort is
		System.out.println(carpet1TotalDisplay);
		System.out.println(carpet2TotalDisplay);
/*
		Carpet 2: Size = 3m 57cm 0mm by 5m 0cm 0mm
          Weight per sq. m. = 1.05
          Area = 17.849999999999998 sq. m.
          Weight = 0t 18kg 743g
          Weight of 60 carpets = 1t 124kg 580g
		//TODO find out, neki miért 580 és nekem miért 550 jön ki?
		*/
	}
}
