/*
Define a class, ShapeList, that can store an arbitrary collection of any objects of subclasses of the Shape class

 */
package HortonExercises.Ch06.ShapesAndras;

/**
 *
 * @author Andras Olah (olahandras78@gmail.com)
 */
public class ShapeList extends LinkedList {
//Constructor for creating a new ShapeList

	public LinkedList shapeList; //minden ShapeList objban lesz egy LinkedList obj

	ShapeList() {
		new LinkedList();
	}

	ShapeList(Shape[] inputShapes) {
		shapeList= new LinkedList();
		for (Shape input:inputShapes){
		shapeList.addItem(input);
		}
	}

	@Override
	public String toString(){
		StringBuffer rtn = new StringBuffer("Shapes List toString()\nShapesList obj #"+hashCode()+"\n");
		rtn.append(shapeList.toString());
		return rtn.toString();
	}
}
