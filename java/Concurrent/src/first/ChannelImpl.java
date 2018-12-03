package first;

import java.util.ArrayList;
import java.util.List;

public class ChannelImpl<T> implements Channel<T> {
	
	private List<T> buffer;
	private int limit;
	
	public ChannelImpl(int limit) {
		this.limit = limit;
		this.buffer = new ArrayList<T>();
	}
	
	public synchronized void putMessage(T message) {
		while (isFull()) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		this.buffer.add(message);
		notifyAll();
	}
	
	public synchronized T takeMessage() {
		while (isEmpty()) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		T returnValue = this.buffer.remove(0);
		notifyAll();
		return returnValue;
	}
	
	public synchronized boolean isEmpty() {
		return this.buffer.size() == 0;
	}
	
	public synchronized boolean isFull() {
		return this.limit == this.buffer.size();
	}
}
