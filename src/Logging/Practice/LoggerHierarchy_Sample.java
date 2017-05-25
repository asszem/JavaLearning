package Logging.Practice;

import java.util.logging.ConsoleHandler;
import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

import Logging.Formatters.ThreadFormatter_Sample;

public class LoggerHierarchy_Sample {

	public static int printHandlers(Handler[] handlers) {
		for (Handler handler : handlers) {
			System.out.println("\tHandler name=" + handler.getClass().getName());
			// rootLogger.removeHandler(handler);
		}
		return handlers.length;
	}

	public static void main(String[] args) {
		Logger rootLogger = Logger.getLogger("");  // Creates a root bankLogger
		Logger logger1 = Logger.getLogger("1");
		Logger logger1_2 = Logger.getLogger("1.2");
		Logger logger1_2_1 = Logger.getLogger("1.2.1");
		
		System.out.println("Loggers hierarchy");
		System.out.println("Parent of 1=" + logger1.getParent().getName());
		System.out.println("Parent of 1.2=" + logger1_2.getParent().getName());
		System.out.println("Parent of 1.2.1=" + logger1_2_1.getParent().getName());
		
		System.out.println("Handlers");
		Handler logger1Handler=new ConsoleHandler();
		logger1.addHandler(logger1Handler);
		Handler[] rootHandlers = rootLogger.getHandlers();
		System.out.println("Root bankLogger Handlers: "+rootHandlers.length);
		printHandlers(rootHandlers);
		Handler[] handlers1 = logger1.getHandlers();
		System.out.println("logger1 Handlers:"+handlers1.length);
		printHandlers(handlers1);
		Handler[] handlers1_2 = logger1_2.getHandlers();
		System.out.println("logger1_2 Handlers:"+handlers1_2.length);
		printHandlers(handlers1_2);
	
		System.out.println("Logger 1 use parent handlers? "+logger1.getUseParentHandlers());
		System.out.println("Logger 1.2 use parent handlers? "+logger1_2.getUseParentHandlers());
		System.out.println("Logger 1.2.1 use parent handlers? "+logger1_2_1.getUseParentHandlers());

//		Formatter logger1Formatter = new logger1Formatter();
		Formatter logger1Formatter = new ThreadFormatter_Sample();
		logger1Handler.setFormatter(logger1Formatter);

		System.out.println("\nProducing log messages");
		rootLogger.info("Info");
		logger1.info("Logger 1");
		logger1_2.info("Logger 1_2");
		logger1_2_1.info("Logger 1_2_1");
	}
}
class logger1Formatter extends Formatter{

	@Override
	public String format(LogRecord record) {
		String logMsgToDisplay = "\tLogger 1\n\t"+formatMessage(record)+"\n";
        return logMsgToDisplay;
	}
	
}
