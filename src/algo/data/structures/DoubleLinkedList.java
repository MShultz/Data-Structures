package algo.data.structures;

import java.util.NoSuchElementException;

public class DoubleLinkedList<T> extends SingleLinkedList<T> {

	@Override
	public boolean add(T value) {
		if (count == 0) {
			Node<T> newNode = new Node<T>(null, null, value);
			this.setHead(newNode);
			this.setTail(newNode);
		} else {
			Node<T> newNode = new Node<T>(null, tail, value);
			tail.setNext(newNode);
			this.setTail(newNode);
		}
		++count;
		return true;
	}

	@Override
	public void insert(T value, int index) {
		if (index >= 0 && index < count && count > 0) {
			Node<T> current = getSpecificNode(index);
			if (index != 0) {
				Node<T> previous = current.getPrevious();
				Node<T> newNode = new Node<T>(current, previous, value);
				current.setPrevious(newNode);
				previous.setNext(newNode);
			} else {
				head.setPrevious(new Node<T>(head, null, value));
				this.setHead(head.getPrevious());
			}
			++count;
		} else {
			throw new IndexOutOfBoundsException("Invalid index: " + index + " for list size: " + count);
		}
	}

	@Override
	public T get(int index) {
		if (index >= 0 && index < count && count > 0) {
			return getSpecificNode(index).getValue();
		} else {
			throw new IndexOutOfBoundsException("Invalid index: " + index + " for list size: " + count);
		}
	}

	@Override
	public T remove() {
		if (count > 0) {
			Node<T> toRemove = head;
			if (count > 1) {
				this.setHead(toRemove.getNext());
				head.setPrevious(null);
				--count;
			} else {
				clear();
			}
			return toRemove.getValue();
		} else
			throw new NoSuchElementException("The list is Empty");
	}

	@Override
	public T removeAt(int index) {
		if (index >= 0 && index < count && count > 0) {
			Node<T> nodeToRemove = getSpecificNode(index);
			Node<T> after = nodeToRemove.getNext();
			Node<T> before = nodeToRemove.getPrevious();
			if (index > 0 && index != count - 1) {
				before.setNext(after);
				after.setPrevious(before);
			} else if (index == 0) {
				after.setPrevious(null);
				this.setHead(after);
			} else {
				before.setNext(null);
				this.setTail(before);
			}
			--count;
			return nodeToRemove.getValue();
		} else {
			throw new IndexOutOfBoundsException("Invalid index: " + index + " for list size: " + count);
		}
	}

	@Override
	public T removeLast() {
		Node<T> toRemove = tail;
		if (count > 0) {
			if (count > 1) {
				Node<T> newTail = tail.getPrevious();
				newTail.setNext(null);
				this.setTail(newTail);
				--count;
			} else
				clear();
			return toRemove.getValue();
		} else {
			throw new NoSuchElementException("The list is Empty");
		}
	}

	@Override
	protected Node<T> getSpecificNode(int index) {
		Node<T> temp;
		int mid = count / 2;
		if (index <= mid) {
			temp = head;
			for (int i = 1; i <= index; ++i) {
				temp = temp.getNext();
			}
		} else {
			temp = tail;
			for (int i = count - 1; i > index; --i) {
				temp = temp.getPrevious();
			}
		}
		return temp;
	}
}
