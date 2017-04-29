package ClassesAndObjects.Practice.H_Ch05_Classes.Line;

import ClassesAndObjects.Practice.H_Ch05_Classes.Line.Line;

public class TryGeometry {
  public static void main(String[] args) {
    // Create two points and display them
    Point start = new Point(0.0, 1.0);
    Point end = new Point(5.0, 6.0);
    System.out.println("Points created are " + start + " and " + end);  //a toString() Point osztálybeli override miatt jeleníti meg így a Point objektumokat
    // Create two lines and display them
    Line line1 = new Line(start, end); 									//Point paraméterekkel létrehozott konstruktum
    Line line2 = new Line(0.0, 3.0, 3.0, 0.0);							//Kézi paraméterekkel létrehozott konstruktum.
    System.out.println("Lines created are " + line1 + " and " + line2); //a toString() Line osztálybeli override miatt írja így ki a Line objektumokat

    // Display the intersection
    System.out.println("Intersection is " + line2.intersects(line1)); 

    // Now move the end point of line1 and show the new intersection
//    end.move(1.0, -1.0);												//Nem változik az érték, mert a Line1 már létre lett hozva
	line1.start.x=0.0;
	line1.start.y =0.0;
    System.out.println("Modified Intersection is " + line1.intersects(line2));
  }
}
