/*This class is used to practice Object serialization*/
package FilesAndDirectories.H12_Serialization;

import java.io.Serializable;
import FilesAndDirectories.ObjectSerialisation;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 *
 * @author Andras Olah (olahandras78@gmail.com)
 */
public class GadgetOwner implements Serializable {

	public String gadgetOwnerName;
	public Mobile mobile;
	public Smartwatch smartwatch;

	public GadgetOwner() {
	}

	@Override
	public String toString() {
		StringBuffer returnStr = new StringBuffer();
		returnStr.append("Gadget owner: ").append(gadgetOwnerName).append("\n");
		return returnStr.toString();
	}

	public static void main(String[] args) {
		GadgetOwner geek1 = new GadgetOwner();
		geek1.gadgetOwnerName = "Andras";
		geek1.mobile = new Mobile(geek1.gadgetOwnerName);
		geek1.smartwatch = new Smartwatch(geek1.gadgetOwnerName);
		System.out.println(geek1);
		System.out.println(geek1.mobile);
		System.out.println(geek1.smartwatch);
		Path targetFile = Paths.get("J:\\Serialising Objects\\GadgetOwners\\geek1.bin");
		ArrayList objectsContainerArrayList =new ArrayList();
		objectsContainerArrayList.add(geek1);
		ObjectSerialisation.serializeObject(targetFile, objectsContainerArrayList);
		objectsContainerArrayList.add(ObjectSerialisation.readObject(targetFile));
		GadgetOwner geek1Readed = (GadgetOwner) objectsContainerArrayList.get(1);
	}
}
