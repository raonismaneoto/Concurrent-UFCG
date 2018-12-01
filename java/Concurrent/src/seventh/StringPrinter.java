package seventh;

import java.util.ArrayList;
import java.util.List;

public class StringPrinter implements Runnable{
	
	private List<String> buffer;
	
	public StringPrinter(ArrayList<String> buffer) {
		this.buffer = buffer;
	}
	
	public void run() {
		while(true) {
			synchronized(buffer) {
				while(buffer.isEmpty()) {
					try {
						buffer.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				
				System.out.println("Current alpha string in buffer is " + buffer.remove(0));
			}
		}
	}

}
