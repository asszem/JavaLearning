/*
DONE - Define a class for rectangle objects defined by two points: the top-left and bottom-right corners of the rectangle. 
DONE - Include a constructor to copy a rectangle,
DONE - a method to return a rectangle object that encloses the current object and the rectangle passed as an argument,
DONE - a method to display the defining points of a rectangle. 

DONE - Test the class by creating four rectangles and combining these cumulatively to end up with a rectangle enclosing them all. 

DONE - Output the defining points of all the rectangles you create.
DONE - A new method, that compares two objects and draws an enclosing rectangle over them
DONE - Generating 4 random rectangles

 */
package HortonExercises.Ch05.Rectangles;

import java.util.Random;

/**
 *
 * @author Andras Olah (olahandras78@gmail.com)
 */
public class RectangleByAndras {

	public static void main(String[] args) {
//Validation test
// Test the class by creating four rectangles and combining these cumulatively to end up with a rectangle enclosing them all. 
		Random veletlenszam = new Random();  //Ez egy RANDOM osztályú változó, nem int!
		int randomSize = 50;
		RectangleByAndras[] rectArray = new RectangleByAndras[10];
		for (int i = 0; i < rectArray.length; i++) {
			//A koordináta párosból a TL x-e kisebb legyen, mint a BR-x-e, és a TL y-ja nagyobb, mint a BRy-ja
			int x1 = veletlenszam.nextInt(randomSize);
			int x2 = veletlenszam.nextInt(randomSize);
			int y1 = veletlenszam.nextInt(randomSize);
			int y2 = veletlenszam.nextInt(randomSize);
			//A kisebb X és a nagyobb Y
			Point rndTl = new Point(Math.min(x1, x2), Math.max(y1, y2));
			//A nagyobb X és a kisebb Y
			Point rndBr = new Point(Math.max(x1, x2), Math.min(y1, y2));
			rectArray[i] = new RectangleByAndras(rndTl, rndBr);
			System.out.println("Iteráció: " + i + " Koordináták: " + rectArray[i]);
		}
		RectangleByAndras enclosedOutput = new RectangleByAndras(rectArray[0]); //rögtön az első elem mehet bele
		for (int i = 0; i < rectArray.length; i++) {  //a második tömb elemtől kezdje az összehasonlítást
			//Az aktuális és az előző összehasonlításából megrajzolja a nagyobbat és annak az értékét rakja a kimenetbe
			enclosedOutput = enclosedOutput.enclose2(rectArray[i]);
		}
		System.out.println("Enclosed Output: " + enclosedOutput);
		enclosedOutput.definingPoints();

//		Point topLeft = new Point(2, 4);
//		Point bottomRight = new Point(4, 2);
//		ch5_ex1 rectangle1 = new ch5_ex1(topLeft, bottomRight);
//		rectangle1.definingPoints();
//		
//		topLeft = new Point(1, 5);
//		bottomRight = new Point(5, 1);
//		ch5_ex1 rectangle2 = new ch5_ex1(topLeft, bottomRight);
//		rectangle2.definingPoints();
//		System.out.println("Enclose 2: "+ rectangle2.enclose2(rectangle1));
//
		/*
		//Creating rectangle objects
		Point topLeft = new Point(5, 10);
		Point bottomRight = new Point(15, 5);
		ch5_ex1 rectangle1 = new ch5_ex1(topLeft, bottomRight);
		System.out.println(rectangle1);

		Point topLeft2 = new Point(20, 10);
		Point bottomRight2 = new Point(25, 5);
		ch5_ex1 rectangle2 = new ch5_ex1(topLeft2, bottomRight2);
		System.out.println(rectangle2);

		//Copying rectangle objects
		ch5_ex1 rectangleCopy = new ch5_ex1(rectangle1);
		System.out.println(rectangleCopy);
//		System.out.println("Rectangle count: " + rectangleCount);

		//Enclosing rectangle object
//		System.out.println(enclose(rectangle1));
		rectangle1.definingPoints();
		ch5_ex1 enclosingRectangle = enclose(rectangle1);
		enclosingRectangle.definingPoints();
		 */
	}

	private Point tl; //top-left corner
	private Point br; //bottom-right corner
	private String name; //legyen neve is a négyszögnek
	private static int rectangleCount;

	//Constructor, with two Point objects as parameters
	RectangleByAndras(final Point topLeft, final Point bottomRight) {
		//A rectangle objektum megadásához használt Point koordináták függetlenek legyenek a rectangle objektumtól
		this.tl = new Point(topLeft); 			//ahhoz, hogy ez működjön, a Point osztályban kell olyan metódus, ami point objektumot fogad el paraméternek
		this.br = new Point(bottomRight);
		name = "Rectangle #" + (++rectangleCount);
	}

	//Constructor to copy a rectangle
	RectangleByAndras(final RectangleByAndras oldRectangle) { 		//A final talán nem is muszáj?
		this.tl = new Point(oldRectangle.tl);  	//A paraméter objektum tl field-je a keresett Point objektum, de ebből ÚJ készül
		this.br = new Point(oldRectangle.br);
//		name = "Rectangle (copied from " + oldRectangle.name + ") #" + (++rectangleCount);
	}

	public String toString() {
		return name + " Top left: <" + tl.x + ">:<" + tl.y + "> Bottom Right: <" + br.x + ">:<" + br.y + ">";
	}
// a method to return a rectangle object that encloses the current object and the rectangle passed as an argument,

	RectangleByAndras enclose2(RectangleByAndras secondRectangle) {
//Két új végpontot meghatározni a két négyszög négy végpontjának összehhasonlításával
		int topLeftX = Math.min(this.tl.getX(), secondRectangle.tl.getX());
		int topLeftY = Math.max(this.tl.getY(), secondRectangle.tl.getY());
		int botRightX = Math.max(this.br.getX(), secondRectangle.br.getX());
		int botRightY = Math.min(this.br.getY(), secondRectangle.br.getY());
		Point newTl = new Point(topLeftX, topLeftY);
		Point newBr = new Point(botRightX, botRightY);
		return new RectangleByAndras(newTl, newBr);
	}

	//Ez a metódus a paraméternek megadott négyszög köré rajzol. De nekünk nem ez kell.
	static RectangleByAndras enclose(final RectangleByAndras innerRectangle) {
// tl.x -1
// tl.y +1
// br.x +1
// br.y -1
//		System.out.println("Enclosing rectangle: " + innerRectangle.name);
//		System.out.println("Original coordinates: \t\t" + innerRectangle.tl + " and " + innerRectangle.br);
		Point newTopLeft = new Point(innerRectangle.tl.x - 1, innerRectangle.tl.y + 1);
		Point newBottomRight = new Point(innerRectangle.br.x + 1, innerRectangle.br.y - 1);
//		System.out.println("Enclosing coordinates: \t\t" + newTopLeft + " and " + newBottomRight);
		return new RectangleByAndras(newTopLeft, newBottomRight);
	}

	void definingPoints() {
		System.out.println("Defining points of rectangle: " + this.name);
		Point bottomLeft = new Point(tl.x, br.y);
		Point topRight = new Point(br.x, tl.y);
		System.out.println("Top Left: \t\t" + this.tl);			//tl.x és tl.y
		System.out.println("Bottom Left: \t\t" + bottomLeft); 	//equals br.y és tl.x (a magasság változik, a szélesség marad). Magasság = a jobb alsó
		System.out.println("Top Right: \t\t" + topRight);		//equals br.x és tl.y (a szélesség változik, a magasság marad). Szélesség egyelő: a jobb alsó
		System.out.println("Bottom Right:\t\t" + this.br);		//br.x és br.y

	}

	static class Point {

		private int x;
		private int y;

		//Constructor with direct parameters
		public Point(int setX, int setY) {
			x = setX;
			y = setY;
		}

		//Constructor with Point object as parameter
		public Point(final Point oldPoint) {
			x = oldPoint.x;
			y = oldPoint.y;
		}

		public int getX() {
			return x;
		}

		public int getY() {
			return y;
		}

		@Override
		public String toString() {
			return "x:<" + x + "> : y<" + y + ">";
		}

	}

}
