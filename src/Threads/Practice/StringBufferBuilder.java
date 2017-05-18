package Threads.Practice;

public class StringBufferBuilder implements Runnable {

	StringBuilder sBuilder;
	StringBuffer sBuffer;
	int instanceCounter;

	public StringBufferBuilder() {
		this.sBuffer = new StringBuffer();
		this.sBuilder = new StringBuilder();
		this.instanceCounter = 0;
	}

	public void appendBuilder(String str) {
		sBuilder.append(str);
	}

	public void appendBuffer(String str) {
		sBuffer.append(str);
	}

	@Override
	public void run() {
		// call the buffer / builder methods and increment the object value
		System.out.println("Thread started: " + Thread.currentThread().getName());
		int counter = 0;
		while (counter < 10) {
			try {
				Thread.sleep(1000);
				appendBuilder(String.valueOf(counter));
				appendBuffer(String.valueOf(counter));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			counter++;
			instanceCounter++;
		}
		System.out.println("Buffer:" + sBuffer);
		System.out.println("Builder:" + sBuilder);
		System.out.println(instanceCounter);
	}

	public static void main(String[] args) {
		StringBufferBuilder instance1 = new StringBufferBuilder();					// Creates a new instance
		Thread bufferThread = new Thread(instance1, "buffer thread");				// Creates a new thread
		Thread builderThread = new Thread(instance1, "builder thread");
		bufferThread.setDaemon(false);												// Sets the thread to User
		builderThread.setDaemon(false);
		bufferThread.start();														// Starts the thread
		builderThread.start();
	}
}
