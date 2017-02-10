package algo.data.structures;

import java.util.NoSuchElementException;

public class SingleLinkedList<T> {
	protected Node<T> head;
	protected Node<T> tail;
	protected int count = 0;

	public void add(T value) {
		if (count == 0) {
			Node<T> newNode = new Node<T>(null, value);
			this.setHead(newNode);
			this.setTail(newNode);
		} else {
			tail.setNext(new Node<T>(null, value));
			tail = tail.getNext();
		}
		++count;
	}

	public void insert(T value, int index) {
		if (index >= 0 && index < count && count > 0) {
			Node<T> current = getSpecificNode(index);
			Node<T> newNode = new Node<T>(current, value);
			if (index != 0) {
				Node<T> previous = getSpecificNode(index - 1);
				previous.setNext(newNode);
			} else
				this.setHead(newNode);
			++count;
		} else {
			throw new IndexOutOfBoundsException("Invalid index: " + index + " for list size: " + count);
		}
	}

	public T get(int index) {
		if (index >= 0 && index < count && count > 0) {
			return getSpecificNode(index).getValue();
		} else {
			throw new IndexOutOfBoundsException("Invalid index: " + index + " for list size: " + count);
		}
	}

	 public int count() {
		return count;
	}

	public T remove() {
		if (count > 0) {
			Node<T> toRemove = head;
			this.setHead(getSpecificNode(1));
			--count;
			return toRemove.getValue();
		} else
			throw new NoSuchElementException("The list is Empty");

	}

	public T removeAt(int index) {
		if (index >= 0 && index < count && count > 0) {
			Node<T> nodeToRemove = getSpecificNode(index);
			if (index > 0)
				getSpecificNode(index - 1).setNext(getSpecificNode(index + 1));
			else
				head = getSpecificNode(index + 1);
			--count;
			return nodeToRemove.getValue();
		} else {
			throw new IndexOutOfBoundsException("Invalid index: " + index + " for list size: " + count);
		}

	}

	public T removeLast() {
		Node<T> toRemove = tail;
		if (count > 0) {
			if (count > 1) {
				Node<T> newTail = getSpecificNode(count - 2);
				newTail.setNext(null);
				this.setTail(newTail);
				--count;
			} else
				clear();
		} else {
			throw new NoSuchElementException("The list is Empty");
		}

		return toRemove.getValue();
	}

	@Override
	public String toString() {
		Node<T> temp = head;
		String response = "";
		while (temp != null) {
			response += temp.getValue().toString();
			temp = temp.getNext();
			if (temp != null)
				response += ", ";
		}
		return response;
	}

	public void clear() {
		this.setHead(null);
		this.setTail(null);
		count = 0;
	}

	public int search(T value) {
		int index = -1;
		if (!isEmpty()) {
			boolean found = false;
			Node<T> temp = head;
			for (int i = 0; i < count && !found; ++i) {
				if (temp.getValue().equals(value))
					index = i;
				temp = temp.getNext();
			}
		}
		return index;
	}

	public boolean isEmpty() {
		return count == 0;
	}

	protected Node<T> getSpecificNode(int index) {
		Node<T> temp = head;
		for (int i = 1; i <= index; ++i) {
			temp = temp.getNext();
		}
		return temp;
	}

	protected void setHead(Node<T> head) {
		this.head = head;
	}

	protected void setTail(Node<T> tail) {
		this.tail = tail;
	}

}