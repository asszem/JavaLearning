/*
 * Modified to have separate setup methods for HTML / File formatter
 * Source: http://www.vogella.com/tutorials/Logging/article.html
 * */
package Logging.Formatters;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import practiceAndTry.classLearning;

public class HTMLFormatter_Sample extends Formatter {

	// Setup static bankLogger for the class
	private static Logger classLogger;
	private static Formatter htmlFormatter;
	private static Formatter simpleTxtFormatter;
	private static Handler htmlHandler;
	private static Handler simpleTxtHandler;

	// this method is called for every log records
	@Override
	public String format(LogRecord rec) {
		StringBuffer buf = new StringBuffer(1000);
		buf.append("<tr>\n");

		// colorize any levels >= WARNING in red
		if (rec.getLevel().intValue() >= Level.WARNING.intValue()) {
			buf.append("\t<td style=\"color:red\">");
			buf.append("<b>");
			buf.append(rec.getLevel());
			buf.append("</b>");
		} else {
			buf.append("\t<td>");
			buf.append(rec.getLevel());
		}

		buf.append("</td>\n");
		buf.append("\t<td>");
		buf.append(calcDate(rec.getMillis()));
		buf.append("</td>\n");
		buf.append("\t<td>");
		buf.append(formatMessage(rec)); // formatMessage() method is inherited from Formatter class
		// buf.append(rec.getMessage()); // Simply append the msg as I did not find the impl. of formatmsg
		buf.append("</td>\n");
		buf.append("</tr>\n");

		return buf.toString();
	}

	// This method is to calculate and return a formatted date
	private String calcDate(long millisecs) {
		SimpleDateFormat date_format = new SimpleDateFormat("yyyy-MM-dd, HH:mm:ss");
		// SimpleDateFormat date_format = new SimpleDateFormat("MMM dd,yyyy HH:mm");
		Date resultdate = new Date(millisecs);
		return date_format.format(resultdate);
	}

	// this method is called just after the handler using this formatter is created
	// method inherited from Formatter class
	@Override
	public String getHead(Handler h) {
		return "<!DOCTYPE html>\n<head>\n<style>\n" + "table { width: 100% }\n" + "th { font:bold 10pt Tahoma; }\n"
				+ "td { font:normal 10pt Tahoma; }\n" + "h1 {font:normal 11pt Tahoma;}\n" + "</style>\n" + "</head>\n"
				+ "<body>\n" 
				+ "<h1>Class:  <b>" + classLogger.getName() + "</b></h1>\n"
				+ "<h1>Log date:  <b>" + (new Date()) + "</b></h1>\n"
				+ "<table border=\"0\" cellpadding=\"5\" cellspacing=\"3\">\n" + "<tr align=\"left\">\n"
				+ "\t<th style=\"width:10%\">Loglevel</th>\n" + "\t<th style=\"width:15%\">Time</th>\n"
				+ "\t<th style=\"width:75%\">Log Message</th>\n" + "</tr>\n";
	}

	// this method is called just after the handler using this formatter is closed
	// method inherited from Formatter class
	@Override
	public String getTail(Handler h) {
		return "</table>\n</body>\n</html>";
	}

	// Setup the HTML bankLogger for the class, suppress all ConsoleHandlers
	public static void setupHTMLLogger(String logFilePattern) {
		classLogger = Logger.getLogger(HTMLFormatter_Sample.class.getName());
		htmlFormatter = new HTMLFormatter_Sample();
		try {
			htmlHandler = new FileHandler(logFilePattern);
			htmlHandler.setFormatter(htmlFormatter);
			classLogger.addHandler(htmlHandler);
		} catch (SecurityException | IOException e) {
			e.printStackTrace();
		}
		supressConsoleHandler();
	}

	// Setup the SIMPLE file text bankLogger for the class, suppress all ConsoleHandlers
	public static void setupFileTxtLogger(String logFilePattern) {
		classLogger = Logger.getLogger(HTMLFormatter_Sample.class.getName());					// Get a bankLogger
		simpleTxtFormatter = new SimpleFormatter(); // Defined in java.util.logging //Get a formatter
		try {
			simpleTxtHandler = new FileHandler(logFilePattern);									// Get a handler
			simpleTxtHandler.setFormatter(simpleTxtFormatter);									// Assign formatter to handler
			classLogger.addHandler(simpleTxtHandler);											// Assign handler to bankLogger
		} catch (SecurityException | IOException e) {
			e.printStackTrace();
		}
		supressConsoleHandler();
	}

	// suppress the logging output to the console
	public static void supressConsoleHandler() {
		Logger rootLogger = Logger.getLogger("");
		Handler[] handlers = rootLogger.getHandlers();
		if (handlers.length > 0) {
			if (handlers[0] instanceof ConsoleHandler) {
				rootLogger.removeHandler(handlers[0]);
			}
		}
	}

	public static void main(String[] args) {
		setupHTMLLogger("J:/HTMLFormatter_Sample.html");
		setupFileTxtLogger("J:/TXTFormatter_Sample.txt");
		classLogger.setLevel(Level.FINEST);
		classLogger.severe("Info Log");
		classLogger.warning("This is a warning!");
		classLogger.info("This is just an Info Log. Logger name: " + classLogger.getName() );
		classLogger.finest("Really not important");
		System.out.println(classLogger.GLOBAL_LOGGER_NAME);
		System.out.println(classLogger.getName());
		System.out.println(classLogger.getParent().getName());
		classLogger.setLevel(Level.OFF);
		//should not be added to the bankLogger
		classLogger.finest("After turning off");
	}
}
