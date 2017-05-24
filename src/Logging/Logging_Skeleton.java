package Logging;

import java.util.logging.Formatter;      //Make sure java.util.logging.formatter imported, not simply java.util.Formatter!
import java.io.IOException;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.logging.XMLFormatter;

public class Logging_Skeleton {

	// Define static variables for each class logging
	private static Logger classLogger;
	private static Formatter customFormatter;
	private static Handler customHandler;

	// Instantiate the Logger, Handler and Formatter
	public static void setupLogger(String filePattern) {
		classLogger = Logger.getLogger(Logging_Skeleton.class.getName()); // Update to actual class name
		customFormatter = new SimpleFormatter();
		// customFormatter = new XMLFormatter()
		try {
			customHandler = new FileHandler(filePattern);
		} catch (SecurityException | IOException e) {
			e.printStackTrace();
		}
		// customHandler = new ConsoleHandler();
		customHandler.setFormatter(customFormatter);
		classLogger.addHandler(customHandler);
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
	public static Handler createFileHandler(String pattern, boolean appendable){
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
		setupLogger("J:/Logging_skeleton.txt");
		classLogger.setLevel(Level.INFO);
		classLogger.info("This is an INFO");
		suppressConsole();
		classLogger.severe("This is an INFO");
		classLogger.setLevel(Level.OFF);
		classLogger.severe("This is an INFO");

	}

}
