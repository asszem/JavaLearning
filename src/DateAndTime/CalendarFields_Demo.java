package DateAndTime;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.Map;

public class CalendarFields_Demo {

	public static Locale hunLocale = new Locale("hu", "HU");

	public static void main(String[] args) {
		GregorianCalendar calendar = new GregorianCalendar();
		System.out.print("getTime():\t\t");
		System.out.println(calendar.getTime());
		System.out.print("YEAR:\t\t\t");
		System.out.println(calendar.get(Calendar.YEAR)); // Month starts from 0!
		System.out.print("MONTH number:\t\t");
		System.out.println(calendar.get(Calendar.MONTH) + 1); // Month starts from 0!
		System.out.print("Month name:\t\t");
		System.out.println(GregorianCalendar_Collection.getMonthNameLocaleSpecific(calendar, hunLocale)); 
		System.out.print("DAY_OF_MONTH (Date):\t");
		System.out.println(calendar.get(Calendar.DATE)); // Same as DAY_OF_MONTH
		System.out.print("DAY_OF_WEEK number:\t");
		System.out.println(calendar.get(Calendar.DAY_OF_WEEK));
		System.out.print("Day of week name:\t");
		System.out.println(GregorianCalendar_Collection.getDayNameLocaleSpecific(calendar, hunLocale)); 
		System.out.print("DAY_OF_WEEK_IN_MONTH:\t");
		System.out.println(calendar.get(Calendar.DAY_OF_WEEK_IN_MONTH)); // Ordinal (sorrendi) day within the current month
		System.out.print("DAY_OF_YEAR:\t\t");
		System.out.println(calendar.get(Calendar.DAY_OF_YEAR));
		System.out.print("WEEK_OF_YEAR:\t\t");
		System.out.println(calendar.get(Calendar.WEEK_OF_YEAR));
		System.out.print("HOUR_OF_DAY:\t\t");
		System.out.println(calendar.get(Calendar.HOUR_OF_DAY));
		System.out.print("MINUTE:\t\t\t");
		System.out.println(calendar.get(Calendar.MINUTE));
		System.out.print("AM:\t\t\t");
		System.out.println(calendar.get(Calendar.AM));
		System.out.print("AM/PM:\t\t\t");
		System.out.println(calendar.get(Calendar.AM_PM));
		System.out.print("ALL_STYLES:\t\t");
		System.out.println(calendar.get(Calendar.ALL_STYLES));
	}
}
