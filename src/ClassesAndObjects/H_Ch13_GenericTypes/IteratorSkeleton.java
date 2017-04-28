package ClassesAndObjects.H_Ch13_GenericTypes;

import java.util.Iterator;
import java.util.function.Consumer;

/**
 * @author Andras Olah (olahandras78@gmail.com)
 */
public class IteratorSkeleton<T> implements Iterable<T> {

	@Override
	public Iterator<T> iterator() {
//		IteratorImplementation iteratorObject= new IteratorImplementation();
		return new IteratorImplementation();//object without reference variable
	}

	private class IteratorImplementation implements Iterator<T> {

		//constructor 
		public IteratorImplementation(){

		}

		@Override
		public boolean hasNext() {
			throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
		}

		@Override
		public T next() {
			throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
		}

		@Override
		public void remove() {
			Iterator.super.remove(); //Interface has default implementation
		}

		@Override
		public void forEachRemaining(Consumer<? super T> cnsmr) {
			Iterator.super.forEachRemaining(cnsmr); //Interface has default implementation
		}

	}

	public static void main(String[] args) {
		IteratorSkeleton<String> strings = new IteratorSkeleton<>();
		for (String string : strings) {
			System.out.println(string);
		}
	}

}
