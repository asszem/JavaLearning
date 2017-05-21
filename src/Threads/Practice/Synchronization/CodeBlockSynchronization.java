package Threads.Practice.Synchronization;

public class CodeBlockSynchronization implements Runnable{

	String text="a";

	synchronized public void updateText() {
		this.text = "Text is updated";
	}
	
	@Override
	public void run(){
		updateText();
	}

	public static void main(String[] args) {
		CodeBlockSynchronization instance = new CodeBlockSynchronization();
		String thisIsAStringObject = "This is a String Object";
		synchronized (thisIsAStringObject) {
			// no other synched method can touch this object
		}
		Thread thread = new Thread(instance);
		thread.setDaemon(true);
		thread.start();
		synchronized (instance.text) {
			instance.text = "Synced in Main";
		}
		try {
			Thread.sleep(25);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		System.out.println(instance.text);
		System.out.println(instance.text);
	}
}
