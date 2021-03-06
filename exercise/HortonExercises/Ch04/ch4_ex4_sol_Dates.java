package HortonExercises.Ch04;

public class ch4_ex4_sol_Dates {

	public static void main(String args[]) {
		String[] dates = new String[10];
		String[] monthNames = {
			"January", "February", "March", "April",
			"May", "June", "July", "August", "September",
			"October", "November", "December"
		};

		int[] daysInMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
		String ending = "th";
		String dayStr = null;
		String monthStr = null;
		String yearStr = null;
		char separator = '/';
		int day = 0;
		int month = 0;
		int year = 0;
		int daysIncrement = 0;

		// Generate a set of arbitrary dates:
		for (int i = 0; i < dates.length; ++i) {
			year = (int) (100.0 * Math.random());                               // Random year value 00 to 99
			month = (int) (12.0 * Math.random()) + 1;                           // Random month value 1 to 12

			// February in a leap year has 29 days in the month so we need to allow for an extra day.
			// Leap years are messy ...
			// A leap year is a year that is divisible by 4 and not by 100, or
			// a leap year is a century and the number of centuries is divisible by 4
			daysIncrement = (((year % 4 == 0) && !(year % 100 == 0)) || (year % 400 == 0)) && (month == 2) ? 1 : 0;

			day = (int) (Math.random() * (daysInMonth[month - 1] + daysIncrement)) + 1;
			dates[i] = "" + day + separator + month + separator + (year < 10 ? "0" : "") + year;
			System.out.println("Day is " + dates[i]);
		}

		// Now the set of dates has been generated, we can interpret and output it.
		int start = 0;                                                     // Word start index
		int end = 0;                                                       // Word end index

		System.out.println();
		for (String date : dates) {
			// Extract the day, month and year strings using the familiar method.
			start = 0;
			end = date.indexOf(separator, start);
			dayStr = date.substring(start, end);
			start = end + 1;
			end = date.indexOf(separator, start);
			monthStr = date.substring(start, end);
			start = end + 1;
			yearStr = date.substring(start);
//			System.out.println("MonthString"+monthStr);
			// Figure out whether "st", "nd", "rd", or "th", should be appended to the day.
			//  Single digit day is "st", "rd", "nd", for "1", "2", "3", and "th" otherwise.
			// Days "11" to "19" are all "th".
			// The remainder are "st", "rd", "nd", for last digit "1", "2", "3",
			// and "th" otherwise.
			if (dayStr.length() == 1) {
				switch (dayStr.charAt(0)) {
					case '1':
						ending = "st";
						break;
					case '2':
						ending = "nd";
						break;
					case '3':
						ending = "rd";
						break;
					default:
						ending = "th";
				}
			} else if (dayStr.charAt(0) == '1') {
				ending = "th";
			} else {
				switch (dayStr.charAt(1)) {
					case '1':
						ending = "st";
						break;
					case '2':
						ending = "nd";
						break;
					case '3':
						ending = "rd";
						break;
					default:
						ending = "th";
				}
			}

			// We can now output the date in the required format.
			// To output the month name string we must use monthStr to get an index to the monthNames array.
			System.out.println(dayStr + ending + " "
							+ monthNames[monthStr.length() == 1 ? monthStr.charAt(0) - '1' : 9 + monthStr.charAt(1) - '0']
							+ " " + "19" + yearStr);
/*
			A monthNames tömb 0-ról indul, de a monthStr 1-ről kezdi a hónapokat, ezért kell kivonni egyet
			Ha egyjegyű a hónapszám, akkor a 0. indexen lévő karakter maga a hónap száma, ebből mínusz egy a hónap indexe a monthNames tömbben
			Ha kétjegyű a hónapszám, akkor az 1. indexen lévő karakter azonosítja a hónapt, ezt kell 9-hez hozzáadni, hogy az indexet megkapjuk
			október 10-0, index 9
			november 11-1, index 10
			december 12-2, index 11
			
			
			*/
			//System.out.println("monthStr:\t"+monthStr);
			//System.out.println("monthStr.length():\t"+monthStr.length());
			//System.out.println("monthStr.charat(0):\t"+monthStr.charAt(0));
			//System.out.println("monthStr.charat(1): "+(monthStr.length()>1?monthStr.charAt(1):""));

//			System.out.println("Hónap neve:"+monthNames[1]);
			// The preceding would be easier using Integer class methods to convert
			// the day and month strings to integers.
			// Number classes (Integer, Double etc.) are covered in Chapter 5.
			// We could index the monthNames array with the expression monthNames[Integer.parseInt(monthStr)]
			// The parseInt() method converts the String argument to an int value.
		}
	}
}
