package HortonExercises.Ch16.Ex123_Andras;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

public class LoggerSetup extends Formatter {

	private static Handler generalHandler;

	public static void setupGeneralHandler() {
		String generalLoggerFile = "J:/Logs/BankOpsLogs/Ex3Logs/GeneralLog.log";
		try {
			generalHandler = new FileHandler(generalLoggerFile);
			generalHandler.setFormatter(new LoggerSetup());
		} catch (SecurityException | IOException e) {
			e.printStackTrace();
		}
	}

	public static void setupLogger(Logger logger, String logfile) {
		Handler handler;
		try {
			handler = new FileHandler(logfile);
			logger.addHandler(handler);
			logger.addHandler(generalHandler);
			handler.setFormatter(new LoggerSetup());
			handler.setLevel(Level.FINE);
			logger.setUseParentHandlers(false);
			logger.setLevel(Level.FINE);
		} catch (SecurityException | IOException e) {
			e.printStackTrace();
		}
	}

	public static void startLogging() {
		setupGeneralHandler();
		String mainLoggerFile = "J:/Logs/BankOpsLogs/Ex3Logs/main_UsingExecutors.log";
		setupLogger(UsingExecutors.logger, mainLoggerFile);
		String transactionSourcesLoggerFile = "J:/Logs/BankOpsLogs/Ex3Logs/TransactionSources.log";
		setupLogger(TransactionSource.logger, transactionSourcesLoggerFile);
		String transactionLoggerFile = "J:/Logs/BankOpsLogs/Ex3Logs/Transactions.log";
		setupLogger(Transaction.logger, transactionLoggerFile);
		String supervisorsLoggerFile = "J:/Logs/BankOpsLogs/Ex3Logs/Supervisors.log";
		setupLogger(Supervisor.logger, supervisorsLoggerFile);
		String clerkLoggerFile = "J:/Logs/BankOpsLogs/Ex3Logs/Clerks.log";
		setupLogger(Clerk.logger, clerkLoggerFile);

	}

	@Override
	public String format(LogRecord record) {
		return "Thread=" + Thread.currentThread().getName() + " " + record.getLevel() + "=" + record.getMessage()
				+ "\n";
	}

}
