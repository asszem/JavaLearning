package DateAndTime;

import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;

public class GregorianCalendar_Collection {

	static Locale hunLocale = new Locale("hu", "HU");

	public static GregorianCalendar getCalendar() {
		GregorianCalendar calendar;
		// set for current instant in time
		calendar = new GregorianCalendar();
		// day:1-31, month:0-11!!
		calendar = new GregorianCalendar(2000, 0, 1, 12, 00, 00);
		// Using enums in Calendar class
		calendar = new GregorianCalendar(1967, Calendar.MARCH, 10);
		// Using timezoneID
		String[] timeZones = java.util.TimeZone.getAvailableIDs();
		calendar = new GregorianCalendar(TimeZone.getTimeZone("America/Chicago"));
		// TimezoneID and locale
		calendar = new GregorianCalendar(TimeZone.getTimeZone("America/Chicago"), Locale.US);
		// Automatically sets time zone to Locale
		calendar = new GregorianCalendar(Locale.UK);
		return calendar;
	}

	// Formatter: YYYY.MM.DD HH:MM according to HU locale
	public static DateFormat getFormatter() {
		DateFormat formatter = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT,
				new Locale("hu", "HU"));
		DateFormat formatter2 = DateFormat.getTimeInstance(DateFormat.SHORT, new Locale("hu", "HU"));
		return formatter;
	}
	public static void displayFormattedCalendar(GregorianCalendar calendar) {
		DateFormat formatter = getFormatter();
		System.out.println(formatter.format(calendar.getTime()));
	}
	
	// Month starts from 0! Pass by value, but object reference is passed so the referenced object is altered
	public static void setCalendarValues(GregorianCalendar calendar, int year, int month, int day) {
		calendar.set(year, month, day);
	}
	// Updates all fields accordingly (1999.12.31 + 1 day = 2000.01.01
	public static void addCalendarValues(GregorianCalendar calendar, int valueToAdd) {
		// see Javadoc for available fields in Calendar.FIELD constant
		calendar.add(Calendar.DATE, valueToAdd);
	}
	// Updates only the selected field (1999.12.31 roll 1 day = 1999.12.01
	public static void rollCalendarValues(GregorianCalendar calendar, int valueToRoll) {
		calendar.roll(Calendar.DATE, valueToRoll);
	}
	public static boolean compareCalendarValues(GregorianCalendar comparedFrom, GregorianCalendar comparedTo) {
		boolean isBefore = comparedFrom.before(comparedTo);
		boolean isAfter = comparedFrom.after(comparedTo);
		int comparison = comparedFrom.compareTo(comparedTo); // -1 0 1
		return isBefore;
	}

	//Get a String array of WeekdayNames based on given locale using DateFormatSymbols
	public static String[] getWeekdayNames(Locale locale) {
		DateFormatSymbols dateFormatSymbols = new DateFormatSymbols(locale);
		String[] weekdayNames = dateFormatSymbols.getWeekdays();
		String[] monthNames = dateFormatSymbols.getMonths();
		return weekdayNames;
	}
	//Get the DAY name of a given calendar object on given locale using DateFormatSymbols
	public static String getWeekdayName(GregorianCalendar calendar, Locale locale) {
		String[] weekdayNames = new DateFormatSymbols(locale).getWeekdays();
		return weekdayNames[calendar.get(Calendar.DAY_OF_WEEK)];
	}


	//Get a map of ALL locale specific NAMES of a field (MONTH, DAY_OF_WEEK) in the provided DateFormat
	public static Map<String, Integer> getCalendarDisplayNames(GregorianCalendar calendar, Locale locale) {
		Map<String, Integer> map = calendar.getDisplayNames(Calendar.MONTH, Calendar.LONG, locale);
		for (String key:map.keySet()){
			System.out.println(key+" ("+map.get(key)+")");
		}
		map = calendar.getDisplayNames(Calendar.DAY_OF_WEEK, Calendar.LONG, locale);
		for (String key:map.keySet()){
			System.out.println(key+" ("+map.get(key)+")");
		}
		return map;
	}
	//Get the MONTH name based on locale of given calendar object 
	public static String getMonthNameLocaleSpecific(GregorianCalendar calendar, Locale locale){
		Map<String, Integer> map = calendar.getDisplayNames(Calendar.MONTH, Calendar.LONG, hunLocale);
		for (String key : map.keySet()) {
			if (map.get(key) == calendar.get(Calendar.MONTH)) {
			return key;
			}
		}
		return null;
	}
	//Get the DAY name based on locale of given calendar object
	public static String getDayNameLocaleSpecific(GregorianCalendar calendar, Locale locale){
		Map<String, Integer> map = calendar.getDisplayNames(Calendar.DAY_OF_WEEK, Calendar.LONG, hunLocale);
		for (String key : map.keySet()) {
			if (map.get(key) == calendar.get(Calendar.DAY_OF_WEEK)) {
			return key;
			}
		}
		return null;
	}

	public static void main(String[] args) {
		// Run debug to track field changes!
		GregorianCalendar calendar = new GregorianCalendar();
		System.out.println(getWeekdayName(calendar, hunLocale));
		System.out.println(getWeekdayName(calendar, Locale.GERMAN));
		getCalendarDisplayNames(calendar, hunLocale);
		getCalendarDisplayNames(calendar, Locale.GERMAN);
		setCalendarValues(calendar, 1999, 11, 31);
		addCalendarValues(calendar, 1);
		addCalendarValues(calendar, -1);
		rollCalendarValues(calendar, 1);
		displayFormattedCalendar(calendar);
	}
}
