package Practice;
/*<editor-fold desc="Array Lists info">
ArrayList<Integer> weeklyTemperatures = new ArrayList<Integer>();
weeklyTemperatures.add(10); // Index: 0
System.out.println(weeklyTemperatures.size());
System.out.println(weeklyTemperatures.get(indexNumber));
System.out.println(weeklyTemperatures.toString());

Source: 
https://www.codecademy.com/courses/learn-java/lessons/data-structures/exercises/arraylist?action=lesson_resume&link_content_target=interstitial_lesson

ArrayList is a pre-defined Java class. To use it, we must first create an ArrayList object.
 
1. Create array list object: ArrayList<type> objectname = new ArrayList<type>();
2. The add method adds integers to the ArrayList objectname.add(index, value);
3. Access elements, by method object.get(index);
4. Size of the array (number of elements): object.size();

We can access the elements of weeklyTemperatures by using an element's index, or position, in the list.
An element's index refers to its location within an ArrayList. 
ArrayLists in Java are zero-indexed, which means that the first element in an ArrayList is at a position of 0.

 </editor-fold>*/

//<editor-fold desc="HashMap info">
/*
HashMap<String, Integer> mobilTelefonok = new HashMap<String, Integer>();
mobilTelefonok.put("Barni", 123);
mobilTelefonok.size();
System.out.println(mobilTelefonok.get("Barni"));
System.out.println(mobilTelefonok.toString());

HashMaps
To access data in an ArrayList, we specified the index. In order to access a value in a HashMap, we specify the key:
</editor-fold>*/

import java.util.ArrayList;
import java.util.HashMap;
public class ArrayLists_HashMaps {
    
    public static void main(String[] args) {
      //Class               object(instance)    értékét most adjuk meg, azaz hozzuk létre.
        ArrayList<Integer> weeklyTemperatures = new ArrayList<Integer>();
        weeklyTemperatures.add(10); // Index: 0
        weeklyTemperatures.add(78); // Index: 1
        weeklyTemperatures.add(67);
        weeklyTemperatures.add(89);
        weeklyTemperatures.add(94); 
        //System.out.println(weeklyTemperatures.get(1));
        weeklyTemperatures.add(1, 100); //Adds the value 100 to the Index: 1 and shifts everything else down, 78 will be Index 2
        //System.out.println(weeklyTemperatures.get(1));
        //System.out.println(weeklyTemperatures.get(2));
        System.out.println("weeklyTemperatures.size() = " + weeklyTemperatures.size());
        System.out.println(weeklyTemperatures.toString());
        
        for (int j = 0; j<weeklyTemperatures.size(); j++){
            System.out.print(weeklyTemperatures.get(j) + ", ");
            if (j == weeklyTemperatures.size()-1) {System.out.println("\n" + j);} //hogy egy sortörést beszúrjon az utolsó iteráció után
            }
        for (Integer temperatureOut : weeklyTemperatures){
            System.out.print(temperatureOut + ", ");
            }
        
        HashMap<String, Integer> mobilTelefonok = new HashMap<String, Integer>();
        mobilTelefonok.put("Barni", 123);
        mobilTelefonok.put("András", 123);
        mobilTelefonok.put("Anikó", 123);
        mobilTelefonok.put("Benő", 123);
        System.out.println("\nmobilTelefonok.toString() eredménye: " + mobilTelefonok.toString());
        System.out.println(mobilTelefonok.get("Barni"));
        for (String nev : mobilTelefonok.keySet()) {
            System.out.print("A Hashmap-ben " + nev);
            System.out.println(" érték:" + mobilTelefonok.get(nev));
        }
       
        
         //Teszteljük, több elem is lehet-e egy HashMap-ben. Nem.
         //HashMap<String, String, String, String> negyFogat = new HashMap<String,String,String,String>();
         
         
	}//end of main
}//end of class
