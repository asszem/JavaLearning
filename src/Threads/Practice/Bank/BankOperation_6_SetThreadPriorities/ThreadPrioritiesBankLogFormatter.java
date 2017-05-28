package Threads.Practice.Bank.BankOperation_6_SetThreadPriorities;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

public class ThreadPrioritiesBankLogFormatter extends Formatter {

	@Override
	public String format(LogRecord record) {
		return "ThreadID=" + record.getThreadID() 
				+ " :: Priority="+Thread.currentThread().getPriority()
				+ " :: Date=" + formatDate(new Date(record.getMillis()))
				+ "\n"
				+ "\tClass=" + record.getSourceClassName().substring(record.getSourceClassName().lastIndexOf(".")+1)
				+ " :: Method=" + record.getSourceMethodName()
				+ " :: Logger=" + record.getLoggerName() 
				+ "\n\t" + record.getLevel()+"="
				+ record.getMessage()+"\n\n";
	}
	
	public static String formatDate(Date date){
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd, EEEE hh:mm:ss");
		return sdf.format(date);
	}

}
