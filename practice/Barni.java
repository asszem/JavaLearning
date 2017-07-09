import java.util.Scanner;

public class Barni {

	public static int osszeadasPelda(int egyik, int masik) {
		System.out.print(egyik+" plusz");
		System.out.print(" "+masik+" egyenlő=");
		System.out.println(egyik+masik);
		return egyik+masik;
	}

	public static void main(String[] args) {
//		osszeadasPelda(5, 9);
//		osszeadasPelda(9, 9);
//		osszeadasPelda(10, 9);
		
		Scanner billentyu = new Scanner(System.in); //létrehoz egy 'billentyu' nevű objektumot, amivel be lehet kérni
		
		System.out.print("Írd be az első számot:");
		int szam1=billentyu.nextInt();  //bekér egy számot a billentyűzetről és eltárolja a szam1 változóba

		System.out.println();

		System.out.print("Írd be a második számot:");
		int szam2=billentyu.nextInt();  //ugyanazt csinálj, mint a fenti, csak a szam2 változóba tárolja
		
		osszeadasPelda(szam1, szam2);   //Alkalmazza (úgy mondják, meghívja) az 'osszeadasPelda' metódust
										//a szam1 és szam2 változóban tárolt értékeket fogja összeadni
	}

}
