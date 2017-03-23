package Test;

import Test.Practice.InterfaceTest;
import static Test.Practice.InterfaceTest.VERS;

/**
 *
 * @author Andras Olah (olahandras78@gmail.com)
 */
//this is redundant, only for testing purposes that a class can implement multiple interfaces
public class RunTest implements Test.Practice.InterfaceTest, Test.Practice.InterfaceMethodsTest {
//Since this class impelemnts InterfaceTest, which extends to InterfaceMethods
//All methods needs to be declared here

	@Override
	public String printOut(String input1, String input2) {
		return input1 + " és " + input2;
	}

	public void helloInterFace() {
		System.out.println("Helló InterFace!");

	}

	public static void main(String[] args) {
		//Mivel ez az osztály implementálja az interfészt, ezért csak az ilyen osztályú objektumokon lehet interfész metódust hívni
		RunTest runTestObject = new RunTest();
		runTestObject.helloInterFace();

		System.out.println("Az élet értelme:" + MEANING_OF_LIFE);
		//ha nem akarok változót létrehozni objektumra hivatkozáshoz, így is lehet
		System.out.println(VERS);
		AnonymusClass.giveMeAnObject(new AnonymusClass() {//class definition
			int classalma = 1;
		}
		);
		Object ob = new Object();
		ClassTest a = new ClassTest("a objekutm");
		System.out.println(a.getName());
		a.greetings();

		System.out.println(
				"Objektumok száma: " + ClassTest.objCount);
		SubClassTest b = new SubClassTest();

		b.greetings();
		ClassTest c = new SubClassTest();  //ClassTest osztályú SubClassTest objektum. Ha van overrideolt method akkor Subclass azt használja. 
		SubClassTest d = new SubClassTest();

		System.out.println(
				"Objektumok száma: " + ClassTest.objCount);
		System.out.println(
				"ClassTest típusú SubClassTest objektum public instance változója elérhető-e? - NEN.");
		System.out.println(
				"SubClassTest típusú SubClassTest objektum public/no access instance változója elérhető-e? - IGEN " + d.subClassNoAccessModifierVariable + " és " + d.subClassPublicVariable);
		System.out.println(d.getName());
		System.out.println(d.classTestProtected); //Subclassból superclass protected változóját el lehet érni

		System.out.println(d.classTestNoAccessModifier); //Subclassból superclass no access modifier  változóját el lehet érni

		d.toString();

		System.out.println(d);

		System.out.println(a);

		System.out.println(d.getClass());
		System.out.println(a.getClass().getName());

		String szoveg = " ez egy teszt";
		Object szovegObject = szoveg;

		System.out.println(
				"szovegObject:" + szovegObject.getClass().getSimpleName());
		System.out.println(
				"szovegObject:" + szovegObject.getClass().getName());

		//Annak ellenőrzése, hogy egy változó milyen osztályba tartozik
		if (szoveg.getClass()
				== java.lang.String.class) {
			System.out.println("A szoveg string az egy String");
		}

		if (a.getClass()
				== SubClassTest.class) {
			System.out.println("Igen, ez " + a.getClass().getSimpleName());
		} else {
			System.out.println("Nem, ez egy:" + a.getClass().getName());
		}
		boolean instanceOfTest = a instanceof ClassTest;

		System.out.println(
				"instanceof eredémnye:" + instanceOfTest);

		System.out.println(
				"getClass() eredménye " + a.getClass());

		System.out.println(
				"getClas() = " + a.getClass());
		System.out.println(
				"getClas().getName() = " + a.getClass());
		System.out.println(
				"getClas().getSimpleName = " + a.getClass().getSimpleName());
		System.out.println(a.getClass().getDeclaredFields());

		//TODO write copy constructor example
		ClassTest.vargs(
				"2", 2, '2', 2.5);
		ClassTest.vIntArgs(
				1, 2, 3, 4);

		Double teszt = Double.valueOf("232.23");

		System.out.println(teszt);

	}
}
