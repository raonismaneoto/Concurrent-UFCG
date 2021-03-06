package seventh;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import first.Channel;

public class StringGenerator implements Runnable{
	
	private Channel<String> buffer;
	
	private static final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
	
	private String getRandomString() {
		String str = "";
		for(int i = 0; i < 10; i++) {
			int character = (int)(Math.random()*ALPHA_NUMERIC_STRING.length());
			str += ALPHA_NUMERIC_STRING.charAt(character);
		}
		return str;
	}
	
	public StringGenerator(Channel<String> buffer) {
		this.buffer = buffer;
	}
	
	public void run() {
		while(true) {
			String generated = getRandomString();
			this.buffer.putMessage(generated);
		}
	}

}
