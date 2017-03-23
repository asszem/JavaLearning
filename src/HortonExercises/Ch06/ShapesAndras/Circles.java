package HortonExercises.Ch06.ShapesAndras;

/**
 *
 * @author Andras Olah (olahandras78@gmail.com)
 */
public class Circles extends Shape  implements ShapeInterface{

	private Point center;
	private int radius;

//Constructors
	public Circles(Point centerInput, int radiusInput) {
		center = centerInput;
		radius = radiusInput;
		posX = (int) centerInput.x;
		posY = (int) centerInput.y;
	}

	@Override
	public void moveShape(int moveX, int moveY) {
		center = new Point (center.x+moveX, center.y+moveY);
	}

	@Override
	public void showShape() {
		System.out.println(toString());
	}

	@Override
	public String toString(){
		String str = "Ez egy kör."
				+"\n\t\tOsztály: " + getClass().getSimpleName()
				+ "\n\t\tHash:" + hashCode()
//				+ "\n\t\tKezdőpont:\t" + center
//				+ "\n\t\tSugár:\t\t" + radius
				;
		return str;
	}
}
