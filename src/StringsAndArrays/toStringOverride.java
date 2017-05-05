/*
Mi történik akkor, ha toString() néven metódust határozok meg?

Hipotézisek	
	1. A toString() metódus beépített metódusa a java.lang.Object osztálynak, így overrideolom azt
	2. A System.out.println(valtozo) automatikusan a toString-et használja. 
	3. Ha átírom a toString-et, akkor úgy írja ki, ahogy én akarom.

Eredmény:

	1. java.lang.Object osztály része. Még a hint is overridinget ajánl.✔
	2. Igen, és ha paraméternek objektumra hivatkozó változó lett megadva, akkor annak hexadecimális kódját írja ki✔
	3. Igen, de csak annak az osztálynak az objektumait, amelyikben át lett írva a toString()✔

Megfigyelések:
	1. A toString() override-hoz az kell, hogy az override is minimum public legyen
	2. String értéket adjon vissza, nem lehet void
	3. Netbeans javasolja az @Override annotation hozzáadását
	4. Minden egyes osztályban más lehet a toString(), így a kiírás attól függ, melyik osztályban lett indítva
		Például az Integer.toString() és a Char.toString() ettől még nem változik, mert azok más osztályban vannak
	5. If you print any object, java compiler internally invokes the toString() method on the object. 


toString () dokumentáció:
	Returns a string representation of the object. 
	the toString method returns a string that "textually represents" this object.
	The result should be a concise but informative representation that is easy for a person to read.
	It is recommended that all subclasses override this method.

	The toString method for class Object returns 
		a string consisting of the name of the class of which the object is an instance
		the at-sign character `@'
		the unsigned hexadecimal representation of the hash code of the object.
	In other words, this method returns a string equal to the value of:
	 getClass().getName() + '@' + Integer.toHexString(hashCode())
	
Webes források:
	http://stackoverflow.com/questions/10734106/how-to-override-tostring-properly-in-java
		If you print any object, java compiler internally invokes the toString() method on the object. 
		So overriding the toString() method, returns the desired output
 */
package StringsAndArrays;

/**
 *
 * @author Andras Olah (olahandras78@gmail.com)
 */
public class toStringOverride {

	String objVar1 = "String instance változó";
	int objVar2 = 42;
	boolean objVar3 = true;
	String objID;

	/*
	@Override
	public String toString(){
		String returnText="Az objektum változói: \nobjVar1 = "+objVar1+"\nobjVar2="+objVar2+"\nobjVar3="+objVar3;
		return returnText;
	}
	 */

	//Create the return string with a StringBuffer
	@Override
	public String toString() {
		StringBuffer strBuf = new StringBuffer();
		strBuf.append("["+objID+"] "+objVar1);
		strBuf.append("\n").append(String.valueOf("["+objID+"] "+objVar2));
		for (int i = 0; i < 5; ++i) {
			strBuf.append("\n["+objID+"] "+"numbers[")
					.append(String.valueOf(i))
					.append("] = ")
					.append(Math.random() * 5);
		}
		strBuf.append("\n["+objID+"] "+"Boolean: " + objVar3);
		return strBuf.toString();
	}

	public static void main(String[] args) {
		toStringOverride objektum = new toStringOverride();
		objektum.objID="ID01";
		System.out.println(objektum);

		toStringOverride objektum2 = new toStringOverride();
		objektum2.objID="ID02";
		objektum2.objVar1 = "Ez a második objektum";
		objektum2.objVar2 = 21;
		objektum2.objVar3 = false;
		System.out.println(objektum2);

		//Ha másik osztályon nincs toString() override:
		Kutyak bloki = new Kutyak(12, "hím", "bernáthegyi", "Blöki");
		System.out.println(bloki);
		int numberTest = 123;
		System.out.println(Integer.toString(numberTest));
	}
}

class Kutyak{
	public int eletkor;
	public String nem;
	public String faj;
	public String nev;

	public Kutyak(int eletkor, String nem, String faj, String nev){
		this.eletkor=eletkor;
		this.nem=nem;
		this.faj=faj;
		this.nev=nev;
	}
}
