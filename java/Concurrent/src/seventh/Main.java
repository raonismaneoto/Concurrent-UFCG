package seventh;

import java.util.ArrayList;

public class Main {

	public static void main(String args[]) {
		ArrayList<String> notFilteredStrings = new ArrayList<String>();
		ArrayList<String> filteredStrings = new ArrayList<String>();
		
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
