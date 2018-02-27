package DesignPatterns.Creational.Singleton;

public class SingletonDemo {
	public static void main(String[] args) {
		// Illegal call
		// SingletonObject instance = new SingletonObject();
		
		SingletonObject instance1 = SingletonObject.getSingletonObject();
		SingletonObject instance2 = SingletonObject.getSingletonObject();
		
		System.out.println("instance1 hash: " + instance1.showInstanceHash());
		System.out.println("instance2 hash: " + instance1.showInstanceHash());
		System.out.println("instance3 hash: " + instance1.showInstanceHash());
		
		SingletonDemo nonSingletonInstance1 = new SingletonDemo();
		SingletonDemo nonSingletonInstance2 = new SingletonDemo();
		SingletonDemo nonSingletonInstance3 = new SingletonDemo();
		
		System.out.println("nonSingleton instance1 hash " + nonSingletonInstance1.hashCode());
		System.out.println("nonSingleton instance2 hash " + nonSingletonInstance2.hashCode());
	}
}

class SingletonObject {

	// Create a private static instance
	private static SingletonObject singleInstance = new SingletonObject();

	// Make the Constructor private so it can not be called
	private SingletonObject() {
	}

	// Create a STATIC getter that will provide the only object
	public static SingletonObject getSingletonObject() {
		return singleInstance;
	}
	
	public int showInstanceHash() {
		return this.hashCode();
	}

}
