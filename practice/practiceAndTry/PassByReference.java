package practiceAndTry;

public class PassByReference {

	int number = 0;

	void increaseNumber() {
		number++;
	}

	void printNumber() {
		System.out.println(number);
	}

	void test(final PassByReference inputObject){
		System.out.println("Metódus hívásakor a number változó értéke a paraméter objektumon:"+inputObject.number);
		System.out.println("Metódus hívásakor a number változó értéke a this objektumon:"+this.number);
		inputObject.number += 10;
	}

	//Konstruktor új, de az argumentumnak megadott objektum tulajdonságaival
	PassByReference (final PassByReference oldObject){
		number = oldObject.number;
	}
	//No args konstruktor is hozzá kell adni, ha már van argsos
	public PassByReference() {
	}
	
	public static void main(String[] args) {
		PassByReference valtozo1 = new PassByReference();
		PassByReference valtozo2 = valtozo1;
		PassByReference tokmasobj = new PassByReference();
		tokmasobj.increaseNumber();
		tokmasobj.increaseNumber();
		System.out.println("valtozo2 értéke: " + valtozo2.number);
		System.out.println("tokmasob értéke: " + tokmasobj.number);
		valtozo2.increaseNumber();
		valtozo1.printNumber();
		valtozo1.test(tokmasobj);
		tokmasobj.printNumber();
		PassByReference tokmasobjMasolat = new PassByReference(tokmasobj);
		tokmasobjMasolat.printNumber();
	}
}
