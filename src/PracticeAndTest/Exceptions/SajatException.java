package PracticeAndTest.Exceptions;

/**
 *
 * @author Andras Olah (olahandras78@gmail.com)
 */
public class SajatException extends Exception {

	public String sajatExceptionValtozo;
	public StackTraceElement[] ste;
	public int sajatSeverity;

	public SajatException() {
	}

	//a GetMessage értéke a konstruktor argumentuma lesz 
	public SajatException(String ok) {
		super(ok);	//Az Exception osztály konstruktorát hívja
		sajatExceptionValtozo = "Ide bármit írhatok";
		sajatSeverity = 10;
		ste = super.getStackTrace();
		//A getMessage ezt szerzi meg és írja ki
	}
//A második paramétert fogja a Throwable osztály getCause() metódusa visszaadni
	public SajatException(int severity, Throwable eredetiHibaEzACause){
		super("Chained teszt",eredetiHibaEzACause);
		sajatSeverity = severity;
	}

//Csak az okot adja át paramétrnek, szöveget nem
	public SajatException(Throwable cause){
		super(cause);
		//Bármi egyéb speciális kód ide, amiért szükség volt erre
	}
}

