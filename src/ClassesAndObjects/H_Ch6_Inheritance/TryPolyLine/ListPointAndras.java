package ClassesAndObjects.H_Ch6_Inheritance.TryPolyLine;

/**
 *
 * @author Andras Olah (olahandras78@gmail.com)
 */
public class ListPointAndras {
//Instance variables

	private Point actualPoint; //Reference to The actual point of the polyline
	private ListPointAndras nextLPA; //A data member to reference the next ListPointAndras object
	//Egy instance variable, ami egy objektumra hivatkozik.
	//the last LPA's object nextLPA should point to null
	public static int numberOfListPointAndrasObjects;

//Constructors
	//Constructor to create a new LPA object which holds a REFERENCE to a Point object 
	public ListPointAndras(Point inputPoint) {
		this.actualPoint = inputPoint;  //No new object created, just a REFERENCE
		//so if the inputPoint changes, the list point changes too
		nextLPA = null; //amikor új instance jön létre, az legyen az utolsó elem, ezért null. 
		++numberOfListPointAndrasObjects;
	}

//Methods
	//Set the pointer to the next ListPointAndras ON THE OBJECT IT WAS CALLED!
	public void setNextListPoint(ListPointAndras inputNextLPA) {
		this.nextLPA = inputNextLPA; //Nem kell ide inputNextLPA.nextLPA, mert magára az objektumra fogunk hivatkozni. Azaz ahogy egy vagy több változó hivatkozhat egy objektumra, úgy ez a változó lehet egy objektum instance változója is
		//
	}

	//Get the next point in the list
	public ListPointAndras getNextListPoint() {
		return nextLPA;
	}

	//to String override
	@Override
	public String toString() {
		int objId=this.hashCode();
		return "[" + actualPoint + "], Hashcode: "+objId;
	}

//for testing purposes only, the real main() will be called from  XXX
	public static void main(String[] args) {
		Point testPoint = new Point(10, 20);
		ListPointAndras a = new ListPointAndras(testPoint);
		System.out.println(a.getClass().getSimpleName() + a);

		//To test that changing the referenced Point object will change the ListPoint object as well
		testPoint.x = 555;
		System.out.println(a.getClass().getSimpleName() + a);

		//To test the value of the nextLPA variable (which is not yet defined)
		System.out.println("a.getNext() = " + a.getNextListPoint());
		//output: null

		Point testPoint2 = new Point(100, 200);
		ListPointAndras b = new ListPointAndras(testPoint2);
		a.setNextListPoint(b);
		//To test the value of the nextLPA variable (which should be b now)
		System.out.println("a.getNext() = " + a.getNextListPoint());
		//a toString() override alapján az output:
		//[100.0,200.0] 
	}
}
