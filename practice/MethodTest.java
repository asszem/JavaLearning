/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author olaha
 */
public class MethodTest {

    int noveldEggyel(int x) {    //pass-by-value demonstrációhoz
        ++x;                    //a megadott argumentet eggyel növeli
        return x;
    }

    public static void main(String[] args) {
        MethodTest objektum1 = new MethodTest();
        //<editor-fold desc="pass-by-value demonstration">
        int eredeti = 10;
        System.out.println("Eredeti előtte= " + eredeti);
        int novelt = objektum1.noveldEggyel(eredeti);                               //az eredeti változó értéke nem változik a függvény hatására!
        System.out.println("Eredeti utána= " + eredeti + " növelt értéke=" + novelt);
    }
    //</editor-fold>
}
