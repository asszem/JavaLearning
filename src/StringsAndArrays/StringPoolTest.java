// https://www.journaldev.com/797/what-is-java-string-pool
package StringsAndArrays;

public class StringPoolTest {

	public static void main(String[] args) {
		// When we use double quotes to create a String, it first looks for String with same value in the String pool,
		// if found it just returns the reference else it creates a new String in the pool and then returns the reference.
		String a="alma"; 				// new reference to a new String object in String pool
		String b="alma"; 				// new reference to the same object in String pool as it already exists
		String c=a; 					// new reference to the same reference as variable a references in the String pool
		String d="ALMA"; 				// new reference to a different, new String object in the String pool
		String e=new String("alma");	// new referenct to a new Object in Heap which is not in the String pool

		System.out.println("a="+a);
		System.out.println("b="+b);
		System.out.println("c="+c);
		System.out.println("d="+d);
		System.out.println("e=new String("+e+")");
		System.out.println();
		System.out.println("a==b? " + (a==b)); // True because same String pool
		System.out.println("a.equals(b)? " + a.equals(b)); // True because same value
		System.out.println("d.toLowerCase()==a? " + (d.toLowerCase()==a)); // False because different strings in String pool
		System.out.println("d.toLowerCase().equals(a)? " + (d.toLowerCase().equals(a))); // True because same value
		System.out.println("e==a? " + (e==a)); // False because d is not in String poolj
		System.out.println("e.equals(a) " + (e.equals(a))); // True because value is same
	}

}
