package Logging.Practice;

import java.io.IOException;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

/**
 * Source: http://www.onjava.com/pub/a/onjava/2002/06/19/log.html
 */
public class Logging_Sample {

	private static Logger theLogger = Logger.getLogger(Logging_Sample.class.getName());

	private static Logger theAnotherLogger = Logger.getLogger("This is the bankLogger's name");

	public static void main(String[] args) {
		
		//Create a filehandler
		try {
//			theLogger.addHandler(new FileHandler());		//log file will be created in user home
			FileHandler handler = new FileHandler("J:/JavalogFile%g%u.txt", true); //append to file
			theLogger.addHandler(handler);
		} catch (SecurityException | IOException e) {
			e.printStackTrace();
		}

		// The root bankLogger's handlers default to INFO. We have to
		// crank them up. We could crank up only some of them
		// if we wanted, but we will turn them all up.
		Handler[] handlers = Logger.getLogger("").getHandlers(); //"" is the top of hierarchy
		for (int index = 0; index < handlers.length; index++) {
			handlers[index].setLevel(Level.FINER);
			if (handlers[index] instanceof ConsoleHandler){
				handlers[index].setFormatter(new MyFormatter());
			}
		}
		// We also have to set our bankLogger to log finer-grained messages
		theLogger.setLevel(Level.FINER);
		Logging_Sample hello = new Logging_Sample("Hello world!");

		//Create a log record
		theLogger.log(Level.FINE, "This is fine!");
		//Use the convenient method
		theAnotherLogger.fine("This is fine!");


		hello.sayHello();
	}

	private String theMessage;

	public Logging_Sample(String message) {
		theMessage = message;
	}

	public void sayHello() {
		theLogger.entering(this.getClass().getName(), "sayHello");
		// use the 'least important' type of message, one at the 'finest' level.
		theLogger.finest("Hello finest logging!"); // finest log level won't go to console
		theLogger.info("Log level: info");
		theAnotherLogger.info("Log from Another bankLogger at INFO level");
		System.err.println(theMessage);
	}
}

class MyFormatter extends Formatter {

    @Override
    public String format(LogRecord record) {
        return record.getLevel() + ":" + record.getMessage()+"\n";
    }
}
