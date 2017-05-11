package ClassesAndObjects.Practice.H_Ch15_UsefulClasses;

import java.util.Observable;
import java.util.Observer;

public class ObservableAndras extends Observable {

	int countObservers;
	String content;

	public void notifyAndrasObservers(String notificationMsg) {
		content = "The content has changed!";
		setChanged();
		notifyObservers(notificationMsg);
	}

	public static void main(String[] args) {
		// Instantiate an observable object
		ObservableAndras observableObject = new ObservableAndras();
		// Instantiate observer objects
		AndrasObserver[] observers = new AndrasObserver[3];
		observers[0] = new AndrasObserver("First observer");
		observers[1] = new AndrasObserver("Second observer");
		observers[2] = new AndrasObserver("Third observer");
		AsszemObserver asszemObserver=new AsszemObserver("Asszem reporting");
		// Add Observers to the Observable object so they will be notified
		for (AndrasObserver currentObserver : observers) {
			observableObject.addObserver(currentObserver);
		}
		observableObject.addObserver(asszemObserver);
		System.out.println("Observers: "+ observableObject.countObservers());
		// Change object
		observableObject.content = "Hello! I have changed!";
		// Notify observers about the change
		observableObject.notifyObservers(); // No setChanged was called
		observableObject.setChanged();
		observableObject.notifyObservers(); // After setChanged called,
											// observers were notified
		observableObject.setChanged();
		observableObject.notifyObservers("String argumentum!");
		// Notify observers via the method in class
		observableObject.notifyAndrasObservers("Sent from notifyObservers method");

		System.out.println("End.");
	}
}

class AndrasObserver implements Observer {
	String observerID;

	public AndrasObserver(String myID) {
		this.observerID = myID;
	}

	@Override
	public void update(Observable ObservableAndras, Object arg) {
		System.out.println(observerID + ": Update method called! " + (arg == null ? "" : arg.toString()));
	}
}

class AsszemObserver implements Observer {
	String observerID;

	public AsszemObserver(String myID) {
		this.observerID = myID;
	}

	@Override
	public void update(Observable ObservableAndras, Object arg) {
		System.out.println(observerID + ": Update method called! " + (arg == null ? "" : arg.toString()));
	}

}
