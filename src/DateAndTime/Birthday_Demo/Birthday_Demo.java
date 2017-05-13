package DateAndTime.Birthday_Demo;

import java.util.GregorianCalendar;
import java.text.DateFormatSymbols;
import static java.util.Calendar.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Birthday_Demo {

	public static GregorianCalendar today = new GregorianCalendar(); // Today's date
	public static BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));

	public static int[] getValidatedInput() {
		System.out.println("Enter your birth date as dd mm yyyy: ");
		int year = getYear();
		int month = getMonth();
		int day = getDay(month, year);
		int[] validatedInput = { year, month, day };
		return validatedInput;
	}

	public static int parseInteger(String input) {
		int returnInt = Integer.parseInt(input);
		return returnInt;
	}

	public static int getYear() {
		int year = 0;
		try {
			while (true) {
				System.out.print("Enter year: ");
				String input;
				input = keyboard.readLine();
				year = parseInteger(input);
				if (!isYearValid(year)) {
					System.out.println("Can't born in the future!");
				} else
					break;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return year;
	}

	public static boolean isYearValid(int year) {
		return (year <= today.get(YEAR));
	}

	public static int getMonth() {
		FormattedInput in = new FormattedInput();
		int month = 0;
		try {
			while (true) {
				System.out.print("Enter month: ");
				month = in.readInt();
				// validation
				if (!isMonthValid(month)) {
					System.out.println("Invalid month. Try again.");
				} else
					break;
			}
		} catch (InvalidUserInputException e) {
			System.out.println("Invalid input - terminating...");
			System.exit(1);
		}
		return month;
	}

	public static boolean isMonthValid(int month) {
		return !(month <= 0 || month > 12);
	}

	public static int getDay(int month, int year) {
		FormattedInput in = new FormattedInput();
		int day = 0;
		try {
			while (true) {
				System.out.print("Enter day: ");
				day = in.readInt();
				// validation
				if (!isDayValid(day, month, year)) {
					System.out.println("Invalid day. Try again.");
				} else
					break;
			}
		} catch (InvalidUserInputException e) {
			System.out.println("Invalid input - terminating...");
			System.exit(1);
		}
		return day;
	}

	public static boolean isDayValid(int day, int month, int year) {
		int[] longMonths = { 1, 3, 5, 7, 8, 10, 12 };
		//Test for leap years
		boolean isLeapYear=today.isLeapYear(year);
		java.util.Arrays.sort(longMonths);
		boolean isDayValid = !(day <= 0 || (java.util.Arrays.binarySearch(longMonths, month) < 0) && day > 30
				|| (month == 2 && (isLeapYear?day > 29:day>28)) || day > 31);
		return isDayValid;
	}

	public static void main(String[] args) {
		int[] input = getValidatedInput();
		int year = input[0];
		int month = input[1];
		int day = input[2];
		// Create birth date calendar month is 0 to 11
		GregorianCalendar birthdate = new GregorianCalendar(year, month - 1, day);

		// Create this years birthday
		GregorianCalendar birthday = new GregorianCalendar(today.get(YEAR), birthdate.get(MONTH), birthdate.get(DATE));

		int age = today.get(YEAR) - birthdate.get(YEAR);

		String[] weekdays = new DateFormatSymbols().getWeekdays(); // Get day names

		System.out.println("You were born on a " + weekdays[birthdate.get(DAY_OF_WEEK)]);
		System.out.println("This year you " + (birthday.after(today) ? "will be " : "are ") + age + " years old.");
		System.out.println("In " + today.get(YEAR) + " your birthday " + (today.before(birthday) ? "will be" : "was")
				+ " on a " + weekdays[birthday.get(DAY_OF_WEEK)] + ".");
	}
}
