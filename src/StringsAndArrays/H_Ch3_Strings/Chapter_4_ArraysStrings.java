package StringsAndArrays.H_Ch3_Strings;
//useStringBuffer, StringTokenizing and StringCharacters examples

//Updated: 2016-11-02, 17:51
/*Notes
String variable refers to the Object
  A String variable is simply a variable that stores a reference to an object of the class String.
  String objects are said to be immutable— which just means that they cannot be changed
  The String object itself is distinct from the variable you use to refer to it. 
  String variable records where the String object is in memory.
  When you change the string referenced by a String variable, you throw away the reference to the old string and replace it with a reference to a new one.

Declaration:
  String newString;

Declaration & Inicialization
  String newString = "This is a string";
  String newString = null;

Discarding an existing string
  oldString = null;

String Arrays[]
  String[] = new String[10];
  Declare an array of String objects where the initial values determine the size of the array:
    String[] colors = {"red", "orange", "yellow", "green", "blue", "indigo", "violet"};

Wrapper Class 
  Each of the primitive types has an equivalent class defined, so for the primitive types I have already discussed there are the wrapper classes
    byte Byte
    short Short
    int Integer
    long Long
    float Float
    double Double
    boolean Boolean
    char Character
      Character.(string1.charAt)

valueOf()
  You call the valueOf() method using the name of the class String. This is because the method is a static member of the String class.
  String doubleString = String.valueOf(3.14159);

equals() / equalsIgnoreCase()
  The equals() method does a case-sensitive comparison of corresponding characters in the strings and returns true if the strings are equal and false otherwise
  if (stringVariable.equals(stringVariable2))
  if (string1.equals(string3))

intern()
  String interning ensures that no two String objects encapsulate the same string, so all String objects encapsulate unique strings
  The intern() method checks the string referenced by string1 against all the String objects currently in existence. 
  If it already exists, the current object is discarded, and string1 contains a reference to the existing object encapsulating the same string
  As a result, the expression string1 == string3 evaluates to true, whereas without the call to intern() it evaluated to false.
  string1 = (string1 + string2).intern();

startsWith() / endsWith()
  boolean b = test.startsWith("A köd"), c=test.endsWith("abb");

compareTo() / compareToIgnoreCase()
  One character is greater than another if the numerical value of its Unicode representation is greater than that of the other. 
  Negative if the String object is less than the argument that you passed, 
	zero if the String, object is equal and 
	positive if greater than the argument. 
	s1.compareTo(s2);

length()
  System.out.println("hossz:"+s1.length());

loLowerCase / toUpperCase
  String s1 = "A köd egyre vastagabb, Johnny egyre hájasabb.";
  System.out.println(s1.toLowerCase());

charAt() nullától indul!!!
  This accepts an integer argument that is the offset of the character position from the beginning of the string
  char ch = Character.toLowerCase(text.charAt(i));
  
  Character
    isLetter()
    isWhitespace()
      if (Character.isWhitespace(s1.charAt(i))) {

indexOf() / lastIndexOf()
  The method indexOf()searches the contents of the string text forward from the beginning and return the index position of the first occurrence
  If none found, the method returns the value –1

  csak bizonyos ponttól keres adott karaktert:
  indexOf(string, startIndex) 

substring()
  substring(string) / substring(startIndex, endIndex)
  Extracts a substring consisting of all the characters from a given index position up to the end of the string. 
  s1.substring(innát);

split(regex, intNumb)
  The first is a String object that specifies a pattern for a delimiter
  If second argument is zero, the pattern is applied as many times as possible and any trailing empty tokens discarded
  The second argument can be ommitted, then it will be considered as zero
  The end of the string is always a delimiter, so the period followed by the end of the string identifies an empty token.

  RegExp 
  You must specify the set of delimiters in the string between square brackets.
  Examples are the string "[abc]" defining 'a', 'b', and 'c' as delimiters, or “[, .:;]”

  Example
    String[] words = s1.split(" ", 0);
    for (String a:words){System.out.print("["+a+"] ");}
    ld. StringTokenizing example!

replace()
  These methods don't change the original string, String objects are immutable.
  To replace one specific character with another throughout a string, you can use the replace() method
  String newText = text.replace(' ', '/'); 

trim()
  To remove whitespace from the beginning and end of a string (but not the interior) you can use the trim() method

toCharArray()
  String text = “To be or not to be”;
  char[] textArray = text.toCharArray();

  You can't use a String object directly as the source of values for a collection-based for loop
  The toCharArray() method can be used to iterate over the characters in a string using a collection-based for loop
  
  Example
  for (char c:s1.toCharArray()){
      System.out.print("["+c+"]");
    }

getChars()
	Extract a substring as an array of characters using the method getChars()
  You do need to create an array that is large enough to hold the characters and pass it as an argument to the method. 

  Expected parameters
  The index position of the first character to be extracted from the string (type int)
  The index position following the last character to be extracted from the string (type int)
  The name of the array to hold the characters extracted (type char[])
  The index of the array element to hold the first character (type int)

  Ez egy VOID method, szóval nem ad vissza értéket, így nem kell/lehet kifejezésbe rakni

  Example
    char[] someWords = new char[100];
    s1.getChars(0, 30, someWords, 0);

copyValueOf(sourceArray, startIndex, number of elements)
  To create a String object from an array of type char[]
  Statikus metódus, ezért a String osztályra kell hívni, nem az adott string objektumra!
  A paraméteres megoldás mutatja, hogy a String-nek is többféle konstruktora lehet.

  Example  
  char[] textArray = {'T', 'o', ' ', 'b', 'e', ' ', 'o', 'r', ' ',  'n', 'o', 't', ' ', 't', 'o', ' ', 'b', 'e' };
  String text = String.copyValueOf(textArray);
  String text = new String(textArray)
  String text = String.copyValueOf(textArray, 9, 3);
  String text = new String(textArray, 9, 3);

StringBuilder class and StringBuffer Class
  Both are used for mutable string manipulation. StringBuilder class is faster but not thread-safe. They both have the same methods
  Operations are faster and easier using mutable objects.

  Examples of creating new objects
  StringBuffer aString = new StringBuffer("A stitch in time");
  String phraseExample = "Experience is what you get when you’re expecting something else.";
  StringBuffer buffer = new StringBuffer(phraseExample);
  StringBuffer myString = null;

  myString.capacity()
  When you create a StringBuffer object from an existing string, the capacity is the length of the string plus 16. 
  Both the capacity and the length are in units of Unicode characters, so twice as many bytes are occupied in memory.
	The ensureCapacity() method enables you to change the default capacity of a StringBuffer object. 
	myString.ensureCapacity(40)

	myString.setLength(6)
	
	append()
		myString.append("és még ezt is");
		myString.append(inputString, firstIndex, lastIndex); - a lastIndex eggyel a keresett után van.
		myString.append(anyVariable);

	indexOf(), lastIndexOf() - ugyanaz, mint String-nél
		position = phrase.lastIndexOf(“three”, 8);

	insert(where, what)
	The first argument specifies the index of the position in the object where the first character is to be inserted
	If you need to insert a subset of an array of type char[] into a StringBuffer object, you can call the version
	of insert() that accepts four arguments, shown below:
	insert(int index, char[] str, int offset, int length)

	charAT(), getChars()
	The StringBuffer class includes the charAt() and getChars() methods, both of which work in the same
	way as the methods of the same name in the String class which you've already seen. The charAt() method
	extracts the character at a given index position, and the getChars() method extracts a range of characters
	and stores them in an array of type char[] starting at a specified index position.

	setCharAT(), delCharAt(), delete()
	You can change/delete a single character in a StringBuffer object by using the setCharAt() method.
	StringBuffer phrase = new StringBuffer(“When the boats come in”);
	phrase.deleteCharAt(10);
	phrase.delete(5, 9); - Note:5-8-ig töröl, a 9. a törlés utáni első karakter indexe

	reverse()
	megfordítja a string tartalmát

	toString()
	You can produce a String object from a StringBuffer object by using the toString() method of the
	StringBuffer class. This method creates a new String object and initializes it with the string contained in
	the StringBuffer object. For example, to produce a String object containing the proverb that you created
	in the previous section, you could write:
	String saying = proverb.toString();
 */
public class Chapter_4_ArraysStrings {

	public static void main(String[] args) {
		StringBuffer aString = new StringBuffer("A stitch in time");
		StringBuilder s1 = new StringBuilder("Ez meg egy StringBuilderes string");
		String phraseExample = "Experience is what you get when you’re expecting something else.";
		StringBuffer buffer = new StringBuffer(phraseExample);
		System.out.println("lenght: " + buffer.length());
		System.out.println("capacity: " + buffer.capacity());
		buffer.ensureCapacity(500);
		System.out.println("capacity: " + buffer.capacity());
		System.out.println(buffer);
		buffer.setLength(500);
		System.out.println(buffer);
		buffer.append("és még ezt is");
		buffer.append(aString, 2, 8);
		System.out.println(buffer);
		double appendNum = 42.1;
		buffer.append(appendNum);
		buffer.append(555);
		char[] text = {'i', 's', ' ', 'e', 'x', 'a', 'c', 't', 'l', 'y'};
		buffer.append(text, 2, 8);
		System.out.println(buffer);
		StringBuffer phrase = new StringBuffer("one two three four");
		String substring = "two";
		String replacement = "twenty";
// Find start of last occurrence of "two"
		int position = phrase.lastIndexOf(substring);
		phrase.replace(position, position + substring.length(), replacement);
		System.out.println(phrase.reverse());
		System.out.println(phrase.reverse());
//	    String s1 = "A köd egyre vastagabb, Johnny egyre hájasabb. alma körte dió";
		//    char[] textArray = {'T', 'o', ' ', 'b', 'e', ' ', 'o', 'r', ' ',  'n', 'o', 't', ' ', 't', 'o', ' ', 'b', 'e' };
		//    String s2 = String.copyValueOf(textArray, 0, 5);
		//    System.out.println(""+s2);
		//    //Karakterenként iterálni for-collectionben
		//    for (char c:s1.toCharArray()){
		//      System.out.print("["+c+"]");
		//    }
		//    
		//    char[] someWords = new char[100];
		//    s1.getChars(0, 30, someWords, 0);
		////    System.out.println(someWords[2]);
		//    
		//    System.out.println("A string: \t\t\t\t\"" + s1 + "\"");
		//    System.out.println("A string hossza:\t\t\t" + s1.length());
		//    System.out.println("A J karakter indexe\t\t\t" + s1.lastIndexOf("J"));
		//    System.out.println("A \"J\" string indexétől kezdve kivágva:\t" + s1.substring(s1.indexOf("J")));
		//    int spaces = 0;
		//    for (int i = 0; i < s1.length(); ++i) {
		//      if (Character.isWhitespace(s1.charAt(i))) {
		////      System.out.println("Ez egy szpész");
		//        spaces++;
		//      }
		//    }
		//    System.out.println("Szóközök száma: \t\t\t" + spaces);
		//    System.out.println("A hatodik karakter:\t\t\t" + s1.charAt(6));
		//
		//    //Az első nagybetű helye
		//    int firstUpperCase = 0;
		//    for (int i = 1; i < s1.length(); i++) { //Azért így, hogy az első karakter ne számítson
		//      if (Character.isUpperCase(s1.charAt(i))) {
		//        firstUpperCase = i;
		//        break;
		//      }
		//    }
		//    System.out.println("Az első nagybetű indexe:\t\t" + firstUpperCase);
		//    System.out.println("A 10. karaktertől kezdve az első \"b\":\t" + s1.indexOf("b", 10));
		//    int karakterSzamlalo = 0;
		//    for (int i = 0; i < s1.length(); i++) {
		//      if (s1.charAt(i) == 'b') {
		//        karakterSzamlalo++;
		//      }
		//    }
		//    System.out.println("A \"b\" betűk száma (for)\t\t\t" + karakterSzamlalo);
		//
		//    //Egy másik megoldás ugyanerre
		//    int aIndex = -1;      // Search start position
		//    karakterSzamlalo = 0; // Count of 'a' occurrences
		//    while ((aIndex = s1.indexOf('b', ++aIndex)) > -1) {
		//      ++karakterSzamlalo;
		//    }
		//    /*The while loop condition expression calls the indexOf() method for the String object referenced by text
		//    and stores the result in the variable aIndex. If the value stored is greater than -1, it means that 'a' was
		//    found, so the loop body executes and count is incremented. Because aIndex has -1 as its initial value, the
		//    search starts from index position 0 in the string, which is precisely what you want. When a search reaches
		//    the end of the string without finding 'a', -1 is returned by the indexOf() method and the loop ends.*/
		//    System.out.println("A \"b\" betűk száma (while):\t\t" + karakterSzamlalo);
		//
		//    //Feladat: szövegben keressen meg elölről majd hátulról karaktereket és irassa ki a számukat.
		////    s1=s1+"egyegy".intern();
		////    s1 = "nincs benne ";
		////Keresés Elölről - string előfordulások száma
		//    String kE = "egy"; //keresett string
		//    int numKE = 0;
		//    int indKE = s1.indexOf(kE);
		////    System.out.println("indKE"+indKE); //az első előfordulás indexe
		//    while (indKE > -1) {  //akkor lép be a ciklusba, ha az indexOf találatot adott, ha nincs találat, sosem lép be
		////      System.out.println("indKE a ciklus elején" + indKE);
		//      //a találat indexéhez adja hozzáa keresett kifejezés hossszát, és végezzen egy újabb keresést, immár attól a pozíciótól
		//      indKE = s1.indexOf(kE, indKE + kE.length());
		////      System.out.println("indKE a ciklus végén" + indKE);
		//      numKE++;
		//    }
		//    System.out.println("A)z \"" + kE + "\" karakterek előfordulása:\t" + numKE);
		//    System.out.println("Az első \"" + kE + "\" előfordulás indexe:\t" + s1.indexOf(kE));
		//
		////Keresés Hátulról - string előfordulások száma
		//    String kH = "as";
		//    int numKH = 0;
		//    int indKH = s1.lastIndexOf(kH); //hátultról kezdve az első előfordulás indexe
		//    while (indKH > -1) {
		//      indKH = s1.lastIndexOf(s1, indKH - kH.length());
		//      numKH++;
		//    }
		//    System.out.println("A)z \"" + kH + "\" karakterek előfordulása:\t" + numKH);
		//    System.out.println("Az utolsó \"" + kH + "\" előfordulás indexe:\t" + s1.lastIndexOf(kH));
		//    System.out.println("Kisbetűvé alakítva:\t\t\t" + s1.toLowerCase());
		//    System.out.println("Nagybetűvé alakítva:\t\t\t" + s1.toUpperCase());
		//    
		//    s1="alma  körte dió mogyoró     ";
		//    String[] words = s1.split("[, .]");
		//    for (String a:words){
		//      System.out.println(a);
		//    }
		/*You can use the indexOf() method in combination with the substring() method to extract a sequence of
substrings that are separated by spaces in a single string*/ //    s1 = "A köd egyre vastagabb, JOhhny egyre hájasabb";
		//    System.out.println("A string: \t\t\t\t\"" + s1 + "\"");
		//    System.out.println("A string hossza: \t\t\t" + s1.length());
		//    int currentIndex = 0;
		//    int nextWhiteSpaceIndex = 0; //Azért indul eggyel, hogy a startIndex utáni első karakterrel kezdje
		//    int numSpaces = 0;
		//    int[] whiteSpaceIndexes = new int[s1.length()]; //annyi elembű tömböt hozzon létre, ahány elem hosszú a string
		//    int numWords = 0;
		//    String[] wordsInString = new String[s1.length()];
		//    int nextWord = 0; //0, ha az előző indexen Space volt, 1, ha más
		//    while (currentIndex < s1.length()) {
		//      if (Character.isWhitespace(s1.charAt(currentIndex))) { //Space
		//        whiteSpaceIndexes[numSpaces++] = currentIndex;  //a numSpacest azután növelje, hogy felhasználta indexként
		//        nextWord = 0; //mindig nullázzuk, ha space-t találtunk
		//      } else { //nem space
		//        if (nextWord == 0) {
		//          numWords++; //ekkor kezdődik új szó
		//        }
		//        nextWord = 1;
		//        int wordStart = currentIndex; //az új szó innen kezdődeik
		//        int wordEnd = currentIndex+1; //Ehhez kell még hozzáadni a következő space indexét
		//        while (wordEnd < s1.length()) {
		//          if (!Character.isWhitespace(s1.charAt(wordEnd))) { //ha NEM space a köv. vizsgált karakter
		//            wordEnd++;
		//          } else {
		//            break; //ha space-t talál lépjen ki a while ciklusból
		//          }
		//        }
		//        wordsInString[numWords]=s1.substring(wordStart, wordEnd);
		//        System.out.println("Index:"+currentIndex+" A szó: "+s1.substring(wordStart, wordEnd));
		//        currentIndex=wordEnd;
		//      }
		//      currentIndex++;
		//    }
		//    System.out.println("Szavak száma: \t\t\t" + numWords);
		//    System.out.print("White space indexek:\t\t\t");
		//    for (int i : whiteSpaceIndexes) {
		//      if (i != 0) {
		//        System.out.print(i + ", ");
		//      }
		//    }
		//    System.out.println(!Character.isWhitespace(s1.charAt(nextWhiteSpace)));
		//
		//    for (int i = 0; i < s1.length(); i++) {  //a ciklus addig fusson, amíg bent vagyunk a stringben
		//      if (startIndex >= s1.length()) {
		//        break; // lépjen ki a for ciklusból, ha a startindex túlmutat a string hosszán
		//      }
		//      if (!Character.isWhitespace(s1.charAt(startIndex))) { //az indexnél lévő karakter NEM whitespace
		////        System.out.println("A "+startIndex+" startIndexen nem whitespace van.");
		//        szavakSzama++;
		//
		////        //Megkeresi a következő whitespace indexét
		//        while (nextWhiteSpace < s1.length() && !Character.isWhitespace(s1.charAt(nextWhiteSpace))) { //a következő NEM karakter whitespace?
		////          System.out.println("A "+nextWhiteSpace+" nextWhiteSpace indexen nem whitespace van.");
		////          System.out.println("Whitespace a következő indexen:" + nextWhiteSpace);
		//          nextWhiteSpace++;
		//        }
		//        //A startIndexet átrakja a következő whitespace helyére
		////        System.out.println("startIndex:\t\t\t" + startIndex);
		////        System.out.println("nextWhiteSpace:\t\t\t" + nextWhiteSpace);
		//        startIndex += nextWhiteSpace;
		////        System.out.println("startIndex updated:\t\t" + startIndex);
		//      } else {
		//        whiteSpaceIndices[numSpaces]=startIndex;
		//        numSpaces++;
		//        
		////        System.out.println("\tSpace-t találtam az " + nextWhiteSpace + " indexen");
		//        nextWhiteSpace++;
		//        startIndex++;
		////        System.out.println("\tstartIndex:\t\t\t" + startIndex);
		////        System.out.println("\tnextWhiteSpace:\t\t\t" + nextWhiteSpace);
		//      }
		//    }//while vége
		//    System.out.println("Szavak száma a stringben:\t\t" + numWords);
		//    System.out.println("Whitespace indexek:");
		//    for (int t:whiteSpaceIndexes){
		//      System.out.print(t+", ");
		//    }
		//    System.out.println("\nA string: \t\t\t\t\"" + s1 + "\"");
		//    String s1 = "Abcd e";
		//    String s2 = "Abcd x";
		//    System.out.println(s1.compareTo(s2));
		//    System.out.println(s2.compareTo(s1));
		//    String test= "A köd egyre vastagabb";
		//    boolean b = test.startsWith("A köd"), c=test.endsWith("abb");
		//    System.out.println(b+" "+c);
		//    b = test.startsWith("a köd"); c=test.endsWith("abB");
		//    System.out.println(b+" "+c);
		//    String anyString = null;
		//    if (anyString == null) {
		//      System.out.println("anyString does not refer to anything!");
		//    }
		//    String[] stars = {"Robert Redford", "Marilyn Monroe", "Boris Karloff", "Lassie ", "Hopalong Cassidy", "Trigger", "Andris"};
		//    System.out.println("Your lucky star for today is " + stars[(int) (stars.length * Math.random())]);
		//          The result won't ever be exactly 6.0 because the value returned by the random()
		//          method is strictly less than 1.0, which is just as well because this would be an illegal index value. The 
		//    String doubleString = String.valueOf(3.14159);
		//    System.out.println(doubleString);
		//    if (doubleString.equals("3.14159")) {
		//      System.out.println("Egyezik");
		//      
		//    } else System.out.println("nem");
	}

}
