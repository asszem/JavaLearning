package DateAndTime;

import java.util.Locale;
import java.util.Date;
import java.text.DateFormat;

public class DateFormatSkeleton {

	// enumeration for DateFormat constants
	public enum DateFormatEnum {
		FULL, LONG, MEDIUM, SHORT
	}

	public static void displayISOCodes() {
		String[] languages = java.util.Locale.getISOLanguages();
		String[] countryCodes = java.util.Locale.getISOCountries();
		int counter = 1;
		System.out.println("Language ISO codes:");
		for (String language : languages) {
			System.out.print(language + " ");
			if (counter++ % 15 == 0)
				System.out.println();
		}
		System.out.println("\nCountry ISO codes:");
		counter = 1;
		for (String countryCode : countryCodes) {
			System.out.print(countryCode + " ");
			if (counter++ % 15 == 0)
				System.out.println();
		}
	}

	public static void displayAvailableLocales() {
		Locale[] availableLocales = DateFormat.getAvailableLocales();
		for (Locale locale : availableLocales) {
			System.out.println(locale);
		}
		System.out.println("Available locales number: " + availableLocales.length);
	}

	public static void displayDate(Locale locale) {
		System.out.println("Locale: " + locale.getDisplayCountry());
		// Iterate through the DateFormatEnum
		for (DateFormatEnum currentFormat : DateFormatEnum.values()) {
			// ordinal() is required because .getDateTimeInstance expects an int
			// arg.
			DateFormat formatter = DateFormat.getDateTimeInstance(currentFormat.ordinal(), currentFormat.ordinal(),
					locale);
			System.out.println(currentFormat + "\t " + formatter.format(new Date()));
		}
	}

	public static void main(String[] args) {
		// displayISOCodes();
		// displayAvailableLocales();

		// Get a Locale object
		Locale hunLocale = new Locale("hu", "HU");

		// Display all DateFormat options for a locale
		displayDate(hunLocale);
		displayDate(Locale.CHINA);
		displayDate(Locale.US);

		// Get today's date
		Date todayDateObject = new Date(); // Object for now – today’s date

		// Print Date or Time only for given locale
		DateFormat formatterUS = DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.US);
		DateFormat formatterHUN = DateFormat.getTimeInstance(DateFormat.FULL, hunLocale);
		System.out.println("Date US:\t" +formatterUS.format(todayDateObject));
		System.out.println("Time HU:\t" +formatterHUN.format(todayDateObject));
	}
}
