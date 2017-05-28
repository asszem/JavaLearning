package Threads.Practice.Bank.BankOperation_6_SetThreadPriorities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class LoggerSetup {

	public static void addFileHandler(String handlerFile, Level logLevel, Logger sourceLogger) {
		try {
			Handler fineHandler = new FileHandler(handlerFile);
			fineHandler.setLevel(logLevel);
			sourceLogger.addHandler(fineHandler);
			sourceLogger.setLevel(logLevel);
		} catch (SecurityException | IOException e1) {
			e1.printStackTrace();
		}
	}

	public static void setCustomDefaultLoggingConfiguration(String logConfigFile) {
		// use standard properties, setup different handler
		try {
			LogManager.getLogManager().readConfiguration(new FileInputStream(logConfigFile));
		} catch (SecurityException | IOException e) {
			e.printStackTrace();
		}
	}
	public static void startLogging(){
		//Load custom default logging configuration
		String logConfigFile = "J:/Logs/BankOpsLogs/ThreadPriorities.properties";
		LoggerSetup.setCustomDefaultLoggingConfiguration(logConfigFile);
	
		//Setup filehandler for Clerks class
		String clerksHandlerFile = "J:/Logs/BankOpsLogs/ThreadPrioritiesLogfiles/ClerkOperations.log";
		LoggerSetup.addFileHandler(clerksHandlerFile, Level.FINE, Clerk.logger);
		
		//Setup filehandler for TransactionSource class
		String transactionHandlerFile="J:/Logs/BankOpsLogs/ThreadPrioritiesLogfiles/TransactionSource.log";
		LoggerSetup.addFileHandler(transactionHandlerFile, Level.FINE, TransactionSource.logger);

	}
}
