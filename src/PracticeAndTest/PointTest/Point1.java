package PracticeAndTest.PointTest;

// Chapter 6 Exercises 1 & 2

public class Point1 {
  // No-arg constructor
  public Point1(){}

  // Construct a Point from its coordinates:
  public Point1(double x, double y) {
    this.x = x;
    this.y = y;
  }

  // Construct a Point from another Point:
  public Point1(Point1 point) {
    x = point.x;
    y = point.y;
  }

  // Method to return a point defined relative to this
  // point in coordinates:
  public Point1 add(Point1 z) {
    return new Point1(x + z.x, y + z.y);
  }

  @Override
  public String toString() {
    return "Point: (" + x + "," + y + ")";
  }

  protected double x;
  protected double y;
}
