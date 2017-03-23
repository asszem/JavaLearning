/*
Write a program that sets up a String variable containing a paragraph of text of your choice. Extract the
words from the text and sort them into alphabetical order. Display the sorted list of words. You could use a
simple sorting method called the bubble sort. To sort an array into ascending order the process is as follows:
	a. Starting with the first element in the array, compare successive elements (0 and 1, 1 and 2, 2 and 3, and so on).
	b. If the first element of any pair is greater than the second, interchange the two elements.
	c. Repeat the process for the whole array until no interchanges are necessary. The array elements are now in ascending order.
 */
package HortonExercises.Ch04;

public class ch4_ex3 {

	public static void main(String[] args) {
		String s1 = "A köd egyre vastagabb, Johnny egyre hájasabb.";
		s1 = "Into the face of the young man who sat on the terrace " +
                  "of the Hotel Magnifique at Cannes crept a look of furtive " +
                  "shame, the shifty, hangdog look which announces that " +
                  "an Englishman is about to talk French." ;
		String[] szavak = s1.split("[ ,.?!]");
		String[] sorted = szavak;  //kezdetben ugyanaz a két string
		for (int j = 0; j < szavak.length; j++) { //külső for, hogy az összes elemen végigmenjen
			for (int i = 0; i < szavak.length; i++) { //belső for, ami az adott elemen elvégzi a bubble sortot
				if (i < szavak.length - 1) { //prevent overflow
//			System.out.println("["+i+"]"+sorted[i]+" ["+(i+1)+"] "+sorted[i+1]);
					if (szavak[i].compareTo(szavak[i + 1]) > 0) {  //string1 nagyobb, mint string2
						String temp = szavak[i];
						szavak[i] = szavak[i + 1];
						szavak[i + 1] = temp;
					}
				}
			}
		}

		for (String s : sorted) {
			System.out.println(s);
		}
	}
}
