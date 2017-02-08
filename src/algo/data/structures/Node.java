package algo.data.structures;

public class Node<T> {
	private T value;
	private Node<T> next;
	private Node<T> previous;
	
	public Node(Node<T> next, Node<T> previous, T value){
		this.setNext(next);
		this.setPrevious(previous);
		this.setValue(value);
	}
	public Node(Node<T> next, T value){
		this.setNext(next);
		this.setPrevious(null);
		this.setValue(value);
	}
	public T getValue() {
		return value;
	}
	public void setValue(T value) {
		this.value = value;
	}
	public Node<T> getNext() {
		return next;
	}
	public void setNext(Node<T> next) {
		this.next = next;
	}
	public Node<T> getPrevious() {
		return previous;
	}
	public void setPrevious(Node<T> previous) {
		this.previous = previous;
	}
	
}
