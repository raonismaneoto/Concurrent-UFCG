package second;

import java.util.concurrent.locks.ReentrantLock;

public class Main {
	private String data;
	private ReentrantLock lock = new ReentrantLock();
	
	public void write(String data) {
		this.data = data;
	}
	
	public ReentrantLock getLock() {
		return this.lock;
	}
	
	public synchronized boolean hasWritten() {
		return this.data != null;
	}
	
	private String reliableRequest() throws InterruptedException {
		Thread mirror1 = new Thread(new Mirror(this, "mirror1.com"));
		Thread mirror2 = new Thread(new Mirror(this, "mirror2.br"));
		Thread mirror3 = new Thread(new Mirror(this, "mirror3.edu"));
		
		mirror1.start();
		mirror2.start();
		mirror3.start();
		
		mirror1.join();
		mirror2.join();
		mirror3.join();
		return this.data;
	}
	
	public static void main(String[] args) throws InterruptedException {
		Main m = new Main();
		String result = m.reliableRequest();
		System.out.println(result);
	}
}
