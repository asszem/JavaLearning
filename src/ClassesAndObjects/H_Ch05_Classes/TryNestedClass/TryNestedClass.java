package ClassesAndObjects.H_Ch05_Classes.TryNestedClass;

public class TryNestedClass {

	static public void main(String[] args) {
		// Create three magic hats and output them
		System.out.println(new MagicHat("Gray Topper"));
		System.out.println(new MagicHat("Black Topper"));
		System.out.println(new MagicHat("Baseball Cap"));
		System.out.println(new MagicHat("Apus sapka"));

		//Creating an independent rabbit using the Constructor, that is not in a hat. This works only if the Rabbit class is static.
		System.out.println("Független nyúl: " + new MagicHat.Rabbit());

		//Ha nem static lenne, akkor előbb meg kéne csinálni a Magic Hat osztályt neki.
		MagicHat ujSapka = new MagicHat("Új sapka");
		MagicHat.Rabbit ujNyuszi = new MagicHat.Rabbit();
		System.out.println(ujSapka);
		System.out.println("Az új nyuszi neve: "+ujNyuszi);  //És ez a nyuszi objektum NINCS benne az új sapkában!

		//Ha nem statikus osztályt akarunk
		MagicHat.nonStaticTest nonStaticNewObject = ujSapka.new nonStaticTest();  //meg kell adni, melyik OBJEKTUMON akarjuk létrehozni az új objektumot
		System.out.println("Nem statikus objektum: "+nonStaticNewObject);
			
	
	}
}
