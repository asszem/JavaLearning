package Variables;

/**
 *
 * @author Andras Olah (olahandras78@gmail.com)
 */
public class Enums {
	//Enumeration    
	//This defines a new type, Day, for variables that can store only one or other of the values specified between the braces. 
	//Note that enum types cannot be local to a method (nem lehet a main-ben)

	// an enumeration type is a special form of class. When you define an enumeration type in your code, the enumeration constants that you specify are created as instances of a class that has the Enum class, which is defined in the java.lang package, as a superclass
	//Because an enumeration is a class, you have the possibility to add your own methods and fields when you define the enumeration type.
	//You can also add your own constructors to initialize any additional fields you introduce. 
	enum Day {
		Monday, Tuesday, Wednesday, Thursday, Friday, Saturday, Sunday
	} //nem kell ; a végére, mert nincs konstruktor ami paramétert kapna, a default konstruktor oké

	public enum Emberek { //Az Enum típus változó nevének nagybetűvel kezdése konvenció
		Andras, Barni, Benő, Anikó; //még ha noargs konstruktor van csak, akkor is kell pontosvessző már
		//No args konstruktor

		Emberek() {
		}
	}

	enum Month {
		January, February, March, April, May, June,
		July, August, September, October, November, December
	}

	//Enums can be defined as a Class, with their own constructors and methods
	public enum Kabatok {
		Kicsi(10), Közepes(20), Nagy(30), Extra_Nagy(40); //itt kell ; a végére, mert a konstruktor paramétert kapott.
		private int kabatMeret;

		//konstruktor a paraméterrel
		Kabatok(int meret) {  //Egy enum konstruktor csak noAccess vagy private lehet, public nem. 
			this.kabatMeret = meret;
		}

		public int getMeret() {
			return kabatMeret;
		}

		@Override //A Kabatok kiíráát írjuk felül, ne a konkrét változót írja ki, hanem amit megadunk 
		public String toString() {
			switch (this) {
				case Kicsi:
					return "S méret: "+kabatMeret+" ordinal:"+ordinal();
				case Közepes:
					return "M méret: "+kabatMeret+" ordinal:"+ordinal();
				case Nagy:
					return "L méret: "+kabatMeret+" ordinal:"+ordinal();
				case Extra_Nagy:
					return "XL méret: "+kabatMeret+" ordinal:"+ordinal();
				default:
					return "difolt méret: "+kabatMeret+" ordinal:"+ordinal();
			}
		}
	}

	public static void main(String[] args) {
		System.out.println("A Kabatok enum összes lehetséges értékének kiírása");
		for (Kabatok kabatOpcio:Kabatok.values()){
			System.out.println(kabatOpcio);
		}

		System.out.println("A dzsekik[]-ben lévő kabátok enum értékei:");
		Kabatok dzsekik[] = {Kabatok.Közepes,Kabatok.Extra_Nagy,Kabatok.Extra_Nagy};
		for (Kabatok out:dzsekik){
			System.out.println(out);
		}

		System.out.println("Egy konkrét elem (dzsekik[0]) mérete:"+dzsekik[0].getMeret());


		
		Emberek apus = Emberek.Andras;
		Month currentMonth = Month.May;
		Month favoriteMonth = currentMonth;

		System.out.println("Az Emberek enum típus értékei és sorrendje (ordinal value):");
		for (Emberek aktualisEmber : Emberek.values()) {
			System.out.print(aktualisEmber + "(" + aktualisEmber.ordinal() + "), ");
		}
		System.out.println("\napus.toString() = " + apus.toString());

//equals()
		Emberek csaladfo = Emberek.Andras;
		boolean csaladfoE = apus.equals(csaladfo);
		System.out.println("A családfő változó és az apus változó értéke megegyezik-e? " + csaladfoE);

//compareTo()		
		//Negative - ordinal value of current instance is less than the argument instance
		//Positive - ordinal value of current instance is greater than the argument instance
		//Zero - they are the same
		int ordinalNegativeTest = apus.compareTo(Emberek.Anikó);
		int ordinalPositiveTest = Emberek.Barni.compareTo(apus);
		int ordinalEqualTest = apus.compareTo(Emberek.Andras);
		//	System.out.println("Ordinal() Andras: "+Emberek.Andras.ordinal());
		//	System.out.println("Ordinal() Anikó: "+Emberek.Anikó.ordinal());
		//	System.out.println("Ordinal() Barni: "+Emberek.Barni.ordinal());
		System.out.println("Negatív teszt: " + ordinalNegativeTest);
		System.out.println("Pozitív teszt: " + ordinalPositiveTest);
		System.out.println("Equal teszt: " + ordinalEqualTest);
	}

}
