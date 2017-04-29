package ClassesAndObjects.LinkedLists.GenericLinkedList;

public class PolyLine {

	//The PolyLine class creates the GenericLinkedList<Point> type from the GenericLinkedList<T> generic type
	//that implements a linked list of Point objects:
	private GenericLinkedList<Point> polyline;                   // The linked list of points, instantiated as type POINT

	// Construct a polyline from an array of coordinate pairs
	public PolyLine(double[][] coords) {
		Point[] points = new Point[coords.length];                         // Array to hold points

		// Create points from the coordinates
		for (int i = 0; i < coords.length; ++i) {
			points[i] = new Point(coords[i][0], coords[i][1]);
		}

		// Create the polyline from the array of points
		polyline = new GenericLinkedList<Point>(points);         // Create list of Point objects. 
		//'polyline' instantiated as type 'Point', this is why the warning of redundant type argument

	}

	// Construct a polyline from an array of points
	public PolyLine(Point[] points) {
		polyline = new GenericLinkedList<>(points);                               // Create list of Point objects
	}

	// Add a Point object to the list
	public void addPoint(Point point) {
		polyline.addItem(point);                                           // Add the point to the list
	}

	// Add a point from a coordinate pair to the list
	public void addPoint(double x, double y) {
		polyline.addItem(new Point(x, y));                                // Add the point to the list
	}

	// String representation of a polyline
	@Override
	public String toString() {
		StringBuffer str = new StringBuffer("Polyline:");
		Point point = polyline.getFirst();
		// Set the 1st point as start
		while (point != null) {
			str.append(" (" + point + ")");                                    // Append the current point
			point = polyline.getNext();                                      // Make the next point current
		}
		return str.toString();
	}

}
