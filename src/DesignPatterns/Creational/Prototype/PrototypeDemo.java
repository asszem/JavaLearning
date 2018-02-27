package DesignPatterns.Creational.Prototype;

import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;

public class PrototypeDemo {
	public static void main(String[] args) {
		PhoneCache.initializeCache();

		// Generating the clones
		Phone clonedIphone1 = (Phone) PhoneCache.getPhone("1");
		Phone clonedIphone2 = (Phone) PhoneCache.getPhone("1");
		Phone clonedSamsung = (Phone) PhoneCache.getPhone("2");
		Phone clonedHuawei = (Phone) PhoneCache.getPhone("3");

		// Verification purposes only
		System.out.println("Cache content:");
		PhoneCache.displayCache();
		System.out.println("\nCloned objects:");

		List<Phone> clones = Arrays.asList(clonedIphone1, clonedIphone2, clonedSamsung, clonedHuawei);
		Hashtable<String, Phone> originals = PhoneCache.getPhoneMap();

		clones.forEach((phone) -> {
			System.out.println("\nCloned class type=\t" + phone.getClass().getSimpleName());
			System.out.println("Cloned phone type:\t" + phone.getType());
			System.out.println("Cloned phone id: \t" + phone.getID());

			System.out.println("Class type equity=\t" + (originals.get(phone.getID()).getClass() == phone.getClass()));
			System.out.println("Object equity=\t\t" + (originals.get(phone.getID()) == phone));

			System.out.println("Original phone hash:\t" + originals.get(phone.getID()).hashCode());
			System.out.println("Cloned phone hash:\t" + phone.getClass().hashCode());
		});
	}

}
