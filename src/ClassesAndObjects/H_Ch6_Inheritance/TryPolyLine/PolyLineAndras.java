package ClassesAndObjects.H_Ch6_Inheritance.TryPolyLine;

//This is an example of designing classess that have data members that are themselves class objects.

/**
 *
 * @author Andras Olah (olahandras78@gmail.com)
 */
public class PolyLineAndras {
//Variables
	 //data members that are class objects themselves
	private ListPointAndras startPoint; //Reference to the first ListPoint of the PolyLine object
	private ListPointAndras endPoint; 	//Reference to the last ListPoint of the PolyLine object
	private ListPointAndras[] ListPointCoords; //To store references to each points in PolyLine
	//Literal instance variables
	private int numPoints; 		//To reference the numbers of points in PolyLine

//Constructors
	//Construct PolyLine from an Array of Points with a method called addPoint
	PolyLineAndras(Point[] inputPointArray) {
		int arrayLength = inputPointArray.length;
		ListPointCoords = new ListPointAndras[arrayLength];
		numPoints = -1; //hogy az első iterációban legyen 0 az értéke, mert tömbindex lesz
//		System.out.println("Megadott pontok száma:" + arrayLength);
		if (inputPointArray != null) {				//Ezt miért kell nézni?
			for (Point p : inputPointArray) {
//				System.out.println("A ciklusban:"+(++numPoints));
				this.addPoint(p); //Az addPoint metódust hívja az új PolyLine objektum ltérehozásakor
		//Mivel ez egy konstruktor, a PolyLineAndras osztályra hivatkozó változó fog rámutatni.
		//az addpoint metódus pedig ezen a PolyLineAndras objektumon lesz meghívva, tehát 
		//a this ebben az esetben a PolyLineAndras objektumra vonatkozik

		//Az addPoints metódus annyiszor fut le, ahány koordináta pár meg lett adva.
		//
				
//				System.out.println("startPoint:" + startPoint);
//				System.out.println("endPoint:" + endPoint);
			}
		}
//		System.out.println("a végén:" + numPoints);
	}

//Methods
	public void addPoint(Point inputP) {
		//Létrehozza a következő ListPoint objektumot a PolyLine objektumban
		//Ha még nem létezett egy pont sem (az első iteráció a konstruktorban)
		if (startPoint == null) {
			startPoint = new ListPointAndras(inputP);
			endPoint = startPoint;
		} else {
			//Az utolsó előtti objekt nextLPA változója erre az új objektumra hivatkozzon
			ListPointAndras newPoint = new ListPointAndras(inputP); //az új objektum
			//Az endPoint változó a PolyLineAndras osztály változója, objektumra hivatkozik, mindig a legutolsó listpointra. Ezért az addPoint függvény minden egyes meghívásakor, ha már van endPoint változó, akkor az ahhoz tartozó nextLPA változót rá kell állítani az újonnan létrejött objektumra:
			this.endPoint.setNextListPoint(newPoint); 	//az endpoint változó által hivatkozott objektumon meghívott setNextListPoint metódus beállítja a referenciát az újonnan létrehozott objektumre
			endPoint = newPoint;
			//Az endPoint változó immár az új newPoint változóra mutasson. 

		}
		++numPoints;
		System.out.println("numPoints: " + numPoints);
		ListPointCoords[numPoints] = endPoint;
	}

	public int getNumPoints() {
		return numPoints;
	}

	// String representation of a polyline - copied from Horton
	@Override
	public String toString() {
		StringBuffer str = new StringBuffer("Polyline:");
		ListPointAndras nextPoint = startPoint;                // Set the 1st point as start
		while (nextPoint != null) {
			str.append(" " + nextPoint);                       // Output the current point
							//itt a ListPointAndras toString override alapján alakítja stringgé a nextPoint változó által hivatkozott objektumot
			nextPoint = nextPoint.getNextListPoint();          // Make the next point current
			//Az első iterációban a nextPoint VÁLTOZÓ a StartPoint változóra mutat, ami egy 
			//ListPointAndras objektumra mutat. Ennek az objektumnak a belső változója a NextLPA, ami szintén egy VÁLTOZÓ és ami egy ListPointAndras objektumra mutat. Ezt szedi ki a listából.
			//Utána a startPoint objektumon meghívja a getNextListPoint metódust, ami kiolvassa az objektum NextLPA változójának az értékét. Mivel ez a változó is egy objektumra (a következőre) hivatkozik, így a nextPoint változó ezután már erre az újabb objektumra fog hivatkozni
			//Az iteráció egész addig folytatódik, amíg valamelyik NextPoint változó által hivatkozott objektumban nem lesz null a NextLPA változó értéke. Ebben az esetben a nextPoint = null, és megáll a while ciklus. 
			//Azért nem vesznek el az objektumok, mert a létrehozott ListPointAndras objektumban lévő nextLPA mindig tárolja a hivatkozást a következőre és amíg a hivatkozás él, addig az objektum is él. 
						
		}
		return str.toString();
	}

//For internal testing only
	public static void main(String[] args) {
		Point[] testPointArray = {new Point(10, 20), new Point(100, 200), new Point(101, 201), new Point(102, 202), new Point(103, 203)};
		PolyLineAndras testPLine = new PolyLineAndras(testPointArray);
//		System.out.println(testPLine.endPoint);
//		testPLine.addPoint(new Point(300, 300)); //Azért nem működik, mert a listPointCords tömb méretét nem növeli a metódus, azt a kontstruktor határozza meg. ArrayList kellene tömb helyett, az dinamikusan növelhető
//		System.out.println(testPLine.endPoint);
//		System.out.println(testPLine.startPoint);
//		System.out.println(testPLine.toString());
//		System.out.println(testPLine.getNumPoints());
		for (ListPointAndras p : testPLine.ListPointCoords) {
			System.out.println(p);
		}
		System.out.println(testPLine);
		System.out.println(testPLine.startPoint.getNextListPoint().getNextListPoint().getNextListPoint());
		System.out.println("Number of ListPintAndras objects created:" + ListPointAndras.numberOfListPointAndrasObjects);
	}

}
