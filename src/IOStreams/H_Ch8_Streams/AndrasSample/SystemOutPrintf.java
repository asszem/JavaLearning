package IOStreams.H_Ch8_Streams.AndrasSample;

import java.util.Locale;

/*
Use this tutorial, it has everything!
https://docs.oracle.com/javase/tutorial/java/data/numberformat.html

Locale hungaryLocale = new Locale("hu", "HU");
System.out.printf(HungaryLocale, "Szöveg %d%n", arguments);

%[argument_index$][flags][width][.precision]conversion

%f - floating (DOUBLE!, tört)
%g - scientific notation
%d - decimal INTEGER (egész) (o - octal, x - hexadecimal)
%s - string
%c - character
%b - boolean
%n - new line

ARGUMENT INDEX$
	This is a decimal integer that identifies one of the arguments that follow the format string by its sequence number, where “1$” refers to the first argument

FLAGS
	‘-’ - left justified
	‘+’ forces a sign to be output for numerical values.
	‘0’ forces numerical values to be zero-padded.

Other sources:
https://docs.oracle.com/javase/tutorial/java/data/numberformat.html
 */
/**
 *
 * @author Andras Olah (olahandras78@gmail.com)
 */
public class SystemOutPrintf {

	public static void main(String[] args) {
		int a = 5, b = 15, c = 255;
		double x = 27.5, y = 33.75;
		boolean b1 = true;
		boolean b2 = false;
		String duma = "Helló!";
		char charVariable = 'A';
		System.out.printf("x = %f (%%f)%ny = %g (%%g)%n", x, y);
		System.out.printf("Boolean b2: %2$b = false (%%2$b) b1: %1$b = true (%%1$b)%n", b1, b2);
		System.out.printf("String %s%n", duma);
		System.out.printf("< teszt: %f (%%f) majd %<g (%%<g) majd %<f (%%<f) %n", x);

		System.out.printf(" a = %d b = %x c = %o%n", a, b, c);
		System.out.printf(" a = %5d b = %5x c = %5o%n", a, b, c);
		System.out.printf(" a = %-5d b = %-5x c = %-5o%n", a, b, c);

		System.out.printf(" x = %2$-15.5f and y = %1$-15.5f%n", y, x);

		int count = 0;
		for (int ch = 'a'; ch <= 'z'; ch++) {
			System.out.printf("Karakter: %1$c (%1$x) ", ch);
			if (++count % 3 == 0) {
				System.out.printf("%n");
			}
		}

		int szam = 1_234_567;
		double szam2 = 1.234_567;
		Locale hungaryLocale = new Locale("hu", "HU");
		System.out.printf("%n%.2f: Default locale\n", 3.1415926535);
		System.out.printf(Locale.GERMANY, "%.2f: Germany locale\n", 3.1415926535);
		System.out.printf(Locale.US, "%.2f: US locale\n", 3.1415926535);
		System.out.printf(hungaryLocale, "%.2f: Hungarian locale\n", 3.1415926535);
		System.out.printf("%nDefault locale: Számok %d és %f%n", szam, szam2);
		System.out.printf(hungaryLocale, "Hungary locale: Számok %-5d és %f%n", szam, szam2);
		System.out.printf(Locale.US, "US locale:      Számok %d és %f%n", szam, szam2);

		System.out.printf("%+09d\n", 452);
		System.out.printf("%09d\n", 452);
		System.out.printf("% 9d\n", 452);
		System.out.printf("%-9d\n", 452);
		System.out.printf("%04d\n", 452); //4 számjegy legyen kiírva


		double xxx = 27.5, yyy = 33.75;
		String outString = String.format("x = %15.2f y = %14.3f", xxx, yyy);
		System.out.println(outString);
		String tesztike = String.format("Ez egy szám: % 13.2f, ez meg egy string%-5.3s",123456.12457,"alma");
		System.out.println(tesztike);

		StringBuffer buf = new StringBuffer();
		java.util.Formatter formatter = new java.util.Formatter(buf);
		double xx = 27.5, yy = 33.75;
		formatter.format("x =  %15.2f y =  % 14.3f", xx, yy);
		System.out.println("Formettar: " + formatter);
		formatter.format("szia");
		System.out.println("Formettar: " + formatter);

	}
}
