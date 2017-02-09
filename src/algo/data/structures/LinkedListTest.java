package algo.data.structures;

import static org.junit.Assert.*;

import org.junit.Test;

public class LinkedListTest {

	@Test
	public void test() {
		SingleLinkedList<String> singleLink = new SingleLinkedList<String>();
		assertTrue(singleLink.isEmpty());
		
		/******Add Test******/
		singleLink.add("Dog");
		assertFalse(singleLink.isEmpty());
		assertEquals(singleLink.get(0), "Dog");
		assertEquals(singleLink.count(), 1);
		singleLink.add("Cat");
		assertFalse(singleLink.isEmpty());
		assertEquals(singleLink.get(0), "Dog");
		assertEquals(singleLink.get(1), "Cat");
		assertEquals(singleLink.count(), 2);
		/******Insert Test*****/
		
	}

}
