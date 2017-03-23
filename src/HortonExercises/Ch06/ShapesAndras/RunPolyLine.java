/*This is to test the revers PolyLine display*/
package HortonExercises.Ch06.ShapesAndras;

import HortonExercises.Ch06.ShapesAndras.LinkedList.ListItem;

/**
 *
 * @author Andras Olah (olahandras78@gmail.com)
 */
public class RunPolyLine {

	public static void main(String[] args) {
		Point[] randomPoints = new Point[1 + ((int) (Math.random() * 10))];
		for (int i = 0; i < randomPoints.length; i++) {
			randomPoints[i] = new Point(Math.random() * 100, Math.random() * 100);
		}
		PolyLine pl = new PolyLine(randomPoints);
		System.out.println("Pontok száma: " + randomPoints.length);
		System.out.println(pl);

		System.out.println(pl.polyline);
		//Write out in a reverse order
		System.out.println("Reverse order:\n");
		for (int i = randomPoints.length; i > 0; i--) {
			if (i == randomPoints.length) {
				System.out.println(Integer.toString(i) + ". " + pl.polyline.getLast());
			} else {
				System.out.println(Integer.toString(i) + ". " + pl.polyline.getPrev());
			}
		}

	}
}
