package seventh;

import java.util.ArrayList;
import java.util.List;

import first.Channel;

public class StringFilter implements Runnable{
	
	private Channel<String> inputBuffer;
	private Channel<String> outputBuffer;
	
	public StringFilter(Channel<String> input, Channel<String> output) {
		this.inputBuffer = input;
		this.outputBuffer = output;
	}
	
	private boolean isAlpha(String input) {
		return input.matches("[a-zA-Z]+");
	}
	
	public void run() {
		while(true) {
			String currentString = inputBuffer.takeMessage();
			if(isAlpha(currentString)) {
				outputBuffer.putMessage(currentString);
			}
		}
	}

}
