
package DateAndTime;

import java.time.*;
import java.lang.*;
import java.util.*;
/**
 * This class is to demonstrate the usage of LocalDateAndTime objects and using default interface methods
 * @author András
 * https://docs.oracle.com/javase/tutorial/java/IandI/defaultmethods.html
 */
public class LocalDateAndTime implements TimeClient{

private LocalDateTime dateAndTime;
    
    public LocalDateAndTime() {
        dateAndTime = LocalDateTime.now();
    }
    
    public void setTime(int hour, int minute, int second) {
        LocalDate currentDate = LocalDate.from(dateAndTime);
        LocalTime timeToSet = LocalTime.of(hour, minute, second);
        dateAndTime = LocalDateTime.of(currentDate, timeToSet);
    }
    
    public void setDate(int day, int month, int year) {
        LocalDate dateToSet = LocalDate.of(year, month, day);
        LocalTime currentTime = LocalTime.from(dateAndTime);
        dateAndTime = LocalDateTime.of(dateToSet, currentTime);
    }
    
    public void setDateAndTime(int day, int month, int year,
                               int hour, int minute, int second) {
        LocalDate dateToSet = LocalDate.of(year, month, day);
        LocalTime timeToSet = LocalTime.of(hour, minute, second); 
        dateAndTime = LocalDateTime.of(dateToSet, timeToSet);
    }
    
    public LocalDateTime getLocalDateTime() {
        return dateAndTime;
    }
    
    public String toString() {
        return dateAndTime.toString();
    }
    
    public static void main(String... args) {
        TimeClient myTimeClient = new LocalDateAndTime();
				myTimeClient.setDateAndTime(9, 12, 1978, 10, 12, 34);
//myTimeClient.setDate(1978, 12, 9);
        System.out.println(myTimeClient.toString());
				System.out.println(myTimeClient.getZonedDateTime("UTC"));
    }
}
