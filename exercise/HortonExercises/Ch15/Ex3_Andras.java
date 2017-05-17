/*Requirement: input user birth date and then output the zodiac sign*/
package HortonExercises.Ch15;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;

public class Ex3_Andras {
	public static GregorianCalendar getBirthDate(int[] sourceDate) {
		GregorianCalendar birthdate = new GregorianCalendar(sourceDate[0], sourceDate[1] - 1, sourceDate[2]);
		return birthdate;
	}

	public static int[] getBirthdateFromKeyboard() {
		int[] date = new int[3];
		System.out.println("Enter birth year:");
		date[0] = getIntKeyboardInput(); // TODO inut date validation
		System.out.println("Enter birth month:");
		date[1] = getIntKeyboardInput();
		System.out.println("Enter birth day:");
		date[2] = getIntKeyboardInput();
		return date;
	}

	public static int getIntKeyboardInput() {
		Scanner keyboard = new Scanner(System.in);
		while (!keyboard.hasNextInt()) {
			System.out.println("Error! Please provide a number");
			keyboard.next();
		}
		// this is only reached when nextInt is there
		int input = keyboard.nextInt();
		// keyboard.close();
		return input;
	}

	public static String getZodiacSign(GregorianCalendar sourceDate) {
		/*
		 * Aquarius (January 20 - February 18) Aquarius Pisces (February 19 -
		 * March 20) Pisces Aries (March 21 - April 19) Aries Taurus (April 20 -
		 * May 20) Taurus Gemini (May 21 - June 20) Gemini Cancer (June 21 -
		 * July 22) Cancer leo (July 23 - August 22) leo Virgo (August 23 -
		 * September 22) Virgo Libra (September 23 - October 22) Libra Scorpio
		 * (October 23 - November 21) Scorpio Sagittarius (November 22 -
		 * December 21) Sagittarius Capricorn (December 22 - January 19)
		 * Capricorn
		 */
		String zodiac;
		int birthMonth = sourceDate.get(Calendar.MONTH)+1; //Months start with 0!
		int birthDay = sourceDate.get(Calendar.DAY_OF_MONTH);
		if (birthMonth == 1 && birthDay >= 20 || birthMonth == 2 && birthDay <= 18) {
			zodiac = "Aquarius";
		}else
		if (birthMonth == 2 && birthDay >= 19 || birthMonth == 3 && birthDay <= 20) {
			zodiac = "Pisces";
		} else
		if (birthMonth == 3 && birthDay >= 21 || birthMonth == 4 && birthDay <= 19) {
			zodiac = "Aries";
		} else
		if (birthMonth == 4 && birthDay >= 20 || birthMonth == 5 && birthDay <= 20) {
			zodiac = "Taurus";
		} else
		if (birthMonth == 5 && birthDay >= 21 || birthMonth == 6 && birthDay <= 20) {
			zodiac = "Gemini";
		} else
		if (birthMonth == 6 && birthDay >= 21 || birthMonth == 7 && birthDay <= 22) {
			zodiac = "Cancer";
		} else
		if (birthMonth == 7 && birthDay >= 23 || birthMonth == 8 && birthDay <= 22) {
			zodiac = "Leo";
		} else
		if (birthMonth == 8 && birthDay >= 23 || birthMonth == 9 && birthDay <= 22) {
			zodiac = "Virgo";
		} else
		if (birthMonth == 9 && birthDay >= 23 || birthMonth == 10 && birthDay <= 22) {
			zodiac = "Libra";
		} else
		if (birthMonth == 10 && birthDay >= 23 || birthMonth == 11 && birthDay <= 21) {
			zodiac = "Scorpio";
		} else
		if (birthMonth == 11 && birthDay >= 22 || birthMonth == 12 && birthDay <= 21) {
			zodiac = "Saggittarius";
		} else
		if (birthMonth == 12 && birthDay >= 22 || birthMonth == 1 && birthDay <= 19) {
			zodiac = "Capricorn";
		} else zodiac="Something went wrong. Date is not in range. Month: " +birthMonth +" Day:"+birthDay;
		return zodiac;
	}

	public static void main(String[] args) {
		GregorianCalendar birthdate = getBirthDate(getBirthdateFromKeyboard());
		System.out.println(getZodiacSign(birthdate));
	}

}
