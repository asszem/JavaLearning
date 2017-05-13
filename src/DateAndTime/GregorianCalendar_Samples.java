package DateAndTime;

import static java.util.Calendar.*;

import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

public class GregorianCalendar_Samples {

	public static Locale hunLocale = new Locale("hu", "HU");

	public static void displayFormattedCalendar(GregorianCalendar calendar) {

		System.out.println(
				DateFormat.getDateInstance(DateFormat.SHORT, new Locale("hu", "HU")).format(calendar.getTime()));
	}

	public static void main(String[] args) {
		DateFormatSymbols dateFormatSymbols = new DateFormatSymbols();
		String[] weekdayNames = dateFormatSymbols.getWeekdays();
		String[] monthNames = dateFormatSymbols.getMonths();

		GregorianCalendar calendar;
		// set for current instant in time
		calendar = new GregorianCalendar();
		System.out.println(calendar.getTime());
		// day:1-31, month:0-11!!
		calendar = new GregorianCalendar(2000, 0, 1, 12, 00, 00);
		System.out.println(calendar.getTime());
		// Using enums in Calendar class
		calendar = new GregorianCalendar(1967, Calendar.MARCH, 10);
		System.out.println(calendar.getTime());
		// Using timezoneID
		calendar = new GregorianCalendar(TimeZone.getTimeZone("America/Chicago"));
		System.out.println(calendar.getTime());
		// timezoneID and locale
		calendar = new GregorianCalendar(TimeZone.getTimeZone("America/Chicago"), Locale.US);
		System.out.println(calendar.getTime());
		// Automatically sets time zone to Locale }
		calendar = new GregorianCalendar(Locale.UK);
		System.out.println(calendar.getTime());

		// Get Calendar values
		System.out.println("\nGet Calendar values\n");
		calendar = new GregorianCalendar();
		System.out.println(calendar.getTime()); // Gets the full data, not just
												// the hours:minutes time
		DateFormat formatter = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT,
				new Locale("hu", "HU"));
		System.out.println(formatter.format(calendar.getTime()));
		DateFormat formatter2 = DateFormat.getTimeInstance(DateFormat.SHORT, new Locale("hu", "HU"));
		System.out.println(formatter2.format(calendar.getTime()));
		System.out.print("Day of week: ");
		System.out.println(calendar.get(DAY_OF_WEEK)); // Week starts at Sunday,
														// Friday is 6

		// Print month and weekday name
		System.out.println("Today is: " + weekdayNames[calendar.get(DAY_OF_WEEK)]);
		System.out.println("Month is: " + monthNames[calendar.get(MONTH)]);

		// Update calendar values - .add() vs. roll()
		calendar.clear();
		calendar.set(2000, 0, 31); // 2000-01-31
		displayFormattedCalendar(calendar);
		calendar.add(YEAR, 17);
		displayFormattedCalendar(calendar);
		calendar.roll(DAY_OF_MONTH, false); // Rolls back one day, if EOM
											// reached, starts from the END
		displayFormattedCalendar(calendar);
		calendar.roll(DAY_OF_MONTH, true); // Rolls forward one day
		displayFormattedCalendar(calendar);
		calendar.roll(DAY_OF_MONTH, true); // Rolls forward one day, if EOM
											// reached, starts from the BEGINNIG
		displayFormattedCalendar(calendar);
		calendar.add(DAY_OF_MONTH, -1); // Updates every field in Calendar
										// object if changed
		displayFormattedCalendar(calendar);
		calendar.roll(Calendar.DAY_OF_MONTH, +10); // rolls 10 days forward,
													// DOESNT! change other
													// fields
		displayFormattedCalendar(calendar);

		// Compare calendar values
		GregorianCalendar calA = new GregorianCalendar();
		GregorianCalendar calB = new GregorianCalendar();
		calB.roll(DAY_OF_MONTH, -1);
		System.out.println(calA.equals(calA));
		System.out.println(calA.equals(calB));
		System.out.print("Cal A: ");
		displayFormattedCalendar(calA);
		System.out.print("Cal B: ");
		displayFormattedCalendar(calB);
		System.out.println("CalA before CalB: " + calA.before(calB));
		System.out.println("CalA after CalB: " + calA.after(calB));
		System.out.println("CalA compareTo CalB: " + calA.compareTo(calB));

		// Display month/day names according to different locales
		GregorianCalendar_Collection.getCalendarDisplayNames(calendar, hunLocale);
		GregorianCalendar_Collection.getCalendarDisplayNames(calendar, Locale.US);
		GregorianCalendar_Collection.getCalendarDisplayNames(calendar, Locale.FRENCH);
	}
}
