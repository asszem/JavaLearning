package Exceptions;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Andras Olah (olahandras78@gmail.com)
 */
public class ExceptionsTest {

//Maga a metódus kezeli az Exceptiont
	public static double osztas(int osztando, int oszto) {
		try {
			System.out.println("osztas() method started");
			System.out.print(Integer.toString(osztando) + "/" + oszto + "=");
			System.out.println(osztando / oszto);
			return osztando / oszto;
		} catch (Exception e) {
			System.out.println("Hiba az osztas() metóduson belül: " + e);
			return 1;
		} finally {
			System.out.println("osztas() method vége");
		}
	}

//A hívónak kezelnie kell az ArithmeticExceptiont
	public static double osztas2(int osztando, int oszto) throws ArithmeticException {
		return osztando / oszto;
	}

//Általános hiba esetén saját exception osztályt dob vissza a hívónak
	public static void osztas3(int osztando, int oszto) throws SajatException {
		try {
			double eredmeny = osztando / oszto;
		} catch (ArithmeticException e) {
			SajatException sajatHiba = new SajatException("Nullával való osztás érvénytelen!");
			if (oszto == 0) {
				throw sajatHiba;
			}

		}
	}

	public static void main(String[] args) throws IOException {
//Saját exception dobása
		System.out.println("\t1. példa");
		try {
			SajatException sajat = new SajatException("Saját hibaüzenet amit a getMessage() kap");
			//A konstruktor első argumentuma egy instance paraméter, a második pedig egy Throwable object
			//Ez a példa az exception chainingre.
			SajatException sajat2 = new SajatException(2,sajat);
			throw sajat2;
		} catch (SajatException sajatHiba) {
			System.out.println("Üzenet: "+ sajatHiba.getMessage());
			System.out.println("Severity: "+sajatHiba.sajatSeverity);
			//Ez lett a Throwable objektumként megadva
			System.out.println("Chained exception: "+sajatHiba.getCause().getMessage());
		}
//Testing methods in Throwable class
		System.out.println("\t2. példa");
		try {
			osztas2(10, 0);
		} catch (ArithmeticException e) {
			System.out.println("Throwable \n"
					+ "\n getMessage= " + e.getMessage()
					+ "\n getCause= " + e.getCause()
					+ "\n getStackTrace= " + e.getStackTrace().getClass()
					+ "\n"
					+ "\n");
		}

//Metódus hívása, ami ArithmeticException-re saját exceptiont dob vissza, annak saját instance változóival
		System.out.println("\t3. példa");
		try {
			osztas3(10, 0);
		} catch (SajatException elkapottHiba) {
			System.out.println("1 " + elkapottHiba.getMessage());
			System.out.println("2 " + elkapottHiba.sajatSeverity);
			System.out.println("3 " + elkapottHiba.sajatExceptionValtozo);
			System.out.println("4 " + elkapottHiba.ste[0]);

		}
//Throws-al ellátott metódus hívása csak try-catch blokkal lehetséges
		System.out.println("\t4. példa");
		try {
			//osztas2 method throws Exception-al van írva,
			//Ezért csak akkor lehet hívni, ha az valahol kezelve van.
			//Vagy a main-hez is hozzá van adva, de ha ott sincs kezelve, akkor build failedlesz.
			osztas2(10, 0);
		} catch (Exception e) {
			StackTraceElement[] stektraisz = e.getStackTrace();
			for (StackTraceElement a : stektraisz) {
				System.out.println("StackTrace metódus: " + a.getMethodName());
				System.out.println("StackTrace osztály: " + a.getClassName());
				System.out.println("StackTrace file: " + a.getFileName());
				System.out.println("StackTrace to string(): " + a.toString());
			}
			System.out.println(stektraisz[0].getMethodName());
			e.fillInStackTrace();
//			throw e;
		}

		System.out.println("\t4. példa");
		/*
		//read() method definition in java.io Class throws IOException
		//so it must either catched in code or add throws IOException to main method
//		int read = System.in.read();
//		System.out.println("Read változó:" + read);
		//call method with embedded try-catch-finally blocks 
		osztas(10, 1);
		osztas(10, 0);
		//catch a metóduson kívül
		try {
			System.out.println("try block a main()ben: ");
			System.out.println("10/" + (10.0 / osztas(0, 1)) + "");
			int[] array = new int[4];
			array[5] = 12;
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("Array Index Out of Bounds exception a main()-ben");
		} catch (Exception e) {
			System.out.println("main() exception" + e);
		}
		//Try outside of for cycle - the cycle stops at the exception
		int i = 10;
		try {
			int j = 2;
			for (i = 3; i > -2; i--) {
				System.out.println("Try block entered");
				System.out.print("i=" + i + " j=" + j + " " + j + "/" + i + "=");
				System.out.println(j / i);
				System.out.println("End of try block");
			}
		} catch (ArithmeticException hiba) {
			System.out.println("Arithmetic exception thrown " + hiba);
		} finally {
			System.out.println("Finally block. Ez mindig lefut. Az i értéke: " + i);
		}

		//Try block inside the for cycle - the cycle continues after the exception
		System.out.println("After catch block and first for cycle");
		int j = 2;
		for (i = 3; i > -2; i--) {
			try {
				System.out.println("\nTry block entered");
				System.out.print("i=" + i + " j=" + j + " " + j + "/" + i + "=");
				System.out.println(j / i);
				System.out.println("End of try block, goto next iteration");
				//Nested catch block
				try {
					if (i == 1) {
						System.out.println("Nested catch block entered.");
						int hibaValtozo = (10 / (i - 1));
					}
				} catch (Exception hiba) {
					System.out.println("Nested catch block hiba!" + hiba);
				}
			} catch (ArithmeticException hiba) {
				System.out.println("Arithmetic exception thrown " + hiba);
			} catch (ArrayIndexOutOfBoundsException | ArrayStoreException hiba) {
				System.out.println("Többféle hibát is kezel: " + hiba);
			} catch (IndexOutOfBoundsException hiba) {
				System.out.println("Out of bounds hiba" + hiba);
			} finally {
				System.out.println("Finally block. Ez mindig lefut. Az i értéke: " + i);
			}

		}
		System.out.println("After catch block");
		 */
	}
}
