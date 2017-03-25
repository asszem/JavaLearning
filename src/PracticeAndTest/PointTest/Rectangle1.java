package PracticeAndTest.PointTest;

// Chapter 6 Exercises 1 & 2

public class Rectangle1  {
	public static void main(String[] args){
		Rectangle1 teszt = new Rectangle1(new Point1(3,6), new Point1(6,3));
		System.out.println("Input: 3,6 és 6,3");
		System.out.println(teszt);
		System.out.println("position:"+teszt.position);
		System.out.println("bottom:"+teszt.bottomRight);
	}
// Constructor
  public Rectangle1(Point1 startDiag, Point1 endDiag) {
    // Position of rectangle is top left corner - minimum x and y:
    position = new Point1(Math.min(startDiag.x, endDiag.x), Math.min(startDiag.y, endDiag.y));

    // Bottom right is relative to top left:
    bottomRight = new Point1(Math.max(startDiag.x,endDiag.x) - position.x,
                            Math.max(startDiag.y,endDiag.y) - position.y);
  }

  @Override
  public String toString()  {
    // Return a string representation of the object:
    return "Rectangle: Top Left: " + position + " Bottom Right: " + position.add(bottomRight);
  }

  // Display the rectangle:
  public void show() {
    System.out.println("\n" + toString());
  }

  // Bottom right of rectangle is defined relative to the reference point, position:
  private Point1 position;
  private Point1 bottomRight;        // Bottom right of rectangle.
}
