package ClassesAndObjects.Practice;

public class Horcsog {
//Instance variables

	private String nev;
	private int eletkor;
	private String szinek;
	private String jellemzok;
	private String nem;
	private String gazdaja;
	private int letrehozasSorrendje=0;

//Static varibales
	public static int horcsogObjektumokSzama=0;
	
//Constructors
	public Horcsog(String nevP, String nemP, String szinekP, String gazdajaP, int eletkorP) {
		nev = nevP;
		nem = nemP;
		szinek = szinekP;
		jellemzok = "teszt";
		gazdaja = gazdajaP;
		eletkor = eletkorP;
		letrehozasSorrendje=++horcsogObjektumokSzama;
	}

//No args constructor 
	public Horcsog() {
		nev = "Alap Hörcsög";
		nem = "semleges";
		szinek = "seszín";
		jellemzok = "jellegtelen";
		gazdaja = "Andras";
		eletkor = 0;
		letrehozasSorrendje=++horcsogObjektumokSzama;
	}

//Accessors
	public String getGender() {   //Accessor or Getter --- this can be used in other packages to reach the "nem" variable
		return nem;
	}

	public String getName() {
		return nev;
	}

	public int getEletkor() {
		return eletkor;
	}

//Mutators
	public void setGender(String newGender) {
		nem = newGender;
	}

	public void setName(String newName) {
		nev = newName;
	}

	public void setEletkor(int newEletkor) {
		eletkor = newEletkor;
	}

//Overrides
	//A System.out.println(objektumnev) ezt írja ki:
	@Override
	public String toString() {
		return 	"A hörcsögünk neve: " + nev + 
				"\n Tulajdonságai: színe: " + szinek + " neme: " + nem + " gazdája: " + gazdaja + " életkora: " + eletkor + "."
				+ "\n "+nev+" a "+letrehozasSorrendje+". hörcsög objektum. Összesen "+horcsogObjektumokSzama+" hörcsögobjektumunk van.";
	}

	public static void main(String[] args) {
//		Horcsog ofli = new Horcsog("Öfli", "nőstény", "fekete (fehér)", "közös", 9);
//		System.out.println("A hörcsögünk neve: " + ofli.nev + " színe: " + ofli.szinek + " neme: " + ofli.nem + " gazdája: " + ofli.gazdaja + " életkora: " + ofli.eletkor + ".");
//
//		Horcsog perec = new Horcsog("Perec", "nőstény", "barnás", "Barni", 8);
//		System.out.println("A másik hörcsögünk neve: " + perec.nev + " színe: " + perec.szinek + " neme: " + perec.nem + " gazdája: " + perec.gazdaja + " életkora: " + perec.eletkor + ".");

	}
}
