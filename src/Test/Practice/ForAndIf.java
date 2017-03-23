package Test.Practice;
import java.util.ArrayList;

public class ForAndIf {
    public static int statikusForAndIfBen;
    
      static int[] initStaticArray = new int[10];                   //creates a new array for 10 elements, without any value
        static {                                                    // Static Initialization block
            System.out.println("Running STATIC initialization block.");
            for(int i = 0 ; i < initStaticArray.length ; ++i) {
                initStaticArray[i] = (int)(100.0*Math.random());    //assign a random number for each element in the array
            }
        }
        
    public static void main(String[] args) {
        
        //Tömb létrehozása a ciklusokhoz
        ArrayList<Integer> tombLista = new ArrayList<Integer>();
        //for loop
        for (int i =0; i < 3; i++){
            tombLista.add(i+3);
            //System.out.println(i);
            }//end of for

        /*for each loop
        for (Integer variableName : arrayName){}
        The colon (:) can be read as "in". The for each loop altogether can be read as
        "for each Integer element (called variableName) in arrayName, print out the value of ArrayName"
        */
        for (Integer tombListaErtek : tombLista) {
            System.out.println(tombListaErtek);
            }
	
               
        
        //Ternary operator
        boolean logikai = true;
        String igazE;
        igazE = (!logikai) ? "Igaz" : "Hamis";
        System.out.println(igazE);
        
    
    
    
    
    
    }//end of main
}//end of class
