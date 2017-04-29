/*
String values are instances of javal.lang.String
The String class is always available
A String is an array of characters in a particular order. A char is a primitive.
String objects are immutable - can't change them after created.
When new value is assigned to string, in the background garbage collection clears previous version of string.


 */
package PracticeAndTry;

public class StringAsAnObject {

  public static void main(String[] args) {
    String string1 = new String("Hello!");
    String string2 = "Hello!";
    char[] chars = {'H', 'e', 'l', 'l', 'l', 'o'};
    String string3 = new String(chars);
    System.out.println(string3);
    
    String s1 = "Welcome to California!";
    System.out.println("Lenght of string: "+s1.length());
    
    int position = s1.indexOf("a!"); //a stringben a paraméternek megadott string hányadik indexen fordul elő, starting from 0
    System.out.println("Position: "+position);
    
    String sub = s1.substring(position); //A megadott pozíciótól írja ki a választ, teljes hosszában
    System.out.println("substring:" + sub);
    String sub2 = s1.substring(position, (s1.length())-1); //a kezdő pozíciótól a string teljes hossza mínusz egy karakterig írja ki a találatot
    System.out.println("substring lenght-1: "+sub2);
          
    String s2 = " Welcome!          ";
    System.out.println("s2 length:"+s2.length());
    int elsoSpace=s2.indexOf("  ");
    System.out.println("Első space char indexe:"+elsoSpace);
    System.out.println("Space nélkül: " + s2.substring(0, elsoSpace));
    
    String trimmelve = s2.trim();
    System.out.println("Trimmelve:"+trimmelve);
    System.out.println("Trimmelve length:" + trimmelve.length());
    
  }
}
