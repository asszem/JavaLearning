package DateAndTime;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author András
 */
public class SimpleDateFormatParseFromString_Demo {

	Date myDate;
	SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");

	public void parseDate(String dateString) {
		String formatDate;
		try {
			myDate = sdf.parse(dateString);
			System.out.println("Parse successfull.");
			System.out.println("Date with native format: " + myDate);
			formatDate = sdf.format(myDate);
			System.out.println("Formatted date:" + formatDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		SimpleDateFormatParseFromString_Demo dmObject = new SimpleDateFormatParseFromString_Demo();
		dmObject.parseDate("12/30/1999");

		SimpleDateFormat sdf2 = new SimpleDateFormat("YYYY-MM-dd, hh:mm");
		System.out.println(sdf2.format(new Date()));
		/*
		 * PATTERNS http://docs.oracle.com/javase/7/docs/api/java/text/SimpleDateFormat.html
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 */
	}

}
