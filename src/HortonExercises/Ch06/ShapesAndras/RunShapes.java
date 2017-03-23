package HortonExercises.Ch06.ShapesAndras;

import java.util.Random;

/**
 *
 * @author Andras Olah (olahandras78@gmail.com)
 */
public class RunShapes {

	public static void main(String[] args) {
		Random rnd = new Random();
		/*
		Point pont1 = new Point(1, 1);
		Point pont2 = new Point(10, 10);
		Lines vonalTeszt = new Lines(10, 10, 20, 20);
		vonalTeszt.showShape();
		Shape vonalTeszt2 = new Lines(pont1, pont2);
		vonalTeszt2.showShape();
		Shape vonalTeszt3 = new Lines();
		vonalTeszt3.showShape();
		System.out.println(vonalTeszt.posX);
		System.out.println(vonalTeszt.posY);
		Shape kor1 = new Circles(pont1, 10);
		kor1.showShape();
		Shape negyzet1 = new Rectangles(pont2, pont1);
		negyzet1.showShape();
		Shape rndShape;
		ShapeList lista = new ShapeList();
		for (int i = 1; i < 11; ++i) {
			switch (rnd.nextInt(3)) {
				case 0: //generate a Line!
					rndShape = new Lines(rnd.nextInt(100), rnd.nextInt(100), rnd.nextInt(100), rnd.nextInt(100));
					break;
				case 1: //generate a Circle!
					rndShape = new Circles(new Point(rnd.nextInt(100), rnd.nextInt(100)), rnd.nextInt(100) );
					break;
				case 2: //Generate a Rectangle
					rndShape = new Rectangles(
							new Point(rnd.nextInt(100), rnd.nextInt(100))
							,
							new Point(rnd.nextInt(100), rnd.nextInt(100))
					);
					break;
				default:
					rndShape = null;
					break;
			}
			System.out.println("Iteráció #"+i);
			rndShape.showShape();
			System.out.println("");
			lista.addItem(rndShape);
		}
		System.out.println("A ShapeList első eleme: "+ lista.getFirst());

		 */
 /*
Define a class, ShapeList, that can store an arbitrary collection of any objects of subclasses of the Shape class

		 */
		Shape[] rndmShapes = new Shape[3];
		for (int i = 0; i < rndmShapes.length; i++) {
			switch (rnd.nextInt(4)) {
				case 0: //generate a Line!
					rndmShapes[i] = new Lines(rnd.nextInt(100), rnd.nextInt(100), rnd.nextInt(100), rnd.nextInt(100));
					break;
				case 1: //generate a Circle!
					rndmShapes[i] = new Circles(new Point(rnd.nextInt(100), rnd.nextInt(100)), rnd.nextInt(100));
					break;
				case 2: //Generate a Rectangle
					rndmShapes[i] = new Rectangles(
							new Point(rnd.nextInt(100), rnd.nextInt(100)),
							new Point(rnd.nextInt(100), rnd.nextInt(100))
					);
					break;
				case 3: //Generate a PolyLine
				{
					Point[] randomPontok = new Point[(int) (Math.random() * 11)]; //0-10 közötti tömb elem
					for (int k = 0; k < randomPontok.length; k++) { //mert az i már foglalt
						randomPontok[k] = new Point(Math.random()*101, Math.random()*101);
					}
				rndmShapes[i] = new PolyLine(randomPontok);
					System.out.println("Ezt a PolyLine-t csináltam!");
					System.out.println(rndmShapes[i]);
				}
				break;
				default:
					rndmShapes[i] = null;
					break;
			}
		}
//		System.out.println("");
//		for (Shape o : rndmShapes) {
//			System.out.println(o);
//		}
		ShapeList lista2 = new ShapeList(rndmShapes);
		System.out.println(lista2);
//		System.out.println("First: "+lista2.shapeList.getFirst());
//		System.out.println("Current: "+ lista2.shapeList.getCurrent());
//		System.out.println("Get next: "+lista2.shapeList.getNext());
//		System.out.println("Current: "+ lista2.shapeList.getCurrent());
//		System.out.println("Get PREV: "+lista2.shapeList.getPrev());
//		System.out.println("Current: "+ lista2.shapeList.getCurrent());
		int hashCodeCurrent = lista2.shapeList.getCurrent() == null ? 0 : lista2.shapeList.getCurrent().hashCode();
		lista2.shapeList.getFirst();
		lista2.shapeList.getNext();
//		lista2.shapeList.insertItem(new Lines(100,100,200,200));
		lista2.shapeList.getNext();
		lista2.shapeList.getNext();
		lista2.shapeList.getNext();
		lista2.shapeList.getNext();
		lista2.shapeList.getNext();
		System.out.println("***Current: " + hashCodeCurrent);
		System.out.println(lista2);
		lista2.shapeList.getFirst();
		lista2.shapeList.getNext();
		hashCodeCurrent = lista2.shapeList.getCurrent() == null ? 0 : lista2.shapeList.getCurrent().hashCode();
		System.out.println("***Current pre delete: " + hashCodeCurrent);
		lista2.shapeList.deleteItem();
		hashCodeCurrent = lista2.shapeList.getCurrent() == null ? 0 : lista2.shapeList.getCurrent().hashCode();
		System.out.println("***Current: " + hashCodeCurrent);
		System.out.println(lista2.shapeList.getCurrent());
		boolean equalsTest;
		equalsTest = lista2.shapeList.getCurrent().equals(lista2.shapeList.getCurrent());
		System.out.println("Egyenlő: " + equalsTest);
		equalsTest = lista2.shapeList.getCurrent().equals(lista2.shapeList.getNext());
		System.out.println("Egyenlő: " + equalsTest);
//		System.out.println(lista2);
	}
}
