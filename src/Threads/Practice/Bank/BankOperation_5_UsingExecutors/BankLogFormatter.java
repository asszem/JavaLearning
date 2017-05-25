package Threads.Practice.Bank.BankOperation_5_UsingExecutors;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

public class BankLogFormatter extends Formatter {

	@Override
	public String format(LogRecord record) {
		return "ThreadID=" + record.getThreadID() 
				+ " :: Date=" + formatDate(new Date(record.getMillis()))
				+ " :: Class=" + record.getSourceClassName().substring(record.getSourceClassName().lastIndexOf(".")+1)
				+ " :: Method=" + record.getSourceMethodName()
				+ " :: Logger=" + record.getLoggerName() 
				+ "\n\t" + record.getLevel()+"="
				+ record.getMessage()+"\n\n";
	}
	
	public static String formatDate(Date date){
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd, hh:mm:ss");
		return sdf.format(date);
	}

}
