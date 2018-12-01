package first;

import java.util.ArrayList;
import java.util.List;

public class ChannelImpl implements Channel {
	
	private List<String> buffer;
	private int limit;
	
	public ChannelImpl(int limit) {
		this.limit = limit;
		this.buffer = new ArrayList<String>();
	}
	
	public synchronized void putMessage(String message) throws Exception {
		if (isFull()) {
			throw new Exception("Buffer limit exceeded");
		}
		
		this.buffer.add(message);
	}
	
	public synchronized String takeMessage() throws Exception {
		if(isEmpty()) {
			throw new Exception("The buffer is empty");
		}
		return this.buffer.remove(0);
	}
	
	public synchronized boolean isEmpty() {
		return this.buffer.size() == 0;
	}
	
	public synchronized boolean isFull() {
		return this.limit == this.buffer.size();
	}
}
