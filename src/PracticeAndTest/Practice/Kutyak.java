package PracticeAndTest.Practice;

public class Kutyak{ //Osztály 
        //Instance variable
        int eletkor;
        String nem;
        String fajta;
        String nev;
        
        //Konstruktor 4 paraméterrel. A konstruktor névnek ugyanannak kell lennie, mint az osztálynévnek. 
        public Kutyak (int kutyakEletkor, String kutyakNem, String kutyakFajta, String kutyakNev) {
            eletkor = kutyakEletkor;
            nem = kutyakNem;
            fajta = kutyakFajta;
            nev = kutyakNev;
        }//end of Kutyak konstruktor
     
        //Metódusok
        //Method: a collection of statements that are grouped together to perform an operation (also caleld a function)
        public void ugatas() {                  //paraméter nélkül, VOID = a metódus nem ad vissza semmilyen értéket. 
            System.out.println(nev + "Vau! Vau! Vau!");
            int labakSzama=4;
        }// ugatas() method vége
        public void ugatas2(){
            System.out.println(nev + " " + "Vaúúúúúúúúúúúúúúúúúúúúúúúúúúúúúúúúúúúúúúúúúúúúúúúúúúúú!");
        }
        public int seta(int napiSeta) {        //a metódus kiírja, hogy a bevitt paraméter alapján mennyit sétált és vissza is adja azt értéknek
            System.out.println(nev+" ma " + napiSeta + " percet sétált.");
            return napiSeta;
        }
        public int labakSzama (){ //a metódus négyet ad vissza
            return 4;
        }
        public String milyenNemu(){ //a metódus az objektumban tárolt nem változó értékét adja vissza
            return nem;
        }
        
     public static void main(String[] args) { 	//main METHOD in which the program runs.
        Kutyak bloki = new Kutyak(10, "hím", "vizsla", "Blöki"); //Blöki objektum létrehozva
        Kutyak morzsa = new Kutyak(5, "nőstény","farkaskutya","Morzsa"); //morzsa objektum létrehozva
        //System.out.println("A kutyusunk neve: " + morzsa.eletkor);
        //bloki.ugatas2();
        //bloki.ugatas();
        //System.out.println("Mennyit sétált ma a kutyus? A válasz: " + bloki.seta(10));
        
        morzsa.seta(15);
        
        /*
        bloki.ugatas(); //ugatas metódus meghívva blökire
        bloki.seta(5); //séta metódus meghívva blökire
        int setaFika = bloki.seta(6); // mivel a seta() metódus a bevitt paramétert adja vissza, ezért 6-ot fog átadni a setaFika változónak és az értékét is megváltoztatja
        System.out.println("Sétafika változó értéke: "+ setaFika);
        int kutyaLabSzam = bloki.labakSzama();
        System.out.println("\nLábak száma: " + kutyaLabSzam + " kutyus neme: "+bloki.milyenNemu());
        System.out.println("\nObjektum: "+bloki+"\nNév: "+bloki.nev+"\nÉletkor: "+bloki.eletkor + "\nNem: "+bloki.nem + "\nFajta: "+bloki.fajta);
*/
	}//end of main() method
}    
  

