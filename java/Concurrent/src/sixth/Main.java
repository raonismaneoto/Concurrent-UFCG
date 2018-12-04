package sixth;

import first.Channel;
import first.ChannelImpl;

public class Main {

	public static void main(String args[]) {
		Channel<Integer> buffer = new ChannelImpl<Integer>(10);
		Producer producer = new Producer(buffer);
		Consumer consumer = new Consumer(buffer);
		
		Thread p = new Thread(producer);
		Thread c = new Thread(consumer);
		
		p.start();
		c.start();
		
		
		try {
			c.join();
			p.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
