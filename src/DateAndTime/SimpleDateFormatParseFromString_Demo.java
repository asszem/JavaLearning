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
		 *G	Era designator	Text	AD
y	Year	Year	1996; 96
Y	Week year	Year	2009; 09
M	Month in year	Month	July; Jul; 07
w	Week in year	Number	27
W	Week in month	Number	2
D	Day in year	Number	189
d	Day in month	Number	10
F	Day of week in month	Number	2
E	Day name in week	Text	Tuesday; Tue
u	Day number of week (1 = Monday, ..., 7 = Sunday)	Number	1
a	Am/pm marker	Text	PM
H	Hour in day (0-23)	Number	0
k	Hour in day (1-24)	Number	24
K	Hour in am/pm (0-11)	Number	0
h	Hour in am/pm (1-12)	Number	12
m	Minute in hour	Number	30
s	Second in minute	Number	55
S	Millisecond	Number	978
z	Time zone	General time zone	Pacific Standard Time; PST; GMT-08:00
Z	Time zone	RFC 822 time zone	-0800
X	Time zone	ISO 8601 time zone	-08; -0800; -08:00 
		 * 
		 */
	}

}
