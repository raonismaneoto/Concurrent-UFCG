package seventh;

import java.util.ArrayList;
import java.util.List;

public class StringFilter implements Runnable{
	
	private List<String> inputBuffer;
	private List<String> outputBuffer;
	
	public StringFilter(ArrayList<String> input, ArrayList<String> output) {
		this.inputBuffer = input;
		this.outputBuffer = output;
	}
	
	private boolean isAlpha(String input) {
		return input.matches("[a-zA-Z]+");
	}
	
	public void run() {
		while(true) {
			synchronized(inputBuffer) {
				synchronized(outputBuffer) {
					while(inputBuffer.isEmpty()) {
						try {
							inputBuffer.wait();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					
					String currentString = inputBuffer.remove(0);
					if(isAlpha(currentString)) {
						outputBuffer.add(currentString);
						outputBuffer.notifyAll();
					}
				}
				

			}
		}
	}

}
