package DateAndTime.LocalDateAndTime_Demo;

import java.time.*;

/**
 * This interface is to demonstrate that if a method was declared as default and implemented in the interface class, then it will be accessible 
 * in every class that implements the interface, without the need to implementing the method in the subclasses
 *
 * @author Andras, based on Oracle Java SE tutorial on default methods in interfaces
 * https://docs.oracle.com/javase/tutorial/java/IandI/defaultmethods.html
 */
public interface TimeClient {

	void setTime(int hour, int minute, int second);

	void setDate(int day, int month, int year);

	void setDateAndTime(int day, int month, int year,
					int hour, int minute, int second);

	LocalDateTime getLocalDateTime();

	//ZonedDateTime getZonedDateTime(String zoneString); //adding a new method to the interface would require to implement the method in all impelemnting classes
	//define a DEFAULT method with implementation. Default methods are public by default

	//This way the interface implementing classes do not have to implement this method
	default ZonedDateTime getZonedDateTime(String zoneString) {
		return ZonedDateTime.of(getLocalDateTime(), getZoneId(zoneString));
	}

//Define an implement! a static method that is used by the getZonedDateTime method
	static ZoneId getZoneId(String zoneString) {
		try {
			return ZoneId.of(zoneString);
		} catch (DateTimeException e) {
			System.err.println("Invalid time zone: " + zoneString
							+ "; using default time zone instead.");
			return ZoneId.systemDefault();
		}
	}

}
