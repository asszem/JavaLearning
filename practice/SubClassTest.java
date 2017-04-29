package PracticeAndTry;

/**
 *
 * @author Andras Olah (olahandras78@gmail.com)
 */
public class SubClassTest extends ClassTest {

	private int subClassPrivateVariable;
	int subClassNoAccessModifierVariable;
	public int subClassPublicVariable;

	public SubClassTest() {
		super("SubClassTestből híva"); // Ha kötelező paramétert megadni a super constructornak, akkor azt itt is meg kell tenni
		subClassNoAccessModifierVariable = objCount + 10;
		subClassPublicVariable = objCount + 10;
		subClassPrivateVariable = objCount + 10;
	}

//	public SubClassTest(int num) {
//		subClassNoAccessModifierVariable = objCount + num;
//		subClassPublicVariable = objCount + num;
//		subClassPrivateVariable = objCount + num;
//
//	}
	@Override
	public void greetings() {
		System.out.println("Greetings from SubClassTest");
	}

	@Override //Compiler ellenőrzi, hogy tényleg ugyanaz-e a method signature mint a base clasnak ahová extendsel. 
	public String toString() {
		return super.toString() + " toString SubClasTest overrride!"; // super.toString() a szülő toString methodját hívja.
	}
}
