package first;

public interface Channel<T> {
	
	T takeMessage();
	void putMessage(T message);
	boolean isFull();
	boolean isEmpty();
}
