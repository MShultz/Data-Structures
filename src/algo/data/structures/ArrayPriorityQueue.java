package algo.data.structures;

import java.util.NoSuchElementException;

import sorting.library.Sorter;

public class ArrayPriorityQueue {
	private final int INCREMENT_SIZE = 1;
	private PQNode[] queue = new PQNode[0];
	private int count = 0;

	public boolean enqueue(int priority, int value) {
		resizeArray();
		queue[count] = new PQNode(priority, value);
		Sorter.insertionSort(queue);
		++count;
		return true;
	}

	public PQNode peek() {
		return count == 0 ? null : queue[0];
	}

	public PQNode dequeue() {
		if (count >= 1) {
			--count;
			return reduceArray();
		} else
			throw new NoSuchElementException("Queue is empty.");
	}

	public int count() {
		return count;
	}

	public String toString() {
		String values = "";
		for(int i = 0; i < queue.length; ++i){
			values += queue[i].toString();
			if(i != queue.length-1)
				values += ", ";
		}
		return values;
	}

	private void resizeArray() {
		PQNode[] holding = new PQNode[queue.length];
		System.arraycopy(queue, 0, holding, 0, queue.length);
		queue = new PQNode[queue.length + INCREMENT_SIZE];
		for (int i = 0; i < holding.length; ++i) {
			queue[i] = holding[i];
		}
	}

	private PQNode reduceArray() {
		PQNode removedNode = queue[0];
		PQNode[] holding = new PQNode[queue.length];
		System.arraycopy(queue, 0, holding, 0, queue.length);
		queue = new PQNode[queue.length - INCREMENT_SIZE];
		for (int i = 0; i < queue.length; ++i) {
			queue[i] = holding[i + 1];
		}
		return removedNode;
	}
	// a. Enqueue(int priority, int value) � Takes the arguments and creates a
	// new PQNode with this data, adds the node to the queue and adjusts the
	// count.
	// b. Peek() � returns highest priority node of the queue (without removing
	// it). Yes, return the whole node.
	// c. Dequeue() � Removes the root node of the queue and adjusts the count.
	// This method should return the removed PQNode.
	// d. Count � Returns the total number of nodes in the queue. In Java this
	// is a method, but in C# this must be a property with a public get and a
	// private/protected set.
	// e. ToString � This method override returns a string containing the
	// elements of the Array as they are found in memory (i.e. you iterate over
	// the array). The string will have the following format:
	// p1:v1, p2:v2, p3:v3,...,pn:vn
	// Each �p� is a priority, each �v� is the corresponding value. The priority
	// and value are separated by a colon. Each priority-value pair is separated
	// by a comma and a space. The final priority-value pair must not be
	// followed by anything (no commas or spaces, etc.).

}
