package PracticeAndTest.Inheritance;

/**
 *
 * @author Andras Olah (olahandras78@gmail.com)
 */
public interface Viselkedes extends Maganhangzok {

	void setEszik(String Kaja); //"A [kutya] [csontot] eszik" vagy "A [kutya] [húst] eszik".

	void setIszik(String Pia); //"A [macska] [vizet] iszik".

	void setAlszik(); //Az [aranyhörcsög] alszik."

	String getMitCsinal(); //[állatnév] épp alszik

	String getHogyHivjak(); //"A [kutya] neve: Öfli"

	String getMilyenFaj(); //"A [állatnév] : kutya"
}
