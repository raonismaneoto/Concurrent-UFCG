package second;

import java.util.concurrent.locks.ReentrantLock;

import first.Channel;
import first.ChannelImpl;

public class ReliableRequest {
	private Channel<String> chan;
	private ReentrantLock lock = new ReentrantLock();
	
	public ReliableRequest() {
		chan = new ChannelImpl<String>(1);
	}
	
	public void write(String data) {
		chan.putMessage(data);
	}
	
	public ReentrantLock getLock() {
		return this.lock;
	}
	
	public synchronized boolean hasWritten() {
		return this.chan.isFull();
	}
	
	public String reliableRequest() {
		Thread mirror1 = new Thread(new Mirror(this, "mirror1.com"));
		Thread mirror2 = new Thread(new Mirror(this, "mirror2.br"));
		Thread mirror3 = new Thread(new Mirror(this, "mirror3.edu"));
		
		mirror1.start();
		mirror2.start();
		mirror3.start();
		
		return this.chan.takeMessage();
	}
	
	public static void main(String[] args) {
		ReliableRequest m = new ReliableRequest();
		String result = m.reliableRequest();
		System.out.println(result);
	}
}
