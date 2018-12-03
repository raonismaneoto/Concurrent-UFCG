package seventh;

import java.util.ArrayList;

import first.Channel;
import first.ChannelImpl;

public class Main {

	public static void main(String args[]) {
		Channel<String> notFilteredStrings = new ChannelImpl<String>(10);
		Channel<String> filteredStrings = new ChannelImpl<String>(10);
		
		StringGenerator generator = new StringGenerator(notFilteredStrings);
		StringFilter filter = new StringFilter(notFilteredStrings, filteredStrings);
		StringPrinter printer = new StringPrinter(filteredStrings);
		
		Thread g = new Thread(generator);
		Thread f = new Thread(filter);
		Thread p = new Thread(printer);
		
		g.start();
		f.start();
		p.start();
		
		try {
			g.join();
			f.join();
			p.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}	
	}
}
