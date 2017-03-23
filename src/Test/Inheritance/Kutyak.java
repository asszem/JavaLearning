package Test.Inheritance;

/**
 *
 * @author Andras Olah (olahandras78@gmail.com)
 */
public class Kutyak extends Allatok implements Viselkedes {

	boolean harap;

	Kutyak(String nev, String nem, int eletkor, boolean harap, String kutyaFaj, int labakSzama) {
		this.nev = nev;
		this.nem = nem;
		this.eletkor = eletkor;
		this.harap = harap;
		this.faj = kutyaFaj;
		this.labakSzama = labakSzama;
	}

	@Override
	public void setEszik(String kaja) {
		this.kaja = kaja;
		this.tevekenyseg ="eszik";
	}

	@Override
	public void setIszik(String pia) {
		this.pia = pia;
		this.tevekenyseg ="iszik";
	}

	@Override
	public void setAlszik() {
		tevekenyseg = "alszik";
	}

	@Override
	public String getMitCsinal() {
		System.out.println(nev+" éppen " + tevekenyseg);
		return tevekenyseg;
	}

	@Override
	public String getHogyHivjak() {
		return nev;
	}

	@Override
	public String getMilyenFaj() {
		return faj;
	}

	public String toString() {
		String rtn ="\nA kutya neve:\t" + nev 
				+ "\nJava osztály:\t" + getClass().getSimpleName()
				+ "\nhashCode:\t" + hashCode()
				+ "\nFaja:\t\t" + faj 
				+ "\nÉletkora:\t" + eletkor
				+ "\nHarap?\t\t" + (harap ? "harapós": "nem harapós")
				+ "\nLábainak száma:\t"+labakSzama ;
		return rtn;
	}

}
