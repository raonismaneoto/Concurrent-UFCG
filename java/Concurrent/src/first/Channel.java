package first;

public interface Channel {
	
	public void putMessage(String message) throws Exception;
	public String takeMessage() throws Exception;
	public boolean isFull();
	public boolean isEmpty();
}
