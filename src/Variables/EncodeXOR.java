/*
XOR (kizáró vagy)  ^ 
When value X is XORed iwth another value Y, and then the result is XORed with Y again, X is produced.

 11101 (x)
^01011 (y)
~~~~~~~~~~
 10110 (x^y)
^01011 (y)
~~~~~~~~~~
 11101 (x)

*/
package Variables;

/**
 *
 * @author Andras Olah (olahandras78@gmail.com)
 */
class EncodeXOR {

	public static void main(String args[]) {
		String msg = "This is a test";
		String encmsg = "";
		String decmsg = "";
		int key = 88;
		String kulcs ="Ez egy durva kulcs";
		int kulcsi=0;

		System.out.print("Original message: ");
		System.out.println(msg);

		// encode the message 
		for (int i = 0; i < msg.length(); i++) {
//			encmsg = encmsg + (char) (msg.charAt(i) ^ key);
			if (kulcsi>=kulcs.length()) kulcsi=0;
			encmsg = encmsg + (char) (msg.charAt(i) ^ kulcs.charAt(kulcsi));
			kulcsi++;
		}

		System.out.print("Encoded message: ");
		System.out.println(encmsg);

		// decode the message 
		kulcsi=0;
		for (int i = 0; i < msg.length(); i++) {
//			decmsg = decmsg + (char) (encmsg.charAt(i) ^ key);
			if (kulcsi>=kulcs.length()) kulcsi=0;
			decmsg = decmsg + (char) (encmsg.charAt(i) ^ kulcs.charAt(kulcsi));
			kulcsi++;
		}

		System.out.print("Decoded message: ");
		System.out.println(decmsg);
	}
}
