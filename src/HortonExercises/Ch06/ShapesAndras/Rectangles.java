package HortonExercises.Ch06.ShapesAndras;

/**
 *
 * @author Andras Olah (olahandras78@gmail.com)
 */
public class Rectangles extends Shape  implements ShapeInterface{
//A rectangle as two points on diagonally opposite corners. 

	private Point topLeft;
	private Point bottomRight;
//Constructor

	public Rectangles(Point tlInput, Point brInput) {
		//Validálás.  A bal felső X<mint a jobb alsó X és a bal felső Y> mint a jobb alsó Y
		/*Ez jó, de nem ez volt a feladat. Az első koordináta mindenképp a kezdőpont lesz
		double tlx = tlInput.x;
		double tly = tlInput.y;
		double brx = brInput.x;
		double bry = brInput.y;
		if (brx < tlx) {
			double temp = tlx;
			tlx = brx;
			brx = temp;
		}
		if (tly < bry) {
			double temp = tly;
			tly = bry;
			bry = tly;
		}
*/
		topLeft = tlInput;
		bottomRight =brInput;
	}


	@Override
	public void moveShape(int moveX, int moveY) {
		topLeft = new Point(topLeft.x+moveX, topLeft.y+moveY);
		bottomRight = new Point(bottomRight.x+moveX, bottomRight.y+moveY);
	}

	@Override
	public void showShape() {
		System.out.println(toString());
	}
	public String toString(){
		String str = "Ez egy négyzet."
				+"\n\t\tOsztály: " + getClass().getSimpleName()
				+ "\n\t\tHash:" + hashCode()
//				+ "\n\t\tStart position:\t" + topLeft
//				+ "\n\t\tOpposite corner:" + bottomRight
				;
		return str;
	}

}
