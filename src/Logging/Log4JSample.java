package Logging;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Log4JSample {
	
	private static final Logger logger = LogManager.getLogger(Log4JSample.class);


	
	public static void main(String[] args) {
		logger.info("Hello log");
	}

}
