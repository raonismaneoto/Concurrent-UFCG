package seventh;

import java.util.ArrayList;
import java.util.List;

import first.Channel;

public class StringPrinter implements Runnable{
	
	private Channel<String> buffer;
	
	public StringPrinter(Channel<String> buffer) {
		this.buffer = buffer;
	}
	
	public void run() {
		while(true) {
			System.out.println("Current alpha string in buffer is " + buffer.takeMessage());
		}
	}

}
