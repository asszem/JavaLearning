package Threads.Practice.Bank.BankOperation_5_UsingExecutors;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class BankLogger {

//	public static String bankLoggerFileName;
	public static Logger bankLogger;
	public static Handler bankHandler;
	public static Handler BankClassFineHandler;

	public static void readCustomConfiguration(String configPath) {
		try {
			LogManager.getLogManager().readConfiguration(new FileInputStream(configPath));
		} catch (SecurityException | IOException e) {
			e.printStackTrace();
		}
	}

	public static void setupLogging(String configFilePath, String bankLoggerFileName) {
		//Default logging level: info
		//Default handler level: info
		//Default handler: filehandler
		//Default format: BankLogFormatter
		readCustomConfiguration(configFilePath);
		try {
			BankClassFineHandler = new FileHandler(bankLoggerFileName + "bank_clas_fine.log");
			BankClassFineHandler.setLevel(Level.FINER);
		} catch (SecurityException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
