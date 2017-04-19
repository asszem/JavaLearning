/*This class is used to practice Object serialization*/
package FilesAndDirectories.H12_Serialization;

import java.io.Serializable;
import java.util.Formatter;

/**
 *
 * @author Andras Olah (olahandras78@gmail.com)
 */
public class Mobile extends GadgetOwner implements Serializable {

	public String mobileType;
	public int mobileNumber;
	private int mobilePINCode;
	transient String timestamp;

	public Mobile() {
		mobileType = "OnePlus3";
		mobileNumber = 123456789;
		mobilePINCode = 9999;
		timestamp = "now!";
	}

	public Mobile(String ownerName) {
		this();
		gadgetOwnerName = ownerName;
	}

	@Override
	public String toString() {
		Formatter returnStr = new Formatter();
		returnStr.format("Owner=%s%nMobile type=%s%nPhone number=%d%nMobile PIN Code=%d%nTimestamp=%s%n", gadgetOwnerName, mobileType, mobileNumber, mobilePINCode, timestamp);
		return returnStr.toString();
	}

}
