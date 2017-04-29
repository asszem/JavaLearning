package ClassesAndObjects.Practice.H_Ch05_Classes.Line;

import static java.lang.Math.sqrt;

class Point {
	/**
	 Javadoc teszt
	 */
  // Coordinates of the point										/Minden Point típusú objektumnak ként instance változója lehet: x és y
  double x;
  double y;

  // Create a point from coordinates								//Constructor, kézzel megadott paraméterkkel
  Point(double xVal, double yVal) {
    x = xVal;
    y = yVal;
  }

  // Create a point from another Point object
  Point(final Point oldPoint) {										//Constructor, Point objektum paraméterrel
    x = oldPoint.x;                    // Copy x coordinate
    y = oldPoint.y;                    // Copy y coordinate
  }

  // Move a point
  void move(double xDelta, double yDelta) {							//Metódus, ami a paraméternek megadott argumentumokkal változtatja a Point obj. változóit
    // Parameter values are increments to the current coordinates
    x += xDelta;
    y += yDelta;
  }

  // Calculate the distance to another point
  double distance(final Point aPoint) {												//metódus, paramétere egy Point objektum
    return sqrt((x - aPoint.x)*(x - aPoint.x) + (y - aPoint.y)*(y - aPoint.y));		//a meghívott Point és a paraméter Point közötti kölönbséget adja vissza
																					//egyetlen double változóként
																					//egyikPointObjektuk.distance(masikPointObjektum)
  }

  // Convert a point to a string 
  @Override
  public String toString() {
    return Double.toString(x) + ", " + y;    // As "x, y"
  }
}
