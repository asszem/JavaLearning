package ClassesAndObjects.H_Ch6_Inheritance.TryPolyLine;

public class Point {
  // Create a point from its coordinates
  public Point(double xVal, double yVal) {
     x = xVal;
     y = yVal;
  }

  // Create a point from another point
  public Point(Point point) {
     x = point.x;
     y = point.y;
  }

  // Convert a point to a string
  @Override public String toString() {
     return x+","+y;
  }

  // Coordinates of the point
  protected double x;  //Both data members of Point are inherited in any subclass because they are specified as protected. 
  protected double y;
}
