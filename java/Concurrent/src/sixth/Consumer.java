package sixth;

import java.util.ArrayList;
import java.util.List;

public class Consumer implements Runnable{
	
	private List<Integer> buffer;
	
	public Consumer(ArrayList<Integer> buffer) {
		this.buffer = buffer;
	}
	
	@Override
	public void run() {
		while (true) {
			synchronized(this.buffer) {
				while(this.buffer.isEmpty()) {
					try {
						this.buffer.wait();
					} catch (Exception e) {
					}
				}
				try {
					Integer currentValue = this.buffer.remove(0);
					if(currentValue %2 == 0) {
						System.out.println("taken " + currentValue);
					}
					Thread.sleep(1000);
				} catch (Exception e) {
				}
			}
		}
	}

}
