/*This class is used to practice Object serialization*/
package FilesAndDirectories.H12_Serialization;

import java.io.Serializable;
import java.util.Formatter;

/**
 *
 * @author Andras Olah (olahandras78@gmail.com)
 */
public class Smartwatch extends GadgetOwner implements Serializable {

	public String watchType;
	public double watchOSVersion;
	private int watchPINCode;
	transient String timestamp;

	public Smartwatch() {
		watchType = "Pebble";
		watchOSVersion = 2.0;
		watchPINCode = 6666;
		timestamp = "Never!";
	}

	public Smartwatch(String ownerName) {
		this();
		gadgetOwnerName = ownerName;
	}

	@Override
	public String toString() {
		Formatter returnStr = new Formatter();
		returnStr.format("Owner=%s%nWatch type=%s%nWatch OS Version=%f%nWatch PIN Code=%d%nTimestamp=%s%n", gadgetOwnerName, watchType, watchOSVersion, watchPINCode, timestamp);
		return returnStr.toString();
	}
}
