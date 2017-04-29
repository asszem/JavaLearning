/*
Write a program to generate 20 random values of type double between 50 and +50 and use the
printf() method for System.out to display them with two decimal places in the following form:
1) +35.93 2) -46.94 3) +42.27 4) +32.09 5) +29.21
6) +13.87 7) -47.87 8) +30.67 9) -25.20 10) +29.67
11) +48.62 12) +6.70 13) +28.97 14) -41.64 15) +16.67
16) +17.01 17) +9.62 18) -15.21 19) +7.46 20) +4.09

 */
package HortonExercises.Ch08.Ex3;

import java.util.Locale;
import java.util.Random;
import java.util.Formatter;

/**
 *
 * @author Andras Olah (olahandras78@gmail.com)
 */
public class Ex3_Andras {

	public static void main(String[] args) {
		Random rnd = new Random();
		String signChar = "";
		Locale hungaryLocale = new Locale("hu", "HU");
		StringBuilder str = new StringBuilder();
		Formatter formazva = new Formatter(str);
		for (int i = 1; i < 21; i++) {
//			double rndGenerated = -50.0 + Math.random() * 100;
			double rndGenerated = -50 + rnd.nextDouble() * 100;
			formazva.format(hungaryLocale, "%4d) %+07.2f||", i, rndGenerated);
			System.out.printf(hungaryLocale, "%3d) %+06.2f ", i, rndGenerated);
			if (i % 5 == 0) {
				System.out.println("");
			formazva.format(hungaryLocale, "%n");
			}
		}
		System.out.println("");
		System.out.println(formazva);
	}
}
