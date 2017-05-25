package Logging.Practice;

import java.util.logging.Filter;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

public class Filter_Skeleton implements Filter {

	@Override
	public boolean isLoggable(LogRecord record) {
		if (record.getMessage().contains("filter-off")) {
			return false;
		} else
			return true;
	}
	
	public static void main(String[] args) {
		Logger logger = Logger.getLogger("FilteredLogger");
		Filter filter  = new Filter_Skeleton();
		System.out.println("Before filter");
		logger.info("Display this: filter-on");
		logger.info("Display this: filter-off");
		logger.setFilter(filter);
		System.out.println("After filter");
		logger.info("Display this: filter-on");
		logger.info("Display this: filter-off");
	}

}
