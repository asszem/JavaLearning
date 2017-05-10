
package inheritancePractice;

/**
 * 
 * @author Andras Olah (olahandras78@gmail.com)
 */
public class Horcsogok extends Allatok implements Viselkedes{

	@Override
	public void setEszik(String kaja) {
		this.kaja = kaja;
	}

	@Override
	public void setIszik(String pia) {
		this.pia=pia;
	}

	@Override
	public void setAlszik() {
		tevekenyseg ="alszik";
	}

	@Override
	public String getMitCsinal() {
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
}
