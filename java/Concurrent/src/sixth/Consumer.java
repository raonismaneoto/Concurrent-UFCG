package sixth;

import java.util.ArrayList;
import java.util.List;

import first.Channel;

public class Consumer implements Runnable{
	
	private Channel<Integer> buffer;
	
	public Consumer(Channel<Integer> buffer) {
		this.buffer = buffer;
	}
	
	@Override
	public void run() {
		while (true) {
			Integer currentValue = (Integer) this.buffer.takeMessage();
			if(currentValue %2 == 0) {
				System.out.println("taken " + currentValue);
			}
			
			try {
				Thread.sleep(1000);
			} catch (Exception e) {}
		}
	}
}
