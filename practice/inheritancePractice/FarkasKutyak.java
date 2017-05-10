package inheritancePractice;

/**
 *
 * @author Andras Olah (olahandras78@gmail.com)
 */
public class FarkasKutyak extends Kutyak implements Viselkedes {

	String farkasKutyaTípus;

	public FarkasKutyak(String nev, String nem, int eletkor, boolean harap, String kutyaFaj, int labakSzama, String farkasKutyaTípus) {
		super(nev, nem, eletkor, harap, kutyaFaj, labakSzama);
		this.farkasKutyaTípus = farkasKutyaTípus;
	}

	@Override
	public void setEszik(String kaja) {
		this.kaja = kaja;
		this.tevekenyseg = "lakmározik";
	}

	@Override
	public void setIszik(String pia) {
		this.pia = pia;
		this.tevekenyseg = "piál";
	}

	@Override
	public void setAlszik() {
		tevekenyseg = "szunyál";
	}

	@Override
	public String getMitCsinal() {
		System.out.println(nev+ " jelenleg " + tevekenyseg);
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

		String rtn = super.toString()+"\nFarkaskutya Típusa: "+farkasKutyaTípus;
		return rtn;
	}
}
