package DateAndTime;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author András
 */
public class SimpleDateFormatParse {

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
		SimpleDateFormatParse dmObject = new SimpleDateFormatParse();
		dmObject.parseDate("12/30/1999");
	}

}
