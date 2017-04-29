package PracticeAndTry.Inheritance;

/**
 *
 * @author Andras Olah (olahandras78@gmail.com)
 */
public class RunAllatok {

	public static void main(String[] args) {
		Kutyak bloki = new Kutyak("Blöki", "hím", 23, false, "tacskó", 4);
		FarkasKutyak rex = new FarkasKutyak("Rex", "hím", 4, true, "farkaskutya", 4, "Rendőrkutya");

		//A farkaskutyák konstruktor lett hívva, ami 7 paramétert kér
		Kutyak dox = new FarkasKutyak("ADoxi", "nőstény", 2, false, "Farkaskutya", 4, "Vakvezető");
		System.out.println(dox);
		System.out.println(rex);
		System.out.println(bloki);
		System.out.println("");
		System.out.println(dox.getHogyHivjak());
		System.out.println(dox.getClass().getSimpleName());

		bloki.setEszik("rántott hús");
		dox.setAlszik();
		dox.getMitCsinal();  //Farkaskutyák metódus hívva
		bloki.getMitCsinal(); //Kutyák metódus hívva. Polimorfizmus!
		dox.jellemzes(); //Allatok metódus hívva
	}

}
