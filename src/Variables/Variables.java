package Variables;

public class Variables {
//Variables
	int miles = 0, // One mile is 8 furlongs
					furlongs = 0, // One furlong is 220 yards
					yards = 0, // One yard is 3 feet
					feet = 0;
	double sunDistance = 1.496E8;
	float electronMass = 9E-28F;  //negatív hatványon: 1/szám, azaz itt 1/9x10^28Floaating, azaz 0.0000000000000000000000000009

	final int FEET_PER_YARD = 3; // Constant values
	final double MM_PER_INCH = 25.4; // that cannot be changed

	public static void happyBirthday(int age) {
		boolean sweet_sixteen = (age == 16);
		boolean majority = (age == 21);
		boolean adult = (age > 21);
		boolean decade = (age % 10) == 0;
		boolean quarter = (age % 25) == 0;

		if (sweet_sixteen || majority || (adult && (decade || quarter))) {
			System.out.println("Super special party, this year!");
		} else {
			System.out.println("One year older. Again.");
		}
	}

	public static void main(String[] args) {
		happyBirthday(16);

		boolean state = true;
		System.out.println("state= " + state);
		System.out.println("!state = " + !state);
		if (!state) { //ha a változó aktuális állapotával ellentétes érték az igaz. if !state=false, ami itt nem igaz, mert a !state = false.
			//Ugye nem igaz, hogy igaz? HAMIS
			System.out.println("(!state = true) evaulated as TRUE");
		} else {
			System.out.println("(!state = true) evaulated as FALSE");
		}
		state = false;
		System.out.println("state= " + state);
		System.out.println("!state = " + !state);
		if (!state) { //A változó alapértéke HAMIS, ennek ellentéte IGAZ, tehát a kiértékelés EZÉRT igaz. Úgy is le lehetne írni, hogy if !state = true
			//Ugye nem igaz, hogy igaz? IGAZ
			System.out.println("(!state = true) evaulated as TRUE");
		} else {
			System.out.println("(!state = true) evaulated as FALSE");
		}
		state = true;
		System.out.println("Ha state=" + state + ", akkor if (!state) kiértékelése: " + (!state ? "igaz" : "hamis"));
		state = false;
		System.out.println("Ha state=" + state + ", akkor if (!state) kiértékelése: " + (!state ? "igaz" : "hamis"));

		char yesno = 'N';
		System.out.println("Yes lett kiválasztva? " + (Character.toLowerCase(yesno) == 'y' ? "igen" : "nem"));

		final int EGY = 1;
		final int KETTO = 2;
		System.out.println(EGY + KETTO);

		int a = 42;
		Integer b = Integer.valueOf(a);
		System.out.println(b);
		String c = "41";
		boolean eredmeny = a == Integer.valueOf(c);
		System.out.println(eredmeny);
	}
}
