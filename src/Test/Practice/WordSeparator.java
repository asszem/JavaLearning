/*
indexOf(int)
substring(startIndex, endIndex)
Character.isLetter
charAT
stringbuffer
toString
 */
package Test.Practice;

public class WordSeparator {

	public static void main(String[] args) {
		String str = "A köd egyre vastagabb, Johhny egyre hájasabb. ";
		//Determine word count
		int wordCount = 0;
		boolean isNewWord = true;
		for (int i = 0; i < str.length(); i++) {
//System.out.println("Index ["+i+"] ["+str.charAt(i)+"]isLetter:"+Character.isLetter(str.charAt(i)));
			if (!Character.isLetter(str.charAt(i))) {//Nem betű az aktuális karakter
//System.out.println("["+i+"]["+str.charAt(i)+"] not letter");
				isNewWord = true;// jelezze, hogy a következő találat új szó lesz - set isNewWord = true
				continue;//lépjen a ciklus következő elemére
			} else//Betű az aktuális karakter
			{
				if (isNewWord) {	//egy új szó első betűje? - isNewWord = true
					wordCount++;
					isNewWord = false;//jelölje, hogy a következő karakter már nem új szó lesz - set isNewWord false
				} else { //nem új szó következő betűje
					//menjen tovább, semmit nem kell csinálnia
				}
			}
		}
		System.out.println("A szavak száma: " + wordCount);

		//Store words in a buffer	
		String[] words = new String[wordCount];
		isNewWord = true;
		int currentWordIndex = -1;  //az első találatkor lesz 0-ra növelve, ami a találatokat tároló tömb kezdő indexe
		StringBuffer temp = new StringBuffer();
		for (int i = 0; i < str.length(); i++) {
			if (!Character.isLetter(str.charAt(i))) {
				isNewWord = true;
				continue;
			} else {
				if (isNewWord) {
					isNewWord = false;
					currentWordIndex++;
					temp = new StringBuffer(Character.toString(str.charAt(i)));
				} else {
					temp.append(Character.toString(str.charAt(i)));
				}
			}
			words[currentWordIndex] = temp.toString();
		}
		//Eredmény kiiratása szavanként
		System.out.println("Az eredeti string: "+str);
		for (String s : words) {
			System.out.println(s);
		}
	}
}
