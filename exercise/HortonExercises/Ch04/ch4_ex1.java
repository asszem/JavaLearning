/*
Create an array of String variables and initialize the array with the names of the months from January
to December.
Create an array containing 12 random decimal values between 0.0 and 100.0. 
Display the names of each month along with the corresponding decimal value. 
Calculate and display the average of the 12 decimal values
 */
package HortonExercises.Ch04;

public class ch4_ex1 {

	public static void main(String[] args) {
		String[] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
		double[] random = new double[12];
		double summary=0;
		for (int i = 0; i < random.length; i++) {
			random[i]=100*Math.random();
			summary +=random[i];
		}
		for (int i = 0; i < months.length; i++) {
			System.out.println(months[i]+" "+random[i]);
		}
		System.out.println("Average:"+summary/12);

//		    // Initialize the months' array with names of the months of the year:
//    String[] monthNames = {
//                           "January"  , "February", "March"   , "April",
//                           "May"      , "June"    , "July"    , "August",
//                           "September","October"  , "November", "December"
//                         };
//
//    double average = 0.0;           // A variable used to find the average.
//
//    // Declare an array which can contain the 12 random numbers:
//    double[] numbers = new double[12];
//
//    // Fill in the numbers' array with 12 numbers between 0.0 and 100.0, and print it out,
//    // alongside each month. Also keep track of the sum of those numbers elements.
//
//    for(int i = 0 ; i  < numbers.length ; ++i) {
//      numbers[i] = 100.0*Math.random();
//      System.out.println("In Month " + monthNames[i] + " number is " + numbers[i]);
//      average += numbers[i];
//    }
//    average /= numbers.length;
//
////    System.out.println("\nAverage of values in numbers is " + average);
	}
}
