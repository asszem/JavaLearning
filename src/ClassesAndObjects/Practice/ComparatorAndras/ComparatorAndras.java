package ClassesAndObjects.Practice.ComparatorAndras;

import java.util.Comparator;


public class ComparatorAndras {
	public static void main(String[] args) {
		MobilePhone[] phones = new MobilePhone[5];
		phones[0] = new MobilePhone("Xavér", 888);
		phones[1] = new MobilePhone("Böbe", 666);
		phones[2] = new MobilePhone("András", 111);
		phones[3] = new MobilePhone("Barnabás", 777);
		phones[4] = new MobilePhone("Zénó", 999);
		java.util.Arrays.sort(phones); //Compares using MobilePhone's class compareTo() method
		CompareMobiles reverseComparator= new CompareMobiles();
		java.util.Arrays.sort(phones,2,4,new CompareMobiles());  //Sorts only values from 2 to 3
		java.util.Arrays.sort(phones, reverseComparator); //Compares using the CompareMobiles class compare() method
		java.util.Arrays.sort(phones); //Sort all values
	}
}

// This class compares two MobilePhone objects and returns result in reverse
// order
class CompareMobiles implements Comparator<MobilePhone> {

	@Override
	public int compare(MobilePhone o1, MobilePhone o2) {
		return o1.type.compareTo(o2.type) * -1; // Reverse order!
	}

	@Override
	/*
	 * This compares the current Comparator<> object with another object of a
	 * type that also implements the Comparator<> interface that you pass as the
	 * argument.
	 */
	public boolean equals(Object comparatorObject) {
		if (this == comparatorObject) // argument is the same object
			return true;
		if (comparatorObject == null) { // argument is null
			return false;
		}
		return getClass() == comparatorObject.getClass();
	}
}

class MobilePhone implements Comparable<MobilePhone>{
	public MobilePhone(String type, int number) {
		this.type = type;
		this.number = number;
	}

	String type;
	int number;

	@Override
	public int compareTo(MobilePhone anotherMobile) {
		return this.type.compareTo(anotherMobile.type);
	}

	@Override
	public String toString(){
		return this.type+" "+this.number+"\n";
	}
	
	
	
}
