package Threads.Practice.Bank.BankOperation_5_UsingExecutors;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class BankLogger {

	// public static String bankLoggerFileName;
	public static Logger bankLogger;
	public static Handler bankHandler;
	public static Handler BankClassFineHandler;
	public static Logger BankClassLogger;

	public static void readCustomConfiguration(String configPath) {
		try {
			LogManager.getLogManager().readConfiguration(new FileInputStream(configPath));
		} catch (SecurityException | IOException e) {
			e.printStackTrace();
		}
	}

	public static void setupLogging(String configFilePath, String bankLoggerFileName) {
		// Default logging level: info
		// Default handler level: info
		// Default handler: filehandler
		// Default format: ThreadPrioritiesBankLogFormatter
		readCustomConfiguration(configFilePath);
		try {
			BankClassFineHandler = new FileHandler(bankLoggerFileName + "Bank_class_fine.log");
			BankClassLogger = Logger.getLogger(Bank.class.getName());
			BankClassLogger.addHandler(BankClassFineHandler);
			BankClassFineHandler.setLevel(Level.FINER);
			BankClassLogger.setLevel(Level.FINER);
		} catch (SecurityException | IOException e) {
			e.printStackTrace();
		}
	}
}
