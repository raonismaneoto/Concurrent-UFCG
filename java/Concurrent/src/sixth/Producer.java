package sixth;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Producer implements Runnable{

	private List<Integer> buffer;
	
	public Producer(ArrayList<Integer> buffer) {
		this.buffer = buffer;
	}
	
	@Override
	public void run() {
		while (true) {
			synchronized(this.buffer) {
				Integer produced = new Random().nextInt(11);
				try {
					System.out.println("produced " + produced);
					this.buffer.add(produced);
					this.buffer.notifyAll();
					Thread.sleep(10000);
				} catch (Exception e) {
				}
			}
		}
	}
}
