package ClassesAndObjects;

import java.util.Observable;
import java.util.Observer;

public class ObservableObserverSkeleton extends Observable {
	public void notifyObserverClass() {
		setChanged();
		notifyObservers();
	}

	public static void main(String[] args) {
		ObservableObserverSkeleton instance = new ObservableObserverSkeleton();
		ObserverClass observerInstance = new ObserverClass();
		instance.addObserver(observerInstance);
		instance.notifyObserverClass();
	}
}

class ObserverClass implements Observer {

	@Override
	public void update(Observable o, Object arg) {
		System.out.println("Notified!");
	}

}
