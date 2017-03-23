/*
Define an array of ten String elements each containing an arbitrary string of the form “month/day/
year”; for example,”10/29/99” or “12/5/01”. Analyze each element in the array and output the date
represented in the form 29th October 1999.
 */
package HortonExercises.Ch04;

public class ch4_ex4 {

	public static void main(String[] args) {
		String[] dates = new String[10];
		for (int i = 0; i < dates.length; i++) {
			double rndY = Math.ceil(99 * Math.random());
			int rndM = (int) Math.ceil(12 * Math.random());
			int numDays = 0;
			switch (rndM) {
				case 1:
				case 3:
				case 5:
				case 7:
				case 8:
				case 10:
				case 12:
					numDays = 31;
					break;
				case 4:
				case 6:
				case 9:
				case 11:
					numDays = 30;
					break;
				case 2:
					// A leap year is a year that is divisible by 4 and not by 100, or
      // a leap year is a century and the number of centuries is divisible by 4
					//Ez egy gyönyörű megoldás:
//					daysIncrement = (((year % 4 == 0) && !(year % 100 == 0)) || (year % 400 == 0)) && (month == 2) ? 1 : 0 ;
					//
					if ((rndY % 4 == 0) && (rndY % 100 !=0)) { //szökőév
						numDays = 29;
					} else { //nem szökőév
						numDays = 28;
					}
					break;
			}
			double rndD = Math.ceil(numDays * Math.random());
			String yearNum;
			if (rndY < 10) {
				yearNum = "0" + (int) rndY;
			} else {
				yearNum = ""+(int) rndY;
			}
			String oneDate = (int) rndM + "/" + (int) rndD + "/" + yearNum;
			dates[i] = oneDate;
//			System.out.println(oneDate);
//			System.out.print("Year:" + (int) rndY+"  ");
//			System.out.print("Month:" + rndM+"  ");
//			System.out.print("NumDays:" + numDays+"  ");
//			System.out.println("");

//29th Octobe 1999
		}
		for (int i = 0; i < dates.length; i++) {
			String dayS;
			String monthS;
			String yearS;
			int monthDelimiter = dates[i].indexOf("/");
//			System.out.println(dates[i] + " Month delimiter index:" + monthDelimiter);
			monthS = dates[i].substring(0, monthDelimiter);
			int yearDelimiter = dates[i].indexOf("/", monthDelimiter + 1);
//			System.out.println("year delimiter index:" + yearDelimiter);
			dayS = dates[i].substring(monthDelimiter + 1, yearDelimiter);
			yearS = dates[i].substring(yearDelimiter + 1);
			//	System.out.println("Date:" + dates[i]);
			//	System.out.println("DayS:" + dayS);
//				System.out.println("MonthS:" + monthS);
			//	System.out.println("yearS:" + yearS);
			String monthName;
			switch (monthS) {
				case "1":
					monthName = "January";
					break;
				case "2":
					monthName = "February";
					break;
				case "3":
					monthName = "March";
					break;
				case "4":
					monthName = "April";
					break;
				case "5":
					monthName = "May";
					break;
				case "6":
					monthName = "June";
					break;
				case "7":
					monthName = "July";
					break;
				case "8":
					monthName = "August";
					break;
				case "9":
					monthName = "September";
					break;
				case "10":
					monthName = "October";
					break;
				case "11":
					monthName = "November";
					break;
				case "12":
					monthName = "December";
					break;
				default:
					monthName = "hiba";
			}
//			System.out.println("Month name:"+monthName);
			String dayName;
			if (dayS.charAt(dayS.length()-1)=='1'){ //az index 0-ról indul, a length viszont 1-ről, ezért kell kivonni egyet
//				System.out.print("Egyre végződik a nap!");
				dayName = "st";
			} else if (dayS.charAt(dayS.length()-1)=='2'){
//				System.out.print("Kettőre végződik a nap!");
				dayName = "nd";
			}else if (dayS.charAt(dayS.length()-1)=='3'){
//				System.out.print("Háromra végződik a nap!");
				dayName = "rd";
			} else dayName="th";
			System.out.println(dates[i]+"----> "+dayS + dayName + " " + monthName + " " + "19" + yearS);
		}
	}//main
}//class
