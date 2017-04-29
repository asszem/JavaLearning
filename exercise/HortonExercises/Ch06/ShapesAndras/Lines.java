package HortonExercises.Ch06.ShapesAndras;

/**
 *
 * @author Andras Olah (olahandras78@gmail.com)
 */
public class Lines extends Shape implements ShapeInterface{
// You can represent a line as two points,

	Point lineStart;
	Point lineEnd;

//Constructors
	//From two point objects
	public Lines(Point lineStartInput, Point lineEndInput) {
		lineStart = lineStartInput;
		lineEnd = lineEndInput;
		posX = (int) lineStartInput.x;
		posY = (int) lineStartInput.y;
	}

	//From set of coordinates
	public Lines(int startX, int startY, int endX, int endY) {
		lineStart = new Point(startX, startY);
		lineEnd = new Point(endX, endY);
		posX = (int) startX;
		posY = (int) startY; 
	}

	//When no parameters is given, use the base class's protected members posX and posY
	public Lines() {
		lineStart = new Point(posX, posY);
		lineEnd = new Point(posX, posY);
		posX = 0;
		posY = 0;
	}

	@Override
	public void moveShape(int moveX, int moveY) {
		lineStart = new Point(lineStart.x + moveX, lineStart.y + moveY);
		lineEnd = new Point(lineEnd.x + moveX, lineEnd.y + moveY);
	}

	@Override
	public void showShape() {
		System.out.println(this.toString());
	}

	@Override
	public String toString() {
		String str = "Ez egy vonal. "
				+"\n\t\tOsztály: " + getClass().getSimpleName()
				+ "\n\t\tHash:" + hashCode()
				+ "\n\t\tKezdőpont:\t" + lineStart
				+ "\n\t\tVégpont:\t" + lineEnd
				;
		return str;
	}
}
