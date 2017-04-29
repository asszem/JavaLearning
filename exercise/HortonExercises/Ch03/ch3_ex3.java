package HortonExercises.Ch03;

public class ch3_ex3 {

	static int random() {
		int rnd = (int) (Math.random() * 49 + 1);
		if (rnd == 50) {
			System.out.println("Basszus");
		}
		return rnd;
	}

	public static void main(String[] args) {
		/*
		A lottery requires that you select six diﬀerent numbers from the integers 1 to 49. Write a program to do this
for you and generate five sets of entries.

Operacionalizálás 1. 
		OK - vegyen hat véletlen számot és töltse fel vele a tömböt.
		//nézze meg, hogy az első szám egyezik-e bármelyikkel az öt másik közül
		//ha igen, addig keressen új véletlen számot, amíg az nem különbözik mindtől
		//ha nem, akkor menjen a következő számra
		//ismételje meg ezt az egészet a 2-5. számokkal is. A hatodikkal már nem kell. 


Operacionalizálás 2
		//ha az array index nagyobb, mint 0 - a második számtól kezdve
		//a 0. array indextől az array index-1-ig nézze meg, hogy
		//egyezik-e a vizsgált array index az aktuális array indexhez jelölt random számmal
		//ha igen, akkor addig generáljon új random számot, amíg nem lesz különböző. 
		//menjen tovább
		 */
		int setNum = 0;
		int[] arraySet = new int[26];
		int randomCandidate;
		boolean isUnique;
		int equalCounter = 0; //csak úgy érdekességképp, hány hibás próbálkozás volt
		do {
			arraySet[0] = random(); //az első szám bármi lehet
			System.out.print("\nSet " + (++setNum) + ": \t");
//Innen 
			for (int i = 1; i < arraySet.length; i++) { //a második számtól (index=1) induljon az ellenőrzés az utolsó számig
				do {
					isUnique = true;//az első cáfolattal állítjuk false-ra, a while ciklus addig megy, amíg igaz nem lesz/marad
					randomCandidate = random();
					for (int j = 0; j < i; j++) { //a korábbi számokat nézzük mind végig
						if (arraySet[j] == randomCandidate) {
							isUnique = false; //ha csak egyszer is belefut egy ilyenbe, a ciklus újra indul. 
							equalCounter++;
						}
					}
				} while (!isUnique);  //addig fusson, amíg nem ellenőrzött minden korábbi értéket

				//ide csak akkor jusson, ha minden ellenőrzés lement
				arraySet[i] = randomCandidate;
			}
//Eddig
			for (int i : arraySet) {
				System.out.print(i + ", ");
			}
			System.out.println(" ---> egyezések száma:" + equalCounter);
			equalCounter = 0;
		} while (setNum < 5);
	}//main
}//class
