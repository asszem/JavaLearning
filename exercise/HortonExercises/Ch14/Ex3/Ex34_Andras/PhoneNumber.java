package HortonExercises.Ch14.Ex3.Ex34_Andras;

import java.io.*;

class PhoneNumber implements Serializable, Comparable<PhoneNumber> {

	private String areacode;
	private String number;
	private static final long serialVersionUID = 1001L;
	private static BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));

	public PhoneNumber(String areacode, String number) {
		this.areacode = areacode;
		this.number = number;
	}

	// Read a phone number from the keyboard
	public static PhoneNumber readNumber() {
		String area = null;                                                // Stores the area code
		String localcode = null;                                           // Stores the local code
		try {
			System.out.print("Enter area code: ");
			area = keyboard.readLine().trim();
			System.out.print("Enter local code: ");
			localcode = keyboard.readLine().trim();
			System.out.print("Enter the number: ");
			localcode += " " + keyboard.readLine().trim();
		} catch (IOException e) {
			System.err.println("Error reading a phone number.");
			e.printStackTrace();
			System.exit(1);
		}
		return new PhoneNumber(area, localcode);
	}

	@Override
	public String toString() {
		return areacode + " " + number;
	}

	//Override equals so that we can compare PhoneNumber objects for equality based on areacode and number fields values
	@Override
	public boolean equals(Object comparedToPhoneNumber) {
		//Verify if argument is a 'PhoneNumber' class. 
		if (!comparedToPhoneNumber.getClass().getSimpleName().equals("PhoneNumber")) {
			return false;  //its not, so it can't be equal
		} else {  //It is a PhoneNumber, so we can cast the argument object to PhoneNumber and compare
			return (this.compareTo((PhoneNumber) comparedToPhoneNumber) == 0); //True if 0. compareTo() method also @Overridden
			//Note:  the comparison could have been completed at this point as well, without @overriding compareTo
		}
	}

	@Override
	public int compareTo(PhoneNumber comparedToPhoneNumber) {
		//Determine if two PhoneNumber objects are the same based on the areacode and number string comparison
		if (this.areacode.equals(comparedToPhoneNumber.areacode)
				&& this.number.equals(comparedToPhoneNumber.number)) {
			return 0;
		}
		//If not, return 1 regardless of their relation
		return 1;
	}

	@Override  //Because PhoneNumber class will be used as Key as well, the hashCode() method needs to be overridden to use fields
	public int hashCode() {
		return 7 * areacode.hashCode() + 13 * number.hashCode();
	}

	public String getAreaCode() {
		return areacode;
	}

	public String getPhoneNumberNotAreaCode() {
		return number;
	}
}
