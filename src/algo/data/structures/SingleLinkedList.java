
package algo.data.structures;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SingleLinkedList<T> implements Collection<T> {
	protected Node<T> head;
	protected Node<T> tail;
	protected String listType;
	protected int count = 0;

	@Override
	public boolean add(T value) {
		if (count == 0) {
			Node<T> newNode = new Node<T>(null, value);
			this.setHead(newNode);
			this.setTail(newNode);
			listType = head.getValue().getClass().getName();
		} else {
			tail.setNext(new Node<T>(null, value));
			tail = tail.getNext();
		}
		++count;
		return true;
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
				if (temp.getValue().equals(value)) {
					index = i;
					found = true;
				}
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

	@Override
	public boolean addAll(Collection<? extends T> collection) {
		boolean hasChanged = !collection.isEmpty();
		Iterator<? extends T> colIterator = collection.iterator();
		while (colIterator.hasNext()) {
			this.add(colIterator.next());
		}
		return hasChanged;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean contains(Object o) {
		boolean contains = false;
		if (head != null && o.getClass().getName().equals(listType)) {
			contains = this.search((T) o) == -1 ? false : true;
		}
		return contains;
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		boolean containsAll = false;
		if (head != null && c.iterator().next().getClass().getName().equals(listType)) {
			containsAll = true;
			for (Object object : c) {
				if (!this.contains(object)) {
					containsAll = false;
				}
			}
		}
		return containsAll;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Iterator<T> iterator() {
		return (Iterator<T>) Arrays.asList(this.toArray()).iterator();
		
	}

	public boolean remove(Object o) {
		if (head != null && o.getClass().getName().equals(listType)) {
			@SuppressWarnings("unchecked")
			int indexAt = search((T) o);
			if (indexAt != -1) {
				this.removeAt(indexAt);
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		boolean allRemoved = false;
		if (head != null &&  c.size() > 0 && c.iterator().next().getClass().getName().equals(listType)) {
			for (Object object : c) {
				if (remove(object))
					allRemoved = true;
			}
		}
		return allRemoved;
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		ArrayList<T> itemsToRemove = new ArrayList<T>();
		Iterator<T> currentLinkedList = this.iterator();
		while(currentLinkedList.hasNext()){
			T currentValue = currentLinkedList.next();
			if(!c.contains(currentValue)){
				itemsToRemove.add(currentValue);
			}
		}
		this.removeAll(itemsToRemove);
		return itemsToRemove.size() > 0;
	}

	@Override
	public int size() {
		return this.count();
	}

	@Override
	public Object[] toArray() {
		return this.toString().equals("") ? new Object[0] : this.toString().split(",\\s");
	}

	@SuppressWarnings({ "unchecked", "hiding" })
	@Override
	public <T> T[] toArray(T[] a) {
		if(a.length >= this.count()){
		 System.arraycopy(this.toArray(), 0, a, 0, this.count());
		 return a;
		}else{
			T[] array = (T[])Array.newInstance(a.getClass().getComponentType(), this.count());
			System.arraycopy(this.toArray(), 0, array, 0, this.count());
			T[] result = (T[])Array.newInstance(a.getClass().getComponentType(), this.count());
			System.arraycopy(array, 0, result, 0, this.count());
				return result;
		}
	}

}