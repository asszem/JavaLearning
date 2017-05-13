package DateAndTime;

import java.util.Locale;
import java.util.Date;
import java.text.DateFormat;

public class DateFormat_Collection {

	// enumeration for DateFormat constants
	public enum DateFormatStylesEnum {
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

	public static void displayAvailableTimeZoneIDs() {
		String[] timeZones = java.util.TimeZone.getAvailableIDs();
		for (String timeZone : timeZones) {
			System.out.println(timeZone);
		}
	}

	// SHORT, MEDIUM, LONG, FULL
	public static void displayDateFormatStyles(Locale locale, Date displayDate) {
		System.out.println("Locale: " + locale.getDisplayCountry());
		// Iterate through the DateFormatStylesEnum
		for (DateFormatStylesEnum currentFormatStyle : DateFormatStylesEnum.values()) {
			// ordinal() is required because .getDateTimeInstance expects an int arg.
			DateFormat formatter = DateFormat.getDateTimeInstance(currentFormatStyle.ordinal(),
					currentFormatStyle.ordinal(), locale);
			System.out.println(currentFormatStyle + "\t " + formatter.format(displayDate));
		}
	}

	//Input string MUST be the same format as defined in DateFormat style. Eg: 2011.11.11. 12:21
	public static Date parseDateFromString(String input, DateFormatStylesEnum style, Locale locale) {
		Date resultDate;
		DateFormat formatter = DateFormat.getDateTimeInstance(style.ordinal(), style.ordinal(), locale);
		try {
			resultDate = formatter.parse(input);
			return resultDate;
		} catch (java.text.ParseException e) {
			System.out.println("Wrong date format!" + e);
			return null;
		}
	}

	public static void main(String[] args) {
		// System.out.println(“The Date string is: “ + fmt.format(aDate));
		// displayISOCodes();
		// displayAvailableLocales();
		// displayAvailableTimeZones();

		// Get a Locale object
		Locale hunLocale = new Locale("hu", "HU");

		// Get today's date
		Date todayDateObject = new Date(); // Object for now – today’s date

		// Display all DateFormat options for a locale
		displayDateFormatStyles(hunLocale, todayDateObject);
		displayDateFormatStyles(Locale.CHINA, todayDateObject);
		displayDateFormatStyles(Locale.US, todayDateObject);

		// Print Date or Time only for given locale
		DateFormat formatterUS = DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.US);
		DateFormat formatterHUN = DateFormat.getTimeInstance(DateFormat.FULL, hunLocale);
		System.out.println("Date US:\t" + formatterUS.format(todayDateObject));
		System.out.println("Time HU:\t" + formatterHUN.format(todayDateObject));

		// Parse string according to locale
		String parseString = "2000.01.01. 10:10";
		Date parsedDate = parseDateFromString(parseString, DateFormatStylesEnum.SHORT, hunLocale);
		System.out.println("Pares string: " + parseString);
		System.out.println("Parsed date: " + parsedDate);

	}
}
