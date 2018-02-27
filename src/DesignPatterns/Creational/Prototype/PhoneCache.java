package DesignPatterns.Creational.Prototype;

import java.util.Hashtable;

public class PhoneCache {

	private static Hashtable<String, Phone> phoneMap = new Hashtable<String, Phone>();

	public static Phone getPhone(String id) {
		Phone cachedPhone = phoneMap.get(id); // Gets the original Phone object
		return (Phone) cachedPhone.clone(); // Returns a CLONE of that object
	}

	// this is to load some objects to the cache
	public static void initializeCache() {
		IPhone iphone = new IPhone();
		Samsung samsung = new Samsung();
		Huawei huawei = new Huawei();

		iphone.setID("1");
		samsung.setID("2");
		huawei.setID("3");

		phoneMap.put(iphone.getID(), iphone);
		phoneMap.put(samsung.getID(), samsung);
		phoneMap.put(huawei.getID(), huawei);
	}

	// Only for verification purpose
	public static void displayCache() {
		phoneMap.forEach((id, phone) -> {
			System.out.println("\nPhone id: " + phone.getID());
			System.out.println("Phone type: " + phone.getType());
			System.out.println("Phone class: " + phone.getClass().getSimpleName());
			System.out.println("Phone hash: " + phone.hashCode());
		});
	}

	public static Hashtable<String, Phone> getPhoneMap() {
		return phoneMap;
	}
}
