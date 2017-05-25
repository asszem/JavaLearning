package Logging;

import java.util.logging.Formatter;      //Make sure java.util.logging.formatter imported, not simply java.util.Formatter!
import java.io.IOException;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.logging.XMLFormatter;
import java.util.logging.Filter;

public class Logging_Skeleton {

	// Define static variables for each class logging
	private static Logger classLogger;
	private static Formatter customFormatter;
	private static Handler customHandler;
	private static Filter customFilter;

	// Instantiate a Logger with Handler, Formatter and Filter
	public static void setupLogger(String filePattern) {
		// Instantiate a Logger
		classLogger = Logger.getLogger(Logging_Skeleton.class.getName()); // Update to actual class name

		// Instantiate a Handler
		try {
			customHandler = new FileHandler(filePattern);
		} catch (SecurityException | IOException e) {
			e.printStackTrace();
		}
		// customHandler = new ConsoleHandler();
		classLogger.addHandler(customHandler);

		// Instantiate and set Formatter for the Handler (not the Logger)!
		customFormatter = new SimpleFormatter();
		// customFormatter = new XMLFormatter()
		customHandler.setFormatter(customFormatter);

		// Instantiate and set filter
		customFilter = new CustomFilterSkeleton();
		classLogger.setFilter(customFilter);
	}

	// suppress the logging output to the console
	public static void suppressConsole() {
		Logger rootLogger = Logger.getLogger("");
		Handler[] handlers = rootLogger.getHandlers();
		if (handlers.length > 0) {									// If the handler has already been removed
			if (handlers[0] instanceof ConsoleHandler) {
				rootLogger.removeHandler(handlers[0]);
			}
		}
	}

	// Remove handler
	public static void removeHandler(Logger sourceLogger, Handler handlerToRemove) {
		sourceLogger.removeHandler(handlerToRemove);
	}

	// Create a FileHandler
	public static Handler createFileHandler(String pattern, boolean appendable) {
		try {
			return new FileHandler(pattern, appendable);
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	// Flags that can be used for output file name formatting
	public static void outputFileFormatPatterns() {
		// / The file name separator of the system. Typically either \ or / .
		// %t The temp directory of the system.
		// %h The user home directory of the system.
		// %g The generation number that distinguishes the rotated log files from each other.
		// %u A unique number to avoid naming conflicts.
		// %% A single percent sign, in case you want to use that in your file name.
		// FileHandler handler3 = new FileHandler("myapp-log.%u.%g.txt", 1024 * 1024, 10, true); //pattern, filesize limit (1024*1024), file count, APPEND)
		// logger.addHandler(handler);
	}

	public static void main(String[] args) {
		setupLogger("J:/Logs/Logging_skeleton.txt");
		classLogger.setLevel(Level.INFO);
		classLogger.info("This is an INFO");
		suppressConsole();
		classLogger.severe("This is an INFO");
		classLogger.severe("This is an INFO filtered");  // should not be displayed
		classLogger.setLevel(Level.OFF);
		classLogger.severe("This is an INFO");

	}

}

class CustomFormatterSkeleton extends Formatter {

	@Override
	public String format(LogRecord record) {
		return record.getLevel() + ":" + record.getMessage() + "\n";
	}
}

class CustomFilterSkeleton implements Filter {

	@Override
	public boolean isLoggable(LogRecord record) {
		if (record.getMessage().contains("filtered")) {
			return false;
		} else {
			return true;
		}
	}

}