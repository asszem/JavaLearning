
package ClassesAndObjects.Practice.TryInheritance;

/**
 * 
 * @author Andras Olah (olahandras78@gmail.com)
 */
public class Allatok implements Maganhangzok{
	String faj;

	//Kérdés, ezeket a változókat nem objektumonként kellene-e definiálni?
	String nev;
	String nem;
	String kaja; 
	String pia;
	String tevekenyseg;
	int eletkor;
	int labakSzama;
	boolean ragadozo;

	void jellemzes(){
		StringBuffer nevelo= new StringBuffer("A");
	for (char test:MAGANHANGZOK){
		if (test == nev.charAt(0)) nevelo.append("z");
	}
		String jellemzes =nevelo+" "+nev+" egy "+faj+" aki éppen "+tevekenyseg;
		System.out.println(jellemzes);
	}

}
