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
		//<editor-fold desc="Create a new GadgetOwner instance: geek1">
		GadgetOwner geek1 = new GadgetOwner();
		geek1.gadgetOwnerName = "Andras";
		geek1.mobile = new Mobile(geek1.gadgetOwnerName);
		geek1.smartwatch = new Smartwatch(geek1.gadgetOwnerName);
		System.out.println(geek1);
		System.out.println(geek1.mobile);
		System.out.println(geek1.smartwatch);
		//</editor-fold>

		//<editor-fold desc="Write geek1 to a file">
		Path targetFile = Paths.get("J:\\Serialising Objects\\GadgetOwners\\geek1.bin");
		ArrayList objectsContainerArrayList = new ArrayList();
		objectsContainerArrayList.add(geek1);
		ObjectSerialisation.serializeObject(targetFile, objectsContainerArrayList);
		//</editor-fold>

		//<editor-fold desc="Read geek1 from file">
		objectsContainerArrayList.add(ObjectSerialisation.readObject(targetFile));
		String className = objectsContainerArrayList.get(1).getClass().getName();
		System.out.println("Readed Object's Class name =\n " + className);
		//</editor-fold>

		//<editor-fold desc="Display readed geek1">
		GadgetOwner geek1Readed = (GadgetOwner) objectsContainerArrayList.get(1);
		System.out.println("After reading");
		System.out.println(geek1Readed);
		System.out.println(geek1Readed.mobile);
		System.out.println(geek1Readed.smartwatch);
		//</editor-fold>

	}
}
