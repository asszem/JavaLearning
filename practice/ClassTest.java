package PracticeAndTry;

public class ClassTest {

	private String objName;
	private int objId;
	public static int objCount=1;
	protected int classTestProtected=42;
	int classTestNoAccessModifier=52;

	//ClassTest() {
	//objId=objCount++;
	//}
	ClassTest(String oName) {
		//this();		//hogy az objCount növelés és az ID adás midig meglegyen
		objId = objCount++;
		objName = oName;
	}

	public void greetings() {
		System.out.println("Helló! greetings() method in ClassTest");
	}

	public String getName(){
		return objName;
	}

	//Variable Argument List, any types
	public static void vargs (Object ... args){
		for (Object actual:args){
			System.out.print(actual+", ");
		}
		System.out.println("");
	}
	//Variable Argument Lists, only specific type
	public static void vIntArgs (int ... args){
		for (int i:args){
			System.out.print(i+", ");
		}
		System.out.println("");
	}
	public static void myArgs(String arguments){
		System.out.println("My argument was: "+arguments);
	}
	public String toString() {
		return "Object #" + objId + " name:" + objName+" HashCode: "+hashCode();
	}
}
