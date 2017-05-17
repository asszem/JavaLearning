package practiceAndTry;

public class PentagonArea {

	public static void main(String[] args) {
		double a = Integer.parseInt(args[0]);
		double b = Integer.parseInt(args[1]);
		double c = Integer.parseInt(args[2]);
		double d = Integer.parseInt(args[3]);
		double e = Integer.parseInt(args[4]);
		if (args.length < 5) {
			System.out.println("Nincs elég oldal adat megadva.");
		} else {
			//a=1000; uncomment az összeérés tesztelésére
			//a=2;    uncomment a szabályosság tesztelésére
			if (a > b + c + d + e || b > a + c + d + e || c > a + b + d + e || d > a + b + c + e || e > a + b + c + d) {
				System.out.println("Az ötszög oldalai nem érnek össze");
			} else if (a != b || a != c || a != d || a != e) {
				System.out.println("Nem szabályos ötszög");
			} else {
				// Szabályos ötszög területe
				// http://www.calculat.org/hu/terulet-kerulet/otszog.html

				// Teszt: oldal hossz:5, terület: 43.01
				double terulet = ((Math.sqrt(25.0 + (10.0 * Math.sqrt(5.0)))) / 4.0) * (a * a);
				System.out.println("Oldalhossz: " + a);
				System.out.println("Terület: " + terulet); // 43.01193501472417

				// Teszt2: oldal hossz: 42.42, terület: 3 095.92
				a = 42.42;
				terulet = ((Math.sqrt(25.0 + (10.0 * Math.sqrt(5.0)))) / 4.0) * (a * a);
				System.out.println("Oldalhossz: " + a);
				System.out.println("Terület: " + terulet); // 3095.924069545181
			}
		}
	}

}
