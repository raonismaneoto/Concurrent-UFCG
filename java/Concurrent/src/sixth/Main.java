package sixth;

import java.util.ArrayList;

public class Main {

	public static void main(String args[]) {
		ArrayList<Integer> buffer = new ArrayList<Integer>();
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
