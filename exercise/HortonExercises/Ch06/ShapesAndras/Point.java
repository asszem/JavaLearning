package HortonExercises.Ch06.ShapesAndras;

public class Point {
	// Coordinates of the point
	protected double x;
	protected double y;
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

}
