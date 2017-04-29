package ClassesAndObjects.Practice.ItaratorAndras;

import java.util.Iterator;

/**
 *
 * @author András
 */
public class IteratorNotSupportedYet<T> implements Iterable<T>{

	//This method MUST be implemented from the Iterable<T> interface
	@Override
	public Iterator<T> iterator() {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}
	public static void main(String[] args) {
		//Create a new instance 
		IteratorNotSupportedYet<String> instance=new IteratorNotSupportedYet();

		//Run the collection based for-loop
		for (String element:instance){
			System.out.println(element);
		}
	}

}
