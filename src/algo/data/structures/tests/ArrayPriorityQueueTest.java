package algo.data.structures.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.NoSuchElementException;

import org.junit.Test;

import algo.data.structures.ArrayPriorityQueue;
import algo.data.structures.PQNode;

public class ArrayPriorityQueueTest {

	@Test
	public void test() {
		testEnqueue();
		testPeek();
		testDequeue();
		testToString();
	}

	private void testEnqueue() {
		ArrayPriorityQueue PQ = new ArrayPriorityQueue();
		assertEquals(0, PQ.count());
		assertTrue(PQ.enqueue(5, 10));
		assertEquals(1, PQ.count());
	}
	
	private void testPeek(){
		ArrayPriorityQueue PQ = new ArrayPriorityQueue();
		assertEquals(0, PQ.count());
		assertEquals(null, PQ.peek());
		assertTrue(PQ.enqueue(5, 10));
		assertEquals(1, PQ.count());
		assertEquals(5,PQ.peek().getPriority());
		assertEquals(10,PQ.peek().getValue());
	}
	
	private void testDequeue(){
		ArrayPriorityQueue PQ = new ArrayPriorityQueue();
		try{
			PQ.dequeue();
		}catch(NoSuchElementException e){
			assertEquals(0, PQ.count());
		}
		assertTrue(PQ.enqueue(5, 10));
		assertEquals(1, PQ.count());
		PQNode removed = PQ.dequeue();
		assertEquals(5, removed.getPriority());
		assertEquals(10, removed.getValue());
		assertEquals(0, PQ.count());	
	}
	
	private void testToString(){
		ArrayPriorityQueue PQ = new ArrayPriorityQueue();
		assertEquals(0, PQ.count());
		assertEquals("", PQ.toString());
		assertTrue(PQ.enqueue(5, 10));
		assertEquals("5:10", PQ.toString());
		assertTrue(PQ.enqueue(5, 5));
		assertEquals(2, PQ.count());
		assertEquals("5:10, 5:5", PQ.toString());
		assertTrue(PQ.enqueue(1, 3));
		assertEquals(3, PQ.count());
		assertEquals("1:3, 5:10, 5:5", PQ.toString());
		assertTrue(PQ.enqueue(6, 10));
		assertEquals(4, PQ.count());
		assertEquals("1:3, 5:10, 5:5, 6:10", PQ.toString());
		PQ.dequeue();
		assertEquals(3, PQ.count());
		assertEquals("5:10, 5:5, 6:10", PQ.toString());
	}

}
