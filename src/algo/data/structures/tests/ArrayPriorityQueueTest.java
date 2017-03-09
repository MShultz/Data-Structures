package algo.data.structures.tests;

import static org.junit.Assert.*;
import org.junit.Test;

import algo.data.structures.ArrayPriorityQueue;

public class ArrayPriorityQueueTest {

	@Test
	public void test() {
		testEnqueue();
	}

	private void testEnqueue() {
		ArrayPriorityQueue PQ = new ArrayPriorityQueue();
		assertEquals(0, PQ.count());
		assertEquals(true, PQ.enqueue(5, 10));
		assertEquals(1, PQ.count());
	}

}
