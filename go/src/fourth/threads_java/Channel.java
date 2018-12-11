package threads_java;

public interface Channel<T> {
	
	public void put(T v);
	public T get();
	
}
