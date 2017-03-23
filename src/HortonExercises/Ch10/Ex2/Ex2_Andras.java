/*
Write a program that creates an 
integer array of date values 
containing month, day, and year as integers for
some number of dates (10, say, so the integer array is two-dimensional with 10 rows and 3 columns). 
The program should write a file with a string representation of each date 
written as Unicode characters. 

For example, the date values 3,2,1990 would be written to the file as 2nd March 1990. 

Make sure that the date strings can be read back, 
either by using a separator character of some kind to mark the end of each string
or by writing the length of each string before you write the string itself
 */
package HortonExercises.Ch10.Ex2;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import static java.nio.file.StandardOpenOption.*;

/**
 *
 * @author Andras Olah (olahandras78@gmail.com)
 */
public class Ex2_Andras {

	public static void main(String[] args) {
//<editor-fold desc="Generate random dates">
		//Generate random dates
		int[][] dates = new int[10][3];
		//Month, Day, Year
		for (int i = 0; i < dates.length; i++) {
			dates[i][2] = (int) (1000 + Math.random() * 1100); //Random year between 1000 and 2100
			dates[i][1] = (int) (1 + Math.random() * 12);
			//Generate days based on Month
			switch (dates[i][1]) {
				case 1:
				case 3:
				case 5:
				case 7:
				case 8:
				case 10:
				case 12:
					dates[i][0] = (int) (1 + Math.random() * 31);
					break;
				case 4:
				case 6:
				case 9:
				case 11:
					dates[i][0] = (int) (1 + Math.random() * 30);
					break;
				case 2: //calculate leap years
					if (dates[i][2] % 4 == 0) {  //leap year
//						System.out.println("szököév! " + dates[i][2]);
						dates[i][0] = (int) (1 + Math.random() * 29);
					} else {
						dates[i][0] = (int) (1 + Math.random() * 28);
					}
					break;
			}
		}
//</editor-fold>
//<editor-fold desc="Create the string representation of dates">
		String[] datesString = new String[dates.length];
		//1st, 2nd, 3rd, 4th...11th, 12th
		String dayStr;
		String[] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
		String separator = "|";
		int current = 0;
		for (int[] date : dates) {
			System.out.printf("%02d-%02d-%d \t-->\t", date[0], date[1], date[2]);
			switch (date[0]) {
				case 1:
				case 11:
				case 21:
				case 31:
					dayStr = "st";
					break;
				case 2:
				case 12:
				case 22:
					dayStr = "nd";
					break;
				case 3:
				case 13:
				case 23:
					dayStr = "rd";
					break;
				default:
					dayStr = "th";
					break;
			}
			datesString[current] = "[" + date[0] + dayStr + separator + months[date[1] - 1] + separator + date[2] + "]";
			System.out.println(datesString[current]);
			current++;
		}
//</editor-fold>
//<editor-fold desc="Write to file using Writer">
		Path p = Paths.get("E:\\javaTest", "Exercises", "H10", "Ex2");
		try {
			Files.createDirectories(p);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		try (BufferedWriter writer = Files.newBufferedWriter(p.resolve("usingWriter.txt"), WRITE, CREATE, TRUNCATE_EXISTING);) {
			for (String s : datesString) {
				writer.write(s);
			}
			System.out.println("Strings written");
		} catch (IOException ex) {
			ex.printStackTrace();
		}
//</editor-fold>
//<editor-fold desc="Write to file using a byte buffer">
		ByteBuffer bb = ByteBuffer.allocate(1024);
		CharBuffer bbC = bb.asCharBuffer();
		for (String s : datesString) {
			bbC.put(s);
			bb.position(bb.position() + 2 * s.length());
		}
		//Opening a file channel
		try (
				FileChannel fileChannel = (FileChannel) Files.newByteChannel(p.resolve("usingByteBuffer.txt"), WRITE, CREATE, TRUNCATE_EXISTING);) {
			bb.flip();
			fileChannel.write(bb);
			System.out.println("File written");
		} catch (IOException e) {
			e.printStackTrace();
		}
//</editor-fold>

	}//main
}//class
