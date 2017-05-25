package Logging.Formatters;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

public class ThreadFormatter_Sample extends Formatter {

	public static String formatType1(LogRecord record) {
		return "\n" + record.getThreadID() + "::" + record.getSourceClassName() + "::" + record.getSourceMethodName()
				+ "::" + new Date(record.getMillis()) + "::" + record.getMessage() + "\n";
	}

	public static String formatType2(LogRecord record) {
		return "ThreadID=" + record.getThreadID() 
				+ " :: Class=" + record.getSourceClassName().substring(record.getSourceClassName().lastIndexOf(".") + 1)
				+ " :: Method=" + record.getSourceMethodName()
				+ " :: Date=" + formatDate(new Date(record.getMillis()))
				+ " :: Logger=" + record.getLoggerName()
				+ "\n\t" + record.getLevel() 
				+ "=" + record.getMessage() 
				+ "\n";
	}

	public static String formatDate(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd, hh:mm");
		return sdf.format(date);
	}

	@Override
	public String format(LogRecord record) {
		return formatType1(record);
	}

}
